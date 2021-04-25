package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class checkPopupServlet
 */
@WebServlet("/member/checkPopup")
public class checkPopupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값 처리
		String checkId = request.getParameter("checkId");
		System.out.println("checkId@servlet = " + checkId);
		
		//2.업무로직 :해당 Id 를 db에서 조회 
		Member member =new MemberService().selectMemberOne(checkId);
		boolean available = member == null ? true : false;;
		
		request.setAttribute("checkId", checkId);
		request.setAttribute("available", available);
		
		//3. 응답처리 : 사용가능여부를 jsp에서 html로 작성
		request.getRequestDispatcher("/WEB-INF/views/login/checkPopup.jsp").forward(request, response);
	}
}
