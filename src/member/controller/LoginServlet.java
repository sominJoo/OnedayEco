package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;
import common.MvcUtils;
import common.onedayecoUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private	MemberService memberService =new MemberService();

	/**
	 * 단순 page 연결
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(request, response);
	}
	
	/**
	 * 일반회원 로그인
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String memberId =request.getParameter("memberId");
		String memberPw=MvcUtils.getSha512(request.getParameter("memberPw"));
		String SaveId= request.getParameter("saveId");
		System.out.println("하루에코(ID)= " +memberId);
		System.out.println("하루에코(PW)=" +memberPw);
		System.out.println("하루에코(SaveId) =" +SaveId);
		
		Member member = memberService.selectMemberOne(memberId);
		System.out.println("Login@Servlet = " +member);
		
		HttpSession session = request.getSession(true);
		if(member != null && memberPw.equals(member.getMemberPw())) {
			session.setAttribute("loginMember", member);
			//saveId : cookie처리
			Cookie c = new Cookie("saveId", memberId);
			c.setPath(request.getContextPath());
			if(SaveId !=null) {
				//Save_id 체크시
				c.setMaxAge(60*60*24*30);	//30일 영속 쿠키로 지정	
			}
			else {
				c.setMaxAge(0);	
			}
			response.addCookie(c);
			
		}
		else {
			session.setAttribute("msg", "로그인에 실패했습니다.");
		}			
		//이전페이지로 리다이렉트 처리
		String referer = request.getHeader("Referer");
		System.out.println("referer@servlet = " + referer);
//			//	리다이렉트 :Url 
		response.sendRedirect(referer);
	}

}
