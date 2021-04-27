package community.RequestTeam.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import community.MemberBoard.model.service.MemberboardService;
import community.RequestTeam.model.service.RequestTeamService;
import community.RequestTeam.model.vo.RequestTeam;

/**
 * Servlet implementation class TeamApplicateServlet
 */
@WebServlet("/community/requestTeam")
public class TeamApplicateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestTeamService requestTeamService = new RequestTeamService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자 입력값
			int teamCnt = Integer.parseInt(request.getParameter("teamCnt"));
			int no = Integer.parseInt(request.getParameter("no"));
			String memberId = request.getParameter("id");
			
			int applicationTimes = requestTeamService.selectApplicationTimes(memberId);
			// 2. 업무로직
			int applicantsCnt = requestTeamService.selectApplicantsCnt(no) + 1;
			String msg;
			if(applicationTimes >= 1) {
				msg = "이미 신청한 챌린지가 있습니다. 해당 챌린지 종료 후 다시 도전하세요.";
			}
			else if(teamCnt <= applicantsCnt) {
				msg = "이미 모집인원이 가득차서 팀챌린지에 신청할 수 없습니다.";
			} else {
				requestTeamService.insertRequestTeam(no, memberId);
				msg = "팀신청을 완료하였습니다.";
			}
			// 3. 리다이렉트 & 사용자 피드백 (dml - 리다이렉트 필수)
			HttpSession session = request.getSession();
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/community/detailedMemberBoardView?no=" + no);		
		} catch(Exception e) {
			// 예외로깅 작업 - controller에서 한번만 하면 됨
			e.printStackTrace();
			// 예외페이지로 넘어가기 위해 was에 예외 다시 던지기
			throw e;
			// 예외처리를 안해주면 tomcat한테 예외가 던져져서 톰캣 예외페이지가 나오는 것
		}
	}
}
