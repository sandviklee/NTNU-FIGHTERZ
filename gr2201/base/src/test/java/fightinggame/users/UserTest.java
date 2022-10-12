package fightinggame.users;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



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
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new User(nonValidUserId, nonValidUserData);
		}, "This UserData is not possible");

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new User(validUserId, nonValidUserData);
		}, "This UserData is not possible");

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new User(nonValidUserId, validUserData);
		}, "This UserData is not possible");

		// User with good input
		user1 = new User(validUserId, validUserData);
		assertTrue(user1.getUserName().equals(validUserId), "Does not have correct field");
		assertTrue(user1.getPassword().equals(validUserData), "Does not have correct field");
	}

	@Test
	@DisplayName("Does it equals correct")
	public void TestEquals() {
		user1 = new User(validUserId, validUserData);
		user2 = new User(validUserIdOther, validUserData);

		assertTrue(user1.equals(user1));
		assertFalse(user1.equals(user2));
	}

	@Test
	@DisplayName("Does it equals correct")
	public void testChangeUserData() {
		user1 = new User(validUserId, validUserData);
		user1.changeUserData(new UserData(validUserIdOther));
		assertTrue(user1.getUserId().equals(new UserId(validUserId)));
		assertTrue(user1.getUserData().equals(new UserData(validUserIdOther)));
	}

	@Test
	public void testChangeUserId() {
		user1 = new User(validUserId, validUserData);
		user1.changeUserId(new UserId(validUserIdOther));
		assertTrue(user1.getUserId().equals(new UserId(validUserIdOther)));
		assertTrue(user1.getUserData().equals(new UserData(validUserData)));
	}
}
