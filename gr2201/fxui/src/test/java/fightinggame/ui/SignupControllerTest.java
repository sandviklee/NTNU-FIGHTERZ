package fightinggame.ui;

import java.io.IOError;
import java.io.IOException;


import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import  static org.junit.jupiter.api.Assertions.assertEqual;

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

  

    private String getErrorMessage(String label){
        return ((Label) getRoot().lookup(label)).getText();
    }

    @Test
    public void testInvalidPassword(){
        click("#passwordField");
        write("...");
        click("#usernameField");
        write("George");
        click("#createAccount");
        assertEqual()
    }



}
