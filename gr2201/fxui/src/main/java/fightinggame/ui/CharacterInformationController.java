package fightinggame.ui;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class CharacterInformationController extends SceneController{
    
    @FXML private ImageView characterSplashArt, difficulty;
    @FXML private Label title, description;
    @FXML private GridPane characterSpecials;
    @FXML private Button goBack;

    public void setCharacter(String character) {
        initCharacter(character);
    }

    private void initCharacter(String character) {
        int i = 0;
        System.out.println((getClass().getResource(character + "SplashArt.png")).toString());
        title.setText(character + " INFO");
        Image characterSplashArt = new Image((getClass().getResource(character + "SplashArt.png")).toString());
       
        this.characterSplashArt.setImage(characterSplashArt);
        // temporary difficulty image, same for all characters
        Image difficulty = new Image((getClass().getResource("Difficulty.png")).toString());
        this.difficulty.setImage(difficulty);
        //title.setText("Ram" + " INFO");
        description.setText("Lorem ipsum comes later.");
        for (Node Imageview : characterSpecials.getChildren()) {
            i += 1;
            if (Imageview instanceof ImageView) {
                ((ImageView) Imageview).setImage(new Image((getClass().getResource(character + "Move" + i + ".jpeg")).toString()));
            }
        }
    }


    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CharacterInformationMenu.fxml"));
        Parent root = loader.load();
        CharacterInformationMenuController controller = loader.getController();
        controller.setUser(super.getUser());
        super.changeScene("NTNU Fighterz", root, event);
    }
}
