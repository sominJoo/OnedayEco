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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//랜덤 뿌려주기
		List<Challenge> list = challengeService.selectShortAll();
		String[] rndList = new String[5];

		
		int[] num = new int[rndList.length];
		int m;
		
		for(int j = 0; j < rndList.length ; j++) {
			num[j] = (int)(Math.random() * (list.size()));
			
			//중복검사
			for(m = 0; m < j; m++) {
				if(num[j] == num[m]) {
					j--;
				}
			}
			
			rndList[j] = list.get(num[j]).getChallenge_name();		
		}
		
//		for(int j = 0; j < rndList.length ; j++) {
//			System.out.println(rndList[j]);
//		}
		
		request.setAttribute("rndList", rndList);
		String s = "d";
		request.setAttribute("s", s);
		request.getRequestDispatcher("/index.jsp")
		.forward(request, response);
		
	}

}
