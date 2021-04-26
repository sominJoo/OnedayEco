package community.MemberBoard.model.vo;

public class MemberboardAttachment {

	private int attachmentId;
	private int aId;
	private String originalFilename;
	private String renamedFilename;
	private boolean attachmentStatus;

	public MemberboardAttachment() {
		super();
	}
	public MemberboardAttachment(int attachmentId, int aId, String originalFilename, String renamedFilename,
			boolean attachmentStatus) {
		super();
		this.attachmentId = attachmentId;
		this.aId = aId;
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
	public int getaId() {
		return aId;
	}
	public void setaId(int aId) {
		this.aId = aId;
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
		return "MemberboardAttachment [attachmentId=" + attachmentId + ", aId=" + aId + ", originalFilename="
				+ originalFilename + ", renamedFilename=" + renamedFilename + ", attachmentStatus=" + attachmentStatus
				+ "]";
	}
}
