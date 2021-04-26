package mypage.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import member.model.dao.MemberDao;
import member.model.exception.MemberException;
import member.model.vo.Member;
import mypage.model.vo.MypageBadge;
import mypage.model.vo.MypageChallenge;
import mypage.model.vo.MypagePoint;

import static common.JDBCTemplate.*;


public class MypageDao {
	Properties prop = new Properties();
	
	public MypageDao(){
		String fileName = "/sql/mypage/mypage-query.properties";
		String absPath = MemberDao.class.getResource(fileName).getPath();
		try {
			prop.load(new FileReader(absPath));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public List<MypageBadge> selectBadgeImage(Connection conn, Date date, Member member) {
		List<MypageBadge> list = new ArrayList<>();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		MypageBadge mBadge =null;
		String sql = prop.getProperty("selectBadgeImage");
		
		try {
			System.out.println("date@Dao =" +date);
			pstmt= conn.prepareStatement(sql);
			pstmt.setDate(1, date);
			pstmt.setDate(2, date);
			pstmt.setString(3, member.getMemberId());
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				mBadge= new MypageBadge();
				mBadge.setBadgeImgId(rs.getInt("badge_img_id"));
				mBadge.setBadgeName(rs.getString("badge_name"));
				mBadge.setBadgeImg(rs.getString("badge_img"));
				mBadge.setChallegeName(rs.getString("challenge_name"));
				mBadge.setBadgeDate(rs.getDate("badge_date"));
				
				list.add(mBadge);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	
	/**
	 * 특정날짜의 뱃지 이미지 정보를 가져옴
	 * @param conn
	 * @param date_sql
	 * @param memberId
	 * @return
	 */
	public List<MypageBadge> selectOneBadgeImage(Connection conn, Date date_sql, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectOneBadgeImage");
		List<MypageBadge> list = new ArrayList<MypageBadge>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, date_sql);
			pstmt.setString(2, memberId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MypageBadge mBadge = new MypageBadge();
				mBadge.setBadgeImgId(rs.getInt("badge_img_id"));
				mBadge.setBadgeName(rs.getString("badge_name"));
				mBadge.setBadgeImg(rs.getString("badge_img"));
				mBadge.setChallegeName(rs.getString("challenge_name"));
				mBadge.setBadgeDate(rs.getDate("badge_date"));
				list.add(mBadge);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemberException("POPUP 뱃지 이미지 조회 오류",e);
		}finally {
			close(rs);
			close(pstmt);
			
		}
		
		return list;
	}

	public List<MypagePoint> selectPoint(Connection conn, Date date_sql, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectPoint");
		System.out.println(date_sql);
		System.out.println("selectPoint@sql"+sql);
		List<MypagePoint> mPointList = new ArrayList<MypagePoint>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, date_sql);
			pstmt.setString(2, memberId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MypagePoint mPoint = new MypagePoint();
				mPoint.setPoint(rs.getInt("point"));
				mPoint.setPointDate(rs.getDate("point_date"));
				mPoint.setChallengName(rs.getString("challenge_name"));
				mPoint.setChallengeTermType(rs.getString("challenge_term_type"));
				mPointList.add(mPoint);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemberException("POPUP 포인트 조회 오류",e);
		}finally {
			close(rs);
			close(pstmt);
		}		
		return mPointList;
	}

	public List<MypageChallenge> selectChallenge(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectChallenge");
		List<MypageChallenge> mChallengeList = new ArrayList<MypageChallenge>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				MypageChallenge mc = new MypageChallenge();
				mc.setChallengeName(rs.getString("challenge_name"));
				mc.setChallengeTermType(rs.getString("challenge_term_type"));
				mc.setChallengeLevel(rs.getInt("challenge_level"));
				mc.setConfirmDate(rs.getDate("confirm_date"));
				mc.setEndDate(rs.getDate("end_date"));
				mChallengeList.add(mc);
			}
		} catch (SQLException e) {
			throw new MemberException("POPUP 챌린지 조회 오류",e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return mChallengeList;
	}

	public List<MypagePoint> selectPointList(Connection conn, Date date, Member member) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectPointMonth");
		List<MypagePoint> mPointList = new ArrayList<MypagePoint>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, date);
			pstmt.setDate(2, date);
			pstmt.setString(3, member.getMemberId());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MypagePoint mPoint = new MypagePoint();
				mPoint.setPoint(rs.getInt("point"));
				mPoint.setPointDate(rs.getDate("point_date"));
				mPointList.add(mPoint);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemberException("POPUP 포인트 조회 오류",e);
		}finally {
			close(rs);
			close(pstmt);
		}		
		return mPointList;
	}

	public List<MypagePoint> selectTeamPoint(Connection conn, Date date_sql, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectTeamPoint");
		List<MypagePoint> mPointList = new ArrayList<MypagePoint>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, date_sql);
			pstmt.setString(2, memberId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MypagePoint mPoint = new MypagePoint();
				mPoint.setPoint(rs.getInt("point"));
				mPoint.setPointDate(rs.getDate("point_date"));
				mPoint.setChallengName(rs.getString("challenge_name"));
				mPoint.setChallengeTermType(rs.getString("challenge_term_type"));
				mPointList.add(mPoint);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemberException("POPUP 포인트 조회 오류",e);
		}finally {
			close(rs);
			close(pstmt);
		}		
		return mPointList;
	}

	public List<MypagePoint> selectTPointList(Connection conn, Date date, Member member) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectTeamPointMonth");
		List<MypagePoint> mTPointList = new ArrayList<MypagePoint>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, date);
			pstmt.setDate(2, date);
			pstmt.setString(3, member.getMemberId());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MypagePoint mPoint = new MypagePoint();
				mPoint.setPoint(rs.getInt("point"));
				mPoint.setPointDate(rs.getDate("point_date"));
				mTPointList.add(mPoint);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemberException("POPUP 포인트 조회 오류",e);
		}finally {
			close(rs);
			close(pstmt);
		}		
		return mTPointList;
	}

}
