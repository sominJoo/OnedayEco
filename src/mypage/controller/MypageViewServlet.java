package mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MypageViewServlet
 */
@WebServlet("/mypage")
public class MypageViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 단순 연결
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int month = Integer.valueOf(request.getParameter("month"));
		
		request.setAttribute("month", month);
		request.getRequestDispatcher("/WEB-INF/views/mypage/mypage.jsp").forward(request, response);;
	}


}
