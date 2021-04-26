package mypage.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import member.model.vo.Member;
import mypage.model.dao.MypageDao;
import mypage.model.vo.MypageBadge;
import mypage.model.vo.MypageChallenge;
import mypage.model.vo.MypagePoint;

import static common.JDBCTemplate.*;

public class MypageService {
	private MypageDao mypageDao = new MypageDao();
	
	
	public List<MypageBadge> selectBadgeImage(Date date, Member member) {
		Connection conn = getConnection();
		List<MypageBadge> list = mypageDao.selectBadgeImage(conn,date,member);
		close(conn);
		return list;
	}


	public List<MypageBadge> selectOneBadgeImage(Date date_sql, String memberId) {
		Connection conn = getConnection();
		List<MypageBadge> mBadgeList= mypageDao.selectOneBadgeImage(conn, date_sql, memberId);
		close(conn);
		return mBadgeList;
	}


	public List<MypagePoint> selectPoint(Date date_sql, String memberId) {
		Connection conn = getConnection();
		List<MypagePoint> mPointList= mypageDao.selectPoint(conn, date_sql, memberId);
		close(conn);
		return mPointList;
	}


	public List<MypageChallenge> selectChallenge(String memberId) {
		Connection conn = getConnection();
		List<MypageChallenge> mChallengeList= mypageDao.selectChallenge(conn, memberId);
		close(conn);
		return mChallengeList;
	}


	public List<MypagePoint> selectPointList(Date date, Member member) {
		Connection conn = getConnection();
		List<MypagePoint> mPointList= mypageDao.selectPointList(conn, date, member);
		close(conn);
		return mPointList;
	}


	public List<MypagePoint> selectTeamPoint(Date date_sql, String memberId) {
		Connection conn = getConnection();
		List<MypagePoint> mPointList= mypageDao.selectTeamPoint(conn, date_sql, memberId);
		close(conn);
		return mPointList;
	}


	public List<MypagePoint> selectTPointList(Date date, Member member) {
		Connection conn = getConnection();
		List<MypagePoint> mTPointList= mypageDao.selectTPointList(conn, date, member);
		close(conn);
		return mTPointList;
	}

}
