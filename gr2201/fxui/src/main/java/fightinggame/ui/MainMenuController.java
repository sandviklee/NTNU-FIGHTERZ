package fightinggame.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class MainMenuController extends SceneController {
    @FXML private Button playSingleplayer, playMultiplayer, characterInfo, settings, exit;

    @FXML
    private void initialize(){
        playMultiplayer.setDisable(true);
        settings.setDisable(true);
        exit.setDisable(true);
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
    private void handlePlayMultiplayer(ActionEvent event){
        //todo
        return;
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
