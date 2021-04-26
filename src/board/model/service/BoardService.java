package board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;
import java.sql.Connection;
import java.util.List;
import board.model.dao.BoardDao;
import board.model.vo.Attachment;
import board.model.vo.Board;

public class BoardService {
	private BoardDao boardDao = new BoardDao();



	public int selectBoardCount() {
		Connection conn = getConnection();
		int totalContents = boardDao.selectBoardCount(conn);
		close(conn);
		return totalContents;
	}

	public Board selectOne(int no) {
		Connection conn = getConnection();
		Board board = boardDao.selectOne(conn, no);
		close(conn);
		return board;
	}

	public Board selectcount(int count) {
		Connection conn = getConnection();
		Board board = boardDao.selectcount(conn, count);
		close(conn);
		return board;
	}



}
