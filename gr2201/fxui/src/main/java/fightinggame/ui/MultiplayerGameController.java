package fightinggame.ui;

import fightinggame.game.World;
import fightinggame.game.GameCharacter;
import fightinggame.game.PlayerProperties;
import fightinggame.game.Terrain;
import fightinggame.game.WorldEntity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.text.Text;

public class MultiplayerGameController extends SceneController{
    @FXML private Canvas worldCanvas;
    @FXML private Button resumeButton;
    @FXML private Button exitButton;
    @FXML private Text textField;
    private World world;
    private ArrayList<WorldEntity> worldEntities = new ArrayList<>();
    private HashMap<String, Image> playerSprites = new HashMap<>();
    private HashMap<String, Image> assetSprites = new HashMap<>();
    private AnimationTimer worldAnimationTimer;
    private boolean paused;
     
    //Asserts the player keys.
    private ArrayList<String> player1Keys = new ArrayList<>(Arrays.asList(".", ",", "W", "D", "A", "DW", "AW", "V", "DV", "AV", "WV", "SV", "DC", "AC", "WC", "SC", "C", "S"));
    private ArrayList<String> player2Keys = new ArrayList<>(Arrays.asList(".", ",", "I", "L", "J", "LI", "JI", "N", "LN", "JN", "IN", "KN", "LM", "JM", "IM", "KM", "M", "K"));

    //Asserts all the worldentity positions
    private ArrayList<Integer> playerPosition = new ArrayList<>(Arrays.asList(200, 300));
    private ArrayList<Integer> player2Position = new ArrayList<>(Arrays.asList(1700, 300));
    private ArrayList<Integer> terrainPosition = new ArrayList<>(Arrays.asList(980, 910));
    private ArrayList<Integer> terrainPosition2 = new ArrayList<>(Arrays.asList(1700, 500));
    private ArrayList<Integer> terrainPosition3 = new ArrayList<>(Arrays.asList(200, 500));
    
    private SpriteRenderer renderer;
    private String keyInputs = "";
    private String keyReleased = "";
    private String path;
    private long fps = 10_000_000;
    

    /**
     * {@link #loadWorld(String, String)} will make an {@code World} with all players and terrain that the game shall have.
     * 
     * @param character  the name of the character loaded
     * @param character2 the name of the second character loaded
     * @param gameStage  the gamestage (not used yet)
     * @param path       the path of sprites etc.
     */
    public void loadWorld(String character, String character2, String gameStage, String path){
        worldCanvas.setFocusTraversable(true);
        this.path = path;	


        GameCharacter player = loadPlayer(character, playerPosition, player1Keys, 1, 1);
        GameCharacter player2 = loadPlayer(character2, player2Position, player2Keys, 2, -1);

        Terrain terrain = loadTerrain("Test", terrainPosition, 1000, 280);
        Terrain terrain2 = loadTerrain("Test2", terrainPosition2, 300, 65);
        Terrain terrain3 = loadTerrain("Test3", terrainPosition3, 300, 65);
        loadSprite(character, playerSprites);
        loadSprite("Assets", assetSprites);
        loadSprite("Background", assetSprites);
        
        worldEntities.add(player);
        worldEntities.add(player2);
        worldEntities.add(terrain3);
        worldEntities.add(terrain2);
        worldEntities.add(terrain);

        world = new World(worldEntities);
        renderer = new SpriteRenderer(worldCanvas, worldEntities, playerSprites, assetSprites, path);
        updateWorld();
    }

    /**
     * Load the character from presistens with given characterName
     * @param character  name of the WorldEntity
     * @param position   of the character
     * @param availKeys  that shall controll the character
     * @return the character with given name
     */
    private GameCharacter loadPlayer(String character, ArrayList<Integer> position, ArrayList<String> availKeys, int playerNumb, int facingDirection){
        CharacterAttributeDAO characterAttributeDAO = new CharacterAttributeDAOImpl();
        PlayerProperties playerProperties = characterAttributeDAO.findCharacter(character);
        ArrayList<String> availKeysArray = new ArrayList<>(availKeys);
        HashMap<String, Media> characterAudio = new HashMap<>();
        loadAudio(character, characterAudio);

        return new GameCharacter(playerProperties, characterAudio, position, availKeysArray, playerNumb, facingDirection); //loaded from json,should maybe have a starting position
    }

