package fightinggame.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;

public class ActionTest {
    private Effectbox hitbox;
    private String spriteName;
    private Point point;
    private int validDuration;
    private int validActionPriority1;
    private int validActionPriority2;
    private int validActionPriority3;
    private int maxValidActionPriority;

    private int negNum;
    private Vector vec;

    private ActionProperties nonValidActionProperties;

    private ActionProperties apInterruptable;
    private ActionProperties apSelfInterruptable;
    private ActionProperties apEnemyInterruptable;
    private ActionProperties apNotInterruptable;

    private Action actionCantBeInterrupted;
    private Action actionCanBeSelfInterrupted;
    private Action actionCanBeEnemyInterrupted;
    private Action actionCanBeInterrupted;

    @BeforeEach
    public void setup() {
        validDuration = 100;
        nonValidActionProperties = null;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);

        point = new Point(0, 0);
        hitbox = new Effectbox(null, point, true, 100, 100);

        apInterruptable = new ActionProperties(spriteName, validActionPriority1, validDuration, true, true, 100, false,
                0, true);
        apSelfInterruptable = new ActionProperties(spriteName, validActionPriority1, validDuration, true, false, 100,
                false, 0, true);
        apEnemyInterruptable = new ActionProperties(spriteName, validActionPriority1, validDuration, false, true, 100,
                false, 0, true);
        apNotInterruptable = new ActionProperties(spriteName, validActionPriority1, validDuration, false, false, 100,
                false, 0, true);

