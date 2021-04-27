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
		
		//랜덤 뿌려주기
		
//		List<Challenge> rndList = challengeService.selectShortAll();
//		System.out.println("랜덤사이즈 : " + rndList.size());
//		for(Challenge c : rndList){
//			System.out.println(c.getChallenge_name());
//		}
//		//List<Challenge> rndList2 = new ArrayList<Challenge>();
//		
//		String[] rndList2 = new String[5];
//		
//		int[] num = new int[rndList2.length];
//		int m;
////		int i = (int)(Math.random() * (rndList.size()));
////		System.out.println("i : " + i);
//		
//		for(int j = 0; j < rndList2.length ; j++) {
//			num[j] = (int)(Math.random() * (rndList.size()));
//			System.out.println("num[" + j + "] : " + num[j]);
//			//중복검사
//			for(m = 0; m < j; m++) {
//				
//				System.out.println("m : " + m);
//				System.out.println("j : " + j);
//				if(num[j] == num[m]) {
//					j--;
//					System.out.println("들어옴");
//					//continue;
//				}
////				else {
////					rndList2[j] = rndList.get(m).getChallenge_name();
////					System.out.println("rndList2[j] : " + rndList2[j]);
////				}
//			}
//			rndList2[j] = rndList.get(num[j]).getChallenge_name();
//			System.out.println("rndList2[" + j + "] : " + rndList2[j]);
//			
//		}
//		for(int j = 0; j < rndList2.length ; j++) {
//			System.out.println(rndList2[j]);
//		}
				
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		System.out.println("sc - 3");
		request.getRequestDispatcher("/WEB-INF/views/challenge/ShortChallenge.jsp")
			.forward(request, response);
		
		
		
		
	}

}
