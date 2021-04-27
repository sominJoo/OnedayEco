package community.MemberBoard.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;
import java.sql.Connection;
import java.util.List;

import community.MemberBoard.model.dao.MemberboardDao;
import community.MemberBoard.model.vo.Challenge;
import community.MemberBoard.model.vo.Memberboard;
import community.MemberBoard.model.vo.MemberboardAttachment;

public class MemberboardService {
	private MemberboardDao memberboardDao = new MemberboardDao();

	public int insertMemberboard(Memberboard memberboard) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberboardDao.insertMemberboard(conn, memberboard);
			// 아직 여기에는 no가 없음. seq_board_no.nextVal해야만 발급되는 번호가 db에 들어감
			// board가 insert될 때 생성된 board고유번호가 attachment의 boardno에 세팅되어야만, attachment테이블에 추가 가능
			// -> 생성된 board_no 가져오기
			int aId = memberboardDao.selectLastAId(conn);
			//redirect location설정
			memberboard.setaId(aId);
			System.out.println("boardNo@service = " + aId);
			
			if(memberboard.getMemberboardAttachment() != null) {
				// *** attach객체에 참조할 boardNo 세팅 ***
				memberboard.getMemberboardAttachment().setaId(aId);
				// attachment객체를 같이 보내기
				result = memberboardDao.insertMemberboardAttachment(conn, memberboard.getMemberboardAttachment());
				// boardNo는 fk를 걸어둠 - board의 no를 참조해야 함
			}
			// 예외가 발생하지 않으면 무조건 commit
			commit(conn);
		} catch(Exception e) {
			// e.printStackTrace();
			// 예외가 발생하면 selectLastBoardNo(), insertAttachment()가 같이 롤백됨
			// insertBoard()마저 취소시킴
			rollback(conn);
			// result = 0;
			throw e;
		} finally {
			close(conn);			
		}
		return result;
	}

	public List<Memberboard> selectChallengeList() {
		Connection conn = getConnection();
		List<Memberboard> list = null;
		try {
		list = memberboardDao.selectChallengeList(conn);
		close(conn);
		} catch(Exception e) {
			throw e;
		} finally {		
		}
		return list;
	}

	public String selectChallengeTitle(int challengeId) {
		Connection conn = getConnection();
		String challengeTitle = memberboardDao.selectChallengeTitle(conn, challengeId);
		close(conn);
		return challengeTitle;
	}

	public Memberboard selectOneMemberboard(int no) {
		Connection conn = getConnection();
		Memberboard memberboard = memberboardDao.selectOneMemberboard(conn, no);
		MemberboardAttachment memberboardattach = memberboardDao.selectOneMemberboardAttachment(conn, no);
		int challengeId = memberboard.getChallengeId();
		Challenge challenge = memberboardDao.selectOneChallenge(conn, challengeId);
		memberboard.setMemberboardAttachment(memberboardattach);
		memberboard.setChallenge(challenge);
		close(conn);
		return memberboard;
	}

	public MemberboardAttachment selectOneMemberboardAttachment(int no) {
		Connection conn = getConnection();
		Memberboard memberboard = memberboardDao.selectOneMemberboard(conn, no);
		MemberboardAttachment memberboardattach = memberboardDao.selectOneMemberboardAttachment(conn, no);
		memberboard.setMemberboardAttachment(memberboardattach);
		close(conn);
		return memberboardattach;
	}

	public int updateMemberboard(Memberboard memberboard) {
		Connection conn = getConnection();
		int result = 0;
		try {
			// 1. board update
			// 원래 있던 것들을 수정하는거니까 update
			result = memberboardDao.updateMemberboard(conn, memberboard);
			// 2. attachment insert
			// 첨부파일이 애초에 없었으니 insert
			if(memberboard.getMemberboardAttachment() != null)
				result = memberboardDao.insertMemberboardAttachment(conn, memberboard.getMemberboardAttachment());
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public int deleteMemberboardAttachment(String attachId) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberboardDao.deleteMemberboardAttachment(conn, attachId);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public int deleteMemberboard(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberboardDao.deleteMemberboard(conn, no);
			// 존재하지 않는 no가 아닌 다른 번호가 들어온다면? 
			// 존재하지 않는 글을 삭제한다면, result는 0
			// delete from board where no = 300 -> 0개 행이 삭제되었습니다. -> 0 리턴
			// result == 0 -> 예외 던지기
			if(result == 0) {
				throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다. : " + no);
			}
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
		close(conn);
		}
		return result;
	}

	public List<Memberboard> selectMemberboardList(int start, int end) {
		Connection conn = getConnection();
		List<Memberboard> list = memberboardDao.selectMemberboardList(conn, start, end);
		close(conn);
		return list;
	}

	public int selectMemberboardCount() {
		Connection conn = getConnection();
		int totalContents = memberboardDao.selectMemberboardCount(conn);
		close(conn);
		return totalContents;
	}

	public int updateAReadCount(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			// 1. board update
			// 원래 있던 것들을 수정하는거니까 update
			result = memberboardDao.updateAReadCount(conn, no);
			// 2. attachment insert
			// 첨부파일이 애초에 없었으니 insert
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public int updateMemberboardLike(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberboardDao.updateMemberboardLike(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public int updateMemberboardLikeCancel(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberboardDao.updateMemberboardLikeCancel(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public int selectSTeamCnt(int aId) {
		Connection conn = getConnection();
		int sTeamCnt = memberboardDao.selectSTeamCnt(conn, aId);
		close(conn);
		return sTeamCnt;
	}
}
