package fightinggame.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;

public class SingleplayerSelectionController extends SceneController{
    @FXML private Button lockIn, goBack;
    @FXML private GridPane characterSelectGrid;
    @FXML private ImageView characterSelected;

    @FXML
    private void initialize(){
        lockIn.setDisable(true);
    }

    private void resetCharacterImageOpacity() {
        for (Node imageView : characterSelectGrid.getChildren()) {
            imageView.setOpacity(1);
        }
    }

    @FXML
    private void handleSelectCharacter(MouseEvent event) {
        resetCharacterImageOpacity();
        ImageView image = (ImageView) event.getSource();
        characterSelected.setImage(new Image(getClass().getResource(image.getId() + "SplashArt.png").toString()));
        image.setOpacity(0.7);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();
        MainMenuController mainMenuController = loader.getController();
        mainMenuController.setUser(super.getUser());
        super.changeScene("NTNU Fighterz", root, event);
    }
}
