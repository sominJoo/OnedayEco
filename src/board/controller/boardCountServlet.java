package board.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board.model.vo.Board;
import common.MvcUtils;
import onedayeco.article.model.vo.Article;
import board.model.exception.BoardException;
import board.model.service.BoardService;

/**
 * Servlet implementation class boardViewCountServlet
 */
@WebServlet("/board/boardCount")
public class boardCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public boardCountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 
		try {
		//2. 업무로직 : board객체 조회(첨부파일 조회)
		int no = boardService.selectBoardNoH();
		Article article = boardService.selectOne(no);
		if(article == null) {
			throw new BoardException("해당 게시글이 존재하지 않습니다.");
		}
		
		//xss공격방지
		article.setArticle_title(MvcUtils.escapeHtml(article.getArticle_title()));
		article.setArticle_content(MvcUtils.escapeHtml(article.getArticle_content()));
		
		// \n개행문자를 <br/>태그로 변경
		article.setArticle_content(MvcUtils.convertLindeFeedToBr(article.getArticle_content()));
		
		//3. jsp forwarding
		request.setAttribute("article", article);
		request.getRequestDispatcher("/WEB-INF/views/community/communityBoardView.jsp")
			   .forward(request, response);
	} catch(Exception e) {
		//로깅
		e.printStackTrace();
		//관리자이메일 알림
		//다시 예외를 던져서 WAS가 정한 에러페이지에서 응답메세지를 작성
		throw e;
	}

	}
}
