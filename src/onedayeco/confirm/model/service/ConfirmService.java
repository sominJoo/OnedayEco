package onedayeco.confirm.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import onedayeco.confirm.model.dao.ConfirmDao;
import onedayeco.confirm.model.vo.Confirm;
import onedayeco.confirm.model.vo.ConfirmAttachment;
import onedayeco.confirm.model.vo.ConfirmComment;

public class ConfirmService {
	
	private ConfirmDao confirmDao = new ConfirmDao();

	public static final String POINT_CHECK_Y = "Y";
	public static final String POINT_CHECK_N = "N";
	
	
	public List<Confirm> selectList(int start, int end) {
		Connection conn = getConnection();
		List<Confirm> list = confirmDao.selectList(conn,start,end);
		close(conn);
		return list;
	}

	public int selectBoardCount() {
		Connection conn = getConnection();
		int totalContents = confirmDao.selectBoardCount(conn);
		close(conn);
		return totalContents;
	}
	
	public int selectDTTBoardCount(String type) {
		Connection conn = getConnection();
		int totalContents = confirmDao.selectDTTBoardCount(conn,type);
		close(conn);
		return totalContents;
	}
	public int insertBoard(Confirm confirm) {
		Connection conn = getConnection();
			int result =0;
		try {
			result = confirmDao.insertBoard(conn,confirm);
			
			//생성된 article_id(없으면 테이블에 등록이 안됌 pk이기 때문에)를 가져오기
			int article_id = confirmDao.selectLastBoardNo(conn);
			System.out.println("boardNo@service = "+article_id);
			//아티클객체에는 원래 no가 없었는데 setting해줌
			//redirect location설정
			confirm.setA_id(article_id);
			
			if(confirm.getAttach() != null) {
				//참조할 article_id세팅
				confirm.getAttach().setA_id(article_id);
				result = confirmDao.insertAttachment(conn, confirm.getAttach()); //attachment객체를 보내는 것
			}
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		
//		if(result >0)commit(conn);
//		else rollback(conn);
		close(conn);
		return result;
	}

	public Confirm selectOne(int no) {
		Connection conn = getConnection();
		Confirm confirm= confirmDao.selectOne(conn, no);
		ConfirmAttachment attach = confirmDao.selectOneAttachment(conn, no);
		confirm.setAttach(attach);
		close(conn);
		return confirm;
	}

	public List<ConfirmComment> selectBoardCommentList(int article_no) {
		Connection conn = getConnection();
		List<ConfirmComment> list = confirmDao.selectBoardCommentList(conn,article_no);
		close(conn);
		return list;
	}

	/**
	 * board_no로 attachment행 조회
	 * 
	 * 
	 */
	public ConfirmAttachment selectOneAttachment(int no) {
		Connection conn = getConnection();
		ConfirmAttachment attach = confirmDao.selectOneAttachment(conn, no);
		close(conn);
		return attach;
	}

	public int insertBoardComment(ConfirmComment cc) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = confirmDao.insertBoardComment(conn,cc);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}
	
	public int deleteBoard(int no) {
		Connection conn = getConnection();
		int result =0;
		try {
			result = confirmDao.deleteBoard(conn,no);
			//존재하지 않는 게시물을 삭제할 경우
			if(result == 0)
				throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다. : "+no);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);	
			throw e; //controller가 예외처리를 결정할 수 있도록 넘김. 예외페이지로 넘김
		} finally {
			close(conn);			
		}
		return result;
	}
	
	public int updateBoard(Confirm confirm) {
		Connection conn = getConnection();
		int result = 0;
		try {
			//1. board update 존재하는 게시물
			result = confirmDao.updateBoard(conn,confirm);
			//2. attachement insert 첨부파일이 없는 상황이라면
			if(confirm.getAttach() != null)
				result = confirmDao.insertAttachment(conn,confirm.getAttach());
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public int deleteAttachment(String attachNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = confirmDao.deleteAttachment(conn,attachNo);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
		
	}

	public int boardCommentDelete(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = confirmDao.boardCommentDelete(conn,no);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	
	
	/**
	 * Admin 포인트 지급 용
	 * @param confirm
	 * @param memberId
	 * @return
	 */
	public int insertPoint(Confirm confirm, String memberId) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = confirmDao.insertPoint(conn,confirm,memberId);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public int updatePointCheck(int boardNo, String memberId) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = confirmDao.updatePointCheck(conn,boardNo, memberId);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public int insertTeamPoint(String user) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = confirmDao.insertTeamPoint(conn,user);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public int insertBadge(String user, String challengeName) {

		Connection conn = getConnection();
		int result = 0;
		try {
			result = confirmDao.insertBadge(conn,user,challengeName);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public int deleteChallenge(String user, String challengeName) {

		Connection conn = getConnection();
		int result = 0;
		try {
			result = confirmDao.deleteChallenge(conn,user,challengeName);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}



}