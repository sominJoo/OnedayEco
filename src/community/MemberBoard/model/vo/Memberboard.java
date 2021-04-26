package community.MemberBoard.model.vo;

import java.sql.Date;

public class Memberboard {
	private int aId;
	private String memberId;
	private int challengeId;
	private String aTitle;
	private String aContent;
	private Date aRegDate;
	private int aReadCount;
	private int aLike;
	private int sTeamCount;
	private MemberboardAttachment memberboardAttachment;
	private Challenge challenge;
	public Memberboard() {
		super();
	}
	public Memberboard(int aId, String memberId, int challengeId, String aTitle, String aContent, Date aRegDate,
			int aReadCount, int aLike, int sTeamCount, MemberboardAttachment memberboardAttachment,
			Challenge challenge) {
		super();
		this.aId = aId;
		this.memberId = memberId;
		this.challengeId = challengeId;
		this.aTitle = aTitle;
		this.aContent = aContent;
		this.aRegDate = aRegDate;
		this.aReadCount = aReadCount;
		this.aLike = aLike;
		this.sTeamCount = sTeamCount;
		this.memberboardAttachment = memberboardAttachment;
		this.challenge = challenge;
	}
	public int getaId() {
		return aId;
	}
	public void setaId(int aId) {
		this.aId = aId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getChallengeId() {
		return challengeId;
	}
	public void setChallengeId(int challengeId) {
		this.challengeId = challengeId;
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
	public int getsTeamCount() {
		return sTeamCount;
	}
	public void setsTeamCount(int sTeamCount) {
		this.sTeamCount = sTeamCount;
	}
	public MemberboardAttachment getMemberboardAttachment() {
		return memberboardAttachment;
	}
	public void setMemberboardAttachment(MemberboardAttachment memberboardAttachment) {
		this.memberboardAttachment = memberboardAttachment;
	}
	public Challenge getChallenge() {
		return challenge;
	}
	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}
}