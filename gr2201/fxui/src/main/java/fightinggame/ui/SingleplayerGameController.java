package fightinggame.ui;

import fightinggame.users.User;
import fightinggame.game.World;
import fightinggame.game.Action;
import fightinggame.game.ActionProperties;
import fightinggame.game.GameCharacter;
import fightinggame.game.Terrain;
import fightinggame.game.WorldEntity;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

public class SingleplayerGameController extends SceneController{
    @FXML private Canvas worldCanvas;
    private World world;
    private ArrayList<WorldEntity> worldEntities = new ArrayList<>();
    private HashMap<String, Image> playerSprites = new HashMap<>();
    private HashMap<String, Image> terrainSprites;
    private ArrayList<Integer> playerPosition = new ArrayList<>(Arrays.asList(925, 730)); //dette blir point
    private ArrayList<Integer> playerHitBox = new ArrayList<>(Arrays.asList(70, 160));
    private ArrayList<Integer> dummyPosition = new ArrayList<>(Arrays.asList(400, 400)); //dette blir point
    private SpriteRenderer renderer;
    private String keyInputs = "";
    private String keyReleased = "";
    private long fps = 10_000_000;
    private boolean held = false;

    public void loadWorld(String character, String gameStage){
        worldCanvas.setFocusTraversable(true);
        GameCharacter player = loadPlayer(character, playerPosition, "WDAS", playerHitBox);
        playerSprites.put(character + "Idle", new Image((getClass().getResource(character + "Idle.png")).toString()));
        playerSprites.put(character + "Run", new Image((getClass().getResource(character + "Run.png")).toString()));
        //GameCharacter dummy = loadPlayer("Dummy", dummyPosition);
        //Terrain terrain = loadTerrain(gameStage);
        //player.setCurrentAction(new Action("Idle", 0, 100000, false, false, 18, true, 0));
        worldEntities.add(player);
        //worldEntities.add(dummy);
        world = new World(worldEntities);
        renderer = new SpriteRenderer(worldCanvas, playerSprites, worldEntities);
        updateWorld();
    }

    private GameCharacter loadPlayer(String character, ArrayList<Integer> position, String availKeys, ArrayList<Integer> hitboxProperties){
        //load user here with serializer
        //ArrayList<String> playerParams = new ArrayList<>(); //placeholder
        String[] strSplit = availKeys.split("");
        ArrayList<String> availKeysArray = new ArrayList<>(Arrays.asList(strSplit));


        return new GameCharacter(character, position, availKeysArray, hitboxProperties, new ArrayList<ActionProperties>(), 1, 1); //loaded from json,should maybe have a starting position
    }

    private Terrain loadTerrain(String terrainSprite){
        return null;
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
        held = true;
    }

    @FXML
    private void handleKeyReleased(KeyEvent event){
        System.out.println(event.getCode());
        System.out.println(held);
        keyReleased += event.getCode();
        held = false;
    }
}


