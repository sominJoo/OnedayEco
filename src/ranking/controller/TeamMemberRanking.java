package ranking.controller;

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
import community.RequestTeam.model.service.RequestTeamService;
import member.model.vo.Member;
import ranking.model.service.PointService;
import ranking.model.vo.PersonalPoint;
import ranking.model.vo.TeamMemberPoint;

/**
 * Servlet implementation class TeamMemberRanking
 */
@WebServlet(urlPatterns = {"/ranking/TeamMemberRanking", "/ranking/TeamMemberRanking2"})
public class TeamMemberRanking extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PointService pointService = new PointService();
	private RequestTeamService requestTeamService = new RequestTeamService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("member_id");
		
		final int numPerPage = 5;
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

		int totalContents = pointService.selectTeamMemberPCount();		
		// B. pageBar영역
		String url = request.getRequestURI();
		String pageBar = MemberboardUtils.getPageBar(cPage, numPerPage, totalContents, url);
		
		HttpSession session = request.getSession();
		if( memberId != null) {
		int aId = requestTeamService.selectAId(memberId);
		String one = pointService.selectTMOne(aId);
		String two = pointService.selectTMTwo(aId);
		String three = pointService.selectTMThree(aId);
		List<TeamMemberPoint> list = pointService.selectTeamMemberPList(start, end, aId);
		// 3. 응답 html처리 - jsp에 위임
		request.setAttribute("one", one);
		request.setAttribute("two", two);
		request.setAttribute("three", three);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("list", list);
		
		RequestDispatcher reqDispatcher = 
				request.getRequestDispatcher("/WEB-INF/views/ranking/TeamMemberRanking.jsp");
		reqDispatcher.forward(request, response);
		}
		
		else {
			// 로그아웃된 상태에서 url로 memberView에 접근할 수 없도록
			session.setAttribute("msg", "로그인 후 사용할 수 있습니다.");
			response.sendRedirect(request.getContextPath());
			return;
		}
	}

}
