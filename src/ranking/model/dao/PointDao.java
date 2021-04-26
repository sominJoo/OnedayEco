package ranking.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import community.MemberBoard.model.exception.MemberboardException;
import community.MemberBoard.model.vo.Challenge;
import community.MemberBoard.model.vo.Memberboard;
import community.RequestTeam.model.dao.RequestTeamDao;
import member.model.dao.MemberDao;
import onedayeco.confirm.model.exception.ConfirmException;
import ranking.model.vo.PersonalPoint;

public class PointDao {

	private Properties prop = new Properties();
	public PointDao() {
		String fileName = "/sql/ranking/ranking-query.properties";
		String absPath = MemberDao.class.getResource(fileName).getPath();
		try {
			prop.load(new FileReader(absPath));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	public List<PersonalPoint> selectPersonalList(Connection conn, int start, int end) {
		List<PersonalPoint> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String  sql = prop.getProperty("selectPersonalList");
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
			PersonalPoint personalP = new PersonalPoint();
			personalP.setMemberId(rset.getString("member_id"));
			personalP.setPoint(rset.getInt("sum"));
			personalP.setrNum(rset.getInt("rnum"));
			// memberboard.setChallenge(challenge);
			list.add(personalP);
		}
	} catch (SQLException e) {
		throw new MemberboardException("개인 랭킹 조회 오류", e);
	} finally {
		// 5. 자원반납(생성역순 rset - pstmt) 
		close(rset);
		close(pstmt);
	}
	return list;
	}
	public int selectPersonalPCount(Connection conn) {
		int totalContents = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String  sql = prop.getProperty("selectPersonalPCount");
		try {
			// 3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			// 4. 쿼리문 실행
			rset = pstmt.executeQuery();
			// 4.1 ResultSet -> Java객체 옮겨담기
			if(rset.next()) {
				totalContents = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. 자원반납(생성역순 rset - pstmt) 
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}
	public String selectOne(Connection conn) {
		String one = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String  sql = prop.getProperty("selectOne");
		try {
			// 3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			// 4. 쿼리문 실행
			rset = pstmt.executeQuery();
			// 4.1 ResultSet -> Java객체 옮겨담기
			if(rset.next()) {
				one = rset.getString("member_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. 자원반납(생성역순 rset - pstmt) 
			close(rset);
			close(pstmt);
		}
		return one;
	}
	public String selectTwo(Connection conn) {
		String two = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String  sql = prop.getProperty("selectTwo");
		try {
			// 3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			// 4. 쿼리문 실행
			rset = pstmt.executeQuery();
			// 4.1 ResultSet -> Java객체 옮겨담기
			if(rset.next()) {
				two = rset.getString("member_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. 자원반납(생성역순 rset - pstmt) 
			close(rset);
			close(pstmt);
		}
		return two;
	}
	public String selectThree(Connection conn) {
		String three = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String  sql = prop.getProperty("selectThree");
		try {
			// 3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			// 4. 쿼리문 실행
			rset = pstmt.executeQuery();
			// 4.1 ResultSet -> Java객체 옮겨담기
			if(rset.next()) {
				three = rset.getString("member_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. 자원반납(생성역순 rset - pstmt) 
			close(rset);
			close(pstmt);
		}
		return three;
	}
	public int insertRankingBadge_personal(Connection conn, String user, int rank) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query ="insert into badge values (seq_badge_id.nextval, null, ?, (select badge_img_id from badge_image where lanking=? and badge_name like 'Rm%') , sysdate)";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,user);
			pstmt.setInt(2,rank);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ConfirmException("뱃지 지급 오류",e);
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int insertRankingBadge_team(Connection conn, String user, int rank) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query ="insert into badge values (seq_badge_id.nextval, null, ?, (select badge_img_id from badge_image where lanking=? and badge_name like 'Tm%') , sysdate)";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,user);
			pstmt.setInt(2,rank);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ConfirmException("뱃지 지급 오류",e);
		}finally {
			close(pstmt);
		}
		return result;
	}
}