        actionCantBeInterrupted = new Action(apInterruptable);
        actionCanBeSelfInterrupted = new Action(apSelfInterruptable);
        actionCanBeEnemyInterrupted = new Action(apEnemyInterruptable);
        actionCanBeInterrupted = new Action(apNotInterruptable);
    }

    @Test
    @DisplayName("Check if constructor creates correct instances")
    public void testConstructor() {

        // First constructor: with Effectbox
        Assertions.assertThrows(NullPointerException.class, () -> {
            new Action(nonValidActionProperties);
        }, "This action is not possible");

        // clean constructor tests:
        try {
            actionCanBeInterrupted = new Action(apInterruptable);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            fail();
        }
        assertFalse(actionCanBeInterrupted.getIsDone(),
                " should not be true as its valid duration is not iterated throug");
    }

    @Test
    @DisplayName("Check if nextActionFrame")
    public void testNextActionFrame() {
        // currentTime must increase
        // for (int t = 0; t < validDuration; t++) {
        // assertEquals(t, actionCantBeInterrupted.getCurrentFrame(),
        // "check if iterateSprite itterates the sprite of the owner action");
        // assertEquals(t, actionCanBeSelfInterrupted.getCurrentFrame(),
        // "check if iterateSprite itterates the sprite of the owner action");
        // assertEquals(t, actionCanBeEnemyInterrupted.getCurrentFrame(),
        // "check if iterateSprite itterates the sprite of the owner action");
        // assertEquals(t, actionCanBeInterrupted.getCurrentFrame(),
        // "check if iterateSprite itterates the sprite of the owner action");

        // actionCantBeInterrupted.nextActionFrame();
        // actionCanBeSelfInterrupted.nextActionFrame();
        // actionCanBeEnemyInterrupted.nextActionFrame();
        // actionCanBeInterrupted.nextActionFrame();

        // assertFalse(actionCantBeInterrupted.getIsDone(), "Action should not be
        // done");
        // assertFalse(actionCanBeSelfInterrupted.getIsDone(), "Action should not be
        // done");
        // assertFalse(actionCanBeEnemyInterrupted.getIsDone(), "Action should not be
        // done");
        // assertFalse(actionCanBeInterrupted.getIsDone(), "Action should not be done");
        // }

        // // all should be done. Should not iterate its AnimationSprite
        // actionCantBeInterrupted.nextActionFrame();
        // actionCanBeSelfInterrupted.nextActionFrame();
        // actionCanBeEnemyInterrupted.nextActionFrame();
        // actionCanBeInterrupted.nextActionFrame();

        // assertEquals(validDuration, actionCantBeInterrupted.getCurrentFrame(),
        // "Should not iterateSprite iterate the sprite of the owner action");
        // assertEquals(validDuration, actionCanBeSelfInterrupted.getCurrentFrame(),
        // "Should not iterateSprite iterate the sprite of the owner action");
        // assertEquals(validDuration, actionCanBeEnemyInterrupted.getCurrentFrame(),
        // "Should not iterateSprite iterate the sprite of the owner action");
        // assertEquals(validDuration, actionCanBeInterrupted.getCurrentFrame(),
        // "Should not iterateSprite iterate the sprite of the owner action");

        // assertTrue(actionCantBeInterrupted.getIsDone(), "Action should be done");
        // assertTrue(actionCanBeSelfInterrupted.getIsDone(), "Action should be done");
        // assertTrue(actionCanBeEnemyInterrupted.getIsDone(), "Action should be done");
        // assertTrue(actionCanBeInterrupted.getIsDone(), "Action should be done");
    }

    @Test
    @DisplayName("Check if trySelfInterrupt works as intended")
    public void testTrySelfInterrupt() {
        // A negativ number should not be able to interrupt
        // actionCantBeInterrupted.trySelfInterrupt(negNum);
        // actionCanBeSelfInterrupted.trySelfInterrupt(negNum);
        // actionCanBeEnemyInterrupted.trySelfInterrupt(negNum);
        // actionCanBeInterrupted.trySelfInterrupt(negNum);

        // assertFalse(actionCantBeInterrupted.getIsDone(), "A negativ value should not
        // interrupt this action");
        // assertFalse(actionCanBeSelfInterrupted.getIsDone(), "A negativ value should
        // not interrupt this action");
        // assertFalse(actionCanBeEnemyInterrupted.getIsDone(), "A negativ value should
        // not interrupt this action");
        // assertFalse(actionCanBeInterrupted.getIsDone(), "A negativ value should not
        // interrupt this action");

        // // An equal value
        // actionCantBeInterrupted.trySelfInterrupt(validActionPriority1);
        // actionCanBeSelfInterrupted.trySelfInterrupt(validActionPriority1);
        // actionCanBeEnemyInterrupted.trySelfInterrupt(validActionPriority1);
        // actionCanBeInterrupted.trySelfInterrupt(validActionPriority1);

        // assertFalse(actionCantBeInterrupted.getIsDone(), "An equal value should not
        // interrupt this action");
        // assertFalse(actionCanBeSelfInterrupted.getIsDone(), "An equal value should
        // not interrupt this action");
        // assertFalse(actionCanBeEnemyInterrupted.getIsDone(), "An equal value should
        // not interrupt this action");
        // assertFalse(actionCanBeInterrupted.getIsDone(), "An equal value should not
        // interrupt this action");

        // max value
        // actionCantBeInterrupted.trySelfInterrupt(maxValidActionPriority);
        // actionCanBeSelfInterrupted.trySelfInterrupt(maxValidActionPriority);
        // actionCanBeEnemyInterrupted.trySelfInterrupt(maxValidActionPriority);
        // actionCanBeInterrupted.trySelfInterrupt(maxValidActionPriority);

        // assertFalse(actionCantBeInterrupted.getIsDone(), "maxValidAction should not
        // interrupt this current action");
        // assertTrue(actionCanBeSelfInterrupted.getIsDone(), "maxValidAction should
        // interrupt this current action");
        // assertFalse(actionCanBeEnemyInterrupted.getIsDone(), "maxValidAction should
        // not interrupt this current action");
        // assertTrue(actionCanBeInterrupted.getIsDone(), "maxValidAction should
        // interrupt this current action");
    }

    @Test
    @DisplayName("Check if tryEnemyInterrupt works as intended")
    public void testTryEnemyInterrupt() {
        // A negativ number:
        // actionCantBeInterrupted.tryEnemyInterrupt(negNum);
        // actionCanBeSelfInterrupted.tryEnemyInterrupt(negNum);
        // actionCanBeEnemyInterrupted.tryEnemyInterrupt(negNum);
        // actionCanBeInterrupted.tryEnemyInterrupt(negNum);

        assertFalse(actionCantBeInterrupted.getIsDone(), "A negativ value should not interrupt this action");
        assertFalse(actionCanBeSelfInterrupted.getIsDone(), "A negativ value should not interrupt this action");
        assertFalse(actionCanBeEnemyInterrupted.getIsDone(), "A negativ value should not interrupt this action");
        assertFalse(actionCanBeInterrupted.getIsDone(), "A negativ value should not interrupt this action");

        // An equal value:
        // actionCantBeInterrupted.trySelfInterrupt(validActionPriority1);
        // actionCanBeSelfInterrupted.trySelfInterrupt(validActionPriority1);
        // actionCanBeEnemyInterrupted.trySelfInterrupt(validActionPriority1);
        // actionCanBeInterrupted.trySelfInterrupt(validActionPriority1);

        assertFalse(actionCantBeInterrupted.getIsDone(), "An equal value should not interrupt this action");
        assertFalse(actionCanBeSelfInterrupted.getIsDone(), "An equal value should not interrupt this action");
        assertFalse(actionCanBeEnemyInterrupted.getIsDone(), "An equal value should not interrupt this action");
        assertFalse(actionCanBeInterrupted.getIsDone(), "An equal value should not interrupt this action");

        // Max value:
        // actionCantBeInterrupted.tryEnemyInterrupt(maxValidActionPriority);
        // actionCanBeSelfInterrupted.tryEnemyInterrupt(maxValidActionPriority);
        // actionCanBeEnemyInterrupted.tryEnemyInterrupt(maxValidActionPriority);
        // actionCanBeInterrupted.tryEnemyInterrupt(maxValidActionPriority);

        // assertFalse(actionCantBeInterrupted.getIsDone(), "maxValidAction should not
        // interrupt this current action");
        // assertFalse(actionCanBeSelfInterrupted.getIsDone(), "maxValidAction should
        // interrupt this current action");
        // assertTrue(actionCanBeEnemyInterrupted.getIsDone(), "maxValidAction should
        // not interrupt this current action");
        // assertTrue(actionCanBeInterrupted.getIsDone(), "maxValidAction should
        // interrupt this current action");
    }

}
