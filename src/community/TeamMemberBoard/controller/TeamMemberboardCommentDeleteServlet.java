package community.TeamMemberBoard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.TeamMemberBoard.model.service.TeamMemberboardService;

/**
 * Servlet implementation class TeamMemberboardCommentDeleteServlet
 */
@WebServlet("/community/teamMemberboardCommentDelete")
public class TeamMemberboardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeamMemberboardService teamMemberboardService = new TeamMemberboardService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 파라미터값 가져오기
			int no = Integer.parseInt(request.getParameter("no"));
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
	
			//2. 비지니스로직 호출
			int result = teamMemberboardService.deleteTeamMemberboardComment(no);
			
			//3. 사용자피드백 & 리다이렉트
			request.getSession().setAttribute("msg", "댓글을 삭제했습니다.");
			response.sendRedirect(request.getContextPath() + "/community/detailedTeamMemberBoardView?no=" + boardNo);
		
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
