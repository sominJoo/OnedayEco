package challenge.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import challenge.model.servcie.ChallengeServcie;
import challenge.model.vo.Challenge;

/**
 * Servlet implementation class ShortChallengeInfo
 */
@WebServlet("/challenge/shortChallengeInfo")
public class ShortChallengeInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChallengeServcie challengeService = new ChallengeServcie();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int c_id = Integer.parseInt(request.getParameter("c_id"));
		//System.out.println("JoinChallengeServlet-c_id : " + c_id);
		
		Challenge challenge = challengeService.selectOne(c_id);
		
		//jsp 포워딩
		request.setAttribute("challenge", challenge);
		request.getRequestDispatcher("/WEB-INF/views/challenge/ShortChallengeInfo.jsp").forward(request, response);

	}

}
