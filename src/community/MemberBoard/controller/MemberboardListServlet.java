package community.MemberBoard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MemberboardUtils;
import community.MemberBoard.model.service.MemberboardService;
import community.MemberBoard.model.vo.Memberboard;
import community.RequestTeam.model.service.RequestTeamService;
import community.RequestTeam.model.vo.RequestTeam;

@WebServlet("/community/memberboardList")
public class MemberboardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
		List<Memberboard> list = memberboardService.selectMemberboardList(start, end);
		System.out.println("list@servlet = " + list);
		int totalContents = memberboardService.selectMemberboardCount();		
		// B. pageBar영역
		String url = request.getRequestURI();
		String pageBar = MemberboardUtils.getPageBar(cPage, numPerPage, totalContents, url);
		
		// 3. 응답 html처리 - jsp에 위임
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/community/Memberboard.jsp")
				.forward(request, response);
		System.out.println("list = " + list);
	}
}