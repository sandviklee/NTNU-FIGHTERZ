package fightinggame.core;

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
	 * Makes a string representation of UserData.
	 */
	@Override
	public String toString() {
		// TODO:

	}

	/**
	 * Takes a string and convert it to a UserData.
	 * Must have all UserData fields in correct order and values must be seperated by comma.
	 * order: [password]
	 * If data is correct form and have each data field with valide values  make UserData,
	 * else return null
	 * @param data  string to interprete
	 * @return UserData with data or null object if containing non valide input
	 */
	public UserData stringToUserData(String data) {
		// TODO:

	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
