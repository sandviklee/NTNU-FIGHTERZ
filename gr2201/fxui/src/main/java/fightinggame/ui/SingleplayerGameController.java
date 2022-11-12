package fightinggame.ui;

import fightinggame.game.World;
import fightinggame.game.ActionProperties;
import fightinggame.game.GameCharacter;
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

    private ArrayList<Integer> playerPosition = new ArrayList<>(Arrays.asList(925, 730)); //dette blir point
    private ArrayList<Integer> playerHitBox = new ArrayList<>(Arrays.asList(70, 160));
    private ArrayList<Integer> terrainPosition = new ArrayList<>(Arrays.asList(1330, 950)); //dette blir point
    private ArrayList<Integer> terrainHitBox = new ArrayList<>(Arrays.asList(900, 280));
    private ArrayList<Integer> terrain2Position = new ArrayList<>(Arrays.asList(1200, 550)); //dette blir point
    private ArrayList<Integer> terrain2HitBox = new ArrayList<>(Arrays.asList(300, 5));
    private ArrayList<Integer> terrain3Position = new ArrayList<>(Arrays.asList(500, 950)); //dette blir point
    private ArrayList<Integer> terrain3HitBox = new ArrayList<>(Arrays.asList(500, 280));
    private ArrayList<Integer> dummyPosition = new ArrayList<>(Arrays.asList(400, 400)); //dette blir point
    
    
    private SpriteRenderer renderer;
    private String keyInputs = "";
    private String keyReleased = "";
    private long fps = 10_000_000;

    public void loadWorld(String character, String gameStage){
        worldCanvas.setFocusTraversable(true);
        GameCharacter player = loadPlayer(character, playerPosition, player1Keys, playerHitBox);
        Terrain terrain = loadTerrain("Test", terrainPosition, terrainHitBox);
        Terrain terrain2 = loadTerrain("Test2", terrain2Position, terrain2HitBox);
        Terrain terrain3 = loadTerrain("Test", terrain3Position, terrain3HitBox);
        playerSprites.put(character + "Idle", new Image((getClass().getResource(character + "Idle.png")).toString()));
        playerSprites.put(character + "Run", new Image((getClass().getResource(character + "Run.png")).toString()));
        playerSprites.put(character + "Jump", new Image((getClass().getResource(character + "Jump.png")).toString()));
        playerSprites.put(character + "SideSpecial", new Image((getClass().getResource(character + "SideSpecial.png")).toString()));
        playerSprites.put(character + "SideNormal", new Image((getClass().getResource(character + "SideNormal.png")).toString()));
        playerSprites.put(character + "NeutralSpecial", new Image((getClass().getResource(character + "NeutralSpecial.png")).toString()));
        playerSprites.put(character + "NeutralSpecialProjectile", new Image((getClass().getResource(character + "NeutralSpecialProjectile.png")).toString()));
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

    private GameCharacter loadPlayer(String character, ArrayList<Integer> position, ArrayList<String> availKeys, ArrayList<Integer> hitboxProperties){
        //load user here with serializer
        //ArrayList<String> playerParams = new ArrayList<>(); //placeholder
        ArrayList<String> availKeysArray = new ArrayList<>(availKeys);


        return new GameCharacter(character, position, availKeysArray, hitboxProperties, new ArrayList<ActionProperties>(), 1, 1); //loaded from json,should maybe have a starting position
    }

    private Terrain loadTerrain(String name, ArrayList<Integer> position, ArrayList<Integer> hitboxProperties){
        return new Terrain(name, position, hitboxProperties);
        //load terrain here with serializer
    }

    private void updateWorld() {
        new AnimationTimer() {
            private long tick = 0;
            @Override
            public void handle(long now) {
                if (now - tick >= fps) {
                    //System.out.println(keyInputs);
                    if (world.getWorldEntities().stream().anyMatch(x -> !worldEntities.contains(x))) {
                        //System.out.println("Oppdatert");
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
 

