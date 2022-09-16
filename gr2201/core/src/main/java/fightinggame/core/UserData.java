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
	public UserData(String password) {
		try {
			valideUserData(password);
			this.password = password;
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return;
		}
		
	}

	private static void valideUserData(String password) throws IllegalArgumentException{
		// Shall detect bad input
		if (password.length() == 0) throw new IllegalArgumentException("Cant have no password");

	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	



	
}
