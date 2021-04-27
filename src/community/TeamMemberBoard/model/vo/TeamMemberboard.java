package community.TeamMemberBoard.model.vo;

import java.sql.Date;
    
public class TeamMemberboard {
	private int teamAId;
	private String memberId;
	private int aId;
	private String aTitle;
	private String aContent;
	private Date aRegDate;
	private int aReadCount;
	private int aLike;
	private ATeamAttachment aTeamAttachment;
	private TeamComment teamComment;
	
	public TeamMemberboard() {
		super();
	}
	public TeamMemberboard(int teamAId, String memberId, int aId, String aTitle, String aContent, Date aRegDate,
			int aReadCount, int aLike, ATeamAttachment aTeamAttachment, TeamComment teamComment) {
		super();
		this.teamAId = teamAId;
		this.memberId = memberId;
		this.aId = aId;
		this.aTitle = aTitle;
		this.aContent = aContent;
		this.aRegDate = aRegDate;
		this.aReadCount = aReadCount;
		this.aLike = aLike;
		this.aTeamAttachment = aTeamAttachment;
		this.teamComment = teamComment;
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
	public int getaId() {
		return aId;
	}
	public void setaId(int aId) {
		this.aId = aId;
	}
	public String getaTitle() {
		return aTitle;
	}
	public void setaTitle(String aTitle) {
		this.aTitle = aTitle;
	}
	public String getaContent() {
		return aContent;
	}
	public void setaContent(String aContent) {
		this.aContent = aContent;
	}
	public Date getaRegDate() {
		return aRegDate;
	}
	public void setaRegDate(Date aRegDate) {
		this.aRegDate = aRegDate;
	}
	public int getaReadCount() {
		return aReadCount;
	}
	public void setaReadCount(int aReadCount) {
		this.aReadCount = aReadCount;
	}
	public int getaLike() {
		return aLike;
	}
	public void setaLike(int aLike) {
		this.aLike = aLike;
	}
	public ATeamAttachment getaTeamAttachment() {
		return aTeamAttachment;
	}
	public void setaTeamAttachment(ATeamAttachment aTeamAttachment) {
		this.aTeamAttachment = aTeamAttachment;
	}
	public TeamComment getTeamComment() {
		return teamComment;
	}
	public void setTeamComment(TeamComment teamComment) {
		this.teamComment = teamComment;
	}
	@Override
	public String toString() {
		return "TeamMemberboard [teamAId=" + teamAId + ", memberId=" + memberId + ", aId=" + aId + ", aTitle=" + aTitle
				+ ", aContent=" + aContent + ", aRegDate=" + aRegDate + ", aReadCount=" + aReadCount + ", aLike="
				+ aLike + ", aTeamAttachment=" + aTeamAttachment + ", teamComment=" + teamComment + "]";
	}
}
