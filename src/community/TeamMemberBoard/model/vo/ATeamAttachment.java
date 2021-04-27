package community.TeamMemberBoard.model.vo;
    
public class ATeamAttachment {
	private int attachmentId;
	private int teamAId;
	private String originalFilename;
	private String renamedFilename;
	private boolean attachmentStatus;

	public ATeamAttachment() {
		super();
	}
	public ATeamAttachment(int attachmentId, int teamAId, String originalFilename, String renamedFilename,
			boolean attachmentStatus) {
		super();
		this.attachmentId = attachmentId;
		this.teamAId = teamAId;
		this.originalFilename = originalFilename;
		this.renamedFilename = renamedFilename;
		this.attachmentStatus = attachmentStatus;
	}
	public int getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}
	public int getTeamAId() {
		return teamAId;
	}
	public void setTeamAId(int teamAId) {
		this.teamAId = teamAId;
	}
	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	public String getRenamedFilename() {
		return renamedFilename;
	}
	public void setRenamedFilename(String renamedFilename) {
		this.renamedFilename = renamedFilename;
	}
	public boolean isAttachmentStatus() {
		return attachmentStatus;
	}
	public void setAttachmentStatus(boolean attachmentStatus) {
		this.attachmentStatus = attachmentStatus;
	}
	@Override
	public String toString() {
		return "ATeamAttachment [attachmentId=" + attachmentId + ", teamAId=" + teamAId + ", originalFilename="
				+ originalFilename + ", renamedFilename=" + renamedFilename + ", attachmentStatus=" + attachmentStatus
				+ "]";
	}
}
