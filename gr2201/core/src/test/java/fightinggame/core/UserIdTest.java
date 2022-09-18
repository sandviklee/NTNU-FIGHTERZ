package fightinggame.core;
import static org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Test;

public class UserIdTest {
	private String valideId = "Username1";
	private String otherValideId = "Username2";

	private String toShortId = "";
	private String specialCharactersId = ".";

	private UserId id;
	private UserId otherId;

	
	@Test
	@DisplayName
	public void testConstructor() {
		// Contructed with bad input values
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new UserId(toShortId);
		}, "This UserId is not possible");

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new UserId(specialCharactersId);
		}, "This UserId is not possible");

		id = new UserId(validePassword);
		assertTrue(id.getUserId().equals(validePassword), "Does not have correct field");
	}

	@Test
	@DisplayName
	public void testEquals() {
		id = new UserId(valideId);
		otherId = new UserId(otherValideId);
		assertFalse(id.equals(otherId), "userIds is not the same");
		assertTrue(id.equals(id), "userId should be the same");
	}

}
