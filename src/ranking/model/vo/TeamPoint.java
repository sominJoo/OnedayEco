package ranking.model.vo;

import java.sql.Date;

public class TeamPoint {
	private int aId;
	private int challengeId;
	private String aTitle;
	private int rNum;
	private int point;
	public TeamPoint() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TeamPoint(int aId, int challengeId, String aTitle, int rNum, int point) {
		super();
		this.aId = aId;
		this.challengeId = challengeId;
		this.aTitle = aTitle;
		this.rNum = rNum;
		this.point = point;
	}
	public int getaId() {
		return aId;
	}
	public void setaId(int aId) {
		this.aId = aId;
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
	public int getrNum() {
		return rNum;
	}
	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	@Override
	public String toString() {
		return "TeamPoint [aId=" + aId + ", challengeId=" + challengeId + ", aTitle=" + aTitle + ", rNum=" + rNum
				+ ", point=" + point + "]";
	}

	
}
