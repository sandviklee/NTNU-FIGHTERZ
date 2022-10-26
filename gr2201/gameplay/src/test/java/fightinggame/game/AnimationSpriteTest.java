package fightinggame.game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;


public class AnimationSpriteTest {
    
    private AnimationSprite animationSprite1;
    private AnimationSprite animationSprite2;

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
            new AnimationSprite(negNum, true, negNum);
		}, "This AnimationSprite is not possible");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new AnimationSprite(negNum, false, negNum);
		}, "This AnimationSprite is not possible");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new AnimationSprite(validFrameAmount, false, negNum);
		}, "This AnimationSprite is not possible");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new AnimationSprite(negNum, false, validAnimationLoopStartFrame);
		}, "This AnimationSprite is not possible");

        // should not accept AnimationSprites with animationLoopStartFrame larger then totalFrames
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new AnimationSprite(validFrameAmount, false, nonValidAnimationLoopStartFrame);
		}, "This AnimationSprite is not possible");

        // valid AnimationSprite should not give exception
        try {
            animationSprite1 = new AnimationSprite(validFrameAmount, false, validAnimationLoopStartFrame);
            animationSprite2 = new AnimationSprite(validFrameAmount, true, validAnimationLoopStartFrame);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            fail();
            // TODO: handle exception
        }
        
        
    }

    @Test
    @DisplayName("Check for correct next logic")
    public void nextTest() {
        animationSprite1 = new AnimationSprite(validFrameAmount, false, validAnimationLoopStartFrame);
        animationSprite2 = new AnimationSprite(validFrameAmount, true, validAnimationLoopStartFrame);

        assertEquals(0, animationSprite1.getCurrentFrame());
        assertEquals(0, animationSprite2.getCurrentFrame());

        // Iterate over all frames
        for (int i = 0; i < validFrameAmount; i++) {
            assertTrue(animationSprite1.getCurrentFrame() == i && animationSprite2.getCurrentFrame() == i);
            animationSprite1.next();
            animationSprite2.next();
        }
        assertEquals(validFrameAmount, animationSprite1.getCurrentFrame(), "Should be at validFrameAmount after lastframes next"); 
        assertEquals(validAnimationLoopStartFrame, animationSprite2.getCurrentFrame(), "Should be at validAnimationLoopStartFrame after lastframes next");        
        System.out.println(validFrameAmount + validAnimationLoopStartFrame + " " + animationSprite2.getCurrentFrame());
        // check what happens after last frame
        animationSprite1.next();
        animationSprite2.next();

        // Iterate after max
        assertEquals(validFrameAmount, animationSprite1.getCurrentFrame(), "Should be at validFrameAmount after lastframes next"); 
        assertEquals(validAnimationLoopStartFrame + 1, animationSprite2.getCurrentFrame(), "Should be at validAnimationLoopStartFrame + 1 after lastframes next");        

    }
}
