package fightinggame.dbAccess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserDAOTest {
	
	@BeforeEach
	public void setup() {
		UserDAO dao = new UserDAOImpl();
	}

	@Test
	@DisplayName("Does getAllUsers() give all Users in Data Storage")
	public void testGetAllUsers() {
		// TODO:
	}

	@Test
	@DisplayName("Does filterAllUsers() give all Users in Data Storage that ")
	public void testFilterAllUsers() {
		// TODO:
	}

	@Test
	@DisplayName("Does getAllUsers() give all Users in Data Storage")
	public void testUpdateUser() {
		// TODO:
	}

	@Test
	@DisplayName("Does getAllUsers() give all Users in Data Storage")
	public void testDeleteUser() {
		// TODO:
	}

	@Test
	@DisplayName("Does getAllUsers() give all Users in Data Storage")
	public void testAddUser() {
		// TODO:
	}
	
}
