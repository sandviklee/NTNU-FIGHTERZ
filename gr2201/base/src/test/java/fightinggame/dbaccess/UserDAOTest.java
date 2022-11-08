package fightinggame.dbaccess;

import fightinggame.users.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDAOTest {
	private UserDAO dao;

	private User testUser1;
	private User testUser2;
	private User testUser3;

	private String path;

	private static void clearFile(String path) throws IOException {
		File currentFile = new File(path + "users.json");
		FileWriter currentWriter = new FileWriter(currentFile);
		currentFile.createNewFile();
		currentWriter.write("");
		currentWriter.close();
	}

	@BeforeEach
	public void setup() {
		// path is to test data storage so running the test will not affect working data storage.
		// This will prevent loss of data of users.
		path = "src/test/resources/fightinggame/dbaccess/";

		dao = new UserDAOImpl(path);
		testUser1 = new User("Subject1", "123");
		testUser2 = new User("Subject2", "456");
		testUser3 = new User("Subject3", "789");
		try {
			clearFile(path);
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	@Test
	@DisplayName("Tests if getAllUsers() give all Users in Data Storage")
	public void testGetAllUsers() {
		assertEquals(0, dao.getAllUsers().size(), "The amount is not correct should be empty");
		// adding one user to db
		dao.addUser(testUser1);
		assertEquals(1, dao.getAllUsers().size(), "The amount is not correct should contain a user");
		assertTrue(dao.getAllUsers().get(0).equals(testUser1), "The content of dao is not correct");
		
		// adding another user db and is it correct order
		dao.addUser(testUser2);
		assertEquals(2, dao.getAllUsers().size(), "The amount is not correct should contain two users");
		assertTrue(dao.getAllUsers().get(0).equals(testUser1), "The content of dao is not correct, either not correct order or bad values");
		assertTrue(dao.getAllUsers().get(1).equals(testUser2), "The content of dao is not correct, either not correct order or bad values");

		// adding another user db and is it correct order
		dao.addUser(testUser3);
		assertEquals(3, dao.getAllUsers().size(), "The amount is not correct should contain three users");
		assertTrue(dao.getAllUsers().get(0).equals(testUser1), "The content of dao is not correct, either not correct order or bad values");
		assertTrue(dao.getAllUsers().get(1).equals(testUser2), "The content of dao is not correct, either not correct order or bad values");
		assertTrue(dao.getAllUsers().get(2).equals(testUser3), "The content of dao is not correct, either not correct order or bad values");
	}

	@Test
	@DisplayName("Tests if updateUser() update given User in Data Storage")
	public void testUpdateUser() {
		// add testUser1
		dao.addUser(testUser1);
		assertTrue(dao.getAllUsers().get(0).equals(testUser1), "The content of dao is not correct, bad values or none");
		dao.updateUser(testUser1.getUserId(), testUser2.getUserData());
		assertTrue(dao.getAllUsers().get(0).equals(testUser1), "The content of dao is not correct, the updateUser() did not update testUser1s UserData");
		
		// Try to update a non added user, should not do anything
		dao.updateUser(testUser3.getUserId(), testUser3.getUserData());
		assertEquals(1, dao.getAllUsers().size(), "The amount is not correct should only contain one user");
		assertTrue(dao.getAllUsers().get(0).equals(testUser1), "The content of dao is not correct, the updateUser() did update testUser1s UserData");
	}

	@Test
	@DisplayName("Tests if deleteUser() removes correct users in Data Storage")
	public void testDeleteUser() {
		assertEquals(0, dao.getAllUsers().size(), "The amount is not correct, should not contain users");

		// Add three new users to db with different id.
		dao.addUser(testUser1);
		dao.addUser(testUser2);
		dao.addUser(testUser3);


		assertEquals(3, dao.getAllUsers().size(), "The amount is not correct should contain three users");
		dao.deleteUser(testUser1.getUserId());
		// Does it remove more than wanted
		assertEquals(2, dao.getAllUsers().size(), "The amount is not correct, should contain two");
		assertTrue(dao.getAllUsers().get(0).equals(testUser2), "The content of dao is not correct, wrong value removed");
		assertTrue(dao.getAllUsers().get(1).equals(testUser3), "The content of dao is not correct, wrong value removed");
		
		// When given a non existent id does not delete any values.
		dao.deleteUser(testUser1.getUserId());
		assertEquals(2, dao.getAllUsers().size(), "The amount is not correct, should contain two");
		assertTrue(dao.getAllUsers().get(0).equals(testUser2), "The content of dao is not correct, wrong value removed");
		assertTrue(dao.getAllUsers().get(1).equals(testUser3), "The content of dao is not correct, wrong value removed");
	}

	@Test
	@DisplayName("Tests if addUser() adds the User in Data Storage")
	public void testAddUser() {
		assertEquals(0, dao.getAllUsers().size(), "The amount is not correct, should not contain users");

		// Add a new user to db
		dao.addUser(testUser1);
		assertEquals(1, dao.getAllUsers().size(), "The amount is not correct should only contain one user");
		assertTrue(dao.getAllUsers().get(0).equals(testUser1), "The content of dao is not correct, bad values or none");

		// Try to add same user to db, should not be added.
		dao.addUser(testUser1);
		assertEquals(1, dao.getAllUsers().size(), "The amount is not correct should only contain one user");
		assertTrue(dao.getAllUsers().get(0).equals(testUser1), "The content of dao is not correct, values changed after addUser()");
	}
}
