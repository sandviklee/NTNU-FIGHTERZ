package fightinggame.core;

import static org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Test;

// import javafx.scene.shape.Path;

public class LoginSignUpTest {
	private LoginSignUp loginSignUp = new LoginSignUp();
	private String valideUsername;
	private String nonValideUsername;
	private String validePassword1;
	private String validePassword2;
	private String nonValidPassword;

	private static void clearFile(Path path) {
		// TODO make file at path clear
		File currentFile = new File(path);
		FileWriter currentWriter = new FileWriter(currentFile, false);
		currentWriter.write("");
		currentWriter.close();
	}

	
	@BeforeEach
	public void setup() {
		Path path = new Path("gr2201/gr2201/dbAccess/src/test/resource/fightinggame/dbAccess/testUsers.txt"); // test path
		loginSignUp.setPath(path);
		valideUsername = "User1";
		nonValideUsername = "!,.*¨¨";
		validePassword1 = "Password1";
		validePassword2 = "Password2";
		nonValidPassword = "pa,ssword";

		clearFile(path);
	}

	@Test
	@DisplayName("Tests if getAllUsers() give all Users in Data Storage")
	public void testLogin() {
		// must make sure User in db
		User valideUser = loginSignUp.signUp(valideUsername, validePassword1, validePassword1);

		// check for bad username good password
		User badPasUser = loginSignUp.login(valideUsername, nonValidPassword);
		assertTrue(badPasUser == null, "user not in db and input values are none valide");

		// check for bad password
		User badIdUser = loginSignUp.login(nonValidPassword, validePassword1);
		assertTrue(badIdUser == null, "user not in db and input values are none valide");

		// check for good username and good password for user not in db
		User userNotInDb = loginSignUp.login(valideUsername, validePassword2);
		assertTrue(userNotInDb == null, "user not in db and input values are none valide");

		// check for good username and good password for user in db
		User userInDb = loginSignUp.login(valideUsername, validePassword1);
		assertTrue(userNotInDb.equals(valideUser));
	}

	@Test
	@DisplayName("Tests if signUp() creates user in Data Storage")
	public void testSignUp() {
		// Non matching passwords get null
		User badPasswordsUser = loginSignUp.signUp(valideUsername, validePassword1, validePassword2);
		assertTrue(badPasswordsUser == null, "user not in db and input values are none valide");

		// Non valid password get null
		User badPasUser = loginSignUp.signUp(valideUsername, nonValidPassword, nonValidPassword);
		assertTrue(badPasUser == null, "user not in db and input values are none valide");

		// Non valid Username get null
		User badIdUser = loginSignUp.signUp(nonValideUsername, validePassword1, validePassword1);
		assertTrue(badIdUser == null, "user not in db and input values are none valide");

		// Valide username and character get user
		User valideUser = loginSignUp.signUp(valideUsername, validePassword1, validePassword1);
		assertTrue(valideUser.getUserId().getUserId().equals(valideUsername), "user do not have correct id");
		assertTrue(valideUser.getUserData().getPassword().equals(validePassword1), "user do not have the correct password");

		// Make same user get null
		User userAlreadyInDb = loginSignUp.signUp(valideUsername, validePassword1, validePassword1);
		assertTrue(userAlreadyInDb == null, "user not in db and input values are none valide");
	}
}
