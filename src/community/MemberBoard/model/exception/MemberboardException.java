package community.MemberBoard.model.exception;

public class MemberboardException extends RuntimeException {

	public MemberboardException() {
		super();
	}
	public MemberboardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public MemberboardException(String message, Throwable cause) {
		super(message, cause);
	}
	public MemberboardException(String message) {
		super(message);
	}
	public MemberboardException(Throwable cause) {
		super(cause);
	}
}