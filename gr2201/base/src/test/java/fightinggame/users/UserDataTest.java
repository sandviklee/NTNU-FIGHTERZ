package fightinggame.users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;


public class UserDataTest {
	

	private String validePassword = "Password1";
	private String validePasswordOther = "Password2";
	private String toShortPassword = "";
	private String specialCharactersPassword = "p.,sad";

	private UserData data1;
	private UserData data2;

	@Test
	@DisplayName("Tests if the constructor works properly")
	public void testConstructor() {
		// Contructed with bad input values
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new UserData(toShortPassword);
		}, "This UserData is not possible");

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new UserData(specialCharactersPassword);
		}, "This UserData is not possible");

		data1 = new UserData(validePassword);
		assertTrue(data1.getPassword().equals(validePassword), "Does not have correct field");
	}

	@Test
	@DisplayName("Tests if the equals works properly")
	public void TestEquals() {
		UserData data1 = new UserData(validePassword);
		UserData data2 = new UserData(validePasswordOther);

		assertTrue(data1.equals(data1));
		assertFalse(data1.equals(data2));
	}
}
