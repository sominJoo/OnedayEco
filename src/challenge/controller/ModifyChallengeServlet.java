package challenge.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import challenge.model.servcie.ChallengeServcie;
import challenge.model.vo.Challenge;

/**
 * Servlet implementation class ModifyChallengeServlet
 */
@WebServlet("/challenge/ModifyChallenge")
public class ModifyChallengeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChallengeServcie challengeService = new ChallengeServcie();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int c_id = Integer.parseInt(request.getParameter("c_id"));
		
		Challenge challenge = challengeService.selectOne(c_id);
		
		//jsp 포워딩
		request.setAttribute("challenge", challenge);
		request.getRequestDispatcher("/WEB-INF/views/challenge/ModifyChallenge.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int c_id = Integer.parseInt(request.getParameter("c_id"));
			int result = 0;
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int level = Integer.parseInt(request.getParameter("level"));
			
			Challenge challenge;
			int point = 100; // S : 100,200,300 L : 500,600,700
			
			if(ShortChallengeServlet.nowChallenge.equals("short")) {
				
				point *= level;
				challenge = new Challenge(c_id, "S", level, title, content, point, 1);
			}
			else { //"long"
				point = point * level + 400;
				System.out.println(point);
				challenge = new Challenge(c_id, "L", level, title, content, point, 30);
			}
			
			//db에 update
			result = challengeService.modifyChallenge(challenge);
			String msg = (result > 0) ? "챌린지 수정 성공" : "챌린지 수정 실패";
			String location = request.getContextPath() + "/challenge/UpdateChallenge";
			
			HttpSession session = request.getSession();
			session.setAttribute("msg", msg);
			response.sendRedirect(location);
	
		} catch (Exception e) {
			e.printStackTrace();
			throw e; //was한테 다시 던져서 에러페이지로 전환함.
			
		}
	}

}
