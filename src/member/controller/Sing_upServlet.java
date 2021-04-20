package member.controller;

import java.io.IOException
;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import common.onedayecoUtils;
import member.model.service.MemberService;

/**
 * Servlet implementation class Sing_upServlet
 */
@WebServlet("/login/member_sing_up")
public class Sing_upServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login/member_sing_up.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//아이디 
		String memberId=request.getParameter("memberId");
		//비밀번호 
		String memberPw=onedayecoUtils.getSha512(request.getParameter("memberPw"));
		//이름
		String memberName = request.getParameter("memberName");
		//닉네임(변경불가
		String memberNickname= request.getParameter("memberNickname");
		//이메일
		String memberEmail =request.getParameter("memberEmail");
		//전화번호
		String memberPhone =request.getParameter("memberPhone");
		Member member =new Member(memberId,memberPw,memberName,memberNickname,memberEmail,memberPhone,null,null,null);
		System.out.println("하루에코 회원정보:  "+member);
		//3.서비스로직호출
		int result = new MemberService().insertMember(member);
		//4. 사용자 피드백 및 페이지 리다이렉트
		String msg = "";
		if(result>0)
			msg = "성공적으로 회원가입되었습니다.";
		else 
			msg = "회원등록에 실패했습니다.";	
		
		request.getSession().setAttribute("msg", msg);
		
		response.sendRedirect(request.getContextPath());
	}

}
