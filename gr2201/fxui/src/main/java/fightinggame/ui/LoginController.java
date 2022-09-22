package fightinggame.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController extends SceneController{
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorMessage;
    @FXML private Hyperlink swichToSignUp;
    @FXML private Button login;

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        // String username = usernameField.getText();
        // String password = passwordField.getText();
        // // User user = LoginSignUp.login(username, password);
        
        // if (user == null){
        //     errorMessage.setText("Wrong username or password");
        //     return;
        // }
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        // Parent root = loader.load();
        // MainMenuController mainMenuController = loader.getController();
        // // mainMenuController.setUser(user);
        // SceneController.changeScene("NTNU Fighterz", root, event);
    }

    @FXML
    private void handleSwichToSignUp(ActionEvent event) throws IOException {
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
        // Parent root = loader.load();
        // SignUpController signUpController = loader.getController();
        // SceneController.changeScene("NTNU Fighterz", root, event);
    }
}
