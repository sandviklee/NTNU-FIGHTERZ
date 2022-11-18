package fightinggame.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class CharacterInformationMenuController extends SceneController {
    @FXML
    private GridPane characterSelectGrid;
    @FXML
    private Button goBack;

    @FXML
    private void handleSelectCharacter(MouseEvent event) {
        ImageView image = (ImageView) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CharacterInformation.fxml"));
        Parent root;
        try {
            root = loader.load();
            CharacterInformationController controller = loader.getController();
            controller.setUser(super.getUser());
            controller.setCharacter(image.getId());
            super.changeSceneMouseEvent("NTNU Fighterz", root, event);
        } catch (IllegalArgumentException | NullPointerException e) {
            showError("Error: Could not instantiate character.",
                    "Something went wrong and character information files could not be found or contained wrong information.");
        } catch (IOException e) {
            showError("Error: Invalid game characters file path.",
                    "Something went wrong and character game files could not be found.");
        }
    }

    @FXML
    private void handleGoBack(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root;
        try {
            root = loader.load();
            MainMenuController controller = loader.getController();
            controller.setUser(super.getUser());
            super.changeScene("NTNU Fighterz", root, event);
        } catch (IOException e) {
            showError("Error: Invalid go back path", "Something went wrong and main menu files could not be found.");
            e.printStackTrace();
        }

    }
}
