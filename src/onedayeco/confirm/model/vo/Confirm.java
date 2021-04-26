package onedayeco.confirm.model.vo;

import java.sql.Date;

public class Confirm {

	private int a_id;
	private String user_id;
	private String challengeName;
	private String confirm_type;
	private String a_title;
	private String a_content;
	private Date a_reg_date;
	private int a_read_count;
	private int a_like;
	private String point_check;
	private ConfirmAttachment attach;
	public Confirm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Confirm(int a_id, String user_id, String challengeName, String confirm_type, String a_title,
			String a_content, Date a_reg_date, int a_read_count, int a_like, String point_check,
			ConfirmAttachment attach) {
		super();
		this.a_id = a_id;
		this.user_id = user_id;
		this.challengeName = challengeName;
		this.confirm_type = confirm_type;
		this.a_title = a_title;
		this.a_content = a_content;
		this.a_reg_date = a_reg_date;
		this.a_read_count = a_read_count;
		this.a_like = a_like;
		this.point_check = point_check;
		this.attach = attach;
	}
	@Override
	public String toString() {
		return "Confirm [a_id=" + a_id + ", user_id=" + user_id + ", challengeName=" + challengeName + ", confirm_type="
				+ confirm_type + ", a_title=" + a_title + ", a_content=" + a_content + ", a_reg_date=" + a_reg_date
				+ ", a_read_count=" + a_read_count + ", a_like=" + a_like + ", point_check=" + point_check + ", attach="
				+ attach + "]";
	}
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getChallengeName() {
		return challengeName;
	}
	public void setChallengeName(String challengeName) {
		this.challengeName = challengeName;
	}
	public String getConfirm_type() {
		return confirm_type;
	}
	public void setConfirm_type(String confirm_type) {
		this.confirm_type = confirm_type;
	}
	public String getA_title() {
		return a_title;
	}
	public void setA_title(String a_title) {
		this.a_title = a_title;
	}
	public String getA_content() {
		return a_content;
	}
	public void setA_content(String a_content) {
		this.a_content = a_content;
	}
	public Date getA_reg_date() {
		return a_reg_date;
	}
	public void setA_reg_date(Date a_reg_date) {
		this.a_reg_date = a_reg_date;
	}
	public int getA_read_count() {
		return a_read_count;
	}
	public void setA_read_count(int a_read_count) {
		this.a_read_count = a_read_count;
	}
	public int getA_like() {
		return a_like;
	}
	public void setA_like(int a_like) {
		this.a_like = a_like;
	}
	public String getPoint_check() {
		return point_check;
	}
	public void setPoint_check(String point_check) {
		this.point_check = point_check;
	}
	public ConfirmAttachment getAttach() {
		return attach;
	}
	public void setAttach(ConfirmAttachment attach) {
		this.attach = attach;
	}
	
	
	
}
