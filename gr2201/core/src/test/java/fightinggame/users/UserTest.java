package fightinggame.users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fightinggame.users.User;


public class UserTest {
	private User validUser;
	private User nonValidUser;

	@BeforeEach
	public void setup() {
		validUser = new User("TestUsername", "TestPassword");
		nonValidUser = new User(",,,", ",,,");
	}

	@Test
	@DisplayName("Tests if the constructor works properly")
	public void testConstructor() {
		// Contructed with bad input values
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new User(toShortPassword);
		}, "This UserData is not possible");

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new UserData(specialCharactersPassword);
		}, "This UserData is not possible");

		data = new UserData(validePassword);
		assertTrue(data.getPassword().equals(validePassword), "Does not have correct field");
	}



	@Test
	@DisplayName("Does it equal correct")
	public void TestEquals() {
		// TODO:
	}

}
