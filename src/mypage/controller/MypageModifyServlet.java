package mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MypageModifyServlet
 */
@WebServlet("/mypage/memberModify")
public class MypageModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService=new MemberService(); 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/mypage/memberModify.jsp").forward(request, response);
	}

	/**
	 * member update
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String memberName = request.getParameter("memberName");
			String memberId = request.getParameter("memberId");
			String memberEmail = request.getParameter("memberEmail");
			String memberPhone = request.getParameter("memberPhone");
			System.out.println("memberName =" + memberName);
			System.out.println("memberId =" + memberId);
			System.out.println("memberEmail =" + memberEmail);
			System.out.println("memberPhone =" + memberPhone);
			
			
			Member member = new Member(memberId, null, memberName, null, memberEmail, memberPhone, null, null, null);
			//2. 업무로직(업데이트)
			int result = memberService.updateMember(member);
			
			request.getSession().setAttribute("msg", "회원정보 수정 성공");
			request.getSession().setAttribute("loginMember", memberService.selectMemberOne(memberId));	//변경된 정보를 session에 저장함
			response.sendRedirect(request.getContextPath()+"/mypage");
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
