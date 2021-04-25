package mypage.model.vo;

import java.sql.Date;

/**
 * 현재 진행중인 챌린지를 받아옴
 * @author ddomin
 *
 */
public class MypageChallenge {
	private String challengeName;
	private String challengeTermType;
	private Date confirmDate;
	private Date endDate;
	private int challengeLevel;
	public MypageChallenge() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MypageChallenge(String challengeName, String challengeTermType, Date confirmDate, Date endDate,
			int challengeLevel) {
		super();
		this.challengeName = challengeName;
		this.challengeTermType = challengeTermType;
		this.confirmDate = confirmDate;
		this.endDate = endDate;
		this.challengeLevel = challengeLevel;
	}
	@Override
	public String toString() {
		return "MypageChallenge [challengeName=" + challengeName + ", challengeTermType=" + challengeTermType
				+ ", confirmDate=" + confirmDate + ", endDate=" + endDate + ", challengeLevel=" + challengeLevel + "]";
	}
	public String getChallengeName() {
		return challengeName;
	}
	public void setChallengeName(String challengeName) {
		this.challengeName = challengeName;
	}
	public String getChallengeTermType() {
		return challengeTermType;
	}
	public void setChallengeTermType(String challengeTermType) {
		this.challengeTermType = challengeTermType;
	}
	public Date getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getChallengeLevel() {
		return challengeLevel;
	}
	public void setChallengeLevel(int challengeLevel) {
		this.challengeLevel = challengeLevel;
	}
	
	
}
