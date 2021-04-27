package board.model.dao;

import static common.JDBCTemplate.close;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import board.model.exception.BoardException;
import board.model.vo.Attachment;
import board.model.vo.Board;
import onedayeco.article.model.vo.Article;

public class BoardDao {
	private Properties prop = new Properties();
	
	public BoardDao() {
		//board-query.properties의 내용읽어와서 prop에 저장
		//resources/sql/board-query.properties가 아니라
		//WEB-INF/classes/sql/board/board-query.properties에 접근해야함.
		String fileName = "/sql/board/board-query.properties";
		String absPath = BoardDao.class.getResource(fileName).getPath();
		try {
			prop.load(new FileReader(absPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public int selectBoardCount(Connection conn) {
		int totalContents = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBoardCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				totalContents = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}
	public Article selectOne(Connection conn, int no) {
		Article board = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectOne");
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println(1111);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			System.out.println(2222);
			while(rs.next()) {
				board = new Article();
				board.setArticle_id(rs.getInt("A_ID"));
				System.out.println(3333);
				board.setUser_id(rs.getString("MEMBER_ID"));
				board.setArticle_type(rs.getString("A_TYPE"));
				board.setArticle_title(rs.getString("A_TITLE"));
				board.setArticle_content(rs.getString("A_CONTENT"));
				board.setArticle_reg_date(rs.getDate("A_REG_DATE"));
				board.setArticle_read_count(rs.getInt("A_READ_COUNT"));
				board.setArticle_like(rs.getInt("A_LIKE"));
			}
		} catch (SQLException e) {
			throw new BoardException("날짜 최신순 등록오류" ,e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return board;
	}
	public Board selectcount(Connection conn, int count) {
		Board board = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectcount");
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println("넘어오는 객체 확인 ");
			pstmt.setInt(1, count);
			rs = pstmt.executeQuery();
			System.out.println(2222);
			while(rs.next()) {
				board = new Board();
				board.setAid(rs.getInt("A_ID"));
				board.setMemberId(rs.getString("MEMBER_ID"));
				board.setAtype(rs.getString("A_TYPE"));
				board.setTitle(rs.getString("A_TITLE"));
				board.setContent(rs.getString("A_CONTENT"));
				board.setRegDate(rs.getDate("A_REG_DATE"));
				board.setRedCount(rs.getInt("A_READ_COUNT"));
				board.setLike(rs.getInt("A_LIKE"));
			}
		} catch (SQLException e) {
			throw new BoardException("조회수 오류  등록오류" ,e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return board;
	}
	public int selectBoardNoC(Connection conn) {
		int boardNoC = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBoardNoC");
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println(pstmt);
			rset = pstmt.executeQuery();
			System.out.println(rset);
			if(rset.next()) {
				boardNoC = rset.getInt("a_id");
				System.out.println("BoardNoC"+boardNoC);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return boardNoC;
	}
	public int selectBoardNoH(Connection conn) {
		int boardNoH = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBoardNoH");
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println(pstmt);
			rset = pstmt.executeQuery();
			System.out.println(rset);
			if(rset.next()) {
				boardNoH = rset.getInt("a_id");
				System.out.println("BoardNoH"+boardNoH);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return boardNoH;
	}
}
