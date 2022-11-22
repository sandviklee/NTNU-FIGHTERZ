package fightinggame.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fightinggame.dao.DAOFactory;
import fightinggame.dao.RODAO;
import fightinggame.game.Action;
import fightinggame.game.GameCharacter;
import fightinggame.game.PlayerProperties;
import fightinggame.game.Terrain;
import fightinggame.game.World;
import fightinggame.game.WorldEntity;
import javafx.scene.media.Media;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MultiplayerGameControllerTest {
    private World world;

    @BeforeEach
    public void setup() {
        ArrayList<Integer> characterPos = new ArrayList<>(Arrays.asList(500, 500));
        ArrayList<Integer> character2Pos = new ArrayList<>(Arrays.asList(500, 500));
        ArrayList<Integer> terrainPos = new ArrayList<>(Arrays.asList(500, 600));
        ArrayList<String> characterKeys = new ArrayList<>(Arrays.asList(".", ",", "W", "D", "A", "DW", "AW", "V", "DV", "AV", "WV", "SV", "DC", "AC", "WC", "SC", "C", "S"));
        ArrayList<String> character2Keys = new ArrayList<>(Arrays.asList(".", ",", "I", "L", "J", "LI", "JI", "N", "LN", "JN", "IN", "KN", "LM", "JM", "IM", "KM", "M", "K"));

        GameCharacter character = loadPlayer("AngryCyclist", characterPos, characterKeys, 1, 1);

        GameCharacter character2 = loadPlayer("AngryCyclist", character2Pos, character2Keys, 2, -1);

        Terrain terrain = new Terrain("TestTerrain", terrainPos, 1000, 100);
        ArrayList<WorldEntity> worldEntities = new ArrayList<>();
        
        worldEntities.add(terrain);
        worldEntities.add(character);
        worldEntities.add(character2);
        this.world = new World(worldEntities);
    }

    @Test
    @DisplayName("Tests for MultiplayerGameController Constructor")
    public void testConstructor() {
        
    }

    @Test
    @DisplayName("Tests for updating world")
    public void testSetActions() {
        //Check for chararacter1 putting character2 in hitstun.
        GameCharacter gameChar1 = world.getGameCharacters().get(0);
        GameCharacter gameChar2 = world.getGameCharacters().get(1);
        
        assertEquals("Idle", gameChar1.getCurrentAction().getName());
        assertEquals("Idle", gameChar2.getCurrentAction().getName());
        world.updateWorld("SC", "");
        assertEquals(0, gameChar1.getCurrentAction().getCurrentTime());
        for (int i = 0; i < 24; i++) {
            world.updateWorld("", "SC");
        }
        

        assertEquals(6, gameChar1.getCurrentAction().getCurrentTime());
        assertEquals("DownNormal", gameChar1.getCurrentAction().getName());
        assertNotNull(gameChar1.getCurrentAction().getHitBox());
        assertEquals("HitStun", gameChar2.getCurrentAction().getName());

        
    }   
    @Test
    @DisplayName("Tests for handleCollision")
    public void testHandleCollison() {


    }

    private GameCharacter loadPlayer(String character, ArrayList<Integer> position, ArrayList<String> availKeys, int playerNumb, int facingDirection){
        RODAO<PlayerProperties, String> characterAttributeDAO = DAOFactory.getCharacterAttributeDAO();
        PlayerProperties playerProperties = characterAttributeDAO.find(character);
        ArrayList<String> availKeysArray = new ArrayList<>(availKeys);
        HashMap<String, Media> characterAudio = new HashMap<>();

        return new GameCharacter(playerProperties, characterAudio, position, availKeysArray, playerNumb, facingDirection); //loaded from json,should maybe have a starting position
    }
}