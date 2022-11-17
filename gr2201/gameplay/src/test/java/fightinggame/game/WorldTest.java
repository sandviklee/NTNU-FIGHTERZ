package fightinggame.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

public class WorldTest {
    

    @Test
    @DisplayName("Tests for World constructor")
    public void testConstructor() {
        Terrain terrain = new Terrain("test", new ArrayList<>(Arrays.asList(0, 0)), 10, 10);

        ArrayList<WorldEntity> worldEntities = new ArrayList<>();
        worldEntities.add(terrain);

        World world = new World(worldEntities);

        assertEquals("test", world.getWorldEntities().get(0).getName());
        
    }
}
