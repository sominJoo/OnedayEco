package board.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.exception.BoardException;
import board.model.service.BoardService;
import board.model.vo.Board;
import common.onedayecoUtils;

/**
 * Servlet implementation class boardViewServlet
 */
@WebServlet("/board/boardView")
public class boardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 사용자 입력값 : no
			int	no  = 0; 
			try {
				no = Integer.parseInt(request.getParameter("no"));
			} catch(NumberFormatException e) {
				throw new BoardException("유효한 게시글 번호가 아닙니다.", e);
			}
			System.out.println("boardViewServlet@article_no = "+no);
			//2. 업무로직 : board객체 조회(첨부파일 조회)
			Board board = boardService.selectOne(no);
			if(board == null) {
				throw new BoardException("해당 게시글이 존재하지 않습니다.");
			}
			
			//xss공격방지
			board.setTitle(onedayecoUtils.escapeHtml(board.getTitle()));
			board.setContent(onedayecoUtils.escapeHtml(board.getContent()));
			
			// \n개행문자를 <br/>태그로 변경
			board.setContent(onedayecoUtils.convertLindeFeedToBr(board.getContent()));
			
			//3. jsp forwarding
			request.setAttribute("board", board);
			request.getRequestDispatcher("/WEB-INF/views/board/boardView.jsp")
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
