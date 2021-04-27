package community.TeamMemberBoard.model.vo;

import java.sql.Date;

public class TeamComment {
	private int commentId; // no - pk
	private int teamAId; // board의 no
	private String memberId; // writer
	private String commentContent; 
	private Date commentRegDate;
	private int commentLevel; // 기본값 : 1 / 댓글 : 1, 대댓글 : 2
	private int commentRef; // 해당 테이블의 no참조, 대댓글인 경우 참조댓글 no, 댓글인 경우 null
	
	public TeamComment() {
		super();
	}
	public TeamComment(int commentId, int teamAId, String memberId, String commentContent, Date commentRegDate,
			int commentLevel, int commentRef) {
		super();
		this.commentId = commentId;
		this.teamAId = teamAId;
		this.memberId = memberId;
		this.commentContent = commentContent;
		this.commentRegDate = commentRegDate;
		this.commentLevel = commentLevel;
		this.commentRef = commentRef;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getTeamAId() {
		return teamAId;
	}
	public void setTeamAId(int teamAId) {
		this.teamAId = teamAId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Date getCommentRegDate() {
		return commentRegDate;
	}
	public void setCommentRegDate(Date commentRegDate) {
		this.commentRegDate = commentRegDate;
	}
	public int getCommentLevel() {
		return commentLevel;
	}
	public void setCommentLevel(int commentLevel) {
		this.commentLevel = commentLevel;
	}
	public int getCommentRef() {
		return commentRef;
	}
	public void setCommentRef(int commentRef) {
		this.commentRef = commentRef;
	}
	@Override
	public String toString() {
		return "TeamComment [commentId=" + commentId + ", teamAId=" + teamAId + ", memberId=" + memberId
				+ ", commentContent=" + commentContent + ", commentLevel=" + commentLevel + ", commentRef=" + commentRef
				+ "]";
	}
}
