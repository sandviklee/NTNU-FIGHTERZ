package fightinggame.users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fightinggame.users.User;


public class UserTest {
	private String validUserId;
	private String nonValidUserId;
	private String validUserData;
	private String nonValidUserData;

	private User user;

	@Test
	@DisplayName("Tests if the constructor works properly")
	public void testConstructor() {
		// Contructed with bad input values
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new User(nonValidUserId, nonValidUserData);
		}, "This UserData is not possible");

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new User(validUserId, nonValidUserData);
		}, "This UserData is not possible");

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new User(nonValidUserId, validUserData);
		}, "This UserData is not possible");

		// user = new User(validUserId, validUserData);
		// assertTrue(user.getPassword().equals(validePassword), "Does not have correct field");
	}



	@Test
	@DisplayName("Does it equal correct")
	public void TestEquals() {
		// TODO:
	}

}
