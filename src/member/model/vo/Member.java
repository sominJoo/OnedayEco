package member.model.vo;

public class Member {
	private  String memberId;
	private  String memberPw;
	private  String memberName;
	private  String memberNickname;
	private  String memberEmail;
	private  String memberPhone;
	private  String memberTeam;
	private  String memberPicture;
	private  String memberRole;
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Member(String memberId, String memberPw, String memberName, String memberNickname, String memberEamil,
			String memberPhone, String memberTeam, String memberPicture, String memberRole) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberNickname = memberNickname;
		this.memberEmail = memberEamil;
		this.memberPhone = memberPhone;
		this.memberTeam = memberTeam;
		this.memberPicture = memberPicture;
		this.memberRole = memberRole;
	}
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPw=" + memberPw + ", memberName=" + memberName
				+ ", memberNickname=" + memberNickname + ", memberEamil=" + memberEmail + ", memberPhone=" + memberPhone
				+ ", memberTeam=" + memberTeam + ", memberPicture=" + memberPicture + ", memberRole=" + memberRole
				 + "]";
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEamil) {
		this.memberEmail = memberEamil;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberTeam() {
		return memberTeam;
	}
	public void setMemberTeam(String memberTeam) {
		this.memberTeam = memberTeam;
	}
	public String getMemberPicture() {
		return memberPicture;
	}
	public void setMemberPicture(String memberPicture) {
		this.memberPicture = memberPicture;
	}
	public String getMemberRole() {
		return memberRole;
	}
	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}

	
}
