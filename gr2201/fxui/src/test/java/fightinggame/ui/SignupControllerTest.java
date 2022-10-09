package fightinggame.ui;

import java.io.IOError;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class SignupControllerTest extends ApplicationTest {
    SignupController controller;
    Parent root;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Signup.fxml"));
        root = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private Parent getRoot () {
        return root;
    }

    private void click(String lable){
        clickOn(LabeledMatchers.hasText(lable));
    }

    private String getLableText(String label){
        return ((Label) getRoot().lookup(label)).getText();
    }

    @BeforeEach
    public void setup(){
        clickOn("#usernameField").eraseText(10);
        clickOn("#passwordField").eraseText(10);
        clickOn("#confirmPasswordField").eraseText(10);
        

    }

    @Test
    public void testInvalidPassword(){
        clickOn("#passwordField").write("...");
        clickOn("#confirmPasswordField").write("...");
        clickOn("#usernameField").write("George");;
        click("Sign up And Log In");
        Assertions.assertEquals("Username already exist or password and usermane does not fit criteria.", getLableText("#onValidCredentials"));

    }

    @Test
    public void testInvalidUsername(){
        clickOn("#passwordField").write("Hans");
        clickOn("#confirmPasswordField").write("Hans");
        clickOn("#usernameField").write("...");
        click("Sign up And Log In");
        Assertions.assertEquals("Username already exist or password and usermane does not fit criteria.", getLableText("#onValidCredentials"));
    }

    @Test
    public void testNonMatchingPassword(){
        clickOn("#passwordField").write("Hans");
        clickOn("#confirmPasswordField").write("Grete");
        clickOn("#usernameField").write("Hugo");
        click("Sign up And Log In");
        Assertions.assertEquals("Passwords not matching", getLableText("#nonMatchingPasswords"));
    }




    



}
