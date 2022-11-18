package fightinggame.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;

public class ActionPropertiesTest {
    private Effectbox validEffectbox;
    private Effectbox nonValidEffectbox;

    private String validSpriteName;
    private String nonValidSpriteName;

    private Vector validVector;
    private Vector nonValidVector;

    private int validDuration;
    private int nonValidDuration;
    private int actionPriority;
    private int validHitBoxStartTime;
    private int nonValidHitBoxStartTime;
    private int validDamage;
    private int nonValidDamage;

    private AnimationSpritePlayer validAnimationSpritePlayer;
    private AnimationSpritePlayer nonValidAnimationSpritPlayere;

    private ActionProperties firstActionProperties;
    private ActionProperties secondActionProperties;
    private ActionProperties thirdActionProperties;

    @BeforeEach
    public void setup() {
        validEffectbox = new Effectbox(null, new Point(0, 0), true, 10, 10);
        nonValidEffectbox = null;
        validSpriteName = "Idle";
        nonValidSpriteName = "";
        validVector = new Vector();
        nonValidVector = null;
        validDuration = 10;
        nonValidDuration = -1;
        actionPriority = 3;
        validHitBoxStartTime = 0;
        nonValidHitBoxStartTime = -1;
        validDamage = 10;
        nonValidDamage = -10;
        validAnimationSpritePlayer = new AnimationSpritePlayer(10, true, 5, 3);
        nonValidAnimationSpritPlayere = null;
    }

    // @Test
    // @DisplayName("Check if constructor creates correct instances")
    // public void testConstructor() {

    // First constructor: public ActionProperties(String spriteName, int
    // actionPriority, int duration, boolean isSelfInterruptible, boolean
    // isEnemyInterruptible, int totalFrames, boolean animationLoop, int
    // animationLoopStartFrame)
    // ActionProperties without a Effectbox, e.g. idle, move.

    // Assertions.assertThrows(IllegalArgumentException.class, () -> {
    // new ActionProperties(nonValidSpriteName, actionPriority, validDuration, true,
    // true,
    // validAnimationSpritePlayer);
    // new ActionProperties(validSpriteName, actionPriority, validDuration, true,
    // true,
    // validAnimationSpritePlayer);
    // new ActionProperties(validSpriteName, actionPriority, nonValidDuration, true,
    // true,
    // validAnimationSpritePlayer);
    // new ActionProperties(validSpriteName, actionPriority, validDuration, true,
    // true,
    // nonValidAnimationSpritePlayer);

    // }, "This ActionProperties is not possible");

    // try {
    // firstActionProperties = new ActionProperties(validSpriteName, actionPriority,
    // validDuration, true, true,
    // validAnimationSpritePlayer);
    // } catch (Exception e) {
    // System.out.println(e.getLocalizedMessage());
    // fail();
    // }
    // assertEquals(null, firstActionProperties.getHitBox(),
    // "this constructor does not have a hitbox and should not assign one either");
    // assertEquals(null, firstActionProperties.getKnockback(),
    // "this constructor does not have knockback and should not assign one either");
    // // assertEquals(null, firstActionProperties.getHitBoxStartTime(),
    // // "this constructor does not have hitbox and should not assign a
    // // hitboxStartTime either");
    // // assertEquals(null, firstActionProperties.getDamage(),
    // // "this constructor does not have hitbox and should not deal damage");

    // assertEquals(validSpriteName, firstActionProperties.getSpriteName(),
    // "this constructor has not set spritename correctly");
    // assertEquals(actionPriority, firstActionProperties.getActionPriority(),
    // "this constructor has not set actionPriority correctly");
    // assertEquals(validDuration, firstActionProperties.getDuration(),
    // "this constructor has not set duration correctly");
    // assertTrue(firstActionProperties.isSelfInterruptible(),
    // "this constructor has not set isSelfInterruptible correctly");
    // assertTrue(firstActionProperties.isEnemyInterruptible(),
    // "this constructor has not set isEnemyInterruptible correctly");
    // assertEquals(validAnimationSpritePlayer,
    // firstActionProperties.getAnimationSprite(),
    // "this constructor has not set animationSprite correctly");

    // Second constructor:
    // ActionProperties with knockback but not effectbox
    // Assertions.assertThrows(IllegalArgumentException.class, () -> {
    // new ActionProperties(nonValidSpriteName, actionPriority, validDuration, true,
    // true,
    // validAnimationSpritePlayer,
    // validVector);
    // new ActionProperties(validSpriteName, actionPriority, nonValidDuration, true,
    // true,
    // validAnimationSpritePlayer,
    // validVector);
    // new ActionProperties(validSpriteName, actionPriority, validDuration, true,
    // true,
    // nonValidAnimationSpritePlayer,
    // validVector);
    // new ActionProperties(validSpriteName, actionPriority, validDuration, true,
    // true, validAnimationSpritePlayer,
    // nonValidVector);
    // }, "This ActionProperties is not possible");

