package community.MemberBoard.model.vo;

public class Challenge {
	private int challengeId;
	private String challengeTermType;
	private int challengeLevel;
	private String challengeName;
	private String challengeInfo;
	private int challengePoint;
	private int challengeTerm;
	public Challenge() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Challenge(int challengeId, String challengeTermType, int challengeLevel, String challengeName,
			String challengeInfo, int challengePoint, int challengeTerm) {
		super();
		this.challengeId = challengeId;
		this.challengeTermType = challengeTermType;
		this.challengeLevel = challengeLevel;
		this.challengeName = challengeName;
		this.challengeInfo = challengeInfo;
		this.challengePoint = challengePoint;
		this.challengeTerm = challengeTerm;
	}
	public int getChallengeId() {
		return challengeId;
	}
	public void setChallengeId(int challengeId) {
		this.challengeId = challengeId;
	}
	public String getChallengeTermType() {
		return challengeTermType;
	}
	public void setChallengeTermType(String challengeTermType) {
		this.challengeTermType = challengeTermType;
	}
	public int getChallengeLevel() {
		return challengeLevel;
	}
	public void setChallengeLevel(int challengeLevel) {
		this.challengeLevel = challengeLevel;
	}
	public String getChallengeName() {
		return challengeName;
	}
	public void setChallengeName(String challengeName) {
		this.challengeName = challengeName;
	}
	public String getChallengeInfo() {
		return challengeInfo;
	}
	public void setChallengeInfo(String challengeInfo) {
		this.challengeInfo = challengeInfo;
	}
	public int getChallengePoint() {
		return challengePoint;
	}
	public void setChallengePoint(int challengePoint) {
		this.challengePoint = challengePoint;
	}
	public int getChallengeTerm() {
		return challengeTerm;
	}
	public void setChallengeTerm(int challengeTerm) {
		this.challengeTerm = challengeTerm;
	}
	@Override
	public String toString() {
		return "Challenge [challengeId=" + challengeId + ", challengeTermType=" + challengeTermType
				+ ", challengeLevel=" + challengeLevel + ", challengeName=" + challengeName + ", challengeInfo="
				+ challengeInfo + ", challengePoint=" + challengePoint + ", challengeTerm=" + challengeTerm + "]";
	}
	

}
