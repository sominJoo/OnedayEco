package onedayeco.confirm.controller;

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

import onedayeco.article.model.service.ArticleService;
import onedayeco.article.model.vo.ArticleAttachment;
import onedayeco.confirm.model.service.ConfirmService;
import onedayeco.confirm.model.vo.ConfirmAttachment;

/**
 * Servlet implementation class FileDownladServlet
 */
@WebServlet("/community/CfileDownload")
public class FileDownladServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ConfirmService confirmService = new ConfirmService();   
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("FileDownladServlet 실행");		
		int no = Integer.parseInt(request.getParameter("no"));
		
		
		//2.업무로직 : 첨부파일 조회
		//originalFileName , renamedFileName
		ConfirmAttachment attach = confirmService.selectOneAttachment(no);
		System.out.println("attach@servlet ="+attach);
		
		//3.파일다운로드
		//a.입출력스트림 생성
		String saveDirectory = getServletContext().getRealPath("/upload/board");
		File f = new File(saveDirectory, attach.getRenamed_filename());
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
		//대상이 응답메세지인 출력스트림 가져오기
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		
		//b.응답헤더작성
		String responseFileName = new String(attach.getOriginal_filename().getBytes("utf-8"),"ISO-8859-1");
		System.out.println(responseFileName);
		response.setContentType("application/octet-stream; charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+responseFileName);
		
		//c.파일출력
		int read = -1;
		while((read = bis.read()) != -1) {
			bos.write(read);
		}
		
		//d.자원반납
		bos.close();
		bis.close();
		}



}
