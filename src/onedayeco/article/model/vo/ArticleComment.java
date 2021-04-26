package onedayeco.article.model.vo;

import java.sql.Date;

public class ArticleComment {
	
	private int comment_id;
	private String user_id;
	private int article_id;
	private String comment_content;
	private Date comment_reg_date;
	private int comment_level;
	private int comment_ref;
	public ArticleComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArticleComment(int comment_id, String user_id, int article_id, String comment_content, Date comment_reg_date,
			int comment_level, int comment_ref) {
		super();
		this.comment_id = comment_id;
		this.user_id = user_id;
		this.article_id = article_id;
		this.comment_content = comment_content;
		this.comment_reg_date = comment_reg_date;
		this.comment_level = comment_level;
		this.comment_ref = comment_ref;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public Date getComment_reg_date() {
		return comment_reg_date;
	}
	public void setComment_reg_date(Date comment_reg_date) {
		this.comment_reg_date = comment_reg_date;
	}
	public int getComment_level() {
		return comment_level;
	}
	public void setComment_level(int comment_level) {
		this.comment_level = comment_level;
	}
	public int getComment_ref() {
		return comment_ref;
	}
	public void setComment_ref(int comment_ref) {
		this.comment_ref = comment_ref;
	}
	@Override
	public String toString() {
		return "ArticleComment [comment_id=" + comment_id + ", user_id=" + user_id + ", article_id=" + article_id
				+ ", comment_content=" + comment_content + ", comment_reg_date=" + comment_reg_date + ", comment_level="
				+ comment_level + ", comment_ref=" + comment_ref + "]";
	}
	
	

}
