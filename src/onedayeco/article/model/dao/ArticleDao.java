package onedayeco.article.model.dao;
import static common.JDBCTemplate.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;

import com.sun.xml.internal.ws.api.message.Attachment;

import onedayeco.article.model.exception.ArticleException;
import onedayeco.article.model.vo.Article;
import onedayeco.article.model.vo.ArticleAttachment;
import onedayeco.article.model.vo.ArticleComment;

public class ArticleDao {
	
	private Properties prop = new Properties();
	
	public ArticleDao() {
		String fileName = "/sql/article/article-query.properties";
		System.out.println(fileName);
		String absPath = ArticleDao.class.getResource(fileName).getPath();
		System.out.println(absPath);
		try {
			prop.load(new FileReader(absPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Article> selectList(Connection conn, int start, int end){
		List<Article> list = new ArrayList<Article>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectList");
		
		try {
			System.out.println("rsettry"+rset);
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			System.out.println("rsettry2"+rset);
			rset = pstmt.executeQuery();
			System.out.println("rsettry3"+rset);
			while(rset.next()) {
				Article article = new Article();
				article.setArticle_id(rset.getInt("a_id"));
				article.setUser_id(rset.getString("member_id"));
				article.setArticle_type(rset.getString("a_type"));
				article.setArticle_title(rset.getString("a_title"));
				article.setArticle_content(rset.getString("a_content"));
				article.setArticle_reg_date(rset.getDate("a_reg_date"));
				article.setArticle_read_count(rset.getInt("a_read_count"));
				article.setArticle_like(rset.getInt("a_like"));
				
				System.out.println(rset.getInt("attachment_id"));
				//첨부파일이 있는 경우
				if(rset.getInt("attachment_id") != 0) {
					ArticleAttachment attach = new ArticleAttachment();
					attach.setAttachment_id(rset.getInt("attachment_id"));
					attach.setArticle_id(rset.getInt("a_id"));
					attach.setOriginal_filename(rset.getString("original_filename"));
					attach.setRenamed_filename(rset.getString("renamed_filename"));
					attach.setAttachment_status("Y".equals(rset.getString("attachment_status"))? true : false);
					article.setAttach(attach);
					
				}
				list.add(article);
//				System.out.println(article);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("DAO"+list);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContents;
	}
	public int selectCorNBoardCount(Connection conn, String type) {
		int totalContents = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectCorNBoardCount");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, type);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				totalContents = rset.getInt("cnt");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContents;
	}


	public int insertBoard(Connection conn, Article article) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getUser_id());
			pstmt.setString(2, article.getArticle_type());
			pstmt.setString(3, article.getArticle_title());
			pstmt.setString(4, article.getArticle_content());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ArticleException("게시물  등록 오류",e);
			
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
			throw new ArticleException("게시물 등록 번호 조회 오류",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return boardNo;
	}

	public int insertAttachment(Connection conn, ArticleAttachment attach) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertAttachment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attach.getArticle_id());
			pstmt.setString(2, attach.getOriginal_filename());
			pstmt.setString(3, attach.getRenamed_filename());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			throw new ArticleException("게시물 첨부파일 등록 오류",e); //Runtime예외를 상속한 BoardException
		}finally {
			close(pstmt);
		}
		return result;
	}

	public Article selectOne(Connection conn, int no) {
		PreparedStatement pstmt = null;
		Article article= null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOne");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				article = new Article();
				article.setArticle_id(rset.getInt("a_id"));
				article.setArticle_title(rset.getString("a_title"));
				article.setUser_id(rset.getString("member_id"));
				article.setArticle_content(rset.getString("a_content"));
				article.setArticle_reg_date(rset.getDate("a_reg_date"));
				article.setArticle_read_count(rset.getInt("a_read_count"));
				article.setArticle_like(rset.getInt("a_like"));
				
			}
						//checked예외는 throws exception을 지정해줘야하니까 간결하게 runtime예외를 만들어서 던짐
		}catch(SQLException e) {
			throw new ArticleException("게시글 조회 오류",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return article;
	}

	public ArticleAttachment selectOneAttachment(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ArticleAttachment attachment= null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOneAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				attachment = new ArticleAttachment();
				attachment.setAttachment_id(rset.getInt("attachment_id"));
				attachment.setArticle_id(rset.getInt("a_id"));
				attachment.setOriginal_filename(rset.getString("original_filename"));
				System.out.println("renamed_filename"+rset.getString("original_filename"));
				attachment.setRenamed_filename(rset.getString("renamed_filename"));
				attachment.setAttachment_status("Y".equals(rset.getString("attachment_status"))? true : false);
			}
		
			
		}catch(SQLException e) {
//			e.printStackTrace(); //예외를 처리한다음 입을 싹 닦아서 controller가 알지 못함
			 throw new ArticleException("첨부파일 조회 오류",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return attachment;
	}

	public List<ArticleComment> selectBoardCommentList(Connection conn, int article_id) {
		List<ArticleComment> commentList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBoardCommentList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, article_id);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ArticleComment ac = new ArticleComment();
				ac.setComment_id(rset.getInt("comment_id"));
				ac.setComment_level(rset.getInt("comment_level"));
				ac.setUser_id(rset.getString("member_id"));
				ac.setComment_content(rset.getString("comment_content"));
				ac.setArticle_id(rset.getInt("a_id"));
				ac.setComment_ref(rset.getInt("comment_ref"));
				ac.setComment_reg_date(rset.getDate("comment_reg_date"));
				commentList.add(ac);
			}
		}catch(SQLException e) {
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return commentList;
	}

