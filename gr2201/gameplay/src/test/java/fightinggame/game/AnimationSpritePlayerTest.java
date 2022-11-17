package fightinggame.game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;


public class AnimationSpritePlayerTest {
    
    private AnimationSpritePlayer AnimationSpritePlayer1;
    private AnimationSpritePlayer AnimationSpritePlayer2;

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
            new AnimationSpritePlayer(negNum, true, negNum, 0);
		}, "This AnimationSpritePlayer is not possible");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new AnimationSpritePlayer(negNum, false, negNum, 0);
		}, "This AnimationSpritePlayer is not possible");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new AnimationSpritePlayer(validFrameAmount, false, negNum, 0);
		}, "This AnimationSpritePlayer is not possible");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new AnimationSpritePlayer(negNum, false, validAnimationLoopStartFrame, 0);
		}, "This AnimationSpritePlayer is not possible");

        // should not accept AnimationSpritePlayers with animationLoopStartFrame larger then totalFrames
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new AnimationSpritePlayer(validFrameAmount, false, nonValidAnimationLoopStartFrame, 0);
		}, "This AnimationSpritePlayer is not possible");

        // valid AnimationSpritePlayer should not give exception
        try {
            AnimationSpritePlayer1 = new AnimationSpritePlayer(validFrameAmount, false, validAnimationLoopStartFrame, 0);
            AnimationSpritePlayer2 = new AnimationSpritePlayer(validFrameAmount, true, validAnimationLoopStartFrame, 0);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            fail();
        }
        
        
    }

    @Test
    @DisplayName("Check for correct next logic")
    public void nextTest() {
        AnimationSpritePlayer1 = new AnimationSpritePlayer(validFrameAmount, false, validAnimationLoopStartFrame, 0);
        AnimationSpritePlayer2 = new AnimationSpritePlayer(validFrameAmount, true, validAnimationLoopStartFrame, 0);

        assertEquals(0, AnimationSpritePlayer1.getCurrentFrame());
        assertEquals(0, AnimationSpritePlayer2.getCurrentFrame());

        // Iterate over all frames
        for (int i = 0; i < validFrameAmount; i++) {
            assertTrue(AnimationSpritePlayer1.getCurrentFrame() == i && AnimationSpritePlayer2.getCurrentFrame() == i);
            AnimationSpritePlayer1.next();
            AnimationSpritePlayer2.next();
        }
        assertEquals(validFrameAmount, AnimationSpritePlayer1.getCurrentFrame(), "Should be at validFrameAmount after lastframes next"); 
        assertEquals(validAnimationLoopStartFrame, AnimationSpritePlayer2.getCurrentFrame(), "Should be at validAnimationLoopStartFrame after lastframes next");        
        System.out.println(validFrameAmount + validAnimationLoopStartFrame + " " + AnimationSpritePlayer2.getCurrentFrame());
        // check what happens after last frame
        AnimationSpritePlayer1.next();
        AnimationSpritePlayer2.next();

        // Iterate after max
        assertEquals(validFrameAmount, AnimationSpritePlayer1.getCurrentFrame(), "Should be at validFrameAmount after lastframes next"); 
        assertEquals(validAnimationLoopStartFrame + 1, AnimationSpritePlayer2.getCurrentFrame(), "Should be at validAnimationLoopStartFrame + 1 after lastframes next");        

    }
}