    // try {
    // secondActionProperties = new ActionProperties(validSpriteName,
    // actionPriority, validDuration, true, true,
    // validAnimationSpritePlayer, validVector);
    // } catch (Exception e) {
    // System.out.println(e.getLocalizedMessage());
    // fail();
    // }
    // assertEquals(null, secondActionProperties.getHitBox(),
    // "this constructor does not have a hitbox and should not assign one either");
    // assertEquals(validVector, secondActionProperties.getKnockback(),
    // "this constructor has not set knockback correctly");
    // // assertEquals(null, secondActionProperties.getHitBoxStartTime(),
    // // "this constructor does not have hitbox and should not assign a
    // // hitboxStartTime either");
    // // assertEquals(null, secondActionProperties.getDamage(),
    // // "this constructor does not have hitbox and should not deal damage");

    // assertEquals(validSpriteName, secondActionProperties.getSpriteName(),
    // "this constructor has not set spritename correctly");
    // assertEquals(actionPriority, secondActionProperties.getActionPriority(),
    // "this constructor has not set actionPriority correctly");
    // assertEquals(validDuration, secondActionProperties.getDuration(),
    // "this constructor has not set duration correctly");
    // assertTrue(secondActionProperties.isSelfInterruptible(),
    // "this constructor has not set isSelfInterruptible correctly");
    // assertTrue(secondActionProperties.isEnemyInterruptible(),
    // "this constructor has not set isEnemyInterruptible correctly");
    // assertEquals(validAnimationSpritePlayer,
    // secondActionProperties.getAnimationSprite(),
    // "this constructor has not set animationSprite correctly");

    // // Third constructor:
    // // ActionProperties with hitbox e.g. attack
    // Assertions.assertThrows(IllegalArgumentException.class, () -> {
    // new ActionProperties(nonValidEffectbox, validSpriteName, true, true,
    // validVector, validDuration,
    // actionPriority, validHitBoxStartTime, validDamage,
    // validAnimationSpritePlayer);
    // new ActionProperties(validEffectbox, nonValidSpriteName, true, true,
    // validVector, validDuration,
    // actionPriority, validHitBoxStartTime, validDamage,
    // validAnimationSpritePlayer);
    // new ActionProperties(validEffectbox, validSpriteName, true, true,
    // nonValidVector, validDuration,
    // actionPriority, validHitBoxStartTime, validDamage,
    // validAnimationSpritePlayer);
    // new ActionProperties(validEffectbox, validSpriteName, true, true,
    // validVector, nonValidDuration,
    // actionPriority, validHitBoxStartTime, validDamage,
    // validAnimationSpritePlayer);
    // new ActionProperties(validEffectbox, validSpriteName, true, true,
    // validVector, validDuration,
    // actionPriority, nonValidHitBoxStartTime, validDamage,
    // validAnimationSpritePlayer);
    // new ActionProperties(validEffectbox, validSpriteName, true, true,
    // validVector, validDuration,
    // actionPriority, validHitBoxStartTime, nonValidDamage,
    // validAnimationSpritePlayer);
    // new ActionProperties(validEffectbox, validSpriteName, true, true,
    // validVector, validDuration,
    // actionPriority, validHitBoxStartTime, validDamage,
    // nonValidAnimationSpritePlayer);
    // }, "This ActionProperties is not possible");

    // // clean test:
    // try {
    // thirdActionProperties = new ActionProperties(validEffectbox, validSpriteName,
    // true, true, validVector,
    // validDuration, actionPriority, validHitBoxStartTime, validDamage,
    // validAnimationSpritePlayer);

    // } catch (Exception e) {
    // System.out.println(e.getLocalizedMessage());
    // fail();
    // }
    // // check fields
    // assertEquals(validEffectbox, thirdActionProperties.getHitBox(),
    // "this constructor has not set HitBox correctly");
    // assertEquals(validVector, thirdActionProperties.getKnockback(),
    // "this constructor has not set knockback correctly");
    // assertEquals(validHitBoxStartTime,
    // thirdActionProperties.getHitBoxStartTime(),
    // "this constructor has not set HitBoxStartTime correctly");
    // assertEquals(validDamage, thirdActionProperties.getDamage(), "this
    // constructor has not set Damage correctly");

    // assertEquals(validSpriteName, thirdActionProperties.getSpriteName(),
    // "this constructor has not set spritename correctly");
    // assertEquals(actionPriority, thirdActionProperties.getActionPriority(),
    // "this constructor has not set actionPriority correctly");
    // assertEquals(validDuration, thirdActionProperties.getDuration(),
    // "this constructor has not set duration correctly");
    // assertTrue(thirdActionProperties.isSelfInterruptible(),
    // "this constructor has not set isSelfInterruptible correctly");
    // assertTrue(thirdActionProperties.isEnemyInterruptible(),
    // "this constructor has not set isEnemyInterruptible correctly");
    // // assertEquals(validAnimationSpritePlayer,
    // // thirdActionProperties.getAnimationSpritePlayer(),
    // // "this constructor has not set animationSprite correctly");
    // }
}
