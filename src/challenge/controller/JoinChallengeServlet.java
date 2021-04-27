package challenge.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import challenge.model.servcie.ChallengeServcie;

/**
 * Servlet implementation class JoinChallengeServlet
 */
@WebServlet("/challenge/JoinChallenge")
public class JoinChallengeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChallengeServcie challengeService = new ChallengeServcie();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//개인 챌린지 도전
		//member_id, challenge_id, confirm_date, end_date
		try {		
			String member_id = request.getParameter("member_id");
			//System.out.println("JoinChallengeServlet-member_id : " + member_id);
			int c_id = Integer.parseInt(request.getParameter("c_id"));
			//System.out.println("JoinChallengeServlet-c_id : " + c_id);

			int result = challengeService.joinChallenge(member_id, c_id);
			String msg = result > 0 ? "챌린지 참여 성공했습니다." : "이미 참여중인 챌린지입니다.";

			request.getSession().setAttribute("msg", msg);

			System.out.println("JoinChallengeServlet : " + ShortChallengeServlet.nowChallenge);

			if (ShortChallengeServlet.nowChallenge == "short") {
				response.sendRedirect(request.getContextPath() + "/challenge/ShortChallenge");
			} else {
				response.sendRedirect(request.getContextPath() + "/challenge/LongChallenge");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
