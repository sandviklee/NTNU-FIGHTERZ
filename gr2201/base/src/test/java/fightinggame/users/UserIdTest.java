package fightinggame.users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;

public class UserIdTest {
	private String validId = "Username1";
	private String otherValidId = "Username2";

	private String toShortId = "";
	private String specialCharactersId = ".";

	private UserId id;
	private UserId otherId;

	
	@Test
	@DisplayName("Test constructor only make correct userIds")
	public void testConstructor() {
		// Constructed with bad input values
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new UserId(toShortId);
		}, "This UserId is not possible");

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new UserId(specialCharactersId);
		}, "This UserId is not possible");

		id = new UserId(validId);
		assertTrue(id.getUserId().equals(validId), "Does not have correct field");
	}

	@Test
	@DisplayName("Test if equals method works")
	public void testEquals() {
		id = new UserId(validId);
		otherId = new UserId(otherValidId);
		assertFalse(id.equals(otherId), "userIds is not the same");
		assertTrue(id.equals(id), "userId should be the same");
	}

	@Test
	@DisplayName("Test toString method")
	public void testToString() {
		id = new UserId(validId);
		assertEquals(validId, id.toString(), "toString does not create correct string");
	}


}
