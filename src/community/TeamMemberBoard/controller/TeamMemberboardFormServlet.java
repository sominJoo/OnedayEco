package community.TeamMemberBoard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class TeamMemberboardForm
 */
@WebServlet("/community/teamMemberboardForm")
public class TeamMemberboardFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher reqDispatcher = 
				request.getRequestDispatcher("/WEB-INF/views/community/teamMemberboardForm.jsp");
		reqDispatcher.forward(request, response);
	}

}
