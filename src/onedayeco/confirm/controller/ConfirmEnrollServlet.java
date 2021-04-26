package onedayeco.confirm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import common.MvcFileRenamePolicy;
import onedayeco.article.model.vo.Article;
import onedayeco.article.model.vo.ArticleAttachment;
import onedayeco.confirm.model.service.ConfirmService;
import onedayeco.confirm.model.vo.Confirm;
import onedayeco.confirm.model.vo.ConfirmAttachment;

/**
 * Servlet implementation class ConfirmEnrollServlet
 */
@WebServlet("/community/confirmEnroll")
public class ConfirmEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConfirmService confirmService = new ConfirmService();
	/**
	 * 0. form에 속성 enctype="multipart/form-data" 추가
	 * 1. MultipartRequest객체 생성 : 서버컴퓨터 파일 저장
	 * 		-request
	 * 		-저장경로
	 * 		-encoding
	 * 		-최대허용크기
	 * 		-파일명 변경정책 객체(같은이름의 파일이더라도 덮어쓰지않도록 변경해주는 것)
	 * 2. db에 파일정보를 저장
	 * 		- 사용자가 저장한 파일명 original_filename
	 * 		- 실제 저장된 파일명 renamed_filename
	 * 
	 * MultipartRequest객체를 사용하면,
	 * 기존 HttpServletRequest에서는 사용자입력값에 접근할 수 없다.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.MultipartRequest객체 생성
			// /WebContent/upload/board/업로드파일명.jpg
			// web root dir를 절대경로로 반환
			String saveDirectory = getServletContext().getRealPath("/upload/board");
			System.out.println("saveDirectory@servlet = "+saveDirectory);
			
			//최대파일 허용크기 10mb = 10 * 1kb * 1kb
			int maxPostSize = 10 * 1024 * 1024;
			
			//인코딩
			String encoding = "utf-8";
			
			//파일명 변경정책 객체
			//중복파일인 경우, numbering처리
			//filerename : 20210406191919_123.jpg  -->보안처리를 위해서
//		FileRenamePolicy policy = new DefaultFileRenamePolicy();
			FileRenamePolicy policy =  new MvcFileRenamePolicy();
			
			MultipartRequest multipartRequest = 
					new MultipartRequest(
							request,   //기존 request전달
							saveDirectory, //저장경로
							maxPostSize,
							encoding,
							policy
							);
			
			//2. db에 게시글/첨부파일 정보 저장
			
			//2-1. 사용자 입력값처리
			String challengeName = multipartRequest.getParameter("challengeName");
			String type = multipartRequest.getParameter("type");
			String title = multipartRequest.getParameter("title");
			String writer = multipartRequest.getParameter("writer");
			String content = multipartRequest.getParameter("content");
			
			//업로드한 파일명
			String originalFileName = multipartRequest.getOriginalFileName("upFile"); //사용자가 업로드한 파일명
			String renamedFileName = multipartRequest.getFilesystemName("upFile"); //저장된 파일명 바꿔있을 수 있다.
			
//		Board board = new Board(0,title,writer,content,null,0,null);
			Confirm confirm= new Confirm();
			confirm.setA_title(title);
			confirm.setChallengeName(challengeName);
			confirm.setConfirm_type(type);
			confirm.setUser_id(writer);
			confirm.setA_content(content);
			//업로드한 파일이 있을 수도 있고 없을 수도 있다
			//첨부파일이 있는 경우
			//multipartRequest.getFile("upFile"):File != null
			if(originalFileName != null) {
				ConfirmAttachment attach = new ConfirmAttachment();
				attach.setOriginal_filename(originalFileName);
				attach.setRenamed_filename(renamedFileName);
				confirm.setAttach(attach);
			}
			
			// 2-2. 업무로직 : db에 insert
			int result = confirmService.insertBoard(confirm);
			String msg = (result >0)?
					"게시글 등록 성공!": "게시글 등록 실패!";
			String location = request.getContextPath();
			location += (result > 0)?
					"/community/confirmView?no=" + confirm.getA_id() :
						"/community/confirmList";
					
					// 3. DML요청 : 리다이렉트 & 사용자피드백
					//  /mvc/board/boardList
					HttpSession session = request.getSession();
					session.setAttribute("msg", msg);
					response.sendRedirect(location);
					
		}catch(Exception e) {
			//로깅
			e.printStackTrace();
			//다시 예외를 던져서 WAS가 정한 에러페이지에서 응답메세지를 작성
			throw e;
		}
	}

}