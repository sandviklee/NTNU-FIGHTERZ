package fightinggame.users;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSignupTest {
	private LoginSignup loginSignup = new LoginSignup();
	private String valideUsername;
	private String valideOtherUsername;

	private String nonValideUsername;
	private String validePassword1;
	private String validePassword2;
	private String nonValidPassword;

	private static void clearFile(String path) throws IOException {
		File currentFile = new File(path + "users.json");
		FileWriter currentWriter = new FileWriter(currentFile, false);
		currentWriter.write("");
		currentWriter.close();
	}


	@BeforeEach
	public void setup() {
		String path = "src/test/resources/fightinggame/dbaccess/"; // test path
		

		loginSignup.setPath(path);
		valideUsername = "User1";
		valideOtherUsername = "User2";

		nonValideUsername = "!,.*¨¨";
		validePassword1 = "Password1";
		validePassword2 = "Password2";
		nonValidPassword = "pa,ssword";

		try {
			clearFile(path);
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	@Test
	@DisplayName("Tests if getAllUsers() give all Users in Data Storage")
	public void testlogIn() {
		// must make sure User in db
		User valideUser = loginSignup.signUp(valideUsername, validePassword1, validePassword1);

		// check for bad username good password
		User badPasUser = loginSignup.logIn(valideUsername, nonValidPassword);
		assertTrue(badPasUser == null, "user not in db and input values are none valide");

		// check for bad password
		User badIdUser = loginSignup.logIn(nonValidPassword, validePassword1);
		assertTrue(badIdUser == null, "user not in db and input values are none valide");

		// check for good username and good password for user not in db
		User userNotInDb = loginSignup.logIn(valideOtherUsername, validePassword2);
		assertTrue(userNotInDb == null, "user not in db and input values are none valide");

		// check for good username and good password for user in db
		User userInDb = loginSignup.logIn(valideUsername, validePassword1);
		assertNotNull(userInDb);
		assertTrue(valideUser.equals(userInDb));
	}

	@Test
	@DisplayName("Tests if signUp() creates user in Data Storage")
	public void testSignUp() {
		// Non matching passwords get null
		User badPasswordsUser = loginSignup.signUp(valideUsername, validePassword1, validePassword2);
		assertTrue(badPasswordsUser == null, "user not in db and input values are none valide");

		// Non valid password get null
		User badPasUser = loginSignup.signUp(valideUsername, nonValidPassword, nonValidPassword);
		assertTrue(badPasUser == null, "user not in db and input values are none valide");

		// Non valid Username get null
		User badIdUser = loginSignup.signUp(nonValideUsername, validePassword1, validePassword1);
		assertTrue(badIdUser == null, "user not in db and input values are none valide");

		// Valide username and character get user
		User valideUser = loginSignup.signUp(valideUsername, validePassword1, validePassword1);
		assertTrue(valideUser.getUserId().getUserId().equals(valideUsername), "user do not have correct id");
		assertTrue(valideUser.getUserData().getPassword().equals(validePassword1), "user do not have the correct password");

		// Make same user get null
		User userAlreadyInDb = loginSignup.signUp(valideUsername, validePassword1, validePassword1);
		assertTrue(userAlreadyInDb == null, "user not in db and input values are none valide");
	}
}
