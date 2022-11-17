package fightinggame.ui;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;

public class SingleplayerSelectionController extends SceneController{
    @FXML private Button lockIn, goBack;
    @FXML private GridPane characterSelectGrid;
    @FXML private ImageView characterSelected;
    private Media audioSelect = new Media(new File("gr2201/fxui/src/main/resources/fightinggame/ui/Audio/CharacterSelect.mp3").toURI().toString());
    private Media audioGame = new Media(new File("gr2201/fxui/src/main/resources/fightinggame/ui/Audio/Game.mp3").toURI().toString());
    

    @FXML
    private void initialize(){
        lockIn.setDisable(true);
        mainAudioPlayer = new MediaPlayer(audioSelect);
        mainAudioPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
              mainAudioPlayer.seek(Duration.ZERO);
            }
        });
        mainAudioPlayer.setVolume(0.1);
        mainAudioPlayer.play();

    }

    public ImageView getCharacterSelected(){
        return this.characterSelected;
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
        mainAudioPlayer.stop();
        mainAudioPlayer = new MediaPlayer(audioGame);
        mainAudioPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
              mainAudioPlayer.seek(Duration.ZERO);
            }
        });
        mainAudioPlayer.setVolume(0.1);
        mainAudioPlayer.play();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WorldCanvas.fxml"));
        Parent root = loader.load();
        SingleplayerGameController singleplayerGameController = loader.getController();
        singleplayerGameController.setUser(super.getUser());
        singleplayerGameController.loadWorld("AngryCyclist", null);
        super.changeSceneFullscreen("NTNU Fighterz", root, event);
    
    }

    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        mainAudioPlayer.stop();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();
        MainMenuController mainMenuController = loader.getController();
        mainMenuController.setUser(super.getUser());
        super.changeScene("NTNU Fighterz", root, event);
    }
}
