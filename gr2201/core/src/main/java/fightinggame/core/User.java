package fightinggame.core;

/**
 * The User represents a user. It will have all info about the logged in user. 
 */
public class User {
	private UserData userData;
	private UserId userId;

	public User(String id, String pas) {
		// TODO: Make constructor more robust with exceptions and checking for edge cases.
		this.userData = new UserData(pas);
		this.userId = new UserId(id);
	}

	public boolean equals(User u) {
		return this.getUserId().equals(u.getUserId());
	}

	public UserId getUserId() {
		return this.userId;
	}

	public UserData getUserData() {
		return this.userData;
	}
	
	public String getUserName() {
		return this.getUserId().getUserId();
	}
	
	public String getPassword() {
		return this.getUserData().getPassword();
	}

	public void setUserId(UserId userId) {
		this.userId = userId;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}
}
