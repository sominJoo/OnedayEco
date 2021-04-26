package board.model.vo;

import java.sql.Date;

public class Board {
	//번호 
	private int aid;
	//아이디 
	private String  memberId;
	//카테고리 
	private String atype;
	//제목 
	private String title;
	//본문 
	private String content;
	//날짜 
	private Date  regDate;
	//조회수
	private  int redCount;
	//좋아요 
	private int like;
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Board(int aid, String memberId, String atype, String title, String content, Date regDate, int redCount,
			int like) {
		super();
		this.aid = aid;
		this.memberId = memberId;
		this.atype = atype;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.redCount = redCount;
		this.like = like;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getAtype() {
		return atype;
	}
	public void setAtype(String atype) {
		this.atype = atype;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getRedCount() {
		return redCount;
	}
	public void setRedCount(int redCount) {
		this.redCount = redCount;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	@Override
	public String toString() {
		return "Board [aid=" + aid + ", memberId=" + memberId + ", atype=" + atype + ", title=" + title + ", content="
				+ content + ", regDate=" + regDate + ", redCount=" + redCount + ", like=" + like + "]";
	}
	
	
	}
