package mypage.model.vo;

import java.sql.Date;

/**
 * badge_image
 * @author ddomin
 *
 */
public class MypageBadge {
	private int badgeImgId;
	private String badgeName;
	private String badgeImg;
	private String challegeName;
	private Date badgeDate;
	public MypageBadge() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MypageBadge(int badgeImgId, String badgeName, String badgeImg, String challegeName, Date badgeDate) {
		super();
		this.badgeImgId = badgeImgId;
		this.badgeName = badgeName;
		this.badgeImg = badgeImg;
		this.challegeName = challegeName;
		this.badgeDate = badgeDate;
	}
	public int getBadgeImgId() {
		return badgeImgId;
	}
	public void setBadgeImgId(int badgeImgId) {
		this.badgeImgId = badgeImgId;
	}
	public String getBadgeName() {
		return badgeName;
	}
	public void setBadgeName(String badgeName) {
		this.badgeName = badgeName;
	}
	public String getBadgeImg() {
		return badgeImg;
	}
	public void setBadgeImg(String badgeImg) {
		this.badgeImg = badgeImg;
	}
	public String getChallegeName() {
		return challegeName;
	}
	public void setChallegeName(String challegeName) {
		this.challegeName = challegeName;
	}
	public Date getBadgeDate() {
		return badgeDate;
	}
	public void setBadgeDate(Date badgeDate) {
		this.badgeDate = badgeDate;
	}
	@Override
	public String toString() {
		return "MypageBadge [badgeImgId=" + badgeImgId + ", badgeName=" + badgeName + ", badgeImg=" + badgeImg
				+ ", challegeId=" + challegeName + ", badgeDate=" + badgeDate + "]";
	}
	
	
}
