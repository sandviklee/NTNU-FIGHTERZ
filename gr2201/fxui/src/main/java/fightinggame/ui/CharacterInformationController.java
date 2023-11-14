package fightinggame.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import fightinggame.utils.CharacterInformationObject;
import fightinggame.dao.DAOFactory;
import fightinggame.dao.RODAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class CharacterInformationController extends SceneController{
    
    @FXML private ImageView characterSplashArt, difficulty;
    @FXML private Label title, description;
    @FXML private GridPane characterSpecials;
    @FXML private Button goBack;

    private static RODAO<CharacterInformationObject, String> dao;

    public void setCharacter(String character) throws IOException {
        dao = DAOFactory.getCharacterInfoDAO();
        initCharacter(character);
    }

    private void initCharacter(String character) {
        CharacterInformationObject characterObject = dao.find(character);
        // Set title
        title.setText(characterObject.getName());
        // Set character image
        Image characterSplashArt = new Image((getClass().getResource(characterObject.getName() + "SplashArt.png")).toString());
        this.characterSplashArt.setImage(characterSplashArt);
        // Set chaacter difficulty
        Image difficulty = new Image((getClass().getResource("Difficulty" + characterObject.getDifficulty() + ".png")).toString());
        this.difficulty.setImage(difficulty);
        // Set character description
        description.setText(characterObject.getDescription());
        // Set character moves
        HashMap<String, String> specialActions = characterObject.getSpecialActions();
        ArrayList<String> specialActionNames = new ArrayList<>(specialActions.keySet());
        int i = 0;
        for (Node Imageview : characterSpecials.getChildren()) {
            if (Imageview instanceof ImageView) {
                String actionName = specialActionNames.get(i);

                ((ImageView) Imageview).setImage(
                        new Image((getClass().getResource(character + "Move" + (i + 1) + ".gif")).toString()));
                Tooltip.install(Imageview, new Tooltip(actionName + "\n" + specialActions.get(actionName)));
            }
            i += 1;
        }
    }

    @FXML
    private void handleGoBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CharacterInformationMenu.fxml"));
            Parent root = loader.load();
            CharacterInformationMenuController controller = loader.getController();
            controller.setUser(super.getUser());
            super.changeScene("NTNU Fighterz", root, event);
        } catch (IOException e) {
            showError("Error: Invalid go back path", "Something went wrong and main menu files could not be found.");
            e.printStackTrace();
        }


    }
}
