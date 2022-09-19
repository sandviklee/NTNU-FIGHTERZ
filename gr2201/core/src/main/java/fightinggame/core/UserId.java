package fightinggame.core;

public class UserId {
	private String userId;

	public UserId(String userId) {
		valideUserId(userId);
		this.userId = userId;
	}
	
	private static void valideUserId(String id) throws IllegalArgumentException{
		if (id.length() == 0) throw new IllegalArgumentException("Id cant be empty");
		if (id) throw new IllegalArgumentException("Id cant be empty");

	}

	/**
	 * Compares the userId field of this and the other UserId. 
	 * @param id  UserID to compare to this.
	 * @return {@code true} if both fields are the same.
	 */
	public boolean equals(UserId id) {
		return this.getUserId().equals(id.getUserId());
	}

	@Override
	public String toString() {
		return getUserId();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
