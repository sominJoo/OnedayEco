package community.RequestTeam.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import community.MemberBoard.model.exception.MemberboardException;
import community.MemberBoard.model.vo.Memberboard;
import community.MemberBoard.model.vo.MemberboardAttachment;
import community.RequestTeam.model.vo.RequestTeam;

import static common.JDBCTemplate.close;

public class RequestTeamDao {

	private Properties prop = new Properties();
	public RequestTeamDao() {
		String fileName = "/sql/memberboard/request-query.properties";
		String absPath = RequestTeamDao.class.getResource(fileName).getPath();
		try {
			prop.load(new FileReader(absPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int selectApplicantsCnt(Connection conn, int no) {
		int applicantsCnt = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// select count(*) cnt from request_team where a_id = ?
		String sql = prop.getProperty("selectApplicantsCnt");
		try {
			// 3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			// 4. 쿼리문 실행
			rset = pstmt.executeQuery();
			// 4.1 ResultSet -> Java객체 옮겨담기
			if(rset.next()) {
				applicantsCnt = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. 자원반납(생성역순 rset - pstmt) 
			close(rset);
			close(pstmt);
		}
		return applicantsCnt;
	}
	public int insertRequestTeam(Connection conn, int no, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		// insertRequestTeam = insert into request_team values (seq_attachment_id.nextVal, ?, ?)
		String sql = prop.getProperty("insertRequestTeam");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberboardException("팀 신청 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	public List<RequestTeam> selectApplicantsCntList(Connection conn, int start, int end) {
		
		List<RequestTeam> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// select a_id, count(request_id) cnt from request_team group by a_id;
		String  sql = prop.getProperty("selectApplicantsCntList");
		
		try {
		// 3. PreparedStatement객체 생성(미완성쿼리)
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		// 4. 쿼리문 실행
		rset = pstmt.executeQuery();
		// 4.1 ResultSet -> Java객체 옮겨담기
		list = new ArrayList<>();			
		// rset.next() 결과집합이 여러행일 때 다음행 있니? -> 행을 가리키는 포인터를 다음행으로 옮겨주는 역할
		while(rset.next()) {
			
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		// 5. 자원반납(생성역순 rset - pstmt) 
		close(rset);
		close(pstmt);
	}
	return list;
	}
	public int selectApplicationTimes(Connection conn, String memberId) {
		int applicationTimes = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// select count(*) cnt from request_team where member_id = ?
		String sql = prop.getProperty("selectApplicationTimes");
		try {
			// 3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			// 4. 쿼리문 실행
			rset = pstmt.executeQuery();
			// 4.1 ResultSet -> Java객체 옮겨담기
			if(rset.next()) {
				applicationTimes = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. 자원반납(생성역순 rset - pstmt) 
			close(rset);
			close(pstmt);
		}
		return applicationTimes;
	}
	public int selectAId(Connection conn, String memberId) {
		int aId = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// select a_id from request_team where member_id = ?
		String sql = prop.getProperty("selectAId");
		try {
			// 3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			// 4. 쿼리문 실행
			rset = pstmt.executeQuery();
			// 4.1 ResultSet -> Java객체 옮겨담기
			if(rset.next()) {
				aId = rset.getInt("a_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. 자원반납(생성역순 rset - pstmt) 
			close(rset);
			close(pstmt);
		}
		return aId;
	}
}