	public int insertBoardComment(Connection conn, ArticleComment ac) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertBoardComment");
		
		try {
			//(comment_id, member_id, a_id, comment_content, comment_level,comment_ref) 
			//values(seq_a_comment_id.nextval,?,?,?,default,?,?)
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,ac.getUser_id());
			pstmt.setInt(2,ac.getArticle_id());
			pstmt.setString(3,ac.getComment_content());
//			pstmt.setDate(4,ac.getComment_reg_date());
			pstmt.setInt(4,ac.getComment_level());
			System.out.println(1);
//			pstmt.setInt(5,bc.getCommentRef() == 0? null:bc.getCommentRef()); 
			pstmt.setObject(5, ac.getComment_ref() == 0? null:ac.getComment_ref());
			//: integrity constraint (WEB.FK_BOARD_COMMENT_REF) violated - parent key not found
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ArticleException("댓글 등록 오류",e);
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
			throw new ArticleException("게시물 삭제 오류",e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int updateBoard(Connection conn, Article article) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateBoard");
		
		try {
//			System.out.println("업데이트 보드");
			pstmt=conn.prepareStatement(query);
//			System.out.println("업데이트 보드 쿼리");
			pstmt.setString(1, article.getArticle_title());
//			System.out.println("업데이트 보드 제목");
			pstmt.setString(2, article.getUser_id());
			pstmt.setString(3, article.getArticle_content());
//			System.out.println("업데이트 보드 컨텐츠");
			pstmt.setInt(4, article.getArticle_id());
//			System.out.println("업데이트 보드 아티클 번호");
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ArticleException("게시물 수정 오류",e);
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
			throw new ArticleException("댓글 삭제 오류",e);
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
			throw new ArticleException("첨부파일 삭제 오류",e);
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateCount(Connection conn, int a_id) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("upCount");
		
		try {
//			System.out.println("업데이트 보드");
			pstmt=conn.prepareStatement(query);
//			System.out.println("업데이트 보드 쿼리");
			pstmt.setInt(1, a_id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ArticleException("조회수 수정 오류",e);
		}finally {
			close(pstmt);
		}
		return result;
	}


}
