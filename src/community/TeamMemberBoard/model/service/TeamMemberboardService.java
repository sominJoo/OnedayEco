package community.TeamMemberBoard.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;
import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpSession;

import community.MemberBoard.model.vo.Challenge;
import community.MemberBoard.model.vo.Memberboard;
import community.MemberBoard.model.vo.MemberboardAttachment;
import community.TeamMemberBoard.model.dao.TeamMemberboardDao;
import community.TeamMemberBoard.model.vo.ATeamAttachment;
import community.TeamMemberBoard.model.vo.TeamComment;
import community.TeamMemberBoard.model.vo.TeamMemberboard;

public class TeamMemberboardService {
	private TeamMemberboardDao teamMemberboardDao = new TeamMemberboardDao();
	
	public int insertTeamMemberboard(TeamMemberboard teamMemberboard) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = teamMemberboardDao.insertTeamMemberboard(conn, teamMemberboard);
			// 아직 여기에는 no가 없음. seq_board_no.nextVal해야만 발급되는 번호가 db에 들어감
			// board가 insert될 때 생성된 board고유번호가 attachment의 boardno에 세팅되어야만, attachment테이블에 추가 가능
			// -> 생성된 board_no 가져오기
			int teamAId = teamMemberboardDao.selectLastTeamAId(conn);
			//redirect location설정
			teamMemberboard.setTeamAId(teamAId);
			System.out.println("boardNo@service = " + teamAId);
			
			if(teamMemberboard.getaTeamAttachment() != null) {
				// *** attach객체에 참조할 boardNo 세팅 ***
				teamMemberboard.getaTeamAttachment().setTeamAId(teamAId);
				// attachment객체를 같이 보내기
				result = teamMemberboardDao.insertTeamMemberboardAttachment(conn, teamMemberboard.getaTeamAttachment());
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

	public List<TeamMemberboard> selectTeamMemberboardList(int start, int end, int aId) {
		Connection conn = getConnection();
		List<TeamMemberboard> list = teamMemberboardDao.selectTeamMemberboardList(conn, start, end, aId);
		close(conn);
		return list;
	}

	public int selectTeamMemberboardCount() {
		Connection conn = getConnection();
		int totalContents = teamMemberboardDao.selectTeamMemberboardCount(conn);
		close(conn);
		return totalContents;
	}

	public int selectAId(String memberId) {
		Connection conn = getConnection();
		int aId = teamMemberboardDao.selectAId(conn, memberId);
		close(conn);
		return aId;
	}

	public TeamMemberboard selectOneTeamMemberboard(int no) {
		Connection conn = getConnection();
		TeamMemberboard teamMemberboard = teamMemberboardDao.selectOneTeamMemberboard(conn, no);
		ATeamAttachment aTeamAttachment = teamMemberboardDao.selectOneTeamAttachment(conn, no);
		teamMemberboard.setaTeamAttachment(aTeamAttachment);
		close(conn);
		return teamMemberboard;
	}

	public int updateTeamMemberboardLike(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = teamMemberboardDao.updateTeamMemberboardLike(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}
	
	public int updateTeamMemberboardLikeCancel(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = teamMemberboardDao.updateTeamMemberboardLikeCancel(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public int updateTeamAReadCount(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			// 1. board update
			// 원래 있던 것들을 수정하는거니까 update
			result = teamMemberboardDao.updateTeamAReadCount(conn, no);
			// 2. attachment insert
			// 첨부파일이 애초에 없었으니 insert
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public int deleteTeamMemberboardAttachment(String attachId) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = teamMemberboardDao.deleteTeamMemberboardAttachment(conn, attachId);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public int updateTeamMemberboard(TeamMemberboard teamMemberboard) {
		Connection conn = getConnection();
		int result = 0;
		try {
			// 1. board update
			// 원래 있던 것들을 수정하는거니까 update
			result = teamMemberboardDao.updateTeamMemberboard(conn, teamMemberboard);
			// 2. attachment insert
			// 첨부파일이 애초에 없었으니 insert
			if(teamMemberboard.getaTeamAttachment() != null)
				result = teamMemberboardDao.insertTeamMemberboardAttachment(conn, teamMemberboard.getaTeamAttachment());
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public int deleteTeamMemberboard(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = teamMemberboardDao.deleteTeamMemberboard(conn, no);
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

	public int insertTeamMemberBoardComment(TeamComment tc) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = teamMemberboardDao.insertTeamMemberBoardComment(conn, tc);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; // controller가 예외처리를 결정할 수 있도록 넘김
		} finally {
		close(conn);
		}
		return result;
	}

	public List<TeamComment> selectTeamCommentList(int no) {
		Connection conn = getConnection();
		List<TeamComment> commentList = teamMemberboardDao.selectTeamCommentList(conn, no);
		close(conn);
		return commentList;
	}

	public int deleteTeamMemberboardComment(int no) {
		Connection conn = getConnection(); 
		int result = 0;
		try {
			result = teamMemberboardDao.deleteTeamMemberboardComment(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

}
