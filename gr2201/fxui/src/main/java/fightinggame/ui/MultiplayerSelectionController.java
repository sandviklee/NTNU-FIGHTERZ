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

public class MultiplayerSelectionController extends SceneController{
    @FXML private Button lockInPlayer1, lockInPlayer2, goBack;
    @FXML private GridPane characterSelectGrid;
    @FXML private ImageView character1Selected;
    @FXML private ImageView character2Selected;
    private ImageView character1SelectedImage;
    private ImageView character2SelectedImage;
    private String path;
    private Media audioSelect;
    private Media audioGame;
    private boolean player1Selecting;

    @FXML
    private void initialize(){
        lockInPlayer1.setDisable(true);
        lockInPlayer2.setDisable(true);
        player1Selecting = true;
        this.path = "../fxui/src/main/resources/fightinggame/ui/";	
        try {
            audioSelect = new Media(new File(path + "Audio/CharacterSelect.wav").toURI().toString());
            audioGame = new Media(new File(path + "Audio/Game.wav").toURI().toString());
            mainAudioPlayer = new MediaPlayer(audioSelect);
            mainAudioPlayer.setOnEndOfMedia(new Runnable() {
                public void run() {
                  mainAudioPlayer.seek(Duration.ZERO);
                }
            });
            mainAudioPlayer.setVolume(0.1);
            mainAudioPlayer.play();
    
        } catch (MediaException e) {
            System.out.println("You either dont have the correct Media Codec or the audio files did not load in.s You cant play audio. Error: " + e);
        }
    }

    private String getCharacterSelected1Id(){
        return this.character1SelectedImage.getId();
    }

    private String getCharacterSelected2Id(){
        return this.character2SelectedImage.getId();
    }

    public ImageView getCharacter1Selected(){
        return this.character1Selected;
    }

    private void resetCharacterImageOpacity() {
        for (Node imageView : characterSelectGrid.getChildren()) {
            imageView.setOpacity(1);
        }
    }

    @FXML
    private void handleSelectCharacter(MouseEvent event) {
        if (player1Selecting) {
            resetCharacterImageOpacity();
            ImageView image = (ImageView) event.getSource();
            this.character1SelectedImage = image;
            character1Selected.setImage(new Image(getClass().getResource(image.getId() + "SplashArt.png").toString()));
            image.setOpacity(0.7);
            lockInPlayer1.setDisable(false);
        } else {
            resetCharacterImageOpacity();
            ImageView image = (ImageView) event.getSource();
            this.character2SelectedImage = image;
            character2Selected.setImage(new Image(getClass().getResource(image.getId() + "SplashArt.png").toString()));
            image.setOpacity(0.7);
            lockInPlayer2.setDisable(false);
        }
    }

    @FXML
    private void handleLockInPlayer1(ActionEvent event) {
        if (getCharacterSelected1Id().equals("AngryCyclist")) {
            lockInPlayer1.setDisable(true);
            resetCharacterImageOpacity();
            player1Selecting = false;
        } else {
            showError("Error: Not a playable character", "This character is not unlocked or playable yet.");
        }
    }

    @FXML
    private void handleLockInPlayer2(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WorldCanvasMulti.fxml"));
            Parent root = loader.load();
            MultiplayerGameController multiplayerGameController = loader.getController();
            multiplayerGameController.setUser(super.getUser());
            multiplayerGameController.loadWorld(getCharacterSelected1Id(), getCharacterSelected2Id(), null, path);
            super.changeSceneFullscreen("NTNU Fighterz", root, event);

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
                System.out.println("You either dont have the correct Media Codec or the audio files did not load in.s You cant play audio. Error: " + e);
            }

        } catch (IOException e) {
            showError("Error: Invalid go back path",
                    "Something went wrong and Main menu page could not be found.");
            e.printStackTrace();

        } catch (NullPointerException e) {
            showError("Error: Not a playable character", "This character is not unlocked or playable yet.");
        }
    }

    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        if (mainAudioPlayer != null) {
            mainAudioPlayer.stop();
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();
        MainMenuController mainMenuController = loader.getController();
        mainMenuController.setUser(super.getUser());
        super.changeScene("NTNU Fighterz", root, event);
    }
}
