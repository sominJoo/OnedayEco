package challenge.model.vo;

public class Challenge {
	private int challenge_id;
	private String challenge_term_type;
	private int challenge_level;
	private String challenge_name;
	private String challenge_info;
	private int challenge_point;
	private int challenge_term;
	
	public Challenge() {
		super();
	}

	public Challenge(int challenge_id, String challenge_term_type, int challenge_level, String challenge_name,
			String challenge_info, int challenge_point, int challenge_term) {
		super();
		this.challenge_id = challenge_id;
		this.challenge_term_type = challenge_term_type;
		this.challenge_level = challenge_level;
		this.challenge_name = challenge_name;
		this.challenge_info = challenge_info;
		this.challenge_point = challenge_point;
		this.challenge_term = challenge_term;
	}

	public int getChallenge_id() {
		return challenge_id;
	}

	public void setChallenge_id(int challenge_id) {
		this.challenge_id = challenge_id;
	}

	public String getChallenge_term_type() {
		return challenge_term_type;
	}

	public void setChallenge_term_type(String challenge_term_type) {
		this.challenge_term_type = challenge_term_type;
	}

	public int getChallenge_level() {
		return challenge_level;
	}

	public void setChallenge_level(int challenge_level) {
		this.challenge_level = challenge_level;
	}

	public String getChallenge_name() {
		return challenge_name;
	}

	public void setChallenge_name(String challenge_name) {
		this.challenge_name = challenge_name;
	}

	public String getChallenge_info() {
		return challenge_info;
	}

	public void setChallenge_info(String challenge_info) {
		this.challenge_info = challenge_info;
	}

	public int getChallenge_point() {
		return challenge_point;
	}

	public void setChallenge_point(int challenge_point) {
		this.challenge_point = challenge_point;
	}

	public int getChallenge_term() {
		return challenge_term;
	}

	public void setChallenge_term(int challenge_term) {
		this.challenge_term = challenge_term;
	}

	@Override
	public String toString() {
		return "Challenge [challenge_id=" + challenge_id + ", challenge_term_type=" + challenge_term_type
				+ ", challenge_level=" + challenge_level + ", challenge_name=" + challenge_name + ", challenge_info="
				+ challenge_info + ", challenge_point=" + challenge_point + ", challenge_term=" + challenge_term + "]";
	}
	
}
