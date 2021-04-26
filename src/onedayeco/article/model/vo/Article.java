package onedayeco.article.model.vo;

import java.sql.Date;

public class Article {
	
	private int article_id;
	private String user_id;
	private String article_type;
	private String article_title;
	private String article_content;
	private Date article_reg_date;
	private int article_read_count;
	private int article_like;
	private String article_write_allow;
	private ArticleAttachment attach;
	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Article(int article_id, String user_id, String article_type, String article_title, String article_content,
			Date article_reg_date, int article_read_count, int article_like, String article_write_allow,
			ArticleAttachment attach) {
		super();
		this.article_id = article_id;
		this.user_id = user_id;
		this.article_type = article_type;
		this.article_title = article_title;
		this.article_content = article_content;
		this.article_reg_date = article_reg_date;
		this.article_read_count = article_read_count;
		this.article_like = article_like;
		this.article_write_allow = article_write_allow;
		this.attach = attach;
	}
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getArticle_type() {
		return article_type;
	}
	public void setArticle_type(String article_type) {
		this.article_type = article_type;
	}
	public String getArticle_title() {
		return article_title;
	}
	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}
	public String getArticle_content() {
		return article_content;
	}
	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}
	public Date getArticle_reg_date() {
		return article_reg_date;
	}
	public void setArticle_reg_date(Date article_reg_date) {
		this.article_reg_date = article_reg_date;
	}
	public int getArticle_read_count() {
		return article_read_count;
	}
	public void setArticle_read_count(int article_read_count) {
		this.article_read_count = article_read_count;
	}
	public int getArticle_like() {
		return article_like;
	}
	public void setArticle_like(int article_like) {
		this.article_like = article_like;
	}
	public String getArticle_write_allow() {
		return article_write_allow;
	}
	public void setArticle_write_allow(String article_write_allow) {
		this.article_write_allow = article_write_allow;
	}
	public ArticleAttachment getAttach() {
		return attach;
	}
	public void setAttach(ArticleAttachment attach) {
		this.attach = attach;
	}
	@Override
	public String toString() {
		return "Article [article_id=" + article_id + ", user_id=" + user_id + ", article_type=" + article_type
				+ ", article_title=" + article_title + ", article_content=" + article_content + ", article_reg_date="
				+ article_reg_date + ", article_read_count=" + article_read_count + ", article_like=" + article_like
				+ ", article_write_allow=" + article_write_allow + ", attach=" + attach + "]";
	}
	
	
	
	
	

}
