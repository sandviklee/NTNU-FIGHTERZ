package fightinggame.users;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

// import javafx.scene.shape.Path;

public class LoginSignUpTest {
	private LoginSignUp loginSignUp = new LoginSignUp();
	private String valideUsername;
	private String nonValideUsername;
	private String validePassword1;
	private String validePassword2;
	private String nonValidPassword;

	private static void clearFile(String path) throws IOException {
		// TODO make file at path clear
		File currentFile = new File(path);
		FileWriter currentWriter = new FileWriter(currentFile, false);
		currentWriter.write("");
		currentWriter.close();
	}

	
	@BeforeEach
	public void setup() {
		String path = "gr2201/gr2201/core/src/test/recources/fightinggame/dbaccess/"; // test path
		loginSignUp.setPath(path);
		valideUsername = "User1";
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
	public void testLogin() {
		// must make sure User in db
		User valideUser = loginSignUp.signUp(valideUsername, validePassword1, validePassword1);

		// if (valideUser == null) 
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
		assertNotNull(userInDb);
		assertTrue(valideUser.equals(userInDb));
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
