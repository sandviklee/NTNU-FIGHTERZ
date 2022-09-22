package fightinggame.core;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class mainMenuController {
    @FXML private Button playSingleplayer, playMultiplayer, characterInfo, settings, exit;

    private void initialize(){
        playMultiplayer.setDisable(true);
        settings.setDisable(true);
        exit.setDisable(true);
    }
    
    @FXML
    private void handlePlaySingleplayer(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Singleplayer.fxml"));
        Parent root = loader.load();
        SingleplayerController singleplayerController = loader.getController();
        singleplayerController.setUser(this.user);
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
        CharacterInfoController characterInfoController = loader.getController();
        characterInfoController.setUser(user);
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
