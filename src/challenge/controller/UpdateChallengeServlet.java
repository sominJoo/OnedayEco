package challenge.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import challenge.model.servcie.ChallengeServcie;
import challenge.model.vo.Challenge;
import common.MvcUtils;

@WebServlet("/challenge/UpdateChallenge")
public class UpdateChallengeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChallengeServcie challengeService = new ChallengeServcie();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final int numPerPage = 5;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
			
		}
		
		int start = (cPage - 1) * numPerPage + 1;
		int end = cPage * numPerPage;
		
		List<Challenge> list;
		
		//pageBar
		int totalContents;
		if(ShortChallengeServlet.nowChallenge.equals("short")) {
			totalContents = challengeService.selectChallengeCount("short");
			list = challengeService.selectList("short",start, end);
//			System.out.println("test");
		}
		else {
			list = challengeService.selectList("long",start, end);
			totalContents = challengeService.selectChallengeCount("long");
		}
		String url = request.getRequestURI();
		String pageBar = MvcUtils.getPageBar(cPage, numPerPage, totalContents, url);

		request.setAttribute("nowChallenge", ShortChallengeServlet.nowChallenge);
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/WEB-INF/views/challenge/AdminChallenge.jsp")
			.forward(request, response);
		
	}

}
