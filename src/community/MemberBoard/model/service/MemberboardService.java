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
		// 조회된 board에 attach 세팅
		memberboard.setMemberboardAttachment(memberboardattach);
		close(conn);
		return memberboard;
	}
}
