package challenge.model.servcie;

import static common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.List;

import challenge.model.dao.ChallengeDao;
import challenge.model.vo.Challenge;

public class ChallengeServcie {
	
	private ChallengeDao challengeDao = new ChallengeDao();

	public List<Challenge> selectList(String type, int start, int end) {
		Connection conn = getConnection();
		//select try - catch ?
		List<Challenge> list = challengeDao.selectChallenge(type, conn, start, end);
		close(conn);
		return list;
	}

	public int selectChallengeCount(String type) {
		Connection conn = getConnection();
		int totalContents = challengeDao.selectChallengeCount(type, conn);
		close(conn);
		return totalContents;
		
	}

	public int deleteChallenge(int c_id) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = challengeDao.deleteChallenge(conn, c_id);
			//존재하지 않는 글을 삭제했다면 예외처리 해줘야함
			if(result == 0)
				throw new IllegalArgumentException("해당 챌린지가 존재하지 않습니다. : " + c_id);
			
			//문제가 없다면 commit
			commit(conn);
		} catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(conn);
		}
		return result;	
	}

	public int joinChallenge(String member_id, int c_id) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = challengeDao.joinChallenge(conn, member_id, c_id);
			//챌린지 참여 실패
			if(result == 0)
				throw new IllegalArgumentException("이미 참여중인 챌린지입니다. " + c_id);
			
			//문제가 없다면 commit
			commit(conn);
		} catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
		}
		return result;
	}

	public int insertChallenge(Challenge challenge) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = challengeDao.insertChallenge(conn, challenge);
			if(result > 0)
				commit(conn);
		} catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
			result = 0;
		} finally {
			close(conn);
		}
		return result;

	}

	public Challenge selectOne(int c_id) {
		Connection conn = getConnection();
		Challenge challenge = challengeDao.selectOne(conn, c_id);
		close(conn);
		return challenge;
	}

	public int modifyChallenge(Challenge challenge) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = challengeDao.modifyChallenge(conn, challenge);
			
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public List<Challenge> selectShortAll() {
		Connection conn = getConnection();
		List<Challenge> list = challengeDao.selectShortAll(conn);
		close(conn);
		return list;
	}
	
}
