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
import common.BoardFileRenamePolicy;

/**
 * Servlet implementation class AdminChallengeServlet
 */
@WebServlet("/challenge/AdminChallenge")
public class AdminChallengeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChallengeServcie challengeService = new ChallengeServcie();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int c_id = 0;
		
		if(request.getParameter("c_id") != null) {
			c_id = Integer.parseInt(request.getParameter("c_id"));
		}		
		
		String crud = request.getParameter("crud");

		if(crud.equals("추가")) {
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int level = Integer.parseInt(request.getParameter("level"));
			Challenge challenge;
			int point = 100; // S : 100,200,300 L : 500,600,700
			
			if(ShortChallengeServlet.nowChallenge.equals("short")) {
				
				point *= level;
				challenge = new Challenge(0, "S", level, title, content, point, 1);
			}
			else { //"long"
				point = point * level + 400;
				System.out.println(point);
				challenge = new Challenge(0, "L", level, title, content, point, 30);
			}
			
			int result = challengeService.insertChallenge(challenge);
			
			String msg = (result > 0) ? "챌린지 등록 성공" : "챌린지 등록 실패";
			
			HttpSession session = request.getSession();
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/challenge/UpdateChallenge");
			
		} else if(crud.equals("삭제")) {
			int result = challengeService.deleteChallenge(c_id);
			String msg = result > 0 ? "챌린지 삭제 성공" : "챌린지 삭제 실패";

			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/challenge/UpdateChallenge");

		} else { //"수정" -> X
			
		}
	}
}
