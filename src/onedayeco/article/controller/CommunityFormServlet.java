package onedayeco.article.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CommunityForm
 */
@WebServlet("/community/communityForm")
public class CommunityFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println(1);
		request.getRequestDispatcher("/WEB-INF/views/community/communityEnroll.jsp")
			   .forward(request, response);
	}
}
