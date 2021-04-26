package onedayeco.article.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CommunityServlet
 */
@WebServlet("/community/community")
public class CommunityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		request.getRequestDispatcher("/WEB-INF/views/community/community.jsp")
		   .forward(request, response);
		
		
	}catch(Exception e) {
		//로깅
		e.printStackTrace();
		//다시 예외를 던져서 WAS가 정한 에러페이지에서 응답메세지를 작성
		throw e;
	}
	}



}
