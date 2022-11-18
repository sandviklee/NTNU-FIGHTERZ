package fightinggame.ui;

import fightinggame.users.User;

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

public class LoginController extends SceneController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorMessage;
    @FXML
    private Hyperlink swichToSignUp;
    @FXML
    private Button login;
    private RemoteModelAccess remoteModelAccess = new RemoteModelAccess();

    @FXML
    private void handleLogIn(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        User tempUser = remoteModelAccess.getUser(username, password);

        if (tempUser == null) {
            errorMessage.setText("Wrong username or password");
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root;
        try {
            root = loader.load();
            MainMenuController mainMenuController = loader.getController();
            mainMenuController.setUser(tempUser);
            super.changeScene("NTNU Fighterz", root, event);
        } catch (IOException e) {
            showError("Error: Invalid login path", "Something went wrong and main menu page could not be found.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSwitchToSignup(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
        Parent root;
        try {
            root = loader.load();
            this.changeScene("NTNU Fighterz", root, event);
        } catch (IOException e) {
            showError("Error: Invalid go back path", "Something went wrong and set up page could not be found.");
            e.printStackTrace();
        }
    }

}
