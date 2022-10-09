package fightinggame.ui;

import java.io.IOError;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
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

    @Test
    public void testInvalidPassword(){
        click("#passwordField");
        write("...");
        click("#confirmPasswordField");
        write("...");
        click("#usernameField");
        write("George");;
        click("#createAccount");
        Assertions.assertEquals("Username already exist or password and usermane does not fit criteria.", getLableText("#onValidCredentials"));
    }

    @Test
    public void testInvalidUsername(){
        click("#passwordField");
        write("Hans");
        click("#confirmPasswordField");
        write("Hans");
        click("#usernameField");
        write("...");
        click("#createAccount");
        Assertions.assertEquals("Username already exist or password and usermane does not fit criteria.", getLableText("#onValidCredentials"));
    }

    @Test
    public void testNonMatchingPassword(){
        click("#passwordField");
        write("Hans");
        click("#confirmPasswordField");
        write("Grete");
        click("#usernameField");
        write("Hugo");
        click("#createAccount");
        Assertions.assertEquals("Passwords not matching", getLableText("#nonMatchingPasswords"));
    }

    



}
