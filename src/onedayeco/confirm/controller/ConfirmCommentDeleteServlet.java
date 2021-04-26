package onedayeco.confirm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onedayeco.article.model.service.ArticleService;
import onedayeco.article.model.vo.ArticleComment;
import onedayeco.confirm.model.service.ConfirmService;
import onedayeco.confirm.model.vo.ConfirmComment;

/**
 * Servlet implementation class ConfirmCommentDeleteServlet
 */
@WebServlet("/community/confirmCommentDelete")
public class ConfirmCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConfirmService confirmService = new ConfirmService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 파라미터값 가져오기
			int no = Integer.parseInt(request.getParameter("comno"));
			int boardNo = Integer.parseInt(request.getParameter("BoardNo"));
			System.out.println("DeleteComment"+no);
   			
			//2. 비지니스로직 호출
			int result = confirmService.boardCommentDelete(no);
			
			//3. 사용자피드백 & 리다이렉트
			request.getSession().setAttribute("msg", "댓글 삭제 성공!");
			response.sendRedirect(request.getContextPath() + "/community/confirmView?no=" + boardNo);
		
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
