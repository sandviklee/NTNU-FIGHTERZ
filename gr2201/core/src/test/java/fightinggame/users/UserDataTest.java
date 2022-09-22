package fightinggame.users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fightinggame.users.UserData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;


public class UserDataTest {
	

	private String validePassword = "Password1";
	private String toShortPassword = "";
	private String specialCharactersPassword = "p.,sad";

	private UserData data;
	
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

		data = new UserData(validePassword);
		assertTrue(data.getPassword().equals(validePassword), "Does not have correct field");
	}

	@Test
	@DisplayName("Tests if the ")
	public void testStringToData() {
		//TODO: make a string an try to get correct UserDAta
	}
}
