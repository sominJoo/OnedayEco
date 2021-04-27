package community.TeamMemberBoard.model.dao;

import static common.JDBCTemplate.close;

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

import community.MemberBoard.model.dao.MemberboardDao;
import community.MemberBoard.model.exception.MemberboardException;
import community.MemberBoard.model.vo.Memberboard;
import community.MemberBoard.model.vo.MemberboardAttachment;
import community.TeamMemberBoard.model.vo.ATeamAttachment;
import community.TeamMemberBoard.model.vo.TeamComment;
import community.TeamMemberBoard.model.vo.TeamMemberboard;

public class TeamMemberboardDao {
	
	private Properties prop = new Properties();
	public TeamMemberboardDao() {
		String fileName = "/sql/teamMemberboard/teamMemberboard-query.properties";
		String absPath = MemberboardDao.class.getResource(fileName).getPath();
		try {
			prop.load(new FileReader(absPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<TeamMemberboard> selectTeamMemberboardList(Connection conn, int start, int end, int aId) {
		List<TeamMemberboard> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// select * from (select row_number() over(order by b.a_id desc) rnum, b.*,  a.attachment_id, a.original_filename, a.renamed_filename, a.attachment_status from a_team b left join a_team_attachment a on b.team_a_id = a.team_a_id and a.attachment_status = 'Y' where a_id=?) b where rnum between ? and ?
		String  sql = prop.getProperty("selectTeamMemberboardList");
		
		try {
		// 3. PreparedStatement객체 생성(미완성쿼리)
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, aId);
		pstmt.setInt(2, start);
		pstmt.setInt(3, end);
		// 4. 쿼리문 실행
		rset = pstmt.executeQuery();
		// 4.1 ResultSet -> Java객체 옮겨담기
		list = new ArrayList<>();			
		// rset.next() 결과집합이 여러행일 때 다음행 있니? -> 행을 가리키는 포인터를 다음행으로 옮겨주는 역할
		while(rset.next()) {
			TeamMemberboard teamMemberboard = new TeamMemberboard();
			teamMemberboard.setTeamAId(rset.getInt("team_a_id"));
			teamMemberboard.setMemberId(rset.getString("member_id"));
			teamMemberboard.setaId(rset.getInt("a_id"));
			teamMemberboard.setaTitle(rset.getString("a_title"));
			teamMemberboard.setaContent(rset.getString("a_content"));
			teamMemberboard.setaRegDate(rset.getDate("a_reg_date"));
			teamMemberboard.setaReadCount(rset.getInt("a_read_count"));
			teamMemberboard.setaLike(rset.getInt("a_like"));
			
			if(rset.getInt("attachment_id") != 0) {
				ATeamAttachment aTeamAttachment = new ATeamAttachment();
				aTeamAttachment.setAttachmentId(rset.getInt("attachment_id"));
				aTeamAttachment.setTeamAId(rset.getInt("team_a_id"));
				aTeamAttachment.setOriginalFilename(rset.getString("original_filename"));
				aTeamAttachment.setRenamedFilename(rset.getString("renamed_filename"));
				aTeamAttachment.setAttachmentStatus("Y".equals(rset.getString("attachment_status")) ?  true : false);
				teamMemberboard.setaTeamAttachment(aTeamAttachment);
			}
			list.add(teamMemberboard);
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

	public int selectTeamMemberboardCount(Connection conn) {
		int totalContents = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// select count(*) cnt from a_team
		String  sql = prop.getProperty("selectTeamMemberboardCount");
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

	public int insertTeamMemberboard(Connection conn, TeamMemberboard teamMemberboard) {
		int result = 0;
		PreparedStatement pstmt = null;
		// insert into a_team(team_a_id, member_id, a_id, a_title, a_content, a_read_count, a_like)
		// values (seq_a_team_id.nextVal, ?, ?, ?, ?, 0, 0)
		String sql = prop.getProperty("insertTeamMemberboard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teamMemberboard.getMemberId()); 
			pstmt.setInt(2, teamMemberboard.getaId());
			pstmt.setString(3, teamMemberboard.getaTitle());
			pstmt.setString(4, teamMemberboard.getaContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberboardException("팀게시판 게시글 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int selectLastTeamAId(Connection conn) {
		int aId = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// select seq_a_team_id.currval team_a_id from dual
		String sql = prop.getProperty("selectLastTeamAId");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				aId = rset.getInt("team_a_id");
			}
		} catch (SQLException e) {
			throw new MemberboardException("팀게시판 게시글 등록 번호 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return aId;
	}

	public int insertTeamMemberboardAttachment(Connection conn, ATeamAttachment aTeamAttachment) {
		int result = 0;
		PreparedStatement pstmt = null;
		// insert into a_team_attachment(attachment_id, team_a_id, original_filename, renamed_filename) 
		// values (seq_team_attachment_id.nextVal, ?, ?, ?)
		String sql = prop.getProperty("insertTeamMemberboardAttachment");
		try {
			//PreparedStatment객체 생성, 미완성 쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aTeamAttachment.getTeamAId());
			pstmt.setString(2, aTeamAttachment.getOriginalFilename());
			pstmt.setString(3, aTeamAttachment.getRenamedFilename());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberboardException("게시물 첨부파일 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int selectAId(Connection conn, String memberId) {
		int aId = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// select * from request_team where member_id = ?
		String  sql = prop.getProperty("selectAId");
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

	public TeamMemberboard selectOneTeamMemberboard(Connection conn, int no) {
		TeamMemberboard teamMemberboard = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// select * from a_team where team_a_id = ?
		String query = prop.getProperty("selectOneTeamMemberboard");
		
		try {
			// 3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(query);
			// 3.1. ?에 값대입 -> 쿼리문 완성작업
			pstmt.setInt(1, no);
			// 4. 쿼리문 실행
			rset = pstmt.executeQuery();
			
			// 4.1 ResultSet -> Java객체 옮겨담기
			if(rset.next()){
				teamMemberboard = new TeamMemberboard();
				teamMemberboard.setTeamAId(rset.getInt("team_a_id"));
				teamMemberboard.setMemberId(rset.getString("member_id"));
				teamMemberboard.setaId(rset.getInt("a_id"));
				teamMemberboard.setaTitle(rset.getString("a_title"));
				teamMemberboard.setaContent(rset.getString("a_content"));
				teamMemberboard.setaRegDate(rset.getDate("a_reg_date"));
				teamMemberboard.setaReadCount(rset.getInt("a_read_count"));
				teamMemberboard.setaLike(rset.getInt("a_like"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemberboardException("팀원찾기 게시글 조회 오류", e);
		} finally {
			// 5. 자원반납(생성역순 rset - pstmt) 
			close(rset);
			close(pstmt);
		}
		return teamMemberboard;	
	}

	public ATeamAttachment selectOneTeamAttachment(Connection conn, int no) {
		ATeamAttachment aTeamAttachment = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// select * from a_team_attachment where team_a_id = ?
		String query = prop.getProperty("selectOneTeamAttachment");
		try {
			// 3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(query);
			// 3.1. ?에 값대입 -> 쿼리문 완성작업
			pstmt.setInt(1, no);
			// 4. 쿼리문 실행
			rset = pstmt.executeQuery();
			// 4.1 ResultSet -> Java객체 옮겨담기
			if(rset.next()){
				aTeamAttachment = new ATeamAttachment();
				aTeamAttachment.setAttachmentId(rset.getInt("attachment_id"));
				aTeamAttachment.setTeamAId(rset.getInt("team_a_id"));
				aTeamAttachment.setOriginalFilename(rset.getString("original_filename"));
				aTeamAttachment.setRenamedFilename(rset.getString("renamed_filename"));
				aTeamAttachment.setAttachmentStatus("Y".equals(rset.getString("attachment_status")) ?  true : false);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			// catch절에서 예외를 출력하고 끝내버리면 컨트롤러에서는 예외가 발생한지 모르니까 예외를 던져줌
			// Dao -> Service -> Controller로 예외 전달
			// checked 예외는 throws exception을 해야하니까,
			// 간결하게 BoardException을 만들어 애초에 발생한 예외를 함께 던진 것
			throw new MemberboardException("팀게시판 첨부파일 조회 오류", e);
		} finally {
			// 5. 자원반납(생성역순 rset - pstmt) 
			close(rset);
			close(pstmt);
		}
		return aTeamAttachment;
	}

	public int updateTeamMemberboardLike(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		// update a_team set a_like = a_like +1 where team_a_id = ?
		String sql = prop.getProperty("updateTeamMemberboardLike");
		try {
			//PreparedStatment객체 생성, 미완성 쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberboardException("좋아요 증가 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateTeamMemberboardLikeCancel(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		// update a_team set a_like = a_like -1 where team_a_id = ?
		String sql = prop.getProperty("updateTeamMemberboardLikeCancel");
		try {
			//PreparedStatment객체 생성, 미완성 쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberboardException("좋아요 취소 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateTeamAReadCount(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		// update a_team set a_read_count = a_read_count +1 where team_a_id = ?
		String sql = prop.getProperty("updateTeamAReadCount");
		try {
			//PreparedStatment객체 생성, 미완성 쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberboardException("팀게시판 게시글 조회수 업데이트 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteTeamMemberboardAttachment(Connection conn, String attachId) {
		int result = 0;
		PreparedStatement pstmt = null;
		// update a_team_attachment set attachment_status = 'N' where attachment_id = ?
		String sql = prop.getProperty("deleteTeamMemberboardAttachment");
		try {
			//PreparedStatment객체 생성, 미완성 쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, attachId); 
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberboardException("첨부파일 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateTeamMemberboard(Connection conn, TeamMemberboard teamMemberboard) {
		int result = 0;
		PreparedStatement pstmt = null;
		// update a_team set a_title = ?, a_content = ? where team_a_id = ?
		String sql = prop.getProperty("updateTeamMemberboard");
		try {
			//PreparedStatment객체 생성, 미완성 쿼리 값대입
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, teamMemberboard.getaTitle());
			pstmt.setString(2, teamMemberboard.getaContent());
			pstmt.setInt(3, teamMemberboard.getTeamAId());
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberboardException("팀원찾기 게시글 수정 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteTeamMemberboard(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		// delete from a_team where team_a_id = ?
		String query = prop.getProperty("deleteTeamMemberboard");
		try {
			//PreparedStatment객체 생성, 미완성 쿼리 값대입
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberboardException("팀게시판 게시글 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertTeamMemberBoardComment(Connection conn, TeamComment tc) {
		int result = 0;
		PreparedStatement pstmt = null;
		// insert into team_comment(comment_id, team_a_id, member_id, comment_content, comment_level, comment_ref)
		// values(seq_a_comment_id.nextval, ?, ?, ?, ?, ?);
		String sql = prop.getProperty("insertTeamMemberBoardComment");
		try {
			//PreparedStatment객체 생성, 미완성 쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tc.getTeamAId());
			pstmt.setString(2, tc.getMemberId());
			pstmt.setString(3, tc.getCommentContent());
			pstmt.setInt(4, tc.getCommentLevel()); 
			pstmt.setObject(5, tc.getCommentRef() == 0 ? null : tc.getCommentRef());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberboardException("댓글등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<TeamComment> selectTeamCommentList(Connection conn, int no) {
		List<TeamComment> commentList = null; 
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// select tc.* from team_comment tc where team_a_id = ?
		// start with comment_level = 1 connect by prior comment_id = comment_ref order siblings by comment_reg_date asc;
		String  sql = prop.getProperty("selectTeamCommentList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no); // 게시글번호로 조회
			rset = pstmt.executeQuery();
			commentList = new ArrayList<>();			
			while(rset.next()) {
				// 한행 한행 boardComment객체로 바꿔서 commentList에 담기
				TeamComment tc = new TeamComment();
				tc.setCommentId(rset.getInt("comment_id"));
				tc.setTeamAId(rset.getInt("team_a_id"));
				tc.setMemberId(rset.getString("member_id"));
				tc.setCommentContent(rset.getString("comment_content"));
				tc.setCommentRegDate(rset.getDate("comment_reg_date"));
				tc.setCommentLevel(rset.getInt("comment_level"));
				tc.setCommentRef(rset.getInt("comment_ref"));
				commentList.add(tc); // list에 boardComment객체 추가
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(rset);
		close(pstmt);
	}
	return commentList;
	}

	public int deleteTeamMemberboardComment(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		// delete from team_comment where comment_id = ?
		String sql = prop.getProperty("deleteTeamMemberboardComment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberboardException("댓글 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
}
