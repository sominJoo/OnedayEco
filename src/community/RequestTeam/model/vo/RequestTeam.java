package community.RequestTeam.model.vo;

public class RequestTeam {
	
	private int requestId;
	private String memberId;
	private int aId;
	
	public RequestTeam() {
		super();
	}
	public RequestTeam(int requestId, String memberId, int aId) {
		super();
		this.requestId = requestId;
		this.memberId = memberId;
		this.aId = aId;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
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
	@Override
	public String toString() {
		return "RequestTeam [requestId=" + requestId + ", memberId=" + memberId + ", aId=" + aId + "]";
	}
}
