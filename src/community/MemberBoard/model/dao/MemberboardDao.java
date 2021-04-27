package community.MemberBoard.model.dao;

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

import community.MemberBoard.model.exception.MemberboardException;
import community.MemberBoard.model.vo.Challenge;
import community.MemberBoard.model.vo.Memberboard;
import community.MemberBoard.model.vo.MemberboardAttachment;
import community.MemberBoard.model.vo.MemberboardExt;

import static common.JDBCTemplate.close;

public class MemberboardDao {
	
	private Properties prop = new Properties();
	public MemberboardDao() {
		String fileName = "/sql/memberboard/board-query.properties";
		String absPath = MemberboardDao.class.getResource(fileName).getPath();
		try {
			prop.load(new FileReader(absPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertMemberboard(Connection conn, Memberboard memberboard) {
		int result = 0;
		PreparedStatement pstmt = null;
		// insert into a_search_team
		// (a_id, member_id, challenge_id, a_title, a_content, a_read_count, a_like, s_team_count)
		// values (seq_article_id.nextVal, ?, ?, ?, ?, 0, 0, ?)
		
		String sql = prop.getProperty("insertMemberboard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberboard.getMemberId()); 
			pstmt.setInt(2, memberboard.getChallengeId());
			pstmt.setString(3, memberboard.getaTitle());
			pstmt.setString(4, memberboard.getaContent());
			pstmt.setInt(5, memberboard.getsTeamCount());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberboardException("팀원찾기 게시글 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int selectLastAId(Connection conn) {
		int aId = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLastAId");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				aId = rset.getInt("a_id");
			}
		} catch (SQLException e) {
			throw new MemberboardException("팀원찾기 게시글 등록 번호 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return aId;
	}

	public int insertMemberboardAttachment(Connection conn, MemberboardAttachment memberboardAttachment) {
		System.out.println("memberboardAttachment : " + memberboardAttachment);
		int result = 0;
		PreparedStatement pstmt = null;
		// insertMemberboardAttachment = insert into s_team_attachment(attachment_id, a_id, original_filename, renamed_filename) values (seq_attachment_id.nextVal, ?, ?, ?)
		String sql = prop.getProperty("insertMemberboardAttachment");
		try {
			//PreparedStatment객체 생성, 미완성 쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberboardAttachment.getaId());
			pstmt.setString(2, memberboardAttachment.getOriginalFilename());
			pstmt.setString(3, memberboardAttachment.getRenamedFilename());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberboardException("게시물 첨부파일 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Memberboard> selectChallengeList(Connection conn) {
		List<Memberboard> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String  sql = prop.getProperty("selectChallengeList");
		
		try {
		// 3. PreparedStatement객체 생성(미완성쿼리)
		pstmt = conn.prepareStatement(sql);
		// 4. 쿼리문 실행
		rset = pstmt.executeQuery();
		// 4.1 ResultSet -> Java객체 옮겨담기
		list = new ArrayList<>();			
		// rset.next() 결과집합이 여러행일 때 다음행 있니? -> 행을 가리키는 포인터를 다음행으로 옮겨주는 역할
		while(rset.next()) {
			Memberboard memberboard = new Memberboard();
			Challenge challenge = new Challenge();
			challenge.setChallengeId(rset.getInt("challenge_id"));
			challenge.setChallengeTermType(rset.getString("challenge_term_type"));
			challenge.setChallengeLevel(rset.getInt("challenge_level"));
			challenge.setChallengeName(rset.getString("challenge_name"));
			challenge.setChallengeInfo(rset.getString("challenge_info"));
			challenge.setChallengePoint(rset.getInt("challenge_point"));
			challenge.setChallengeTerm(rset.getInt("challenge_term"));
			memberboard.setChallenge(challenge);
			// memberboard.setChallenge(challenge);
			list.add(memberboard);
		}
	} catch (SQLException e) {
		throw new MemberboardException("챌린지 조회 오류", e);
	} finally {
		// 5. 자원반납(생성역순 rset - pstmt) 
		close(rset);
		close(pstmt);
	}
	return list;
	}

	public String selectChallengeTitle(Connection conn, int challengeId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String challengeTitle = null;
		// select challenge_name from challenge where challenge_id = ?
		String query = prop.getProperty("selectChallengeTitle");
		
		try {
			// 3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(query);
			// 3.1. ?에 값대입 -> 쿼리문 완성작업
			pstmt.setInt(1, challengeId);
			// 4. 쿼리문 실행
			rset = pstmt.executeQuery();
			
			// 4.1 ResultSet -> Java객체 옮겨담기
			if(rset.next()){
				challengeTitle = rset.getString("challenge_name");
			}
		} catch (SQLException e) {
			throw new MemberboardException("챌린지 이름 조회 오류", e);
		} finally {
			// 5. 자원반납(생성역순 rset - pstmt) 
			close(rset);
			close(pstmt);
		}
		return challengeTitle;	
	}

	public Memberboard selectOneMemberboard(Connection conn, int no) {
		Memberboard memberboard = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOneMemberboard");
		
		try {
			// 3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(query);
			// 3.1. ?에 값대입 -> 쿼리문 완성작업
			pstmt.setInt(1, no);
			// 4. 쿼리문 실행
			rset = pstmt.executeQuery();
			
			// 4.1 ResultSet -> Java객체 옮겨담기
			if(rset.next()){
				memberboard = new Memberboard();
				memberboard.setaId(rset.getInt("a_id"));
				memberboard.setMemberId(rset.getString("member_id"));
				memberboard.setChallengeId(rset.getInt("challenge_id"));
				memberboard.setaTitle(rset.getString("a_title"));
				memberboard.setaContent(rset.getString("a_content"));
				memberboard.setaRegDate(rset.getDate("a_reg_date"));
				memberboard.setaReadCount(rset.getInt("a_read_count"));
				memberboard.setaLike(rset.getInt("a_like"));
				memberboard.setsTeamCount(rset.getInt("s_team_count"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemberboardException("팀원찾기 게시글 조회 오류", e);
		} finally {
			// 5. 자원반납(생성역순 rset - pstmt) 
			close(rset);
			close(pstmt);
		}
		return memberboard;	
	}

	public MemberboardAttachment selectOneMemberboardAttachment(Connection conn, int no) {
		MemberboardAttachment memberboardAttach = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// select * from s_team_attachment where a_id = ?
		String query = prop.getProperty("selectOneMemberboardAttachment");
		try {
			// 3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(query);
			// 3.1. ?에 값대입 -> 쿼리문 완성작업
			pstmt.setInt(1, no);
			// 4. 쿼리문 실행
			rset = pstmt.executeQuery();
			// 4.1 ResultSet -> Java객체 옮겨담기
			if(rset.next()){
				memberboardAttach = new MemberboardAttachment();
				memberboardAttach.setAttachmentId(rset.getInt("attachment_id"));
				memberboardAttach.setaId(rset.getInt("a_id"));
				memberboardAttach.setOriginalFilename(rset.getString("original_filename"));
				memberboardAttach.setRenamedFilename(rset.getString("renamed_filename"));
				memberboardAttach.setAttachmentStatus("Y".equals(rset.getString("attachment_status")) ?  true : false);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			// catch절에서 예외를 출력하고 끝내버리면 컨트롤러에서는 예외가 발생한지 모르니까 예외를 던져줌
			// Dao -> Service -> Controller로 예외 전달
			// checked 예외는 throws exception을 해야하니까,
			// 간결하게 BoardException을 만들어 애초에 발생한 예외를 함께 던진 것
			throw new MemberboardException("팀원찾기 첨부파일 조회 오류", e);
		} finally {
			// 5. 자원반납(생성역순 rset - pstmt) 
			close(rset);
			close(pstmt);
		}
		return memberboardAttach;	
	}

	public Challenge selectOneChallenge(Connection conn, int challengeId) {
			Challenge challenge = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String query = prop.getProperty("selectOneChallenge");
			
			try {
				// 3. PreparedStatement객체 생성(미완성쿼리)
				pstmt = conn.prepareStatement(query);
				// 3.1. ?에 값대입 -> 쿼리문 완성작업
				pstmt.setInt(1, challengeId);
				// 4. 쿼리문 실행
				rset = pstmt.executeQuery();

				// 4.1 ResultSet -> Java객체 옮겨담기
				if(rset.next()){
					challenge = new Challenge();
					challenge.setChallengeId(rset.getInt("challenge_id"));
					challenge.setChallengeTermType(rset.getString("challenge_term_type"));
					challenge.setChallengeLevel(rset.getInt("challenge_level"));
					challenge.setChallengeName(rset.getString("challenge_name"));
					challenge.setChallengeInfo(rset.getString("challenge_info"));
					challenge.setChallengePoint(rset.getInt("challenge_point"));
					challenge.setChallengeTerm(rset.getInt("challenge_term"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new MemberboardException("팀원찾기 게시글 조회 오류", e);
			} finally {
				// 5. 자원반납(생성역순 rset - pstmt) 
				close(rset);
				close(pstmt);
			}
			return challenge;	
		}

	public int updateMemberboard(Connection conn, Memberboard memberboard) {
		int result = 0;
		PreparedStatement pstmt = null;
		// update a_search_team set challenge_id = ?, a_title = ?, a_content = ?, s_team_count = ? where a_id = ?
		String sql = prop.getProperty("updateMemberboard");
		try {
			//PreparedStatment객체 생성, 미완성 쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberboard.getChallengeId());
			pstmt.setString(2, memberboard.getaTitle());
			pstmt.setString(3, memberboard.getaContent());
			pstmt.setInt(4, memberboard.getsTeamCount());
			pstmt.setInt(5, memberboard.getaId());
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

	public int deleteMemberboardAttachment(Connection conn, String attachId) {
		int result = 0;
		PreparedStatement pstmt = null;
		// update s_team_attachment set attachment_status = 'N' where attachment_id = ?
		String sql = prop.getProperty("deleteMemberboardAttachment");
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

	public int deleteMemberboard(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		// delete from a_search_team where a_id = ?
		String query = prop.getProperty("deleteMemberboard");
		try {
			//PreparedStatment객체 생성, 미완성 쿼리 값대입
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberboardException("팀원찾기 게시글 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Memberboard> selectMemberboardList(Connection conn, int start, int end) {
		List<Memberboard> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// select * from ( select row_number() over(order by b.a_id desc) rnum, b.*,  a.a_id attachment_id, a.original_filename, a.renamed_filename, a.attachment_status from a_search_team b left join s_team_attachment a on b.a_id = a.a_id and a.attachment_status = 'Y') b where rnum between ? and ?
		String  sql = prop.getProperty("selectMemberboardList");
		
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
			MemberboardExt memberboard = new MemberboardExt();
			// Memberboard memberboard = new Memberboard();
			memberboard.setaId(rset.getInt("a_id"));
			memberboard.setMemberId(rset.getString("member_id"));
			memberboard.setChallengeId(rset.getInt("challenge_id"));
			memberboard.setaTitle(rset.getString("a_title"));
			memberboard.setaContent(rset.getString("a_content"));
			memberboard.setaRegDate(rset.getDate("a_reg_date"));
			memberboard.setaReadCount(rset.getInt("a_read_count"));
			memberboard.setaLike(rset.getInt("a_like"));
			memberboard.setsTeamCount(rset.getInt("s_team_count"));
			memberboard.setRequestTeamCnt(rset.getInt("request_team_cnt"));
			
			MemberboardAttachment memberboardAttach = new MemberboardAttachment();
			memberboardAttach.setAttachmentId(rset.getInt("attachment_id"));
			memberboardAttach.setaId(rset.getInt("a_id"));
			memberboardAttach.setOriginalFilename(rset.getString("original_filename"));
			memberboardAttach.setRenamedFilename(rset.getString("renamed_filename"));
			memberboardAttach.setAttachmentStatus("Y".equals(rset.getString("attachment_status")) ?  true : false);
				memberboard.setMemberboardAttachment(memberboardAttach);
			list.add(memberboard);
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

	public int selectMemberboardCount(Connection conn) {
		int totalContents = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String  sql = prop.getProperty("selectMemberboardCount");
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

	public int updateAReadCount(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		// update a_search_team set a_read_count = a_read_count +1 where a_id = ?
		String sql = prop.getProperty("updateAReadCount");
		try {
			//PreparedStatment객체 생성, 미완성 쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberboardException("팀원찾기 게시글 조회수 업데이트 오류", e);
		} finally {
			close(pstmt);
			
		}
		return result;
	}

	public int updateMemberboardLike(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		// update a_search_team set a_like = a_like +1 where a_id = ?
		String sql = prop.getProperty("updateMemberboardLike");
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

	public int updateMemberboardLikeCancel(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		// update a_search_team set a_like = a_like -1 where a_id = ?
		String sql = prop.getProperty("updateMemberboardLikeCancel");
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

	public int selectSTeamCnt(Connection conn, int aId) {
		int sTeamCnt = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// select s_team_count from a_search_team where a_id = ?
		String  sql = prop.getProperty("selectSTeamCnt");
		try {
			// 3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aId);
			// 4. 쿼리문 실행
			rset = pstmt.executeQuery();
			// 4.1 ResultSet -> Java객체 옮겨담기
			if(rset.next()) {
				sTeamCnt = rset.getInt("s_team_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. 자원반납(생성역순 rset - pstmt) 
			close(rset);
			close(pstmt);
		}
		System.out.println("총원 : " + sTeamCnt);
		return sTeamCnt;
	}
}
