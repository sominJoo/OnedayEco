package community.TeamMemberBoard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.MemberboardUtils;
import community.MemberBoard.model.service.MemberboardService;
import community.MemberBoard.model.vo.Memberboard;
import community.RequestTeam.model.service.RequestTeamService;
import community.RequestTeam.model.vo.RequestTeam;
import community.TeamMemberBoard.model.service.TeamMemberboardService;
import community.TeamMemberBoard.model.vo.TeamMemberboard;
import member.model.vo.Member;

@WebServlet("/community/teamMemberboard")
public class TeamMemberboardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeamMemberboardService teamMemberboardService = new TeamMemberboardService();
	private MemberboardService memberboardService = new MemberboardService();
	private RequestTeamService requestTeamService = new RequestTeamService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값
		final int numPerPage = 6;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			// 좌항에 대입하지 못했다면 cPage에 1이 그대로 담기니까 예외처리코드 불필요
		}
		// 2. 업무로직
		// A. Contents영역 : start컨텐츠 ~ end컨텐츠
		int start = (cPage - 1) * numPerPage + 1;
		int end = cPage * numPerPage;
		
		HttpSession session = request.getSession();
		Member loginMember = null;
		if(session != null) {
			loginMember = (Member) session.getAttribute("loginMember");
		
		if(loginMember != null) {
		String memberId = loginMember.getMemberId();
		int aId = requestTeamService.selectAId(memberId);
		int applicantsCnt = requestTeamService.selectApplicantsCnt(aId);
		System.out.println("신청자수 : " + applicantsCnt);
		int sTeamCount = memberboardService.selectSTeamCnt(aId);
		System.out.println("정원 : " + sTeamCount);
		String location = null;
		String msg = null;

		// if(sTeamCount - 1 == applicantsCnt){
		if(sTeamCount == applicantsCnt){
			List<TeamMemberboard> list = teamMemberboardService.selectTeamMemberboardList(start, end, aId);
			System.out.println("list@servlet = " + list);
			int totalContents = teamMemberboardService.selectTeamMemberboardCount();		
			// B. pageBar영역
			String url = request.getRequestURI();
			String pageBar = MemberboardUtils.getPageBar(cPage, numPerPage, totalContents, url);
			
			// 3. 응답 html처리 - jsp에 위임
			request.setAttribute("pageBar", pageBar);
			request.setAttribute("list", list);
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/views/community/teamMemberboard.jsp")
				.forward(request, response);
		} else if(sTeamCount - 1 > applicantsCnt) {
			request.getSession().setAttribute("msg", "챌린지 모집인원이 완료되어야 입장하실 수 있습니다.");
			response.sendRedirect(request.getContextPath() + "/community/community");
		} else {
			request.getSession().setAttribute("msg", "챌린지를 신청해주세요.");
			response.sendRedirect(request.getContextPath() + "/community/community");
			}
	}
}
	}}