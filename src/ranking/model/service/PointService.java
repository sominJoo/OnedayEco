package ranking.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import ranking.model.dao.PointDao;
import ranking.model.vo.PersonalPoint;

public class PointService {
	
	private PointDao pointDao = new PointDao();

	public List<PersonalPoint> selectPersonalList(int start, int end) {
		Connection conn = getConnection();
		List<PersonalPoint> list = null;
		try {
		list = pointDao.selectPersonalList(conn, start, end);
		close(conn);
		} catch(Exception e) {
			throw e;
		} finally {		
		}
		return list;
	}

	public int selectPersonalPCount() {
		Connection conn = getConnection();
		int totalContents = pointDao.selectPersonalPCount(conn);
		close(conn);
		return totalContents;
	}

	public String selectOne() {
		Connection conn = getConnection();
		String one = pointDao.selectOne(conn);
		close(conn);
		return one;
	}

	public String selectTwo() {
		Connection conn = getConnection();
		String two = pointDao.selectTwo(conn);
		close(conn);
		return two;
	}

	public String selectThree() {
		Connection conn = getConnection();
		String three = pointDao.selectThree(conn);
		close(conn);
		return three;
	}

	public int insertRankingBadge_personal(String user, int rank) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = pointDao.insertRankingBadge_personal(conn,user,rank);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public int insertRankingBadge_team(String user, int rank) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = pointDao.insertRankingBadge_team(conn,user,rank);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

}
