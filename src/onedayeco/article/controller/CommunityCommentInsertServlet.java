package onedayeco.article.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onedayeco.article.model.service.ArticleService;
import onedayeco.article.model.vo.ArticleComment;

/**
 * Servlet implementation class CommunityBoardCommentInsert
 */
@WebServlet("/community/communityBoardCommentInsert")
public class CommunityCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleService articleService = new ArticleService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println(5);
			//1.사용자 입력값 처리
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			int commentLevel = Integer.parseInt(request.getParameter("commentLevel"));
			int commentRef = Integer.parseInt(request.getParameter("commentRef"));
			String writer = request.getParameter("writer");
			String content= request.getParameter("content");
			//int comment_id, String user_id, int article_id, String comment_content, Date comment_reg_date, int comment_level, int comment_ref
			ArticleComment ac = new ArticleComment(0,writer,boardNo,content,null,commentLevel,commentRef);
			
			System.out.println("boardComment@servlet = "+ac);
			
			//2.업무로직
			int result = articleService.insertBoardComment(ac);
//	
			//3.사용자피드백 & 리다이렉트
			request.getSession().setAttribute("msg",  "댓글 달기 성공!");
			response.sendRedirect(request.getContextPath() + "/community/communityBoardView?no="+boardNo);
						
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
	}
}
