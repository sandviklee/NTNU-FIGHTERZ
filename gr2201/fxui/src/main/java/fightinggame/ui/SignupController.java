package fightinggame.ui;
import fightinggame.users.LoginSignup;
import fightinggame.users.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Popup;


public class SignupController extends SceneController{
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Button createAccount, goBack;
    @FXML private Label nonValidCredentials;
    @FXML private Label nonMatchingPasswords;

    @FXML
    private void handleCreateAccount (ActionEvent event) throws IOException{
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        User tempUser = LoginSignup.signUp(username, password, confirmPassword);

        if (tempUser == null){
            if (password.equals(confirmPassword)){
                nonValidCredentials.setText("Username already exists or password/username do not fit criteria.");
            }
            else{
                nonMatchingPasswords.setText("Passwords do not match.");
            }
            return;
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Welcome to NTNU Fighterz!");
        alert.setHeaderText("Welcome to NTNU Fighterz!");

        File tutorialText = new File(getClass().getResource("Tutorial.txt").toExternalForm());
        Scanner reader = new Scanner(tutorialText);
        String tutorial = "";
        while (reader.hasNextLine()){
            tutorial = tutorial + reader.nextLine();
        }
        reader.close();

        Label label = new Label(tutorial);
        label.setWrapText(true);
        Font font = new Font("Comic Sans MS", 30);
        label.setFont(font);
        alert.getDialogPane().setContent(label);

        DialogPane dialogPane = alert.getDialogPane();
        String cssPath = getClass().getResource("css/popup.css").toString();
        dialogPane.getStylesheets().add(cssPath);
        dialogPane.getStyleClass().add("popup");
        dialogPane.setPrefSize(1080, 640);;
        ((Button) dialogPane.lookupButton(ButtonType.OK)).setFont(font);

        alert.showAndWait();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();
        MainMenuController mainMenuController = loader.getController();
        mainMenuController.setUser(tempUser);
        super.changeScene("NTNU Fighterz", root, event);
    }

    @FXML 
    private void handleBackToLogin(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        super.changeScene("NTNU Fighterz", root, event);
    }

}
