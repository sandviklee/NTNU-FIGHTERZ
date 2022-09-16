package fightinggame.core;

public class UserId {
	private String userId;

	public UserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Compares the userId field of this and the other UserId. 
	 * return {@code true} if both fields are the same.
	 * @param id UserID to compare to this.
	 * @return
	 */
	public boolean equals(UserId id) {
		return this.getUserId().equals(id.getUserId());
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
