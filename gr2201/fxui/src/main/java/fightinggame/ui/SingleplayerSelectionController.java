package fightinggame.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class SingleplayerSelectionController extends SceneController{
    @FXML private Button lockIn, goBack;
    @FXML private GridPane characterSelectGrid;
    @FXML private ImageView characterSelected;

    @FXML
    private void initialize(){
        lockIn.setDisable(true);
    }

    @FXML
    private void handleSelectCharacter(ActionEvent event) {
        Image image = (Image) event.getSource();
        characterSelected.setImage(image);
        ((ImageView) event.getSource()).setOpacity(0.7);
        lockIn.setDisable(false);
    }

    @FXML
    private void handleLockIn(ActionEvent event) throws IOException {
        // Future content
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("singleplayer.fxml"));
        // Parent root = loader.load();
        // SignUpController signUpController = loader.getController();
        // SceneController.changeScene("NTNU Fighterz", root, event);

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("This part of the game is not implemented yet :)");
        alert.showAndWait();
    }

    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        Parent root = loader.load();
        MainMenuController mainMenuController = loader.getController();
        mainMenuController.setUser(this.getUser());
        this.changeScene("NTNU Fighterz", root, event);
    }
}
