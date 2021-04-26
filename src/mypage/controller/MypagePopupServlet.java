package mypage.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import mypage.model.service.MypageService;
import mypage.model.vo.MypageBadge;
import mypage.model.vo.MypageChallenge;
import mypage.model.vo.MypagePoint;

/**
 * Servlet implementation class MypagePopupServlet
 */
@WebServlet("/mypage/mypagePopup")
public class MypagePopupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MypageService mypageService = new MypageService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MypagePopupServlet 호출됨!");
		int year = 2021;
		int month = Integer.parseInt(request.getParameter("month"));
		int date = Integer.parseInt(request.getParameter("date"));
		
		String date_ = year +"-"+month+"-"+date;
		System.out.println(month);
		Date date_sql = Date.valueOf(date_);
		System.out.println("현재 달 : date_@sdf ="+date_sql);		

		
		Member member = (Member)request.getSession().getAttribute("loginMember");
		
		//뱃지 이미지 가져오는 업무 (해당 날짜) - null 가능
		List<MypageBadge> mBadgeList = mypageService.selectOneBadgeImage(date_sql,member.getMemberId());
		for(MypageBadge m : mBadgeList)
			System.out.println("mBadgeList : "+m);
		
		
		//개인 Point 정보(해당 날짜)  - null 가능 
		List<MypagePoint> mPointList =  mypageService.selectPoint(date_sql, member.getMemberId());
		for(MypagePoint m : mPointList)
			System.out.println("mPointList : "+m);

		//팀 Point 정보(해당 날짜)  - null 가능 
		List<MypagePoint> mTPointList =  mypageService.selectTeamPoint(date_sql, member.getMemberId());
		for(MypagePoint m : mTPointList)
			System.out.println("mTPointList : "+m);
		
		//진행중인 challenge 가져오는 업무
		List<MypageChallenge> mChallengeList =  mypageService.selectChallenge(member.getMemberId());
		for(MypageChallenge m : mChallengeList)
			System.out.println("mChallengeList : "+m);
		
		request.setAttribute("month", month);
		request.setAttribute("date", date);
		request.setAttribute("mBadgeList", mBadgeList);
		request.setAttribute("mPointList", mPointList);
		request.setAttribute("mChallengeList", mChallengeList);
		request.setAttribute("mTPointList", mTPointList);
		request.getRequestDispatcher("/WEB-INF/views/mypage/mypagePopup.jsp").forward(request, response);;
	}

}
