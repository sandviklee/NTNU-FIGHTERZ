package fightinggame.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerPropertiesTest {
    private PlayerProperties playerProperties;
    private String name;
    private int weight;
    private int length;
    private int width;
    private int speed;
    private ArrayList<ActionProperties> actionP;
    
    @BeforeEach
    public void setup() {
        this.name = "Test";
        this.weight = 10;
        this.length = 100;
        this.width = 50;
        this.speed = 20;
        this.actionP = null;
        playerProperties = new PlayerProperties(name, weight, length, width, speed, actionP);
    }

    @Test
    @DisplayName("Test the constructor")
    public void testConstructor() {
        assertEquals(name, playerProperties.getCharacterName());
        assertEquals(weight, playerProperties.getWeight());
        assertEquals(length, playerProperties.getLength()
        );
        assertEquals(width, playerProperties.getWidth());
        assertEquals(speed, playerProperties.getSpeed());
        assertEquals(actionP, playerProperties.getActionProperties());
    }
}
