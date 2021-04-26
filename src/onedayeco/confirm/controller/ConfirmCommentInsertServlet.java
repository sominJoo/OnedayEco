package onedayeco.confirm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onedayeco.article.model.service.ArticleService;
import onedayeco.article.model.vo.ArticleComment;
import onedayeco.confirm.model.service.ConfirmService;
import onedayeco.confirm.model.vo.ConfirmComment;

/**
 * Servlet implementation class ConfirmCommentInsertServlet
 */
@WebServlet("/community/confirmCommentInsert")
public class ConfirmCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConfirmService confirmService = new ConfirmService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.사용자 입력값 처리
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			int commentLevel = Integer.parseInt(request.getParameter("commentLevel"));
			int commentRef = Integer.parseInt(request.getParameter("commentRef"));
			String writer = request.getParameter("writer");
			String content= request.getParameter("content");
			//int comment_id, String user_id, int article_id, String comment_content, Date comment_reg_date, int comment_level, int comment_ref
			ConfirmComment cc = new ConfirmComment(0,writer,boardNo,content,null,commentLevel,commentRef);
			
			System.out.println("boardComment@servlet = "+cc);
			
			//2.업무로직
			int result = confirmService.insertBoardComment(cc);
//			String msg = result > 0?  "댓글 등록 성공!" : "댓글 등록 실패!";
			// => 실패하면 오류남으로 이 처리 필요 없음
			
			//3.사용자피드백 & 리다이렉트
			request.getSession().setAttribute("msg",  "댓글 등록 성공!");
			response.sendRedirect(request.getContextPath() + "/community/confirmView?no="+boardNo);
						
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
	}

}
