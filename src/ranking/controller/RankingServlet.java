package ranking.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MemberboardUtils;
import ranking.model.service.PointService;
import ranking.model.vo.PersonalPoint;

/**
 * Servlet implementation class Ranking
 */
@WebServlet("/ranking")
public class RankingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PointService pointService = new PointService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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

		int totalContents = pointService.selectPersonalPCount();		
		// B. pageBar영역
		String url = request.getRequestURI();
		String pageBar = MemberboardUtils.getPageBar(cPage, numPerPage, totalContents, url);
		List<PersonalPoint> list = pointService.selectPersonalList(start, end);
		String one = pointService.selectOne();
		String two = pointService.selectTwo();
		String three = pointService.selectThree();
		// 3. 응답 html처리 - jsp에 위임
		request.setAttribute("one", one);
		request.setAttribute("two", two);
		request.setAttribute("three", three);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("list", list);
		RequestDispatcher reqDispatcher = 
				request.getRequestDispatcher("/WEB-INF/views/ranking/Ranking.jsp");
		reqDispatcher.forward(request, response);
	}
}
