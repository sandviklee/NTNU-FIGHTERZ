package fightinggame.users;

public class UserId {
	private String userId;

	/**
	 * Constructs a {@code UserId} with given userId.
	 * @param userId  string to try to constuct userId
	 * @throws IllegalArgumentException when a userId is given that
	 */
	public UserId(final String userId) throws IllegalArgumentException{
		valideUserId(userId);
		this.userId = userId;
	}
	
	private static void valideUserId(final String id) throws IllegalArgumentException{
		if (id.isEmpty()) throw new IllegalArgumentException("Id cant be empty");
		if (!id.matches("[a-zA-Z0-9]*")) throw new IllegalArgumentException("Id must be alphaNumeric");
	}

	/**
	 * Compares the userId field of this and the other UserId. 
	 * @param id  UserID to compare to this.
	 * @return {@code true} if both fields are the same.
	 */
	public boolean equals(final UserId id) {
		return this.getUserId().equals(id.getUserId());
	}

	@Override
	public String toString() {
		return getUserId();
	}

	public String getUserId() {
		return userId;
	}

	private void setUserId(final String userId) {
		this.userId = userId;
	}
}
