package mypage.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import mypage.model.service.MypageService;
import mypage.model.vo.MypageBadge;
import mypage.model.vo.MypagePoint;

/**
 * Servlet implementation class MypageViewServlet
 */
@WebServlet("/mypage")
public class MypageViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MypageService mypageService = new MypageService();
	/**
	 * 단순 연결
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String monthStr= request.getParameter("month");
		Calendar now =Calendar.getInstance();
		int month= 0;
		if(monthStr != null) {
			month = Integer.valueOf(monthStr);		
		}
		else {
			month = now.get(Calendar.MONTH)+1;
		}
		
		String month_str="";
		if(month<10) {
			month_str += ("2021-"+"0"+ month+"-01");
		}
		else {
			month_str += ("2021-"+ month+"-01");
		}
		Date date = Date.valueOf(month_str);
		System.out.println("현재 달 : date@sdf ="+date);		

		
		Member member = (Member)request.getSession().getAttribute("loginMember");
		
		//뱃지 이미지 가져오는 업무 (멤버 아이디, 해당 달)
		List<MypageBadge> badgeList = mypageService.selectBadgeImage(date,member);	


		//포인트 가져오는 업무 (멤버 아이디, 해당 달)
		List<MypagePoint> pointList = mypageService.selectPointList(date,member);	
		List<MypagePoint> tPointList = mypageService.selectTPointList(date,member);	
		
		request.setAttribute("month", month);		
		request.setAttribute("badgeList", badgeList);		
		request.setAttribute("pointList", pointList);		
		request.setAttribute("tPointList", tPointList);		
		request.getRequestDispatcher("/WEB-INF/views/mypage/mypage.jsp").forward(request, response);
	}


}
