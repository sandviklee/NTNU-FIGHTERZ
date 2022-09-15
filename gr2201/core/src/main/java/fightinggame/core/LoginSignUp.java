package fightinggame.core;

import fightinggame.dbAccess;
/**
 * LoginSignUp will handle all login and sign up functions and validate input.
 */
public class LoginSignUp {
	private UserDAO dao = new UserDAOimpl();

	/**
	 * Checks if inputinfo is valide and return a {@code User} from data storage that match input.
	 * 
	 * For valide input check if a user with corresensponding userName and password is contained in the data storage.
	 * When either does not complie return null.
	 * @param userName the string that login will attempt to find a corresensponding user
	 * @param password the the string that login will attempt to find a corresensponding user
	 * @param confirmePassword the string to compare password equal to
	 * 
	 * @return A User if valide input and user in data storage else null.
	 */
	public static User login(String userName, String password, String confirmePassword) {
		// TODO:
	}

	/**
	 * Checks if inputinfo is valide and makes a {@code User} in the data storage and return said {@code User}.
	 * 
	 * It checks if the {@code username} is in data storage if it is already in use return null, 
	 * else make a new return a {@code User} from data storage that match input. 
	 * Return said new {@code User}.
	 * 
	 * @param userName          the string that will be will be 
	 * @param password          the string that will be saved as password for the new {@code User}
	 * @param confirmePassword  the string to compare password equal to
	 * @return  a new {@code User} that is saved in data storage. If already in storage return null.
	 */
	public static User signUp(String userName, String password, String confirmePassword) {
		// TODO:
	}


	/**
	 * Checks if username is a non empty string only containing a combaination of letters and numbers.
	 * @param userName the string to test
	 * @return true if userName is valide else false
	 */
	private static boolean validateUserName(String userName) {
		// TODO: 
	}

	/**
	 * validatePassword shall check if password is a non empty string only containing a combaination of letters and numbers.
	 * @param password a string
	 * @return true if password is valide else false
	 */
	private static boolean validatePassword(String password) {
		// TODO: 
	}
	

	private static boolean isCorrespondingPasswords(String p1, String p2) {
		return p1.equal(p2);
	}

	/**
	 * hashPassword shall hash password.
	 */
	private static int hashPassword(String p) {
		// TODO: Find a good hashing algo
		// pass
	}
}
