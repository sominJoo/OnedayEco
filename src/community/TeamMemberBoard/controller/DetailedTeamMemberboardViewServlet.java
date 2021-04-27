package community.TeamMemberBoard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MemberboardUtils;
import community.MemberBoard.model.exception.MemberboardException;
import community.MemberBoard.model.service.MemberboardService;
import community.MemberBoard.model.vo.Memberboard;
import community.TeamMemberBoard.model.service.TeamMemberboardService;
import community.TeamMemberBoard.model.vo.TeamComment;
import community.TeamMemberBoard.model.vo.TeamMemberboard;

/**
 * Servlet implementation class DetailedTeamMemberboardView
 */
@WebServlet("/community/detailedTeamMemberBoardView")
public class DetailedTeamMemberboardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeamMemberboardService teamMemberboardService = new TeamMemberboardService();	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 : no
		try {
		int no  = 0; 
		try {
			no = Integer.parseInt(request.getParameter("no"));
		} catch(NumberFormatException e) {
			throw new MemberboardException("유효한 게시글 번호가 아닙니다.", e);
		}
		//2. 업무로직 : board객체 조회(첨부파일 조회)
		teamMemberboardService.updateTeamAReadCount(no);
		TeamMemberboard teamMemberboard = teamMemberboardService.selectOneTeamMemberboard(no);
		if(teamMemberboard == null) {
			throw new MemberboardException("해당 게시글이 존재하지 않습니다.");
		}
		// xss공격 방지
		// 사용자가 입력할 수 있는 부분에는 다 처리해줘야 함
		teamMemberboard.setTeamAId(teamMemberboard.getTeamAId());
		teamMemberboard.setMemberId(MemberboardUtils.escapeHtml(teamMemberboard.getMemberId()));
		teamMemberboard.setaId(teamMemberboard.getaId());
		teamMemberboard.setaTitle(MemberboardUtils.escapeHtml(teamMemberboard.getaTitle()));
		teamMemberboard.setaContent(MemberboardUtils.convertLineFeedtoBr(teamMemberboard.getaContent()));
		teamMemberboard.setaRegDate(teamMemberboard.getaRegDate());
		teamMemberboard.setaReadCount(teamMemberboard.getaReadCount());
		teamMemberboard.setaLike(teamMemberboard.getaLike());
		
		List<TeamComment> commentList = teamMemberboardService.selectTeamCommentList(no);
		
		//3. jsp forwarding
		request.setAttribute("teamMemberboard", teamMemberboard);
		request.setAttribute("commentList", commentList);
		request.getRequestDispatcher("/WEB-INF/views/community/detailedTeamMemberboardView.jsp")
			   .forward(request, response);
		} catch(Exception e) {
			// 로깅
			e.printStackTrace();
			// 관리자 이메일 알림
			// 다시 예외를 던져서 WAS가 정한 에러페이지에서 응답메시지 작성
			throw e;
		}
	}

}
