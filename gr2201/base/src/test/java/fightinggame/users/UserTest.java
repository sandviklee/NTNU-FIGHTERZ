package fightinggame.users;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fightinggame.users.User;


public class UserTest {
	private String validUserId = "username1";
	private String validUserIdOther = "username2";

	private String nonValidUserId = "pasdf.-,";
	private String validUserData = "password1";
	private String nonValidUserData = "pasdf.-,";

	private User user1;
	private User user2;

	@Test
	@DisplayName("Tests if the constructor works properly")
	public void testConstructor() {
		// Contructed with bad input values
		Assertions.assertThrows(NullPointerException.class, () -> {
			new User(nonValidUserId, nonValidUserData);
		}, "This UserData is not possible");

		Assertions.assertThrows(NullPointerException.class, () -> {
			new User(validUserId, nonValidUserData);
		}, "This UserData is not possible");

		Assertions.assertThrows(NullPointerException.class, () -> {
			new User(nonValidUserId, validUserData);
		}, "This UserData is not possible");

		// user = new User(validUserId, validUserData);
		// assertTrue(user.getPassword().equals(validePassword), "Does not have correct field");
	}



	@Test
	@DisplayName("Does it equal correct")
	public void TestEquals() {
		// TODO:
		user1 = new User(validUserId, validUserData);
		user2 = new User(validUserIdOther, validUserData);

		assertTrue(user1.equals(user1));
		assertFalse(user1.equals(user2));

	}

}
