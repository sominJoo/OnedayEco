package onedayeco.confirm.model.vo;

import java.sql.Date;

public class ConfirmComment {
	
	private int comment_id;
	private String user_id;
	private int a_id;
	private String comment_content;
	private Date comment_reg_date;
	private int comment_level;
	private int comment_ref;
	public ConfirmComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ConfirmComment(int comment_id, String user_id, int a_id, String comment_content, Date comment_reg_date,
			int comment_level, int comment_id2) {
		super();
		this.comment_id = comment_id;
		this.user_id = user_id;
		this.a_id = a_id;
		this.comment_content = comment_content;
		this.comment_reg_date = comment_reg_date;
		this.comment_level = comment_level;
		this.comment_ref = comment_id2;
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
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
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
		return "ConfirmComment [comment_id=" + comment_id + ", user_id=" + user_id + ", a_id=" + a_id
				+ ", comment_content=" + comment_content + ", comment_reg_date=" + comment_reg_date + ", comment_level="
				+ comment_level + ", comment_id2=" + comment_ref + "]";
	}
	
	

}
