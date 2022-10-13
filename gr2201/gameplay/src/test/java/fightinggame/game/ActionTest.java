package fightinggame.game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ActionTest {
    private String validSpriteName;
    private int validDuration;
    private int validActionPriority1;
    private int validActionPriority2;
    private int validActionPriority3;


    private int negNum;
    private Boolean truthy;
    private Boolean falsy;
    private Effectbox box;
    private Vector vec;


    private Action actionCantBeInterupted;
    private Action actionCanBeSelfInterupted;
    private Action actionCanBeEnemyInterupted;


    @BeforeEach
    public void setup() {
        
    } 

    @Test
    @DisplayName("Check if constructor creates correct instances")
    public void testConstructor() {

        // First constructor: with EffectBox

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Action(box, validSpriteName, truthy, 
                truthy, vec, validDuration, actionPriority, 
                hitBoxStartTime, damage, totalFrames, animationLoop, animationLoopStartTime);
		}, "This AnimationSprite is not possible");


        // Second constructor:

    }
}
