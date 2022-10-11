package fightinggame.ui;

import fightinggame.users.User;

import java.util.ArrayList;
import java.util.HashMap;

import fightinggame.game.World;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;

public class SingleplayerGameController extends SceneController {
    @FXML private Canvas canvas;
    private World world;
    private HashMap<String, Image> playerSprites;
    private HashMap<String, Image> terrainSprites;
    private Pair<Integer, Integer> playerPosition = new Pair<>(200,200); //dette blir point
    private Pair<Integer, Integer> dummyPosition = new Pair<>(400,400); //dette blir point

    public SingleplayerGameController(){
    }

    public void loadWorld(String character, String gameStage){
        Player player = loadPlayer(character, playerPosition);
        Player dummy = loadPlayer("Dummy", dummyPosition);
        Terrain terrain = loadTerrain(gameStage);
    }

    private Player loadPlayer(String character, Pair<Integer, Integer> position){
        //load user here with serializer
        ArrayList<String> playerParams = new ArrayList<>(); //placeholder
        return new Player(playerParams, position); //loaded from json,should maybe have a starting position
    }

    private Terrain loadTerrain(){
        //load terrain here with serializer
    }

    @FXML
    private void handleUserInput(KeyEvent Event){
        
    }
}
