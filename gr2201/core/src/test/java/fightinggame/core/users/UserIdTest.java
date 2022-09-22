package fightinggame.core.users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fightinggame.users.UserId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;

public class UserIdTest {
	private String valideId = "Username1";
	private String otherValideId = "Username2";

	private String toShortId = "";
	private String specialCharactersId = ".";

	private UserId id;
	private UserId otherId;

	
	@Test
	@DisplayName("Test")
	public void testConstructor() {
		// Contructed with bad input values
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new UserId(toShortId);
		}, "This UserId is not possible");

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new UserId(specialCharactersId);
		}, "This UserId is not possible");

		id = new UserId(valideId);
		assertTrue(id.getUserId().equals(valideId), "Does not have correct field");
	}

	@Test
	@DisplayName("Test if equals method works")
	public void testEquals() {
		id = new UserId(valideId);
		otherId = new UserId(otherValideId);
		assertFalse(id.equals(otherId), "userIds is not the same");
		assertTrue(id.equals(id), "userId should be the same");
	}

}
