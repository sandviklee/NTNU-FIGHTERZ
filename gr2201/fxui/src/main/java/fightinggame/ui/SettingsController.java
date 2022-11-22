package fightinggame.ui;

import java.io.IOException;

import fightinggame.users.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SettingsController extends SceneController {
    @FXML private TextField confirmPasswordField, passwordField, confirmDelete;
    @FXML private Label feedback, username;
    @FXML private Button changePasswordButton, goBack, deleteUser;
    private RemoteModelAccess remoteModelAccess = new RemoteModelAccess();

    public void updateView(){
        //username.setText(getUser().getUserId().getUserId());
        username.setText("Username");
    }

    @FXML
    private void handleChangePassword(ActionEvent event) {
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!password.equals(confirmPassword)) {
            feedback.setText("Passwords do not match.");
            return;
        }

        User tempUser = new User(getUser().getUserId(), getUser().getUserData());
        tempUser.getUserData().changePassword(password);
        System.out.println("The tempUser:" + tempUser);
        boolean changedPassword = remoteModelAccess.putUser(tempUser);

        if (changedPassword) {
            getUser().getUserData().changePassword(password);
            feedback.setText("Changed password!");
        } else {
            feedback.setText("Invalid password, only use letter and numbers.");
        }
    }

    @FXML
    private void handleDeleteUser(ActionEvent event){
        if (!confirmDelete.getText().equals("DELETE")){
            feedback.setText("To delete your user, type 'DELETE' in the field bellow");
            return;
        }

        if (remoteModelAccess.deleteUser(getUser())){
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
        feedback.setText("Could not delete user");
    }

    @FXML
    private void handleGoBack(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();
        MainMenuController mainMenuController = loader.getController();
        mainMenuController.setUser(super.getUser());
        super.changeScene("NTNU Fighterz", root, event);
    }
}
