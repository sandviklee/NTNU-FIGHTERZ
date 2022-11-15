package fightinggame.ui;

import fightinggame.game.World;
import fightinggame.game.GameCharacter;
import fightinggame.game.PlayerProperties;
import fightinggame.game.Point;
import fightinggame.game.Terrain;
import fightinggame.game.WorldEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class SingleplayerGameController extends SceneController{
    @FXML private Canvas worldCanvas;
    private World world;
    private ArrayList<WorldEntity> worldEntities = new ArrayList<>();
    private HashMap<String, Image> playerSprites = new HashMap<>();
    private boolean paused;
     
    private ArrayList<String> player1Keys = new ArrayList<>(Arrays.asList(".", ",", "W", "D", "A", "DW", "AW", "V", "DV", "AV", "WV", "SV", "DC", "AC", "WC", "SC", "C", "S"));
    private ArrayList<String> player2Keys = new ArrayList<>(Arrays.asList(".", ",", "I", "L", "J", "LI", "JI", "N", "LN", "JN", "IN", "KN", "LM", "JM", "IM", "KM", "M", "K"));

    private ArrayList<Integer> playerPosition = new ArrayList<>(Arrays.asList(280, 200));
    private ArrayList<Integer> dummyPosition = new ArrayList<>(Arrays.asList(1700, 200));
    private ArrayList<Integer> terrainPosition = new ArrayList<>(Arrays.asList(980, 910));
    private ArrayList<Integer> terrainPosition2 = new ArrayList<>(Arrays.asList(1700, 560));
    private ArrayList<Integer> terrainPosition3 = new ArrayList<>(Arrays.asList(280, 560));
    
    
    private SpriteRenderer renderer;
    private String keyInputs = "";
    private String keyReleased = "";
    private long fps = 10_000_000;

    public void loadWorld(String character, String gameStage){
        worldCanvas.setFocusTraversable(true);
        GameCharacter player = loadPlayer(character, playerPosition, player1Keys, 1);
        GameCharacter player2 = loadPlayer(character, dummyPosition, player2Keys, -1);
        //GameCharacter dummy = loadPlayer("Dummy", dummyPosition);
        Terrain terrain = loadTerrain("Test", terrainPosition, 1150, 150);
        Terrain terrain2 = loadTerrain("Test2", terrainPosition2, 250, 70);
        Terrain terrain3 = loadTerrain("Test3", terrainPosition3, 250, 70);
        loadCharacterSprite(character, playerSprites);
        //loadCharacterSprite("Dummy", playerSprites);
        loadCharacterSprite("Assets", playerSprites);
        
        //System.out.println(playerSprites);
        worldEntities.add(player);
        worldEntities.add(player2);
        //worldEntities.add(dummy);
        worldEntities.add(terrain3);
        worldEntities.add(terrain2);
        worldEntities.add(terrain);

        world = new World(worldEntities);
        renderer = new SpriteRenderer(worldCanvas, worldEntities, playerSprites);
        updateWorld();
    }

    /**
     * Load the character from presistens with given characterName
     * @param character  name of the WorldEntity
     * @param position   of the character
     * @param availKeys  that shall controll the character
     * @return the character with given name
     */
    private GameCharacter loadPlayer(String character, ArrayList<Integer> position, ArrayList<String> availKeys, int facingDirection){
        CharacterAttributeDAO characterAttributeDAO = new CharacterAttributeDAOImpl();
        PlayerProperties playerProperties = characterAttributeDAO.findCharacter(character);
        ArrayList<String> availKeysArray = new ArrayList<>(availKeys);

        return new GameCharacter(playerProperties, position, availKeysArray, facingDirection); //loaded from json,should maybe have a starting position
    }

    private GameCharacter loadPlayer(String character, ArrayList<Integer> position){ //Dummy character
        return new GameCharacter(character, position); 
    }

    private Terrain loadTerrain(String name, ArrayList<Integer> position, int width, int heigth){
        return new Terrain(name, position, width, heigth);
        //load terrain here with serializer
    }

    private void updateWorld() {
        new AnimationTimer() {
            private long tick = 0;
            @Override
            public void handle(long now) {
                if (now - tick >= fps) {
                    if (world.getWorldEntities().stream().anyMatch(x -> !worldEntities.contains(x))) {
                        renderer.updateRendererWorldEntities(world.getWorldEntities());
                    }
                    world.updateWorld(keyInputs, keyReleased);
                    renderer.update();
                    resetKeyInputs();
                }
            tick = now;
            }
        }.start();
    }

    private void loadCharacterSprite(String character, HashMap<String, Image> spriteHash) {
        File[] spriteFiles = new File("../fxui/src/main/resources/fightinggame/ui/" + character).listFiles();
        for (File sprite : spriteFiles) {
            spriteHash.put((character + sprite.getName()).split("\\.")[0], new Image((getClass().getResource(character + "/" + sprite.getName())).toString()));
            
        }
    }

    private void resetKeyInputs() {
        this.keyInputs = "";
        this.keyReleased = "";
    }

    @FXML
    private void handleKeyPressed(KeyEvent event){
        keyInputs += event.getCode();
    }

    @FXML
    private void handleKeyReleased(KeyEvent event){
        keyReleased += event.getCode();
    }

    
}
 

