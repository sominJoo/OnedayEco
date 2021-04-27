package community.TeamMemberBoard.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.TeamMemberBoard.model.service.TeamMemberboardService;
import community.TeamMemberBoard.model.vo.TeamComment;

/**
 * Servlet implementation class TeamMemberboardCommentInsertServlet
 */
@WebServlet("/community/teamMemberboardCommentInsert")
public class TeamMemberboardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeamMemberboardService teamMemberboardService = new TeamMemberboardService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  try {
	  
				int teamAId = Integer.parseInt(request.getParameter("boardNo"));
				int commentLevel = Integer.parseInt(request.getParameter("commentLevel"));
				int commentRef = Integer.parseInt(request.getParameter("commentRef"));
				String writer = request.getParameter("writer");
				String content = request.getParameter("comment");
				TeamComment tc = new TeamComment(0, teamAId, writer, content, null, commentLevel, commentRef);
				System.out.println("boardComment@servlet = " + tc);
				// [no=0, commnetLevel=1, writer=admin, content=c, boardNo=113, commentRef=0, regDate=null]
				// 2. 업무로직
				int result = teamMemberboardService.insertTeamMemberBoardComment(tc);
				// 3. 사용자 피드백 & 리다이렉트
				// cf. catch절로 떨어지지 않으면 무조건 댓글등록 성공, result가 0인지 1인지 여부 불필요
				request.getSession().setAttribute("msg", "댓글을 성공적으로 등록했습니다.");
				// 현재페이지로 이동, 그러려면 게시글 번호 필요
				response.sendRedirect(request.getContextPath() + "/community/detailedTeamMemberBoardView?no=" + teamAId);
			  } catch(Exception e) {
				 e.printStackTrace();
				 throw e;
	}

}
}