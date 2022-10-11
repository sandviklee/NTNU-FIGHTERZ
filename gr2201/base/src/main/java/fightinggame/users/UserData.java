package fightinggame.users;

/**
 * UserData is a container of all data about a user such as password.
 */
public class UserData {
	private String password;
	// future member data Such as MMR, unlocked characters, achivments, key controls etc.

	/**
	 * Contructs UserData but will throw exception on bad input.
	 * @param password  the password stored in the new {@code UserData}
	 * @throws IllegalArgumentException  on bad input such as no password
	 */
	public UserData(String password) throws IllegalArgumentException{
		valideUserData(password);
		this.password = password;
	}

	private static void valideUserData(String password) throws IllegalArgumentException{
		// Shall detect bad input
		boolean isEmpty = password.isEmpty();
		boolean notOnlyContainsLettersAndNumbers = !password.matches("[a-zA-Z0-9]*");
		if (isEmpty || notOnlyContainsLettersAndNumbers) throw new IllegalArgumentException("Invalid password");
	}

	/**
	 * Checks if this userData has the same fields as other UserData
	 * @param u UserData to compere
	 * @return true if they are equal
	 */
	public boolean equals(UserData u) {
		return this.getPassword().equals(u.getPassword());
	}
	/**
	 * Makes a string representation of UserData. 
	 * Where each field is seperated by a seperator.
	 * @return The string representation of UserData
	 */
	@Override
	public String toString() {
		String seperator = ", ";
		return this.getPassword();

	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
