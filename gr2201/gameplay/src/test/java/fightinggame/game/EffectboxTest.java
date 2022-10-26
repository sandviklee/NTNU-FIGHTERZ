package fightinggame.game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;

public class EffectboxTest {
    private int validHeight;
    private int validLength;
    private int zero;
    private int negNum;

    private WorldEntity validWorldEntity;
    private WorldEntity nonValidWorldEntity;

    private Point validPoint;
    private Point otherValidPoint;
    private Point nonValidPoint;

    private Effectbox traversableEffectbox;
    private Effectbox otherTraversableEffectbox;
    private Effectbox nonTraversableEffectbox;
    private Effectbox otherNonTraversableEffectbox;



    @BeforeEach
    public void setup() {
        validHeight = 10;
        validLength = 10;
        zero = 0;
        negNum = -10;

        nonValidWorldEntity = null;
        validWorldEntity = new WorldEntity();
        traversableEffectbox = new Effectbox(validWorldEntity, validPoint, true, validLength, validHeight);
        otherTraversableEffectbox = new Effectbox(validWorldEntity, otherValidPoint, true, validLength, validHeight);
        nonTraversableEffectbox = new Effectbox(validWorldEntity, validPoint, false, validLength, validHeight);
        otherNonTraversableEffectbox = new Effectbox(validWorldEntity, otherValidPoint, false, validLength, validHeight);

    }
    

    @Test
    @DisplayName("Check if the Constructor works ")        
    public void testConstructor() {
        
        // clean test for Constructor
        try {
            new Effectbox(validWorldEntity, validPoint, true, validLength, validHeight);
            new Effectbox(validWorldEntity, validPoint, false, validLength, validHeight);
        }
        catch(Exception e) {
            System.out.println(e.getLocalizedMessage());
            fail();
        }


        // Impropper constructions (dirty tests)
        // bad height and length values
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Effectbox(validWorldEntity, validPoint, true, validLength, negNum);
            new Effectbox(validWorldEntity, validPoint, false, validLength, negNum);

            new Effectbox(validWorldEntity, validPoint, true, negNum, validHeight);
            new Effectbox(validWorldEntity, validPoint, false, negNum, validHeight);
		}, "This Effectbox is not possible, should not allow negativ values as height or length");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Effectbox(validWorldEntity, validPoint, true, validLength, zero);
            new Effectbox(validWorldEntity, validPoint, false, validLength, zero);

            new Effectbox(validWorldEntity, validPoint, true, zero, validHeight);
            new Effectbox(validWorldEntity, validPoint, false, zero, validHeight);
		}, "This Effectbox is not possible, should not allow zero values as height or length");

        // bad owner
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Effectbox(nonValidWorldEntity, validPoint, true, validLength, validHeight);
            new Effectbox(nonValidWorldEntity, validPoint, false, validLength, validHeight);
		}, "This Effectbox is not possible, should not allow owner to be null");

        // bad point
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Effectbox(validWorldEntity, nonValidPoint, true, validLength, validHeight);
            new Effectbox(validWorldEntity, nonValidPoint, false, validLength, validHeight);
		}, "This Effectbox is not possible, should not allow point to be null");
    }

    @Test
    @DisplayName("Check if the WorldEntityInEffectArea works ")    
    public void testWorldEntityInEffectArea() {
        assertTrue(traversableEffectbox.worldEntityInEffectArea(validWorldEntity), " traversableEffectbox should not be in validWorldEntity");
        assertFalse(otherTraversableEffectbox.worldEntityInEffectArea(validWorldEntity), " otherTraversableEffectbox should be in validWorldEntity");
        assertTrue(nonTraversableEffectbox.worldEntityInEffectArea(validWorldEntity), " nonTraversableEffectbox should not be in validWorldEntity");
        assertFalse(otherNonTraversableEffectbox.worldEntityInEffectArea(validWorldEntity), " otherNonTraversableEffectbox should be in validWorldEntity");
    }

}
