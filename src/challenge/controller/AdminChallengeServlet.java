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
//		System.out.println("c_id : " + c_id);
		
		String crud = request.getParameter("crud");
//		System.out.println("crud : " + crud);
		
//		System.out.println("AdminChallengeServlet : " + ShortChallengeServlet.nowChallenge);
		
		if(crud.equals("추가")) {
			//1. 사용자 입력 값 처리
			System.out.println("추가 들어옴"); 
			
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
			
			//2. 업무로직 : db에 insert	
			int result = challengeService.insertChallenge(challenge);
			
			String msg = (result > 0) ? "챌린지 등록 성공" : "챌린지 등록 실패";
			//insert할때는 0보다 아래인 상황보다 그냥 오류가 나는 상황이 많음
			//예외처리 하기
			
			//3. DML요청이므로 리다이렉트와 사용자 피드백 필요
			HttpSession session = request.getSession();
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/challenge/UpdateChallenge");
			
		} else if(crud.equals("삭제")) {
			System.out.println("삭제 들어옴");
			int result = challengeService.deleteChallenge(c_id);
			String msg = result > 0 ? "챌린지 삭제 성공" : "챌린지 삭제 실패";

			System.out.println(msg);
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/challenge/UpdateChallenge");

		} else { //"수정" -> X
//			System.out.println("수정 들어옴");
//			System.out.println("c_id : " + c_id);
//			
//			//1. 
//
//			//2.하나 수정해야 할 것 받아서
//			Challenge challenge = challengeService.selectOne(c_id);
//			
//			System.out.println("test");
//			//3. jsp포워딩
//			request.setAttribute("challenge", challenge);
//			request.getRequestDispatcher("/WEB-INF/views/challenge/ModifyChallenge.jsp")
//					.forward(request, response);
//	
			
		}
	}
}
