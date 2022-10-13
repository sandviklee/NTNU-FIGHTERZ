package fightinggame.ui;

import fightinggame.users.User;
import fightinggame.game.World;
import fightinggame.game.GameCharacter;
import fightinggame.game.Terrain;
import fightinggame.game.WorldEntity;

import java.util.ArrayList;
import java.util.HashMap;


import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;

public class SingleplayerGameController extends SceneController {
    @FXML private Canvas worldCanvas;
    private World world;
    private ArrayList<WorldEntity> worldEntities;
    private HashMap<String, Image> playerSprites;
    private HashMap<String, Image> terrainSprites;
    private Pair<Integer, Integer> playerPosition = new Pair<>(200,200); //dette blir point
    private Pair<Integer, Integer> dummyPosition = new Pair<>(400,400); //dette blir point
    private SpriteRenderer renderer;

    public void loadWorld(String character, String gameStage){
        GameCharacter player = loadPlayer(character, playerPosition);
        GameCharacter dummy = loadPlayer("Dummy", dummyPosition);
        //Terrain terrain = loadTerrain(gameStage);
        //worldEntities.add(player);
        //worldEntities.add(dummy);
        renderer = new SpriteRenderer(worldCanvas, player);
        updateWorld();
    }

    private GameCharacter loadPlayer(String character, Pair<Integer, Integer> position){
        //load user here with serializer
        ArrayList<String> playerParams = new ArrayList<>(); //placeholder
        //return new GameCharacter(playerParams, position); //loaded from json,should maybe have a starting position
        return null;
    }

    private Terrain loadTerrain(String terrainSprite){
        return null;
        //load terrain here with serializer
    }

    private void updateWorld() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                //world.updateWorld();
                renderer.update();
            }
        }.start();
    }

    @FXML
    private void handleUserInput(KeyEvent Event){
        
    }
}
