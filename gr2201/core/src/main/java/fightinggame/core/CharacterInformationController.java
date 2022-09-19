package fightinggame.core;

import javafx.fxml.FXML;

public class CharacterInformationController {
    private String character;
    
    @FXML private ImageView characterSplashArt, difficulty;
    @FXML private Label title, description;
    @FXML private GridPane characterSpecials;
    @FXML private Button goBack;

    public void setCharacter(String character) {
        this.character = character;
    }

    @FXML
    private void initalize(){
        characterSplashArt.setImage(getClass().getResource(character + "SplashArt.png"));
        // temporary difficulty image, same for all characters
        difficulty.setImage(getClass().getResource("Difficulty.jpg"));
        title.setText(character.toUpperCase() + " INFO");
        description.setText("Lorem ipsum comes later.");
        for (int i = 0; i < 5; i++) {
            ImageView special = new ImageView(getClass().getResource(character + "Move" + i + ".jpg"));
            characterSpecials.add(special);
        }
    }

    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CharacterInformationMenu.fxml"));
        Parent root = loader.load();
        CharacterInformationMenuController controller = loader.getController();
        controller.setUser(user);
        SceneController.changeScene("NTNU Fighterz", root, event);
    }
}
