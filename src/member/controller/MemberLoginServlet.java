package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberLogin
 */
@WebServlet("/login/snsLogin")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new  MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("MemberLoginServlet 실행");
		
		
		String memberId = request.getParameter("id");
		System.out.println("memberId =  "+memberId);
		Member member = memberService.selectMemberOne(memberId);
		HttpSession session = request.getSession();
		if(member != null) {
			System.out.println("sessionId@memberLoginServlet = " + session.getId());
			session.setAttribute("loginMember", member);
		}
		else {		
			session.setAttribute("msg", "로그인 실패");
		}
		
		
		
		request.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(request, response);
	}
	

}
