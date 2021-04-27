package challenge.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import challenge.model.servcie.ChallengeServcie;
import challenge.model.vo.Challenge;

/**
 * Servlet implementation class RandomChallenge
 */
@WebServlet("/challenge/RandomChallenge")
public class RandomChallengeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChallengeServcie challengeService = new ChallengeServcie();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//랜덤 뿌려주기
		List<Challenge> list = challengeService.selectShortAll();
		String[] rndList = new String[5];

		System.out.println("랜덤사이즈 : " + list.size());
		for(Challenge c : list){
			System.out.println(c.getChallenge_name());
		}
		
		int[] num = new int[rndList.length];
		int m;
		
		for(int j = 0; j < rndList.length ; j++) {
			num[j] = (int)(Math.random() * (list.size()));
			System.out.println("num[" + j + "] : " + num[j]);
			//중복검사
			for(m = 0; m < j; m++) {
				if(num[j] == num[m]) {
					j--;
					System.out.println("들어옴");
				}
			}
			rndList[j] = list.get(num[j]).getChallenge_name();
			System.out.println("rndList[" + j + "] : " + rndList[j]);
			
		}
		
		for(int j = 0; j < rndList.length ; j++) {
			System.out.println(rndList[j]);
		}
		
		int rnd = 1;
		String s = "d";
		System.out.println("test");
		request.setAttribute("rndList", rndList);
		request.setAttribute("s", s);
//		response.sendRedirect("ondayeco/index.jsp");
//		request.getRequestDispatcher("/WEB-INF/views/common/newindex.jsp")
//			.forward(request, response);
		request.getRequestDispatcher("/index.jsp")
		.forward(request, response);
		
	}

}
