package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board.model.vo.Board;
import common.onedayecoUtils;
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
			// 1.사용자 입력값: 조회수: count
			int count = 0;
			try {
				count = Integer.parseInt(request.getParameter("count"));
			} catch (NumberFormatException e) {
				throw new BoardException("유효한 조회수가 아닙니다.", e);
			}
			System.out.println("조회수 조회입니다= " + count);
			//2. 업무로직: 
			Board board =boardService.selectcount(count);
			if(board == null) {
				throw new BoardException("해당 조회수가  존재하지 않습니다.");
		}
			//xss공격방지
			board.setTitle(onedayecoUtils.escapeHtml(board.getTitle()));
			board.setContent(onedayecoUtils.escapeHtml(board.getContent()));
			
			// \n개행문자를 <br/>태그로 변경
			board.setContent(onedayecoUtils.convertLindeFeedToBr(board.getContent()));
			
			//3. jsp forwarding
			request.setAttribute("board", board);
			request.setAttribute("count", count);
			request.getRequestDispatcher("/WEB-INF/views/board/boardCount.jsp")
				   .forward(request, response);
		}catch (Exception e) {
			//로깅
			e.printStackTrace();
			//관리자이메일 알림
			//다시 예외를 던져서 WAS가 정한 에러페이지에서 응답메세지를 작성
			throw e;
			
		}
	}
}
