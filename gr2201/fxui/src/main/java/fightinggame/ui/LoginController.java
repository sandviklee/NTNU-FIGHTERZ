package fightinggame.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import fightinggame.users.LoginSignup;
import fightinggame.users.User;

public class LoginController extends SceneController{
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorMessage;
    @FXML private Hyperlink swichToSignUp;
    @FXML private Button login;

    @FXML
    private void handleLogIn(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        User tempUser = LoginSignup.logIn(username, password);
        
        if (tempUser == null){
            errorMessage.setText("Wrong username or password");
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();
        MainMenuController mainMenuController = loader.getController();
        mainMenuController.setUser(tempUser);
        super.changeScene("NTNU Fighterz", root, event);
    }

    @FXML
    private void handleSwitchToSignup(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
        Parent root = loader.load();
        this.changeScene("NTNU Fighterz", root, event);
    }
}