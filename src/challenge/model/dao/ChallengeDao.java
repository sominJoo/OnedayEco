package challenge.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import static common.JDBCTemplate.*;

import challenge.controller.ShortChallengeServlet;
import challenge.model.exception.ChallengeException;
import challenge.model.vo.Challenge;

public class ChallengeDao {
	
	private Properties prop = new Properties();
	
	Calendar cal = new GregorianCalendar();
	
	public ChallengeDao() {
		String fileName = "/sql/challenge/challenge-query.properties";
		String absPath = ChallengeDao.class.getResource(fileName).getPath();
		try {
			prop.load(new FileReader(absPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Challenge> selectChallenge(String type, Connection conn, int start, int end) {
		List<Challenge> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql;
		if (type.equals("short")) {
			sql = prop.getProperty("selectShortChallenge");
		}else {
			sql = prop.getProperty("selectLongChallenge");
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Challenge challenge = new Challenge();
				challenge.setChallenge_id(rset.getInt("challenge_id"));
				challenge.setChallenge_term_type(rset.getString("challenge_term_type"));
				challenge.setChallenge_level(rset.getInt("challenge_level"));
				challenge.setChallenge_name(rset.getString("challenge_name"));
				challenge.setChallenge_info(rset.getString("challenge_info"));
				challenge.setChallenge_point(rset.getInt("challenge_point"));
				challenge.setChallenge_term(rset.getInt("challenge_term"));
				list.add(challenge);
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new ChallengeException("챌린지 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	//
	public int selectChallengeCount(String type, Connection conn) {
		int totalContents = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectChallengeCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			if(type.equals("short"))
				pstmt.setString(1, "S");
			else
				pstmt.setString(1, "L");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				totalContents = rset.getInt("cnt");		
			}
		} catch (SQLException e) {
			throw new ChallengeException("챌린지 count 오류", e);
			//e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}

	public int deleteChallenge(Connection conn, int c_id) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteChallenge");
		//String sql = prop.getProperty("deletChallenge");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new ChallengeException("챌린지 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int joinChallenge(Connection conn, String member_id, int c_id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int confirmNum = 0;
		int result = 0;
		Date date = new Date(cal.getTimeInMillis());
		
		System.out.println("ChallengeDao - joinChallenge : " + ShortChallengeServlet.nowChallenge);
		
		
		String sql = prop.getProperty("seletJoinCount");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			pstmt.setInt(2, c_id);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				confirmNum = rset.getInt("cnt");		
			}
			//confirmNum이 1이면 그 미션하는중이고 0이면 안 하는중이다.
			System.out.println("confirmNum : " + confirmNum);
			
			if(confirmNum == 1)
				result = 0;
			else {
				if(ShortChallengeServlet.nowChallenge == "short") {
					sql = prop.getProperty("insertJoinShortChallenge");
				}
				else {
					sql = prop.getProperty("insertJoinLongChallenge");
				}
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, member_id);
				pstmt.setInt(2, c_id);
				pstmt.setDate(3, date);
				result = pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			throw new ChallengeException("챌린지 도전 오류", e);
			//e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertChallenge(Connection conn, Challenge challenge) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertChallenge");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, challenge.getChallenge_term_type());
			pstmt.setInt(2, challenge.getChallenge_level());
			pstmt.setString(3, challenge.getChallenge_name());
			pstmt.setString(4, challenge.getChallenge_info());
			pstmt.setInt(5, challenge.getChallenge_point());
			pstmt.setInt(6, challenge.getChallenge_term());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new ChallengeException("챌린지 등록 오류", e);
			//e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Challenge selectOne(Connection conn, int c_id) {
		Challenge challenge = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOne");
		try{
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, c_id);
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				challenge = new Challenge();
				challenge.setChallenge_id(rset.getInt("challenge_id"));
				challenge.setChallenge_term_type(rset.getString("challenge_term_type"));
				challenge.setChallenge_level(rset.getInt("challenge_level"));
				challenge.setChallenge_name(rset.getString("challenge_name"));
				challenge.setChallenge_info(rset.getString("challenge_info"));
				challenge.setChallenge_point(rset.getInt("challenge_point"));
				challenge.setChallenge_term(rset.getInt("challenge_term"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return challenge;
	}

	public int modifyChallenge(Connection conn, Challenge challenge) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("modifyChallenge");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, challenge.getChallenge_level());
			pstmt.setString(2, challenge.getChallenge_name());
			pstmt.setString(3, challenge.getChallenge_info());
			pstmt.setInt(4, challenge.getChallenge_point());
			pstmt.setInt(5, challenge.getChallenge_id());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new ChallengeException("챌린지 수정 오류", e);
			//e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Challenge> selectShortAll(Connection conn) {
		List<Challenge> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectShortAll");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Challenge challenge = new Challenge();
				challenge.setChallenge_id(rset.getInt("challenge_id"));
				challenge.setChallenge_term_type(rset.getString("challenge_term_type"));
				challenge.setChallenge_level(rset.getInt("challenge_level"));
				challenge.setChallenge_name(rset.getString("challenge_name"));
				challenge.setChallenge_info(rset.getString("challenge_info"));
				challenge.setChallenge_point(rset.getInt("challenge_point"));
				challenge.setChallenge_term(rset.getInt("challenge_term"));
				list.add(challenge);
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new ChallengeException("챌린지 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	

}

