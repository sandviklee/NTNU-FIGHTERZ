package fightinggame.users;

import fightinggame.dbaccess.*;

// import path
/**
 * LoginSignup will handle all login and sign up functions and validate input.
 */
public class LoginSignup {
	private static UserDAO dao = new UserDAOImpl();

	/**
	 * Checks if inputinfo is valid and return a {@code User} from data storage that match input.
	 * 
	 * For valid input check if a user with corresensponding username and password is contained in the data storage.
	 * When either does not complie return null.
	 * @param username  the string that login will attempt to find a corresensponding user
	 * @param password  the the string that login will attempt to find a corresensponding user
	 * @return A User if valid input and user in data storage else null.
	 */
	public static User logIn(String username, String password) {
		if (!validateUserName(username) || !validatePassword(password, password)) return null;
		User user = new User(username, password);
		User userFromDb = dao.findUser(user);
		if (userFromDb == null) return null;
		return userFromDb;
	}

	/**
	 * Checks if inputinfo is valid and makes a {@code User} in the data storage and return said {@code User}.
	 * 
	 * It checks if the {@code username} is in data storage if it is already in use return null, 
	 * else make a new return a {@code User} from data storage that match input. 
	 * Return said new {@code User}.
	 * 
	 * @param username         the string that will be will be 
	 * @param password         the string that will be saved as password for the new {@code User}
	 * @param confirmPassword  the string to compare password equal to
	 * @return  a new {@code User} that is saved in data storage. If already in storage return null.
	 */
	public static User signUp(String username, String password, String confirmPassword) {
		if (!validateUserName(username) || !validatePassword(password, confirmPassword)) return null;
		User user = new User(username, password);

		// check if user in db and add when not
		User userFromDb = dao.findUser(user);
		if (userFromDb != null) return null;
		dao.addUser(user);
		return user;
	}

	/**
	 * Checks if username is a non empty string only containing a combaination of letters and numbers.
	 * @param username the string to test
	 * @return true if username is valid else false
	 */
	private static boolean validateUserName(String username) {
		return isAlphanumericAndNonEmpty(username);
	}

	/**
	 * Checks if password is a non empty string only containing a combaination of letters and numbers and that confirmPassword matches password.
	 * 
	 * @param password         the string to check if valid
	 * @param confirmPassword  the string to check if equal to password
	 * @return true if password is valid else false
	 */
	private static boolean validatePassword(String password, String confirmPassword) {
		boolean correspondingPassewords = isCorrespondingPasswords(password, confirmPassword);
		boolean correctString = isAlphanumericAndNonEmpty(password);
		return (correspondingPassewords && correctString);
	}

	private static boolean isAlphanumericAndNonEmpty(String str) {
		boolean notEmpty = !str.isEmpty();
		boolean containsOnlyLettersAndNumbers = str.matches("[a-zA-Z0-9]*");
		return (notEmpty && containsOnlyLettersAndNumbers);
	}

	private static boolean isCorrespondingPasswords(String pass1, String pass2) {
		return pass1.equals(pass2);
	}

	public void setPath(String path) {
		((UserDAOImpl) dao).setPath(path);
	}
}