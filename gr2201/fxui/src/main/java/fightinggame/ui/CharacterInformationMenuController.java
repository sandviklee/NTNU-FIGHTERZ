package fightinggame.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class CharacterInformationMenuController extends SceneController{
    @FXML private GridPane characterSelectGrid;
    @FXML private Button goBack;

    @FXML
    private void handleSelectCharacter(ActionEvent event) throws IOException {
        ImageView image = (ImageView) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CharacterInformation.fxml"));
        Parent root = loader.load();
        CharacterInformationController controller = loader.getController();
        controller.setUser(super.getUser());
        controller.setCharacter(image.getId());
        super.changeScene("NTNU Fighterz", root, event);
    }
    
    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();
        MainMenuController controller = loader.getController();
        controller.setUser(super.getUser());
        super.changeScene("NTNU Fighterz", root, event);
    }
}
