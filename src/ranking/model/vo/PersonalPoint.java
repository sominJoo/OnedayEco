package ranking.model.vo;

import java.sql.Date;

public class PersonalPoint {
	private int pPointId;
	private String memberId;
	private int challengeId;
	private int point;
	private Date pointDate;
	private int rNum;

	public PersonalPoint() {
		super();
	}
	public PersonalPoint(int pPointId, String memberId, int challengeId, int point, Date pointDate, int rNum) {
		super();
		this.pPointId = pPointId;
		this.memberId = memberId;
		this.challengeId = challengeId;
		this.point = point;
		this.pointDate = pointDate;
		this.rNum = rNum;
	}
	public int getpPointId() {
		return pPointId;
	}
	public void setpPointId(int pPointId) {
		this.pPointId = pPointId;
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
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public Date getPointDate() {
		return pointDate;
	}
	public void setPointDate(Date pointDate) {
		this.pointDate = pointDate;
	}
	public int getrNum() {
		return rNum;
	}
	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
	@Override
	public String toString() {
		return "PersonalPoint [pPointId=" + pPointId + ", memberId=" + memberId + ", challengeId=" + challengeId
				+ ", point=" + point + ", pointDate=" + pointDate + ", rNum=" + rNum + "]";
	}
}
