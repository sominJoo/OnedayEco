package onedayeco.confirm.model.dao;
import static common.JDBCTemplate.*;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import onedayeco.confirm.model.exception.ConfirmException;
import onedayeco.confirm.model.service.ConfirmService;
import onedayeco.confirm.model.vo.Confirm;
import onedayeco.confirm.model.vo.ConfirmAttachment;
import onedayeco.confirm.model.vo.ConfirmComment;

public class ConfirmDao {

	private Properties prop = new Properties();
	
	public ConfirmDao() {
		
		
		String fileName ="/sql/article/confirm-query.properties";
		System.out.println(fileName);
		String absPath = ConfirmDao.class.getResource(fileName).getPath();
		System.out.println(absPath);
		try {
			prop.load(new FileReader(absPath));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public List<Confirm> selectList(Connection conn, int start, int end) {
		List<Confirm> list = new ArrayList<Confirm>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Confirm confirm= new Confirm();
				confirm.setA_id(rset.getInt("a_id"));
				confirm.setUser_id(rset.getString("member_id"));
				confirm.setConfirm_type(rset.getString("confirm_type"));
				confirm.setA_title(rset.getString("a_title"));
				confirm.setA_content(rset.getString("a_content"));
				confirm.setA_reg_date(rset.getDate("a_reg_date"));
				confirm.setA_read_count(rset.getInt("a_read_count"));
				confirm.setA_like(rset.getInt("a_like"));
				confirm.setPoint_check(rset.getString("point_check"));
				
				System.out.println(rset.getInt("attachment_id"));
				//첨부파일이 있는 경우
				if(rset.getInt("attachment_id") != 0) {
					ConfirmAttachment attach = new ConfirmAttachment();
					attach.setAttachment_id(rset.getInt("attachment_id"));
					attach.setA_id(rset.getInt("a_id"));
					attach.setOriginal_filename(rset.getString("original_filename"));
					attach.setRenamed_filename(rset.getString("renamed_filename"));
					attach.setAttachment_status("Y".equals(rset.getString("attachment_status"))? true : false);
					confirm.setAttach(attach);
					
				}
				list.add(confirm);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			new ConfirmException("인증게시물 불러오기 오류",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int selectBoardCount(Connection conn) {
		int totalContents = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
				
		String query = prop.getProperty("selectBoardCount");
		
		try {
			pstmt=conn.prepareStatement(query);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				totalContents = rset.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			new ConfirmException("인증게시물페이징 불러오기 오류",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContents;
	}
	
	public int selectDTTBoardCount(Connection conn,String type) {
		int totalContents = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectDTTBoardCount");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,type);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				totalContents = rset.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			new ConfirmException("인증게시물페이징 불러오기 오류",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContents;
	}
	public int insertBoard(Connection conn, Confirm confirm) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertBoard");
		System.out.println("insertSQL : "+sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, confirm.getUser_id());
			pstmt.setString(2, confirm.getChallengeName());
			pstmt.setString(3, confirm.getConfirm_type());
			pstmt.setString(4, confirm.getA_title());
			pstmt.setString(5, confirm.getA_content());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ConfirmException("인증게시물  등록 오류",e);
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectLastBoardNo(Connection conn) {
		int boardNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
				
		String query = prop.getProperty("selectLastBoardNo");
		
		try {
			pstmt=conn.prepareStatement(query);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				boardNo = rset.getInt("a_id");
			}
			
		} catch (SQLException e) {
			throw new ConfirmException("인증게시물 등록 번호 조회 오류",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return boardNo;
	}

	public int insertAttachment(Connection conn, ConfirmAttachment attach) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertAttachment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attach.getA_id());
			pstmt.setString(2, attach.getOriginal_filename());
			pstmt.setString(3, attach.getRenamed_filename());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			throw new ConfirmException("인증게시물 첨부파일 등록 오류",e); //Runtime예외를 상속한 BoardException
		}finally {
			close(pstmt);
		}
		return result;
	}

	public Confirm selectOne(Connection conn, int no) {
		PreparedStatement pstmt = null;
		Confirm confirm= null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOne");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				confirm = new Confirm();
				confirm.setA_id(rset.getInt("a_id"));
				confirm.setA_title(rset.getString("a_title"));
				confirm.setUser_id(rset.getString("member_id"));
				confirm.setA_content(rset.getString("a_content"));
				confirm.setA_reg_date(rset.getDate("a_reg_date"));
				confirm.setA_read_count(rset.getInt("a_read_count"));
				confirm.setChallengeName(rset.getString("challenge_name"));
				confirm.setConfirm_type(rset.getString("confirm_type"));
				confirm.setPoint_check(rset.getString("point_check"));
				confirm.setA_like(rset.getInt("a_like"));				
			}
						//checked예외는 throws exception을 지정해줘야하니까 간결하게 runtime예외를 만들어서 던짐
		}catch(SQLException e) {
			throw new ConfirmException("인증게시글 조회 오류",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return confirm;
	}

	public ConfirmAttachment selectOneAttachment(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ConfirmAttachment attachment= null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOneAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				attachment = new ConfirmAttachment();
				attachment.setAttachment_id(rset.getInt("attachment_id"));
				attachment.setA_id(rset.getInt("a_id"));
				attachment.setOriginal_filename(rset.getString("original_filename"));
				System.out.println("renamed_filename"+rset.getString("original_filename"));
				attachment.setRenamed_filename(rset.getString("renamed_filename"));
				attachment.setAttachment_status("Y".equals(rset.getString("attachment_status"))? true : false);
			}
		
			
		}catch(SQLException e) {
//			e.printStackTrace(); //예외를 처리한다음 입을 싹 닦아서 controller가 알지 못함
			 throw new ConfirmException("인증게시판 첨부파일 조회 오류",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return attachment;
	}

	public List<ConfirmComment> selectBoardCommentList(Connection conn, int article_id) {
		List<ConfirmComment> commentList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBoardCommentList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, article_id);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ConfirmComment ac = new ConfirmComment();
				ac.setComment_id(rset.getInt("comment_id"));
				ac.setComment_level(rset.getInt("comment_level"));
				ac.setUser_id(rset.getString("member_id"));
				ac.setComment_content(rset.getString("comment_content"));
				ac.setA_id(rset.getInt("a_id"));
				ac.setComment_ref(rset.getInt("comment_ref"));
				ac.setComment_reg_date(rset.getDate("comment_reg_date"));
				commentList.add(ac);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ConfirmException("인증게시판 댓글 목록 오류",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return commentList;
	}

	public int insertBoardComment(Connection conn, ConfirmComment cc) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertBoardComment");
		
		try {
			//(comment_id, member_id, a_id, comment_content, comment_level,comment_ref) 
			//values(seq_a_comment_id.nextval,?,?,?,default,?,?)
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,cc.getUser_id());
			pstmt.setInt(2,cc.getA_id());
			pstmt.setString(3,cc.getComment_content());
//			pstmt.setDate(4,ac.getComment_reg_date());
			pstmt.setInt(4,cc.getComment_level());
			System.out.println(1);
//			pstmt.setInt(5,bc.getCommentRef() == 0? null:bc.getCommentRef()); 
			pstmt.setObject(5, cc.getComment_ref() == 0? null:cc.getComment_ref());
			//: integrity constraint (WEB.FK_BOARD_COMMENT_REF) violated - parent key not found
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ConfirmException("인증게시판 댓글 등록 오류",e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteBoard(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteBoard");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ConfirmException("인증게시판 게시물 삭제 오류",e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int updateBoard(Connection conn, Confirm confirm) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateBoard");
		
		try {
//			System.out.println("업데이트 보드");
			pstmt=conn.prepareStatement(query);
//			System.out.println("업데이트 보드 쿼리");
			pstmt.setString(1, confirm.getConfirm_type());
			pstmt.setString(2, confirm.getA_title());
//			System.out.println("업데이트 보드 제목");
			pstmt.setString(3, confirm.getA_content());
//			System.out.println("업데이트 보드 컨텐츠");
			pstmt.setInt(4, confirm.getA_id());
//			System.out.println("업데이트 보드 아티클 번호");
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ConfirmException("인증게시판 게시물 수정 오류",e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int boardCommentDelete(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("boardCommentDelete");
		
		System.out.println("DAO");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ConfirmException("인증게시판 댓글 삭제 오류",e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteAttachment(Connection conn, String attachNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteAttachment");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,attachNo );
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ConfirmException("인증게시판 첨부파일 삭제 오류",e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int insertPoint(Connection conn, Confirm confirm, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertPoint");
		
		try {

			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,confirm.getA_id());
			pstmt.setString(2,memberId);
			pstmt.setInt(3,confirm.getA_id());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ConfirmException("포인트 지급 오류",e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int updatePointCheck(Connection conn, int boardNo, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updatePointCheck");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, ConfirmService.POINT_CHECK_Y);
			pstmt.setInt(2, boardNo);
			pstmt.setString(3, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ConfirmException("인증게시판 포인트 체크 수정 오류",e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int insertTeamPoint(Connection conn, String user) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertTeamPoint");
		
		try {

			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,user);
			pstmt.setString(2,user);
			pstmt.setString(3,user);
			pstmt.setString(4,user);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ConfirmException("포인트 지급 오류",e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	
	
	//뱃지 지급
	public int insertBadge(Connection conn, String user, String challengeName) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query ="insert into badge values (seq_badge_id.nextval, (select challenge_id from challenge where challenge_name=?), ? , (select badge_img_id from badge_image where challenge_id=(select challenge_id from challenge where challenge_name=?)), sysdate)";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,user);
			pstmt.setString(2,user);
			pstmt.setString(3,challengeName);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ConfirmException("뱃지 지급 오류",e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteChallenge(Connection conn, String user, String challengeName) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteChallenge");		
		try {

			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,user);
			pstmt.setString(2,challengeName);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ConfirmException("챌린지 조인 삭제 오류",e);
		}finally {
			close(pstmt);
		}
		return result;
	}



}

