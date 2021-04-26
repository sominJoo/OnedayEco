package onedayeco.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MvcUtils;
import member.model.vo.Member;
import onedayeco.confirm.model.service.ConfirmService;
import onedayeco.confirm.model.vo.Confirm;

/**
 * Servlet implementation class ConfirmPointServlet
 */
@WebServlet("/admin/confirmPointServlet")
public class ConfirmPointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConfirmService confirmService = new ConfirmService();
	
	
	/**
	 * ajax 처리
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			String user = (request.getParameter("user"));

			System.out.println("boardNo@point"+boardNo);

			//2.업무로직 : article객체 조회(첨부파일 조회)
			Confirm confirm= confirmService.selectOne(boardNo); 
			String type = confirm.getConfirm_type();
			int result;
			if(type.equals("팀")) {
				result = confirmService.insertTeamPoint(user);			//팀 포인트
			}
			else {
				result = confirmService.insertPoint(confirm, user);			//개인 포인트
			}
			
			
			//포인트 지급 성공 시 
			if(result>0) {
				//게시물 포인트 지급 확인 update
				int result2 = confirmService.updatePointCheck(boardNo,user);				
			}
			
			response.sendRedirect(request.getContextPath()+"/community/confirmList");
			
		}catch(Exception e) {
			//로깅
			e.printStackTrace();
			//다시 예외를 던져서 WAS가 정한 에러페이지에서 응답메세지를 작성
			throw e;
		}	
	}
	
}
