package fightinggame.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class CharacterInformationController extends SceneController implements Initializable{
    private String character;
    
    @FXML private ImageView characterSplashArt, difficulty;
    @FXML private Label title, description;
    @FXML private GridPane characterSpecials;
    @FXML private Button goBack;

    public void setCharacter(String character) {
        this.character = character;

        initCharacter(character);
    }

    private void initCharacter(String character) {
        System.out.println((getClass().getResource(character + "SplashArt.png")).toString());
        title.setText(character + " INFO");
        Image characterSplashArt = new Image((getClass().getResource(character + "SplashArt.png")).toString());
       
        this.characterSplashArt.setImage(characterSplashArt);
        // temporary difficulty image, same for all characters
        Image difficulty = new Image((getClass().getResource("Difficulty.png")).toString());
        this.difficulty.setImage(difficulty);
        //title.setText("Ram" + " INFO");
        description.setText("Lorem ipsum comes later.");
        for (int i = 1; i < 5; i++) {
            Image characterSpecials = new Image((getClass().getResource(character + "Move" + i + ".png")).toString());
            this.characterSpecials.getChildren().add(new ImageView(characterSpecials));
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // System.out.println(character);
        System.out.println((getClass().getResource("RamSplashArt.png")).toString());
        System.out.println(character+ "HEI");
        Image characterSplashArt = new Image((getClass().getResource("Ram" + "SplashArt.png")).toString());
       
        this.characterSplashArt.setImage(characterSplashArt);
        // temporary difficulty image, same for all characters
        Image difficulty = new Image((getClass().getResource("Difficulty.png")).toString());
        this.difficulty.setImage(difficulty);
        //title.setText("Ram" + " INFO");
        description.setText("Lorem ipsum comes later.");
        for (int i = 1; i < 5; i++) {
            Image characterSpecials = new Image((getClass().getResource("Ram" + "Move" + i + ".png")).toString());
            this.characterSpecials.getChildren().add(new ImageView(characterSpecials));
        }
        
    }
}
