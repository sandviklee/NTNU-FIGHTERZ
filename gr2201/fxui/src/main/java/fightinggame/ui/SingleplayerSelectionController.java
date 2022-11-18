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
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;

public class SingleplayerSelectionController extends SceneController {
    @FXML
    private Button lockIn, goBack;
    @FXML
    private GridPane characterSelectGrid;
    @FXML
    private ImageView characterSelected;
    private Media audioSelect;
    private Media audioGame;
    private ImageView currentImage;
    

    @FXML
    private void initialize() {
        lockIn.setDisable(true);
        try {
            audioSelect = new Media(new File("../fxui/src/main/resources/fightinggame/ui/Audio/CharacterSelect.wav")
                    .toURI().toString());
            audioGame = new Media(
                    new File("../fxui/src/main/resources/fightinggame/ui/Audio/Game.wav").toURI().toString());
            mainAudioPlayer = new MediaPlayer(audioSelect);
            mainAudioPlayer.setOnEndOfMedia(new Runnable() {
                public void run() {
                    mainAudioPlayer.seek(Duration.ZERO);
                }
            });
            mainAudioPlayer.setVolume(0.1);
            mainAudioPlayer.play();

        } catch (MediaException e) {
            System.out.println("Since you dont have the correct Media codec. You cant play audio. Error: " + e);
        }

    }

    public ImageView getCharacterSelected() {
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
        currentImage = image;
        characterSelected.setImage(new Image(getClass().getResource(image.getId() + "SplashArt.png").toString()));
        image.setOpacity(0.7);
        lockIn.setDisable(false);
    }

    @FXML
    private void handleLockIn(ActionEvent event) {
        if (mainAudioPlayer != null) {
            mainAudioPlayer.stop();
        }

        try {
            mainAudioPlayer = new MediaPlayer(audioGame);
            mainAudioPlayer.setOnEndOfMedia(new Runnable() {
                public void run() {
                    mainAudioPlayer.seek(Duration.ZERO);
                }
            });
            mainAudioPlayer.setVolume(0.1);
            mainAudioPlayer.play();

        } catch (MediaException e) {
            System.out.println("Since you dont have the correct Media codec. You cant play audio. Error: " + e);
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WorldCanvas.fxml"));
            Parent root = loader.load();
            SingleplayerGameController singleplayerGameController = loader.getController();
            singleplayerGameController.setUser(super.getUser());
            singleplayerGameController.loadWorld("AngryCyclist", null);
            super.changeSceneFullscreen("NTNU Fighterz", root, event);
        } catch (IOException e) {
            showError("Error: Invalid go back path",
                    "Something went wrong and Main menu page could not be found.");
            e.printStackTrace();
        }
    
    }

    @FXML
    private void handleGoBack(ActionEvent event) {
        if (mainAudioPlayer != null) {
            mainAudioPlayer.stop();
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root;
        try {
            root = loader.load();
            MainMenuController mainMenuController = loader.getController();
            mainMenuController.setUser(super.getUser());
            super.changeScene("NTNU Fighterz", root, event);
        } catch (IOException e) {
            showError("Error: Invalid go back path",
                    "Something went wrong and Main menu page could not be found.");
            e.printStackTrace();
        }
    }
}
