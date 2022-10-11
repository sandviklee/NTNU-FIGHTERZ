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
import javafx.scene.control.Label;

public class LoginControllerTest extends ApplicationTest{
    private LoginController controller;
    private Parent root;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Login.fxml"));
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
    public void testInvalidUser(){
        clickOn("#passwordField").write("...");
        clickOn("#usernameField").write("Not a user");;
        click("Login");
        Assertions.assertEquals("Wrong username or password", getLableText("#errorMessage"));
    }

    
}
