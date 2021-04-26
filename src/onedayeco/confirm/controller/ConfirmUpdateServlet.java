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
 * Servlet implementation class ConfirmUpdateServlet
 */
@WebServlet("/community/confirmUpdate")
public class ConfirmUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConfirmService confirmService = new ConfirmService();
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//1. 사용자 입력값
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println(no);
		
	//2. 업무로직
		Confirm confirm = confirmService.selectOne(no);
		
	//3.jsp	포워딩
		request.setAttribute("confirm", confirm);
		request.getRequestDispatcher("/WEB-INF/views/community/confirmUpdate.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			//1.MultipartRequest객체 생성
			// /WebContent/upload/board/업로드파일명.jpg
			// web root dir를 절대경로로 반환
			String saveDirectory = getServletContext().getRealPath("/upload/board");
			System.out.println("saveDirectoru@servlet = "+saveDirectory);
			
			//최대파일 허용크기 10mb = 10 * 1kb * 1kb
			int maxPostSize = 10 * 1024 * 1024;
			
			//인코딩
			String encoding = "utf-8";
			
			//파일명 변경정책 객체
			//중복파일인 경우, numbering처리
			//filerename : 20210406191919_123.jpg  -->보안처리를 위해서
//		FileRenamePolicy policy = new DefaultFileRenamePolicy();
			FileRenamePolicy policy = new MvcFileRenamePolicy();
			
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
//		String title = request.getParameter("title"); =>null
			int no = Integer.parseInt(multipartRequest.getParameter("no"));
			String type = multipartRequest.getParameter("type");
			System.out.println("Update"+type);
			String title = multipartRequest.getParameter("title");
			String writer = multipartRequest.getParameter("writer");
			String content = multipartRequest.getParameter("content");
			
			//업로드한 파일명
			String originalFileName = multipartRequest.getOriginalFileName("upFile"); //사용자가 업로드한 파일명
			String renamedFileName = multipartRequest.getFilesystemName("upFile"); //저장된 파일명 바꿔있을 수 있다.
			
			//삭제할 첨부파일번호
			String attachNo = multipartRequest.getParameter("delFile");
			System.out.println("attachNo@servlet ="+attachNo);
			
//		Board board = new Board(0,title,writer,content,null,0,null);
			Confirm confirm= new Confirm();
			confirm.setConfirm_type(type);
			confirm.setA_id(no);
			confirm.setA_title(title);
			confirm.setUser_id(writer);
			confirm.setA_content(content);
			
			//업로드한 파일이 있을 수도 있고 없을 수도 있다
			//첨부파일이 있는 경우
			//multipartRequest.getFile("upFile"):File != null
			if(originalFileName != null) {
				ConfirmAttachment attach = new ConfirmAttachment();
				attach.setA_id(no);
				attach.setOriginal_filename(originalFileName);
				attach.setRenamed_filename(renamedFileName);
				confirm.setAttach(attach);
			}
			
			// 2-2. 업무로직 :
			// 첨부파일
			int result = 0;
			if(attachNo != null)
				confirmService.deleteAttachment(attachNo);
			
			// db에 insert
			result = confirmService.updateBoard(confirm);
			String msg = (result >0)?
					"게시글 수정 성공!": "게시글 수정 실패!";
			String location = request.getContextPath()
					+"/community/confirmView?no=" + confirm.getA_id() ;
		
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
