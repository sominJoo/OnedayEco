package community.MemberBoard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import community.MemberBoard.model.service.MemberboardService;
import community.MemberBoard.model.vo.Memberboard;

/**
 * Servlet implementation class MemberboardLikeServlet
 */
@WebServlet("/community/memberboardLike")
public class MemberboardLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberboardService memberboardService = new MemberboardService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String memberId = request.getParameter("id");
		int no = Integer.parseInt(request.getParameter("no"));
		memberboardService.updateMemberboardLike(no);
		// 3. 리다이렉트 & 사용자 피드백 (dml - 리다이렉트 필수)
		HttpSession session = request.getSession();
		session.setAttribute("clickLike", no);
		response.sendRedirect(request.getContextPath() + "/community/detailedMemberBoardView?no=" + no);		
	} catch(Exception e) {
		// 예외로깅 작업 - controller에서 한번만 하면 됨
		e.printStackTrace();
		// 예외페이지로 넘어가기 위해 was에 예외 다시 던지기
		throw e;
		// 예외처리를 안해주면 tomcat한테 예외가 던져져서 톰캣 예외페이지가 나오는 것
		}
	}
}
