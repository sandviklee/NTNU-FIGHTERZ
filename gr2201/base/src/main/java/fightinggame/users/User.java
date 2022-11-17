package fightinggame.users;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The User represents a user. It will have all info about the logged in user.
 */
public class User {
	private UserData userData;
	private UserId userId;

	public User(String id, String password) throws IllegalArgumentException {
		// uses validators in classes UserData and UserId
		try {
			this.userData = new UserData(password);
			this.userId = new UserId(id);

		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Bad input" + e.getLocalizedMessage());
		}
	}

	public User(UserId id, UserData data) {
		if (id == null || data == null) {
			throw new IllegalArgumentException("Bad input, id or data is null");
		}
		this.userId = id;
		this.userData = data;
	}

	/**
	 * Checks if this user has the same UserId as other User
	 * 
	 * @param u User to compere
	 * @return if they are equal
	 */
	public boolean equals(User u) {
		return this.getUserId().equals(u.getUserId()) && this.getPassword().equals(u.getPassword());
	}

	@JsonGetter
	public UserId getUserId() {
		return this.userId;
	}

	@JsonGetter
	public UserData getUserData() {
		return this.userData;
	}

	@JsonIgnore
	public String getUserName() {
		return this.getUserId().getUserId();
	}

	@JsonIgnore
	public String getPassword() {
		return this.getUserData().getPassword();
	}

	/**
	 * Changes UserId to new userId.
	 * If {@code userId} cant be constructed with userId, then does not chage
	 * userId.
	 * 
	 * @param userId the id to try to change field userId.
	 */
	public void changeUserId(UserId userId) {
		this.setUserId(userId);
	}

	/**
	 * Changes UserData to new userData.
	 * If {@code userData} cant be constructed with userData, then does not chage
	 * userData.
	 * 
	 * @param userData the data to try to change field userData.
	 */
	public void changeUserData(UserData userData) {
		this.setUserData(userData);
	}

	private void setUserId(UserId userId) {
		this.userId = userId;
	}

	private void setUserData(UserData userData) {
		this.userData = userData;
	}
}
