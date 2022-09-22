package fightinggame.core;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class UserTest {
	private User u1;
	private User u2;

	@BeforeEach
	public void setup() {
		u1 = new User("TestUsername", "TestPassword");
		u2 = new User("TestUsername", "TestPassword");

	}


	@Test
	@DisplayName("Does it equal correct")
	public void TestEquals() {
		// TODO:
	}

}
