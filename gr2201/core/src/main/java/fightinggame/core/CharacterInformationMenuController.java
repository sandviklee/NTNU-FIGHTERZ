package fightinggame.core;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class CharacterInformationMenuController {
    @FXML private GridPane characterSelectGrid;
    @FXML private Button goBack;

    @FXML
    private void handleSelectCharacter(ActionEvent event) {
        ImageView image = (ImageView) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CharacterInformation.fxml"));
        Parent root = loader.load();
        CharacterInformationController controller = loader.getController();
        controller.setUser(user);
        controller.setCharacter(image.getId());
        SceneController.changeScene("NTNU Fighterz", root, event);
    }
    
    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();
        MainMenuController controller = loader.getController();
        controller.setUser(user);
        SceneController.changeScene("NTNU Fighterz", root, event);
    }
}
