package fightinggame.ui;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class MainMenuController extends SceneController {
    @FXML private Button playSingleplayer, playMultiplayer, characterInfo, tutorial, exit;

    @FXML
    private void initialize(){
    }
    
    @FXML
    private void handlePlaySingleplayer(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CharacterSelectSingle.fxml"));
        Parent root = loader.load();
        SingleplayerSelectionController singleplayerSelectionController = loader.getController();
        singleplayerSelectionController.setUser(super.getUser());
        super.changeScene("NTNU Fighterz", root, event);
    }

    @FXML
    private void handlePlayMultiplayer(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CharacterSelectMulti.fxml"));
        Parent root = loader.load();
        MultiplayerSelectionController multiplayerSelectionController = loader.getController();
        multiplayerSelectionController.setUser(super.getUser());
        super.changeScene("NTNU Fighterz", root, event);
    }
    
    @FXML
    private void handleCharacterInfo(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CharacterInformationMenu.fxml"));
        Parent root = loader.load();
        CharacterInformationMenuController characterInformationMenuController = loader.getController();
        characterInformationMenuController.setUser(super.getUser());
        super.changeScene("NTNU Fighterz", root, event);
    }

    @FXML
    private void handleTutorial(ActionEvent event) {
        TutorialComponent tutorial = new TutorialComponent();
        tutorial.popupTutorial();
    }

    @FXML
    private void handleExit(ActionEvent event){
        Platform.exit();
    }

}
