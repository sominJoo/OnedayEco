package onedayeco.confirm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onedayeco.confirm.model.service.ConfirmService;

/**
 * Servlet implementation class ConfirmDeleteServlet
 */
@WebServlet("/community/confirmDelete")
public class ConfirmDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ConfirmService confirmService = new ConfirmService();
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 사용자 입력값 처리
			//삭제할 board no
			int no = Integer.parseInt(request.getParameter("no"));
			
			
			//2. 업무로직 : db에 delete
			int result = confirmService.deleteBoard(no);
			String msg = (result > 0)? "게시글 삭제 성공!" : "게시글 삭제 실패";
			String location = request.getContextPath();
			location += result>0?  "/community/confirmList": "/community/confirmView?no=" + no;
			
			//3.DML 요청 : 리다이렉트 & 사용자피드백
			HttpSession session = request.getSession();
			session.setAttribute("msg", msg);
			response.sendRedirect(location);
		} catch(Exception e) {
			//예외 로깅
			e.printStackTrace();
			//예외페이지 전환을 위해서 was에 예외던짐;
			throw e; //예외페이지가 나오는 이유는 톰켓한테 예외가 던져젔기 때문이다.
		}
	}

}
