package onedayeco.article.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CommunityEnviromentServlet
 */
@WebServlet("/community/enviroment3")
public class CommunityEnviromentServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		request.getRequestDispatcher("/WEB-INF/views/community/enviroment(list3).jsp")
		   .forward(request, response);
		
	}catch(Exception e) {
		//로깅
		e.printStackTrace();
		//다시 예외를 던져서 WAS가 정한 에러페이지에서 응답메세지를 작성
		throw e;
	}
	}

}
