package community.MemberBoard.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class MemberboardExt extends Memberboard implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int requestTeamCnt;

	public MemberboardExt() {
		super();
	}

	public MemberboardExt(int aId, String memberId, int challengeId, String aTitle, String aContent, Date aRegDate,
			int aReadCount, int aLike, int sTeamCount, MemberboardAttachment memberboardAttachment, Challenge challenge,
			int requestTeamCnt) {
		super(aId, memberId, challengeId, aTitle, aContent, aRegDate, aReadCount, aLike, sTeamCount,
				memberboardAttachment, challenge);
		this.requestTeamCnt = requestTeamCnt;
	}

	public int getRequestTeamCnt() {
		return requestTeamCnt;
	}

	public void setRequestTeamCnt(int requestTeamCnt) {
		this.requestTeamCnt = requestTeamCnt;
	}

	@Override
	public String toString() {
		return "MemberboardExt [requestTeamCnt=" + requestTeamCnt + ", getaId()=" + getaId() + ", getMemberId()="
				+ getMemberId() + ", getChallengeId()=" + getChallengeId() + ", getaTitle()=" + getaTitle()
				+ ", getaContent()=" + getaContent() + ", getaRegDate()=" + getaRegDate() + ", getaReadCount()="
				+ getaReadCount() + ", getaLike()=" + getaLike() + ", getsTeamCount()=" + getsTeamCount()
				+ ", getMemberboardAttachment()=" + getMemberboardAttachment() + ", getChallenge()=" + getChallenge()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}
