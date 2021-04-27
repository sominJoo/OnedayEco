package onedayeco.article.model.service;

import java.sql.Connection;
import java.util.List;



import static common.JDBCTemplate.*;

import onedayeco.article.model.dao.ArticleDao;
import onedayeco.article.model.vo.Article;
import onedayeco.article.model.vo.ArticleAttachment;
import onedayeco.article.model.vo.ArticleComment;

public class ArticleService {

	private ArticleDao articleDao = new ArticleDao();
	
	public List<Article> selectList(int start, int end){
		Connection conn = getConnection();
		List<Article> list = articleDao.selectList(conn,start,end);
		close(conn);
		System.out.println(list);
		return list;
	}

	public int selectBoardCount() {
		Connection conn = getConnection();
		int totalContents = articleDao.selectBoardCount(conn);
		close(conn);
		return totalContents;
	}
	public int selectCorNBoardCount(String type) {
		Connection conn = getConnection();
		int totalContents = articleDao.selectCorNBoardCount(conn,type);
		close(conn);
		return totalContents;
	}

	/**
	 * 첨부파일 있는 경우, attach객체를 attachment테이블에 등록한다.
	 * - article등록, attachment등록은 하나의 트랜잭션으로 처리되어야한다.
	 * - 하나의 Connection에 의해 처리되어야한다.
	 * 
	 *
	 * @return
	 */
	public int insertBoard(Article article) {
		Connection conn = getConnection();
			int result =0;
		try {
			result = articleDao.insertBoard(conn,article);
			
			//생성된 article_id(없으면 테이블에 등록이 안됌 pk이기 때문에)를 가져오기
			int article_id = articleDao.selectLastBoardNo(conn);
			System.out.println("boardNo@service = "+article_id);
			//아티클객체에는 원래 no가 없었는데 setting해줌
			//redirect location설정
			article.setArticle_id(article_id);
			
			if(article.getAttach() != null) {
				//참조할 article_id세팅
				article.getAttach().setArticle_id(article_id);
				result = articleDao.insertAttachment(conn, article.getAttach()); //attachment객체를 보내는 것
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

	public Article selectOne(int no) {
		Connection conn = getConnection();
		Article article= articleDao.selectOne(conn, no);
		ArticleAttachment attach = articleDao.selectOneAttachment(conn, no);
		article.setAttach(attach);
		close(conn);
		return article;
	}

	public List<ArticleComment> selectBoardCommentList(int article_no) {
		Connection conn = getConnection();
		List<ArticleComment> list = articleDao.selectBoardCommentList(conn,article_no);
		close(conn);
		return list;
	}

	/**
	 * board_no로 attachment행 조회
	 * 
	 * 
	 */
	public ArticleAttachment selectOneAttachment(int no) {
		Connection conn = getConnection();
		ArticleAttachment attach = articleDao.selectOneAttachment(conn, no);
		close(conn);
		return attach;
	}

	public int insertBoardComment(ArticleComment ac) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = articleDao.insertBoardComment(conn,ac);
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
			result = articleDao.deleteBoard(conn,no);
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
	
	public int updateBoard(Article article) {
		Connection conn = getConnection();
		int result = 0;
		try {
			//1. board update 존재하는 게시물
			result = articleDao.updateBoard(conn,article);
			//2. attachement insert 첨부파일이 없는 상황이라면
			if(article.getAttach() != null)
				result = articleDao.insertAttachment(conn,article.getAttach());
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
			result = articleDao.deleteAttachment(conn,attachNo);
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
			result = articleDao.boardCommentDelete(conn,no);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public int updateCount(int a_id) {
		Connection conn = getConnection();
		int result = 0;
		try {
			//1. board update 존재하는 게시물
			result = articleDao.updateCount(conn,a_id);
			//2. attachement insert 첨부파일이 없는 상황이라면
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}



}
