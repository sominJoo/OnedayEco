package mypage.model.vo;

import java.sql.Date;

/**
 * point에 대한 정보와 challenge 정보를 받음
 * @author ddomin
 *
 */
public class MypagePoint {
	private int point;
	private String challengName;
	private Date pointDate;
	private String challengeTermType;
	public MypagePoint() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MypagePoint(int point, String challengName, Date pointDate, String challengeTermType) {
		super();
		this.point = point;
		this.challengName = challengName;
		this.pointDate = pointDate;
		this.challengeTermType = challengeTermType;
	}
	@Override
	public String toString() {
		return "MypagePoint [point=" + point + ", challengName=" + challengName + ", pointDate=" + pointDate
				+ ", challengeTermType=" + challengeTermType + "]";
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getChallengName() {
		return challengName;
	}
	public void setChallengName(String challengName) {
		this.challengName = challengName;
	}
	public Date getPointDate() {
		return pointDate;
	}
	public void setPointDate(Date pointDate) {
		this.pointDate = pointDate;
	}
	public String getChallengeTermType() {
		return challengeTermType;
	}
	public void setChallengeTermType(String challengeTermType) {
		this.challengeTermType = challengeTermType;
	}
	
	
		
}
