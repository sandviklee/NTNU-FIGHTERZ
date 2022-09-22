package fightinggame.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class SingleplayerSelectionController {
    @FXML private Button lockIn, goBack;
    @FXML private GridPane characterSelectGrid;
    @FXML private ImageView characterSelected;

    @FXML
    private void initialize(){
        lockIn.setDisable(true);
    }

    @FXML
    private void handleSelectCharacter(ActionEvent event) {
        ImageView image = (ImageView) event.getSource();
        characterSelected.setImage(new Imageview(image));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        Parent root = loader.load();
        MainMenuController controller = loader.getController();
        controller.setUser(user);
        SceneController.changeScene("NTNU Fighterz", root, event);
    }
}
