package fightinggame.core;

/**
 * UserData is a container of all data about a user such as password.
 */
public class UserData {
	private String password;
	// future member data Such as MMR, unlocked characters, achivments, key controls etc.

	/**
	 * Contructs UserData but will throw exception on bad input.
	 * @param password the password stored in the new {@code UserData}
	 * @throws IllegalArgumentException on bad input such as no password
	 */
	public UserData(String password) throws IllegalArgumentException{
		if (password.length() == 0) throw new IllegalArgumentException("Cant have no password");
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	



	
}
