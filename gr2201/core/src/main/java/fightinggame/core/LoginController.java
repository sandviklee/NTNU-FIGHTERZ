package fightinggame.core;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

import fightinggame.core;

public class LoginController {
    @FXML TextField usernameField;
    @FXML PasswordField passwordField;
    @FXML TextArea errorMessage;
    @FXML Hyperlink swichToSignUp;
    @FXML Button login;

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        User user = LoginSignUp.login(username, password);
        
        if (user = null){
            errorMessage.setText("Wrong username or password");
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        Parent root = loader.load();
        MainMenuController mainMenuController = loader.getController();
        mainMenuController.setUser(user);
        SceneController.changeScene("NTNU Fighterz", root, event);
    }

    @FXML
    private void handleSwichToSignUp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signUp.fxml"));
        Parent root = loader.load();
        SignUpController signUpController = loader.getController();
        SceneController.changeScene("NTNU Fighterz", root, event);
    }
}