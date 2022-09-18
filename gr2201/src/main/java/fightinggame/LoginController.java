package fightinggame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import fightinggame.core;

public class LoginController {
    @FXML TextField usernameField;
    @FXML PasswordField passwordField;
    @FXML TextArea errorMessage;
    @FXML Hyperlink swichToSignin;
    @FXML Button login;

    @FXML
    private void handleLogin(ActionEvent event){
        String username = usernameField.getText();
        String password = passwordField.getText();
        User user = LoginSignUp.login(username, password);
        
        if (user = null){
            errorMessage.setText("Wrong username or password");
            return;
        }
        

    

}
}