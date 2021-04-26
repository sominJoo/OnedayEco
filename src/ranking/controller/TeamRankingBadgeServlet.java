package ranking.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ranking.model.service.PointService;

/**
 * Servlet implementation class TeamRankingBadgeServlet
 */
@WebServlet("/admin/badgeTeamRankInsert?rankt")
public class TeamRankingBadgeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int rank = Integer.parseInt(request.getParameter("rank"));
			String user = (request.getParameter("user"));
				
			
			int result = new PointService().insertRankingBadge_team(user,rank);	
			
			
			response.sendRedirect(request.getContextPath()+"/community/confirmList");
			
		} catch (Exception e) {
			throw e;
		}
		
	}

}
