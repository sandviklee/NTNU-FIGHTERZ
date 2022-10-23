package fightinggame.game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    private EffectBox traversableEffectBox;
    private EffectBox otherTraversableEffectBox;
    private EffectBox nonTraversableEffectBox;
    private EffectBox otherNonTraversableEffectBox;



    @BeforeEach
    public void setup() {
        validHeight = 10;
        validLength = 10;
        zero = 0;
        negNum = -10;

        nonValidWorldEntity = null;
        validWorldEntity = new WorldEntity();
        traversableEffectBox = new EffectBox(validWorldEntity, validPoint, true, validLength, validHeight);
        otherTraversableEffectBox = new EffectBox(validWorldEntity, otherValidPoint, true, validLength, validHeight);
        nonTraversableEffectBox = new EffectBox(validWorldEntity, validPoint, false, validLength, validHeight);
        otherNonTraversableEffectBox = new EffectBox(validWorldEntity, otherValidPoint, false, validLength, validHeight);

    }
    

    @Test
    @DisplayName("Check if the Constructor works ")        
    public void testConstructor() {
        
        // clean test for Constructor
        try {
            new EffectBox(validWorldEntity, validPoint, true, validLength, validHeight);
            new EffectBox(validWorldEntity, validPoint, false, validLength, validHeight);
        }
        catch(Exception e) {
            fail();
            System.out.println(e.getLocalized);
        }


        // Impropper constructions (dirty tests)
        // bad height and length values
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new EffectBox(validWorldEntity, validPoint, true, validLength, negNum);
            new EffectBox(validWorldEntity, validPoint, false, validLength, negNum);

            new EffectBox(validWorldEntity, validPoint, true, negNum, validHeight);
            new EffectBox(validWorldEntity, validPoint, false, negNum, validHeight);
		}, "This EffectBox is not possible, should not allow negativ values as height or length");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new EffectBox(validWorldEntity, validPoint, true, validLength, zero);
            new EffectBox(validWorldEntity, validPoint, false, validLength, zero);

            new EffectBox(validWorldEntity, validPoint, true, zero, validHeight);
            new EffectBox(validWorldEntity, validPoint, false, zero, validHeight);
		}, "This EffectBox is not possible, should not allow zero values as height or length");

        // bad owner
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new EffectBox(nonValidWorldEntity, validPoint, true, validLength, validHeight);
            new EffectBox(nonValidWorldEntity, validPoint, false, validLength, validHeight);
		}, "This EffectBox is not possible, should not allow owner to be null");

        // bad point
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new EffectBox(validWorldEntity, nonValidPoint, true, validLength, validHeight);
            new EffectBox(validWorldEntity, nonValidPoint, false, validLength, validHeight);
		}, "This EffectBox is not possible, should not allow owner to be null");
    }

    @Test
    @DisplayName("Check if the WorldEntityInEffectArea works ")    
    public void testWorldEntityInEffectArea() {
        assertTrue(traversableEffectBox.worldEntityInEffectArea(validWorldEntity), " traversableEffectBox should not be in validWorldEntity");
        assertFalse(otherTraversableEffectBox.worldEntityInEffectArea(validWorldEntity), " otherTraversableEffectBox should be in validWorldEntity");
        assertTrue(nonTraversableEffectBox(validWorldEntity), " nonTraversableEffectBox should not be in validWorldEntity");
        assertFalse(otherNonTraversableEffectBox(validWorldEntity), " otherNonTraversableEffectBox should be in validWorldEntity");
    }

}