    private Terrain loadTerrain(String name, ArrayList<Integer> position, int width, int heigth){
        return new Terrain(name, position, width, heigth);
    }

    private void updateWorld() {
        this.worldAnimationTimer = new AnimationTimer() {
            private long tick = 0;
            @Override
            public void handle(long now) {
                if (now - tick >= fps) {
                    if (!world.getGameOver()) {
                        if (world.getWorldEntities().stream().anyMatch(x -> !worldEntities.contains(x))) {
                            renderer.updateRendererWorldEntities(world.getWorldEntities());
                        }
                        world.updateWorld(keyInputs, keyReleased);
                        renderer.update();
                        resetKeyInputs();
                    } else {
                        gameOver();
                    }

                }
            tick = now;
            }
        };

        worldAnimationTimer.start();
        worldCanvas.toFront();
        textField.setText("");
        worldCanvas.setFocusTraversable(true);
        resumeButton.setFocusTraversable(false);
        exitButton.setFocusTraversable(false);

    }

    private void gameOver() {
        paused = true;
        worldAnimationTimer.stop();
        resumeButton.setDisable(true);
        textField.setText("GameOver! \n Winner: Player" + world.getGameWinner());
        openCloseMenu();
    }


    private void resumeGame() {
        paused = false;
        worldAnimationTimer.start();
        textField.setText("");
        openCloseMenu();
    }

    private void pauseGame() {
        paused = true;
        textField.setText("Game Paused \n Resume the game?");
        worldAnimationTimer.stop();
        openCloseMenu();
    }

    private void openCloseMenu() {
        if (paused) {
            worldCanvas.toBack();
        } else {
            worldCanvas.toFront();
        }
    }

    private void resetKeyInputs() {
        this.keyInputs = "";
        this.keyReleased = "";
    }

    @FXML
    private void handleKeyPressed(KeyEvent event){
        if(event.getCode()== KeyCode.ESCAPE) {
            if(paused){
                resumeGame();
            }
            else {
                pauseGame();
            }
        }
        keyInputs += event.getCode();
    }

    @FXML
    private void handleKeyReleased(KeyEvent event){
        keyReleased += event.getCode();
    }

    @FXML
    private void handleExit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();
        MainMenuController mainMenuController = loader.getController();
        mainMenuController.setUser(super.getUser());
        super.changeScene("NTNU Fighterz", root, event);
    }

    @FXML
    private void handleResume(ActionEvent event) {
        resumeGame();
    }

    private void loadSprite(String name, HashMap<String, Image> spriteHash) {
        File[] spriteFiles = new File(this.path + name).listFiles();
        for (File sprite : spriteFiles) {
            spriteHash.put((name + sprite.getName()).split("\\.")[0], new Image((getClass().getResource(name + "/" + sprite.getName())).toString()));
            
        }
    }

    /**
     * Loads the all the audios in the audio folder for the game character.
     * @throws MediaException if the computer doesn't suppoert MediaPlayer codec.
     */
    private void loadAudio(String character, HashMap<String, Media> audioHash) {
        try {
            File[] audioFiles = new File(this.path + "Audio/" + character).listFiles();
            for (File audio : audioFiles) {
                audioHash.put((audio.getName()).split("\\.")[0], new Media(new File(this.path + "Audio/" + character + "/" + audio.getName()).toURI().toString()));
                
            }
    
        } catch (MediaException e) {
            System.out.println("You either dont have the correct Media Codec or the audio files did not load in.s You cant play audio. Error: " + e);
        }

    }

    
}
 

