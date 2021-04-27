package board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;
import java.sql.Connection;
import java.util.List;
import board.model.dao.BoardDao;
import board.model.vo.Attachment;
import board.model.vo.Board;
import onedayeco.article.model.vo.Article;

public class BoardService {
	private BoardDao boardDao = new BoardDao();



	public int selectBoardCount() {
		Connection conn = getConnection();
		int totalContents = boardDao.selectBoardCount(conn);
		close(conn);
		return totalContents;
	}

	public Article selectOne(int no) {
		Connection conn = getConnection();
		Article board = boardDao.selectOne(conn, no);
		close(conn);
		return board;
	}

	public Board selectcount(int count) {
		Connection conn = getConnection();
		Board board = boardDao.selectcount(conn, count);
		close(conn);
		return board;
	}

	public int selectBoardNoC() {
		Connection conn = getConnection();
		int boardNoC = boardDao.selectBoardNoC(conn);
		close(conn);
		return boardNoC;
	}

	public int selectBoardNoH() {
		Connection conn = getConnection();
		int boardNoH = boardDao.selectBoardNoH(conn);
		close(conn);
		return boardNoH;
	}



}
