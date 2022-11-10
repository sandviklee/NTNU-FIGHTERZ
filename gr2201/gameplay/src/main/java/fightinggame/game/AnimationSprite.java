package fightinggame.game;
/**
 * AnimationSprite shall represent a sprite and its change for 
 */
public class AnimationSprite {
    private int currentFrame = 0;
    private int holdFrame = 0;
    private int holdFrameLength;
    private int totalFrames;
    private int animationLoopStartFrame;
    private boolean animationLoop;

    /**
     * Make AnimationSprite with given attributes
     * @param totalFrames
     * @param animationLoop
     * @param animationLoopStartFrame
     */
    public AnimationSprite(int totalFrames, boolean animationLoop, int animationLoopStartFrame, int holdFrameLength){
        if (validUserInput(totalFrames)) {
            this.totalFrames = totalFrames;
        }
        if (validUserInput(animationLoopStartFrame) && (animationLoopStartFrame < totalFrames)) {
            this.animationLoopStartFrame = animationLoopStartFrame;
        }
        this.animationLoop = animationLoop;
        this.holdFrameLength = holdFrameLength;
        
    }
    /**
     * getCurrentFrame sends out currentFrame.
     * Makes the current frame available for other classes. 
     */
    public int getCurrentFrame() {
        return currentFrame;
    }
    /**
     * Jump to frame if it exists.
     * @param userInput integer
     */
    private boolean validUserInput(int userInput) {
        if (userInput < 0) {
            throw new IllegalArgumentException("UserInput: "+ userInput + " er mindre enn null.");
        } else {
            return true;
        }
    }

    /**
     * Next increments currentFrame.
     * When currentFrame exceeds totalFrames either jump to animationLoopStartTime if animationLoop is true else stop
     */
    public void next(){
        if (currentFrame < totalFrames) {
            
            if (holdFrame < holdFrameLength) {
                holdFrame += 1;
            } else {
                holdFrame = 0;
                this.currentFrame += 1;
            }
        }

        if (animationLoop && !hasNext()) {
            jump(animationLoopStartFrame);
        }
    }
    /**
     * Jump to frame if it exists.
     * @param frame index
     */
    private void jump(int frame){
        if (frame >= 0 && frame <= totalFrames) {
            this.currentFrame = frame;
        }
    }

    /**
     * Check if AnimationSprites has a next frame.
     * @return true if it has a next frame else false.
     */
    private boolean hasNext(){
        if (currentFrame >= totalFrames) {
            return false;
        } else {
            return true;
        }
    }        
}
