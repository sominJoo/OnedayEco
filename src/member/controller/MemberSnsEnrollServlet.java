package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import member.model.service.MemberService;
import member.model.vo.Member;


/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/login")
public class MemberSnsEnrollServlet extends HttpServlet {
	private MemberService memberService = new MemberService(); 
	
	private static final long serialVersionUID = 1L;

	/**
	 * sns회원가입
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try {
			
			String memberId = request.getParameter("id");
			String memberName = request.getParameter("name");
			String memberEmail = request.getParameter("email");
			String memberNickname = request.getParameter("nickname");
			String memberPhone = request.getParameter("mobile");
			
			
			Member member = new Member(memberId, null, memberName, memberNickname, memberEmail, memberPhone, null, null, null);
			System.out.println("member@servlet : " + member);
			
			//2. 업무로직
			//회원 확인
			Member m = memberService.selectMemberOne(member.getMemberId());
			System.out.println("m@servlet : " + m);
			
			int result= 0;
			//회원없으면 회원가입
			if(m == null) {
				result = memberService.insertMember(member);
			}
			System.out.println("회원가입 result=" + result);
			response.setContentType("application/json; charset=utf-8");
			Gson gson = new Gson();
			String jsonStr = gson.toJson(member);

			PrintWriter out = response.getWriter();
			out.print(jsonStr);
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}		
	}

}
