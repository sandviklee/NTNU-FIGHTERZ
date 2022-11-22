package fightinggame.springboot.restserver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fightinggame.users.User;

public class RestServerServiceTest {
	private final ServerService serverService = new ServerService();

	private final User userInDb = new User("s", "s");
	private final User userInDbOther = new User("THISUSERISINDB", "THISUSERISINDBPASSWORD");

	private final User userNotInDb = new User("THISUSERISNOTINDB", "THISUSERISNOTINDBPASSWORD");

	@BeforeEach
	public void setup() {
		serverService.postUser(userInDb.getUserName(), userInDb.getPassword(), userInDb.getPassword());
		serverService.postUser(userInDbOther.getUserName(), userInDbOther.getPassword(), userInDbOther.getPassword());
		serverService.deleteUser(userNotInDb.getUserId());

	}

	@Test
	@DisplayName("Test if the getter works")
	public void testGetUser() {
		assertTrue(userInDb.equals(serverService.getUser(userInDb.getUserName(), userInDb.getPassword())),
				"The user is expected to be in db");

		assertTrue(null == serverService.getUser(userNotInDb.getUserName(),
				userNotInDb.getPassword()),
				"The user is not expected to be in db");
	}

	@Test
	@DisplayName("Test if the post works")
	public void testPostUser() {
		assertTrue(userNotInDb.equals(serverService.postUser(userNotInDb.getUserName(), userNotInDb.getPassword(),
				userNotInDb.getPassword())),
				"The user is not expected to be in db and should be addable");

		assertTrue(null == serverService.postUser(userInDb.getUserName(),
				userInDb.getPassword(),
				userInDb.getPassword()),
				"The user is expected to be in db and should not be addable");
	}

	@Test
	@DisplayName("Test if the put works")
	public void testPutUser() {
		assertTrue(serverService.updateUser(userInDbOther.getUserId().getUserId(), userInDb.getUserData().getPassword()), "The user is expected to update");
		assertFalse(serverService.updateUser(userNotInDb.getUserId().getUserId(), userInDb.getUserData().getPassword()), "The user is not expected to update");
	}

	@Test
	@DisplayName("Test the delete endpoint")
	public void testDeleteUser() {
		assertTrue(serverService.deleteUser(userInDb.getUserId()),
				"The user is expected to be in db and should be deletable");

		assertFalse(serverService.deleteUser(userNotInDb.getUserId()),
				"The user is expected to be in db and should not be deletable");
	}
}
