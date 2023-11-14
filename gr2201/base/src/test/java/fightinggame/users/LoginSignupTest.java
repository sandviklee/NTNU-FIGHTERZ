package fightinggame.users;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fightinggame.users.LoginSignup;
import fightinggame.users.User;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSignupTest {
    private LoginSignup loginSignup = new LoginSignup();
    private String validUsername;
    private String validOtherUsername;

    private String nonValidUsername;
    private String validPassword1;
    private String validPassword2;
    private String nonValidPassword;

    private static void clearFile(String path) throws IOException {
        File currentFile = new File(path + "users.json");
        FileWriter currentWriter = new FileWriter(currentFile, false);
        currentWriter.write("");
        currentWriter.close();
    }

    @BeforeEach
    public void setup() {
        String path = "src/test/resources/"; // test path

        loginSignup.setPath(path);
        validUsername = "User1";
        validOtherUsername = "User2";

        nonValidUsername = "!,.*¨¨";
        validPassword1 = "Password1";
        validPassword2 = "Password2";
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
        User validUser = loginSignup.signUp(validUsername, validPassword1, validPassword1);

        // check for bad username good password
        User badPasUser = loginSignup.logIn(validUsername, nonValidPassword);
        assertTrue(badPasUser == null, "user not in db and input values are none valid");

        // check for bad password
        User badIdUser = loginSignup.logIn(nonValidPassword, validPassword1);
        assertTrue(badIdUser == null, "user not in db and input values are none valid");

        // check for good username and good password for user not in db
        User userNotInDb = loginSignup.logIn(validOtherUsername, validPassword2);
        assertTrue(userNotInDb == null, "user not in db and input values are none valid");

        // check for good username and good password for user in db
        User userInDb = loginSignup.logIn(validUsername, validPassword1);
        assertNotNull(userInDb);
        assertTrue(validUser.equals(userInDb));
    }

    @Test
    @DisplayName("Tests if signUp() creates user in Data Storage")
    public void testSignUp() {
        // Non matching passwords get null
        User badPasswordsUser = loginSignup.signUp(validUsername, validPassword1, validPassword2);
        assertTrue(badPasswordsUser == null, "user not in db and input values are none valid");

        // Non valid password get null
        User badPasUser = loginSignup.signUp(validUsername, nonValidPassword, nonValidPassword);
        assertTrue(badPasUser == null, "user not in db and input values are none valid");

        // Non valid Username get null
        User badIdUser = loginSignup.signUp(nonValidUsername, validPassword1, validPassword1);
        assertTrue(badIdUser == null, "user not in db and input values are none valid");

        // Valid username and character get user
        User validUser = loginSignup.signUp(validUsername, validPassword1, validPassword1);
        assertTrue(validUser.getUserId().getUserId().equals(validUsername), "user do not have correct id");
        assertTrue(validUser.getUserData().getPassword().equals(validPassword1),
                "user do not have the correct password");

        // Make same user get null
        User userAlreadyInDb = loginSignup.signUp(validUsername, validPassword1, validPassword1);
        assertTrue(userAlreadyInDb == null, "user not in db and input values are none valid");
    }
}
