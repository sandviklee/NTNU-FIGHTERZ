package fightinggame.dbaccess;

import fightinggame.users.User;
import fightinggame.users.UserId;

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
		File currentFile = new File(path);
		FileWriter currentWriter = new FileWriter(currentFile, false);
		currentWriter.write("");
		currentWriter.close();
	}

	@BeforeEach
	public void setup() {
		// path is to test data storage so running the test will not affect working data storage.
		// This will prevent loss of data of users.
		
		path = "gr2201/core/src/test/recources/fightinggame/dbaccess/";
		dao = new UserDAOImpl(path);
		((UserDAOImpl) dao).setPath(path);
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
		dao.addUser(testUser1.getUserId(), testUser1.getUserData());
		assertEquals(1, dao.getAllUsers().size(), "The amount is not correct should contain a user");
		assertTrue(dao.getAllUsers().get(0).equals(testUser1.getUserId().toString() + ", " + testUser1.getUserData().toString()), "The content of dao is not correct");
		
		// adding another user db and is it correct order
		dao.addUser(testUser2.getUserId(), testUser2.getUserData());
		assertEquals(2, dao.getAllUsers().size(), "The amount is not correct should contain two users");
		assertTrue(dao.getAllUsers().get(0).equals(testUser1.getUserId().toString() + ", " + testUser1.getUserData().toString()), "The content of dao is not correct, either not correct order or bad values");
		assertTrue(dao.getAllUsers().get(1).equals(testUser2.getUserId().toString() + ", " + testUser2.getUserData().toString()), "The content of dao is not correct, either not correct order or bad values");

		// adding another user db and is it correct order
		dao.addUser(testUser3.getUserId(), testUser3.getUserData());
		assertEquals(3, dao.getAllUsers().size(), "The amount is not correct should contain three users");
		assertTrue(dao.getAllUsers().get(0).equals(testUser1.getUserId().toString() + ", " + testUser1.getUserData().toString()), "The content of dao is not correct, either not correct order or bad values");
		assertTrue(dao.getAllUsers().get(1).equals(testUser2.getUserId().toString() + ", " + testUser2.getUserData().toString()), "The content of dao is not correct, either not correct order or bad values");
		assertTrue(dao.getAllUsers().get(2).equals(testUser3.getUserId().toString() + ", " + testUser3.getUserData().toString()), "The content of dao is not correct, either not correct order or bad values");
	}

	@Test
	@DisplayName("Does filterAllUsers() give all Users in Data Storage that ")
	public void testFilterAllUsers() {
		assertEquals(0, dao.getAllUsers().size(), "The amount is not correct, should not contain users");
		String filter = testUser1.getUserData().toString();
		String filterContainingAllEntries = ",";
		String nonFilter = testUser2.getUserData().toString();

		// Add a two users with same UserData and one with other UserData
		dao.addUser(testUser1.getUserId(), testUser1.getUserData());
		dao.addUser(testUser2.getUserId(), testUser1.getUserData());
		dao.addUser(testUser3.getUserId(), testUser3.getUserData());
		assertEquals(3, dao.getAllUsers().size(), "The amount is not correct should contain two users");
		
		assertTrue(dao.filterAllUsers(filter).get(0).equals(testUser1.getUserId().toString() + ", " + testUser1.getUserData().toString()), "The content of dao is not correct, either not correct order or bad values");
		dao.filterAllUsers(filter);

		for (int i = 0; i < 100; i++) {
			dao.addUser(new UserId(Integer.toString(i)), testUser1.getUserData());
			
			for (String user : dao.filterAllUsers(filter)) {
				assertTrue(user.equals(Integer.toString(i) + ", " + testUser1.getUserData().toString()), "The content of dao is not correct, either not correct order or bad values");
			}
		}
		assertEquals(102, dao.filterAllUsers(filter).size(), "The amount is not correct, should not contain many users");

		// check if superFilter gets all entries
		assertEquals(103, dao.filterAllUsers(filterContainingAllEntries).size(), "The amount is not correct, should contain many users");
		assertEquals(0, dao.filterAllUsers(nonFilter).size(), "The amount is not correct, should not contain any users");

	}

	@Test
	@DisplayName("Tests if updateUser() update given User in Data Storage")
	public void testUpdateUser() {
		// add testUser1
		dao.addUser(testUser1.getUserId(), testUser1.getUserData());
		assertTrue(dao.getAllUsers().get(0).equals(testUser1.getUserId().toString() + ", " + testUser1.getUserData().toString()), "The content of dao is not correct, bad values or none");
		dao.updateUser(testUser1.getUserId(), testUser2.getUserData());
		assertTrue(dao.getAllUsers().get(0).equals(testUser1.getUserId().toString() + ", " + testUser2.getUserData().toString()), "The content of dao is not correct, the updateUser() did not update testUser1s UserData");
		
		// Try to update a non added user, should not do anything
		dao.updateUser(testUser3.getUserId(), testUser3.getUserData());
		assertEquals(1, dao.getAllUsers().size(), "The amount is not correct should only contain one user");
		assertTrue(dao.getAllUsers().get(0).equals(testUser1.getUserId().toString() + ", " + testUser2.getUserData().toString()), "The content of dao is not correct, the updateUser() did update testUser1s UserData");
		
		// TODO DECISION: in case of bug where several users with same userId are in the data storage. Should it update all instances with same UserId or just the first?

	}

	@Test
	@DisplayName("Tests if deleteUser() removes correct users in Data Storage")
	public void testDeleteUser() {
		assertEquals(0, dao.getAllUsers().size(), "The amount is not correct, should not contain users");

		// Add three new user to db with different id.
		dao.addUser(testUser1.getUserId(), testUser1.getUserData());
		dao.addUser(testUser2.getUserId(), testUser1.getUserData());
		dao.addUser(testUser3.getUserId(), testUser1.getUserData());


		assertEquals(3, dao.getAllUsers().size(), "The amount is not correct should contain three users");
		dao.deleteUser(testUser1.getUserId());
		// Does it remove more then wanted
		assertEquals(2, dao.getAllUsers().size(), "The amount is not correct, should contain two");
		assertTrue(dao.getAllUsers().get(0).equals(testUser2.getUserId().toString() + ", " + testUser1.getUserData().toString()), "The content of dao is not correct, wrong value removed");
		assertTrue(dao.getAllUsers().get(1).equals(testUser3.getUserId().toString() + ", " + testUser1.getUserData().toString()), "The content of dao is not correct, wrong value removed");
		
		// When given a non existing Id does it not delete any values.
		dao.deleteUser(testUser1.getUserId());
		assertEquals(2, dao.getAllUsers().size(), "The amount is not correct, should contain two");
		assertTrue(dao.getAllUsers().get(0).equals(testUser2.getUserId().toString() + ", " + testUser1.getUserData().toString()), "The content of dao is not correct, wrong value removed");
		assertTrue(dao.getAllUsers().get(1).equals(testUser3.getUserId().toString() + ", " + testUser1.getUserData().toString()), "The content of dao is not correct, wrong value removed");

		// TODO DECISION: in case of bug where several users with same userId are in the data storage. Should it update all instances with same UserId or just the first?
	}

	@Test
	@DisplayName("Tests if addUser() adds the User in Data Storage")
	public void testAddUser() {
		assertEquals(0, dao.getAllUsers().size(), "The amount is not correct, should not contain users");

		// Add a new user to db
		dao.addUser(testUser1.getUserId(), testUser1.getUserData());
		assertEquals(1, dao.getAllUsers().size(), "The amount is not correct should only contain one user");
		assertTrue(dao.getAllUsers().get(0).equals(testUser1.getUserId().toString() + ", " + testUser1.getUserData().toString()), "The content of dao is not correct, bad values or none");

		// Add same user to db it should not be added
		dao.addUser(testUser1.getUserId(), testUser1.getUserData());
		assertEquals(1, dao.getAllUsers().size(), "The amount is not correct should only contain one user");
		assertTrue(dao.getAllUsers().get(0).equals(testUser1.getUserId().toString() + ", " + testUser1.getUserData().toString()), "The content of dao is not correct, values changed after addUser()");
	}

	// @Test
	// @DisplayName("Tests if addUser() adds the User in Data Storage")
	// public void testPath() {
	// 	UserDAO daoPathTest = new UserDAOImpl();
	// 	String testPath = new String();
	// 	daoPathTest.setPath(testPath);
	// 	assertTrue(daoPathTest.getPath().equals(testPath));
	// }
}
