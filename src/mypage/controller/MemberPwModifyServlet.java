package mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.MvcUtils;
import member.model.service.MemberService;
import member.model.vo.Member;
import mypage.model.service.MypageService;

/**
 * Servlet implementation class MemberPwModifyServlet
 */
@WebServlet("/mypage/modifyPassword")
public class MemberPwModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/mypage/memberPwModify.jsp").forward(request, response);
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String password = request.getParameter("current-password");
			String newPassword = MvcUtils.getSha512(request.getParameter("newPassword"));
			System.out.println("password = "+password);
			System.out.println("newPassword = "+newPassword);
			
			HttpSession session = request.getSession();
			Member member = (Member)session.getAttribute("loginMember");
			
			//2.업무 로직
			int result = new MemberService().updatePassword(member, password, newPassword);
			
			request.getSession().setAttribute("msg", "비밀번호 수정 성공");
			response.sendRedirect(request.getContextPath()+"/mypage");	
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
