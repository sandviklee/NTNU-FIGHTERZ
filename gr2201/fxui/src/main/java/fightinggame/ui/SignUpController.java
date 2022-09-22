package fightinggame.ui;
import fightinggame.users.LoginSignUp;
import fightinggame.users.User;

import java.io.IOException;
import java.nio.file.Path;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;


public class SignUpController extends SceneController{
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Button createAccount, goBack;
    @FXML private TextArea nonValidCredentials;
    @FXML private TextArea nonMatchingPasswords;

    @FXML
    private void handleCreateAccount (ActionEvent event) throws IOException{
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        User tempUser = LoginSignUp.signUp(username, password, confirmPassword);

        if (tempUser == null){
            if (password.equals(confirmPassword)){
                nonValidCredentials.setText("Username already exist or password and usermane does not fit criteria.");
            }
            else{
                nonMatchingPasswords.setText("Passwords not matching");
            }
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();
        MainMenuController mainMenuController = loader.getController();
        mainMenuController.setUser(tempUser);
        this.changeScene("NTNU Fighterz", root, event);
    }

    @FXML 
    private void handleBackToLogin(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        LoginController loginController = loader.getController();
        this.changeScene("NTNU Fighterz", root, event);
    }

}
