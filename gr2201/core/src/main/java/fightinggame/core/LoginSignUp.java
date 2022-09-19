package fightinggame.core;

import fightinggame.dbAccess;
/**
 * LoginSignUp will handle all login and sign up functions and validate input.
 */
public class LoginSignUp {
	private static UserDAO dao = new UserDAOImpl();

	/**
	 * Checks if inputinfo is valide and return a {@code User} from data storage that match input.
	 * 
	 * For valide input check if a user with corresensponding username and password is contained in the data storage.
	 * When either does not complie return null.
	 * @param username  the string that login will attempt to find a corresensponding user
	 * @param password  the the string that login will attempt to find a corresensponding user
	 * @return A User if valide input and user in data storage else null.
	 */
	public static User login(String username, String password) {
		if (!validateUserName(username) || !validatePassword(password, password)) return null;
		try {
			User user = new User(username, password);
			String userData = dao.findUser(user.getUserId());
			if (userData.isEmpty()) return null;

			// check if password is the same in db as login input
			if (password.equals(userData.split(",")[1])) return null;
			user.changeUserData(userData);
			return user;
		} catch (IllegalArgumentException e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}

	/**
	 * Checks if inputinfo is valide and makes a {@code User} in the data storage and return said {@code User}.
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
		try {
			User user = new User(username, password);
	
			// check if user in db and add when not
			String userData = dao.findUser(user.getUserId());
			if (userData.isEmpty()) return null;
			dao.addUser(user.getUserId(), user.getUserData());
			return user;
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}

	/**
	 * Checks if username is a non empty string only containing a combaination of letters and numbers.
	 * @param username the string to test
	 * @return true if username is valide else false
	 */
	private static boolean validateUserName(String username) {
		return containsOnlyLettersAndNumbers(username);
	}

	/**
	 * Checks if password is a non empty string only containing a combaination of letters and numbers and that confirmPassword matches password.
	 * 
	 * @param password         the string to check if valide
	 * @param confirmPassword  the string to check if equal to password
	 * @return true if password is valide else false
	 */
	private static boolean validatePassword(String password, String confirmPassword) {
		boolean CorrespondingPassewords = isCorrespondingPasswords(password, confirmPassword);
		boolean correctString = containsOnlyLettersAndNumbers(password);
		return (CorrespondingPassewords && correctString);
	}

	private static boolean containsOnlyLettersAndNumbers(String str) {
		boolean notEmpty = !password.isEmpty();
		boolean containsOnlyLettersAndNumbers = password.matches("[a-zA-Z0-9]*");
		return (notEmpty && containsOnlyLettersAndNumbers);
	}

	private static boolean isCorrespondingPasswords(String p1, String p2) {
		return p1.equals(p2);
	}

	/**
	 * hashPassword shall hash password.
	 */
	private static int hashPassword(String p) {
		// TODO: Find a good hashing algo and a decoder.
	}

	public void setPath(Path p) {
		this.getDAO().setPath(p);
	}
}
