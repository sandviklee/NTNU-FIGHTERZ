package fightinggame.core;

import static org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Test;


public class UserDataTest {
	

	private String validePassword = "Password1";
	private String toShortPassword = "";
	private String specialCharactersPassword = "p.,sad";

	private UserData data;
	
	@Test
	@DisplayName
	public void testConstructor() {
		// Contructed with bad input values
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new UserData(toShortPassword);
		}, "This UserData is not possible");

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new UserData(specialCharactersPassword);
		}, "This UserData is not possible");

		data = new UserData(validePassword);
		assertTrue(data.getPassword().equals(validePassword), "Does not have correct field");
	}

	@Test
	@DisplayName
	public void test() {

	}

	@Test
	@DisplayName
	public void testStringToData() {

	}
}
