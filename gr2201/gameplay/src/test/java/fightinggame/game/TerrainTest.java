package fightinggame.game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;

public class TerrainTest {
    private ArrayList<Integer> pos;
    
    @BeforeEach
    public void setup() {
        pos = new ArrayList<>();
        pos.add(0);
        pos.add(0);

    }
    @Test
    @DisplayName("Check if constructor creates correct terrain object")
    public void testConstructor() {
        Terrain nonTraversableTerrain = new Terrain("terrain1", pos, false);
        Terrain traversableTerrain = new Terrain("terrain2", pos, true);

        assertTrue(traversableTerrain.isTraversable());
        assertFalse(traversableTerrain.isTraversable());
    }
}
