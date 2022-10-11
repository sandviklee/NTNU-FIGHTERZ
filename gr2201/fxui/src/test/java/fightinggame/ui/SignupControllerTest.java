package fightinggame.ui;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
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

    private void click(String label){
        clickOn(LabeledMatchers.hasText(label));
    }

    private String getLabelText(String label){
        return ((Label) getRoot().lookup(label)).getText();
    }

    @Test
    public void testInvalidPassword(){
        clickOn("#passwordField").write("...");
        clickOn("#confirmPasswordField").write("...");
        clickOn("#usernameField").write("George");;
        click("Sign up And Log In");
        Assertions.assertEquals("Username already exists or password/username do not fit criteria.", getLabelText("#nonValidCredentials"));

    }

    @Test
    public void testInvalidUsername(){
        clickOn("#passwordField").write("Hans");
        clickOn("#confirmPasswordField").write("Hans");
        clickOn("#usernameField").write("...");
        click("Sign up And Log In");
        Assertions.assertEquals("Username already exists or password/username do not fit criteria.", getLabelText("#nonValidCredentials"));
    }

    @Test
    public void testNonMatchingPassword(){
        clickOn("#passwordField").write("Hans");
        clickOn("#confirmPasswordField").write("Grete");
        clickOn("#usernameField").write("Hugo");
        click("Sign up And Log In");
        Assertions.assertEquals("Passwords do not match.", getLabelText("#nonMatchingPasswords"));
    }

    @Test
    public void testBack(){
        clickOn("#goBack");
        Assertions.assertNotNull(getCurrentRootById("loginRoot"), "Wrong root when pressing goBack button");
        
    }

    private Parent getCurrentRootById(String id){
        for (Window window: Window.getWindows()){
            if (window.isShowing() && window instanceof Stage){
                if (window.getScene().getRoot().getId().equals(id)){
                    return window.getScene().getRoot();
                }
            }
        }
        return null;
    }






    



}
