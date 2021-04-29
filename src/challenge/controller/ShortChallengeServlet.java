package challenge.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import challenge.model.servcie.ChallengeServcie;
import challenge.model.vo.Challenge;
import common.MvcUtils;

@WebServlet("/challenge/ShortChallenge")
public class ShortChallengeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChallengeServcie challengeService = new ChallengeServcie();
	public static String nowChallenge="short";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		nowChallenge = "short";
		final int numPerPage = 5;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
			
		}

		int start = (cPage - 1) * numPerPage + 1;
		int end = cPage * numPerPage;
		List<Challenge> list = challengeService.selectList("short",start, end);
		//pageBar
		int totalContents = challengeService.selectChallengeCount("short"); //*
		String url = request.getRequestURI();
		String pageBar = MvcUtils.getPageBar(cPage, numPerPage, totalContents, url);
				
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/WEB-INF/views/challenge/ShortChallenge.jsp")
			.forward(request, response);
		
		
		
		
	}

}
