package fightinggame.ui;
import fightinggame.users.LoginSignup;
import fightinggame.users.User;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


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

        showTutorial();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();
        MainMenuController mainMenuController = loader.getController();
        mainMenuController.setUser(tempUser);
        super.changeScene("NTNU Fighterz", root, event);
    }

    private void showTutorial() {
        // create alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Welcome to NTNU Fighterz!");
        alert.setHeaderText("Welcome to NTNU Fighterz!");
        
        // get tutorial
        //TODO: Fix persistence
        String tutorial = "";
        try {
            File tutorialText = new File("../fxui/src/main/resources/fightinggame/ui/Tutorial.txt");
            tutorial = readFromFile(tutorialText);
        } catch (Exception e) {
            System.out.println(e);
        }

        String cssPath = getClass().getResource("css/popup.css").toString();

        // set and style content text
        TextArea area = new TextArea(tutorial);
        area.setId("TutorialContent");
        alert.getDialogPane().setContent(area);

        area.setWrapText(true);
        area.setEditable(false);
        area.getStylesheets().add(cssPath);
        area.getStyleClass().add("textarea");

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(cssPath);
        dialogPane.getStyleClass().add("popup");
        dialogPane.setPrefSize(1280, 720);;

        alert.showAndWait();
    }
    
    //TODO: fix persistence thing
    private static String readFromFile(File userFile) throws IOException {		
		String usersInfo = "";
        System.out.println(userFile);
		if (userFile.exists()){
            System.out.println("Yes");
			Scanner userFileReader = new Scanner(userFile);

			while(userFileReader.hasNextLine()) {
				String line = userFileReader.nextLine();
				usersInfo += line + "\n";
			}
			userFileReader.close();

		}
		return usersInfo;
    }

    @FXML 
    private void handleBackToLogin(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        super.changeScene("NTNU Fighterz", root, event);
    }

}
