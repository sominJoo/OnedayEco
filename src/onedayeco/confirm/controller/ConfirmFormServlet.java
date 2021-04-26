package onedayeco.confirm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import mypage.model.service.MypageService;
import mypage.model.vo.MypageChallenge;

/**
 * Servlet implementation class ConfirmFormServlet
 */
@WebServlet("/community/confirmForm")
public class ConfirmFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member member = (Member)request.getSession().getAttribute("loginMember");
		//현재 진행중인 챌린지를 가져오는 업무
		List<MypageChallenge> mChallengeList =  new MypageService().selectChallenge(member.getMemberId());
		for(MypageChallenge m : mChallengeList)
			System.out.println("mChallengeList : "+m);
		
		request.setAttribute("mChallengeList", mChallengeList);
		request.getRequestDispatcher("/WEB-INF/views/community/confirmEnroll.jsp")
			   .forward(request, response);
	}
}
