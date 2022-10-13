package fightinggame.game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;


public class AnimationSpriteTest {
    
    private AnimationSprite animationSprite1;
    private AnimationSprite animationSprite1;

    private int validFrameAmount;
    private int validAnimationLoopStartFrame ;
    private int nonValidAnimationLoopStartFrame ;
    private int negNum;

    @BeforeEach
    public void setup() {
        negNum = -1;
        validFrameAmount = 10;
        validAnimationLoopStartFrame = 5;
        nonValidAnimationLoopStartFrame = 11;
    }

    @Test
    @DisplayName("Test constructor")
    public void testConstructor() {
        // Constructor should not accept negativ numbers
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            AnimationSprite(negNum, true, negNum);
		}, "This AnimationSprite is not possible");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            AnimationSprite(negNum, false, negNum);
		}, "This AnimationSprite is not possible");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            AnimationSprite(validFrameAmount, false, negNum);
		}, "This AnimationSprite is not possible");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            AnimationSprite(negNum, false, validAnimationLoopStartFrame);
		}, "This AnimationSprite is not possible");

        // should not accept non logical AnimationSprites
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            AnimationSprite(validFrameAmount, false, nonValidAnimationLoopStartFrame);
		}, "This AnimationSprite is not possible");

        // valid AnimationSprite should not give exception
        animationSprite1 = new AnimationSprite(validFrameAmount, false, validAnimationLoopStartFrame);
        animationSprite2 = new AnimationSprite(validFrameAmount, true, validAnimationLoopStartFrame);
    }

    @Test
    @DisplayName("Check for correct next logic")
    public void nextTest() {
        animationSprite1 = new AnimationSprite(validFrameAmount, false, validAnimationLoopStartFrame);
        animationSprite2 = new AnimationSprite(validFrameAmount, true, validAnimationLoopStartFrame);

        assertEquals(0, animationSprite1.getFrame());
        assertEquals(0, animationSprite2.getFrame());

        // Iterate over all frames
        for (int i = 0; i < validFrameAmount; i++) {
            animationSprite1.next();
            animationSprite2.next();
            assertTrue(animationSprite1.getFrame() == i && animationSprite2.getFrame() == i);
        }

        // check what happens after last frame
        animationSprite1.next();
        animationSprite2.next();

        assertEquals(validFrameAmount, animationSprite1.getFrame(), "Should be at validFrameAmount after lastframes next"); 
        assertEquals(validAnimationLoopStartFrame, animationSprite2.getFrame(), "Should be at validAnimationLoopStartFrame after lastframes next");        

        assertFalse(animationSprite1.hasNext());
        assertTrue(animationSprite2.hasNext());
    }

}
