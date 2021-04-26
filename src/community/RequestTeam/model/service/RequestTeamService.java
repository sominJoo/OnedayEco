package community.RequestTeam.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import community.MemberBoard.model.vo.Memberboard;
import community.MemberBoard.model.vo.MemberboardAttachment;
import community.RequestTeam.model.dao.RequestTeamDao;
import community.RequestTeam.model.vo.RequestTeam;


public class RequestTeamService {
	
	private RequestTeamDao requestTeamDao = new RequestTeamDao();

	public int selectApplicantsCnt(int no) {
		Connection conn = getConnection();
		int applicantsCnt = requestTeamDao.selectApplicantsCnt(conn, no);
		close(conn);
		return applicantsCnt;
	}

	public int insertRequestTeam(int no, String memberId) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = requestTeamDao.insertRequestTeam(conn, no, memberId);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public List<RequestTeam> selectApplicantsCntList(int start, int end) {
		Connection conn = getConnection();
		List<RequestTeam> applicantsCntList = requestTeamDao.selectApplicantsCntList(conn, start, end);
		close(conn);
		return applicantsCntList;
	}

	// 신청횟수
	public int selectApplicationTimes(String memberId) {
		Connection conn = getConnection();
		int applicationTimes = requestTeamDao.selectApplicationTimes(conn, memberId);
		close(conn);
		return applicationTimes;
	}

	public int selectAId(String memberId) {
		Connection conn = getConnection();
		int aId = requestTeamDao.selectAId(conn, memberId);
		close(conn);
		return aId;
	}
}