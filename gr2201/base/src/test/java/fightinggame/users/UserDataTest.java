package fightinggame.users;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;


public class UserDataTest {
	private String validPassword = "Password1";
	private String validPasswordOther = "Password2";
	private String toShortPassword = "";
	private String specialCharactersPassword = "p.,sad";

	private UserData data1;
	private UserData data2;

	@Test
	@DisplayName("Tests if the constructor works properly")
	public void testConstructor() {
		// Constructed with bad input values
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new UserData(toShortPassword);
		}, "This UserData is not possible");

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new UserData(specialCharactersPassword);
		}, "This UserData is not possible");

		data1 = new UserData(validPassword);
		assertTrue(data1.getPassword().equals(validPassword), "Does not have correct field");
	}

	@Test
	@DisplayName("Tests if the equals works properly")
	public void TestEquals() {
		data1 = new UserData(validPassword);
		data2 = new UserData(validPasswordOther);

		assertTrue(data1.equals(data1));
		assertFalse(data1.equals(data2));
	}

	@Test
	@DisplayName("Tests if the getPassword works properly")
	public void TestToString() {
		data1 = new UserData(validPassword);
		assertEquals(validPassword, data1.toString());
	}

	@Test
	@DisplayName("Tests if the setPassword works properly")
	public void TestSetPassword() {
		data1 = new UserData(validPassword);
		assertEquals(validPassword, data1.getPassword());
		data1.changePassword(validPasswordOther);
		assertEquals(validPasswordOther, data1.getPassword());
		// Dont change password on bad password
		data1.changePassword(specialCharactersPassword);
		assertEquals(validPasswordOther, data1.getPassword());

	}

}
