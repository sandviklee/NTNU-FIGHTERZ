package fightinggame.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class MainMenuController {
    @FXML private Button playSingleplayer, playMultiplayer, characterInfo, settings, exit;

    @FXML
    private void initialize(){
        playMultiplayer.setDisable(true);
        settings.setDisable(true);
        exit.setDisable(true);
    }
    
    @FXML
    private void handlePlaySingleplayer(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Singleplayer.fxml"));
        Parent root = loader.load();
        SingleplayerSelectionController singleplayerSelectionController = loader.getController();
        singleplayerSelectionController.setUser(user);
        SceneController.changeScene("NTNU Fighterz", root, event);
    }

    @FXML
    private void handlePlayMultiplayer(ActionEvent event){
        //todo
        return;
    }
    
    @FXML
    private void handleCharacterInfo(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CharacterInfo.fxml"));
        Parent root = loader.load();
        CharacterInformationController characterInformationController = loader.getController();
        characterInformationController.setUser(user);
        SceneController.changeScene("NTNU Fighterz", root, event);
    }

    @FXML
    private void handleSettings(ActionEvent event){
        //todo
        return;
    }

    @FXML
    private void handleExit(ActionEvent event){
        //todo
        return;
    }

}
