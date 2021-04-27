package ranking.model.vo;

public class TeamMemberPoint {
	private String memberId;
	private int sum;
	private int rNum;
	public TeamMemberPoint() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TeamMemberPoint(String memberId, int sum, int rNum) {
		super();
		this.memberId = memberId;
		this.sum = sum;
		this.rNum = rNum;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getrNum() {
		return rNum;
	}
	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
	@Override
	public String toString() {
		return "TeamMemberPoint [memberId=" + memberId + ", sum=" + sum + ", rNum=" + rNum + "]";
	}
}
