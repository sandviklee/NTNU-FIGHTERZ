package fightinggame.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VectorTest {


     @Test
    @DisplayName("Check if the Constructor works ")        
    public void testConstructor() {
        
        // clean test for Constructor
        try {
            new Vector();
            new Vector();
        }
        catch(Exception e) {
            fail();
            System.out.println(e.getLocalized);
        }


        // Impropper constructions (dirty tests)
        // bad height and length values
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new EffectBox(validWorldEntity, validPoint, true, validLength, negNum);

		}, "This EffectBox is not possible, should not allow negativ values as height or length");
    }
    
    @Test
    @DisplayName("")
    public void testAddVector() {

    }

    @Test
    @DisplayName("")
    public void testApplyAcceleration() {

    }

    @Test
    @DisplayName("")
    public void testGetVx() {

    }

    @Test
    @DisplayName("")
    public void testGetVy() {

    }
}
