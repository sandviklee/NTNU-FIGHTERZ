package fightinggame.ui;

import fightinggame.users.User;
import fightinggame.game.World;
import fightinggame.game.GameCharacter;
import fightinggame.game.PlayerProperties;
import fightinggame.game.Point;
import fightinggame.game.Terrain;
import fightinggame.game.WorldEntity;

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
    private ArrayList<String> player1Keys = new ArrayList<>(Arrays.asList(".", ",", "W", "D", "A", "DW", "AW", "B", "DB", "AB", "WB", "SB", "DV", "AV", "WV", "SV"));

    // Should switch to Point
    private ArrayList<Integer> playerPosition = new ArrayList<>(Arrays.asList(925, 730));
    private ArrayList<Integer> terrainPosition = new ArrayList<>(Arrays.asList(1330, 950));
    private ArrayList<Integer> terrain2Position = new ArrayList<>(Arrays.asList(1200, 550));
    private ArrayList<Integer> terrain3Position = new ArrayList<>(Arrays.asList(500, 950));
    private ArrayList<Integer> dummyPosition = new ArrayList<>(Arrays.asList(400, 400));

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

        Terrain terrain = loadTerrain("Test", terrainPosition, 900, 280);
        Terrain terrain2 = loadTerrain("Test2", terrain2Position, 300, 5);
        Terrain terrain3 = loadTerrain("Test", terrain3Position, 500, 280);

        playerSprites.put(character + "Idle", new Image((getClass().getResource(character + "Idle.png")).toString()));
        playerSprites.put(character + "Run", new Image((getClass().getResource(character + "Run.png")).toString()));
        playerSprites.put(character + "Jump", new Image((getClass().getResource(character + "Jump.png")).toString()));
        playerSprites.put(character + "SideSpecial", new Image((getClass().getResource(character + "SideSpecial.png")).toString()));
        playerSprites.put(character + "SideNormal", new Image((getClass().getResource(character + "SideNormal.png")).toString()));
        //GameCharacter dummy = loadPlayer("Dummy", dummyPosition);
        //Terrain terrain = loadTerrain(gameStage);
        //player.setCurrentAction(new Action("Idle", 0, 100000, false, false, 18, true, 0));
        worldEntities.add(player);
        worldEntities.add(terrain3);
        worldEntities.add(terrain2);
        worldEntities.add(terrain);
        //worldEntities.add(dummy);
        world = new World(worldEntities);
        renderer = new SpriteRenderer(worldCanvas, playerSprites, worldEntities);
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
                    //System.out.println(keyInputs);
                    world.updateWorld(keyInputs, keyReleased);
                    renderer.update();
                    resetKeyInputs();
                }
            tick = now;
            }
        }.start();
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
 

