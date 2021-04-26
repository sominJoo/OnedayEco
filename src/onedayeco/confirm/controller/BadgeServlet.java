package onedayeco.confirm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MvcUtils;
import onedayeco.confirm.model.exception.ConfirmException;
import onedayeco.confirm.model.service.ConfirmService;
import onedayeco.confirm.model.vo.Confirm;

/**
 * Servlet implementation class BadgeServlet
 */
@WebServlet("/admin/badgeInsert")
public class BadgeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConfirmService confirmService = new ConfirmService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int article_id = 0;
		try {
			article_id = Integer.parseInt(request.getParameter("boardNo"));
			System.out.println(article_id);
		}catch(NumberFormatException e) {
			throw new ConfirmException("유효한 게시글 번호가 아닙니다.",e);
		}
		
		String user = (request.getParameter("user"));
		String challengeName = (request.getParameter("challenge"));
		
		//2.업무로직 : article객체 조회(첨부파일 조회)
		Confirm confirm= confirmService.selectOne(article_id); 
		if(confirm == null) {
			throw new ConfirmException("해당 게시글이 존재하지 않습니다.");
		}
		
		
		
		int result = new ConfirmService().insertBadge(user,confirm.getChallengeName());
		
		
		if(result>0) {
			//뱃지 지급완료된 challenge는 challenge_join에서 삭제
			int result2 =confirmService.deleteChallenge(user,confirm.getChallengeName());
		}
		
		
		
		
		
		
		response.sendRedirect(request.getContextPath()+"/community/confirmList");
	}

}
