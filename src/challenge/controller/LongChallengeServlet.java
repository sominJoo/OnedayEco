package challenge.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import challenge.model.exception.ChallengeException;
import challenge.model.servcie.ChallengeServcie;
import challenge.model.vo.Challenge;
import common.MvcUtils;

@WebServlet("/challenge/LongChallenge")
public class LongChallengeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChallengeServcie challengeService = new ChallengeServcie();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ShortChallengeServlet.nowChallenge = "long";

		final int numPerPage = 5;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
			
		}
		
		int start = (cPage - 1) * numPerPage + 1;
		int end = cPage * numPerPage;
		List<Challenge> list = challengeService.selectList("long",start, end);
		if(list == null) {
			throw new ChallengeException("기간 챌린지 목록이 존재하지 않습니다.");
		}
		
		//\n개행문자를 <br/>태그로 변경
		//MvcUtils
		//MvcUtils.convertLineFeedToBr(list.)
		
		//pageBar
		int totalContents = challengeService.selectChallengeCount("long");
		String url = request.getRequestURI();
		String pageBar = MvcUtils.getPageBar(cPage, numPerPage, totalContents, url);

		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/WEB-INF/views/challenge/LongChallenge.jsp")
			.forward(request, response);
		
	}

}
