package fightinggame.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

public class WorldTest {
    private World world;
    private double characterStartXPos;

    @BeforeEach
    public void setup() {
        ArrayList<Integer> characterPos = new ArrayList<>(Arrays.asList(500, 500));
        ArrayList<Integer> terrainPos = new ArrayList<>(Arrays.asList(500, 600));
        this.characterStartXPos = characterPos.get(0);
        ArrayList<String> dummyKeys = new ArrayList<>(Arrays.asList(".", ",", "W", "D", "A", "DW", "AW", "V", "DV", "AV", "WV", "SV", "DC", "AC", "WC", "SC", "C", "S"));
        GameCharacter character = new GameCharacter("TestCharacter", characterPos, dummyKeys, 1);
        Terrain terrain = new Terrain("TestTerrain", terrainPos, 1000, 100);
        ArrayList<WorldEntity> worldEntities = new ArrayList<>();
        worldEntities.add(terrain);
        worldEntities.add(character);
        this.world = new World(worldEntities);
        System.out.println(character.getAvailKeys());

    }

    @Test
    @DisplayName("Tests for World constructor")
    public void testConstructor() {
        assertEquals("TestTerrain", world.getWorldEntities().get(0).getName());   
        assertEquals("TestCharacter", world.getWorldEntities().get(1).getName());
        assertEquals("TestCharacter", world.getGameCharacters().get(0).getName());
    }

    @Test
    @DisplayName("Tests for setAction")
    public void testSetActions() {
        //Firstly check that when a gamecharacter is spawned, it should have the "Idle" Action
        Action currentAction = world.getGameCharacters().get(0).getCurrentAction();
        assertEquals("Idle", currentAction.getName());
        //Terrain should not have an action, and therefore it should be null.
        currentAction = world.getWorldEntities().get(0).getCurrentAction();
        assertNull(currentAction);
        //Now we can check if setting a new action for the game character works.
        world.updateWorld("D", "");
        currentAction = world.getGameCharacters().get(0).getCurrentAction();
        assertEquals("Run", currentAction.getName());
        //Now check for different position on the canvas.
        double newCharacterXPos = world.getGameCharacters().get(0).getX();
        assertNotEquals(characterStartXPos, newCharacterXPos);
        
    }   
}
