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
     
    private ArrayList<String> player1Keys = new ArrayList<>(Arrays.asList(".", ",", "W", "D", "A", "DW", "AW", "B", "DB", "AB", "WB", "SB", "DV", "AV", "WV", "SV"));

    // Should switch to Point
    private ArrayList<Integer> playerPosition = new ArrayList<>(Arrays.asList(500, 500));
    private ArrayList<Integer> terrainPosition = new ArrayList<>(Arrays.asList(800, 910));

    private ArrayList<Integer> dummyPosition = new ArrayList<>(Arrays.asList(1100, 500));

    // private Point playerPosition = new Point(925, 730);
    // private Point terrainPosition = new Point(1330, 950);
    // private Point terrain2Position = new Point(1200, 550);
    // private Point terrain3Position = new Point(500, 950);
    // private Point dummyPosition = new Point(400, 400);

    
    private SpriteRenderer renderer;
    private String keyInputs = "";
    private String keyReleased = "";
    private long fps = 10_000_000;

    public void loadWorld(String character, String gameStage){
        worldCanvas.setFocusTraversable(true);
        GameCharacter player = loadPlayer(character, playerPosition, player1Keys);
        GameCharacter dummy = loadPlayer("Dummy", dummyPosition);
        Terrain terrain = loadTerrain("Test", terrainPosition, 1300, 200);
        loadCharacterSprite(character, playerSprites);
        loadCharacterSprite("Dummy", playerSprites);
        loadCharacterSprite("Assets", playerSprites);
        
        //System.out.println(playerSprites);
        worldEntities.add(player);
        worldEntities.add(dummy);
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
    private GameCharacter loadPlayer(String character, ArrayList<Integer> position, ArrayList<String> availKeys){
        CharacterAttributeDAO characterAttributeDAO = new CharacterAttributeDAOImpl();
        PlayerProperties playerProperties = characterAttributeDAO.findCharacter(character);
        ArrayList<String> availKeysArray = new ArrayList<>(availKeys);

        return new GameCharacter(playerProperties, position, availKeysArray); //loaded from json,should maybe have a starting position
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
                        worldEntities = world.getWorldEntities();
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
        File[] spriteFiles = new File("gr2201/fxui/src/main/resources/fightinggame/ui/" + character).listFiles();
        for (File sprite : spriteFiles) {
            spriteHash.put((character + sprite.getName()).split("\\.")[0], new Image((getClass().getResource(character + "/" + sprite.getName())).toString()));
            
        }
    }

    private void resetKeyInputs() {
        this.keyInputs = "";
        this.keyReleased = "";
    }

    private 

    @FXML
    private void handleKeyPressed(KeyEvent event){
        keyInputs += event.getCode();
    }

    @FXML
    private void handleKeyReleased(KeyEvent event){
        keyReleased += event.getCode();
    }

    
}
 

