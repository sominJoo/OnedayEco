package mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/mypage/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String memberId = request.getParameter("memberId");
			int result = new MemberService().deleteMember(memberId);
			HttpSession session = request.getSession();
			if(result>0) {
				response.sendRedirect(request.getContextPath() + "/logout");
			}
			else {
				session.setAttribute("msg", "회원정보삭제에 실패했습니다.");
				response.sendRedirect(request.getContextPath());			
			}
			
		}catch(Exception e) {
			throw e;
		}
		
	}

}
