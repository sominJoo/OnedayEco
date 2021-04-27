package community.MemberBoard.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.MemberBoard.model.service.MemberboardService;
import community.MemberBoard.model.vo.MemberboardAttachment;

/**
 * Servlet implementation class MemberboardFileDownloadServlet
 */
@WebServlet("/community/memberboardFileDownload")
public class MemberboardFileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberboardService memberboardService = new MemberboardService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 사용자 입력값 : no 게시글번호
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 2. 업무로직 : 게시글번호로 첨부파일 조회
		MemberboardAttachment memberboardattach = memberboardService.selectOneMemberboardAttachment(no); 
		System.out.println("attach@servlet = " + memberboardattach);
	
		String saveDirectory = getServletContext().getRealPath("/upload/memberboard"); // 파일이 저장된 경로
		File f = new File(saveDirectory, memberboardattach.getRenamedFilename());// 다운받을 파일 객체 File(파일의 경로, 파일 이름)
		BufferedInputStream bis = 
				new BufferedInputStream(new FileInputStream(f));
		// [출력 스트림]
		BufferedOutputStream bos = 
				new BufferedOutputStream(response.getOutputStream());
		// 응답헤더작성 (이거 파일이야! 다운로드 받을 준비해!)
		String responseFileName = new String(memberboardattach.getOriginalFilename().getBytes("utf-8"), "ISO-8859-1");
		response.setContentType("application/octet-stream; charset=utf-8");
		response.setHeader("Content-Disposition",
							"attachment;filename=" + responseFileName
							);
		// 파일출력 (쓰기작업)
		int read = -1;
		while((read = bis.read()) != -1) {
			bos.write(read);
		}
		// 자원반납
		bos.close();
		bis.close();
	}
}
