package onedayeco.article.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MvcUtils;
import onedayeco.article.model.exception.ArticleException;
import onedayeco.article.model.service.ArticleService;
import onedayeco.article.model.vo.Article;
import onedayeco.article.model.vo.ArticleComment;

/**
 * Servlet implementation class ArticleViewServlet
 */
@WebServlet("/community/communityBoardView")
public class CommunityBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleService articleService = new ArticleService();
     
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{//1. 사용자 입력값:article_no 
			int article_id = 0;
			try {
				article_id = Integer.parseInt(request.getParameter("no"));
				System.out.println(article_id);
			}catch(NumberFormatException e) {
				throw new ArticleException("유효한 게시글 번호가 아닙니다.",e);
			}
			System.out.println("ArticleViewServlet@article_no = "+article_id);
		
			//2.업무로직 : article객체 조회(첨부파일 조회)/조회수 증가
			Article article = articleService.selectOne(article_id);
			article.setArticle_read_count(articleService.updateCount(article_id));
			
			if(article == null) {
				throw new ArticleException("해당 게시글이 존재하지 않습니다.");
			}
			
			//xss공격방지
			article.setArticle_title(MvcUtils.escapeHtml(article.getArticle_title()));
			article.setArticle_content(MvcUtils.escapeHtml(article.getArticle_content()));
			
			//\n개행문자를 <br/>태그로 변경
			article.setArticle_content(MvcUtils.convertLindeFeedToBr(article.getArticle_content()));
			
			//댓글 가져오기
			List<ArticleComment> commentList =
						articleService.selectBoardCommentList(article_id);
			System.out.println("commentList@servlet ="+commentList);
			
			//3. jsp forwarding
			request.setAttribute("article", article);
			request.setAttribute("commentList",commentList);
			request.getRequestDispatcher("/WEB-INF/views/community/communityBoardView.jsp")
					.forward(request,response);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}


}
