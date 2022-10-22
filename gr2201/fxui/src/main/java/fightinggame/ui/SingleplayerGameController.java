package fightinggame.ui;

import fightinggame.users.User;
import fightinggame.game.World;
import fightinggame.game.Action;
import fightinggame.game.GameCharacter;
import fightinggame.game.Terrain;
import fightinggame.game.WorldEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;

public class SingleplayerGameController extends SceneController {
    @FXML private Canvas worldCanvas;
    private World world;
    private ArrayList<WorldEntity> worldEntities = new ArrayList<>();
    private HashMap<String, Image> playerSprites = new HashMap<>();
    private HashMap<String, Image> terrainSprites;
    private ArrayList<Integer> playerPosition = new ArrayList<>(Arrays.asList(800, 620)); //dette blir point
    private ArrayList<Integer> dummyPosition = new ArrayList<>(Arrays.asList(400, 400)); //dette blir point
    private SpriteRenderer renderer;
    private long fps = 10_000_000;

    public void loadWorld(String character, String gameStage){
        GameCharacter player = loadPlayer(character, playerPosition);
        playerSprites.put(character + "Idle", new Image((getClass().getResource(character + "Idle.png")).toString()));
        //GameCharacter dummy = loadPlayer("Dummy", dummyPosition);
        //Terrain terrain = loadTerrain(gameStage);
        player.setCurrentAction(new Action(gameStage, 0, 100000, false, false, 18, true, 0));
        worldEntities.add(player);
        //worldEntities.add(dummy);
        this.world = new World(worldEntities);
        renderer = new SpriteRenderer(worldCanvas, this.playerSprites, worldEntities);
   
        updateWorld();
    }

    private GameCharacter loadPlayer(String character, ArrayList<Integer> position){
        //load user here with serializer
        //ArrayList<String> playerParams = new ArrayList<>(); //placeholder
        return new GameCharacter(character, position); //loaded from json,should maybe have a starting position
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
                    renderer.update();
                    world.updateWorld();
                }
            tick = now;
            }
        }.start();
    }

    @FXML
    private void handleUserInput(KeyEvent Event){
        
    }
}
