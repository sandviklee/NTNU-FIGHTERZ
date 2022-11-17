package fightinggame.game;
/**
 * The {@code AnimationSprite} class represents an AnimationPlayer 
 */
public class AnimationSpritePlayer {
    private int currentFrame = 0;
    private int totalFrames;
    private int animationLoopStartFrame;
    private int holdFrame;
    private int frameCounter = 0;
    private boolean animationLoop;

    /**
     * Make AnimationSprite with given attributes
     * @param totalFrames
     * @param animationLoop
     * @param animationLoopStartFrame
     * @param holdFrame
     */
    public AnimationSpritePlayer(int totalFrames, boolean animationLoop, int animationLoopStartFrame, int holdFrame){
        if (validUserInput(totalFrames)) {
            this.totalFrames = totalFrames;
        }
        if (validUserInput(animationLoopStartFrame) && (animationLoopStartFrame < totalFrames)) {
            this.animationLoopStartFrame = animationLoopStartFrame;
        }
        this.animationLoop = animationLoop;
        this.holdFrame = holdFrame;
        
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
            if (holdFrame != 0) {
                if (frameCounter <= holdFrame) {
                    frameCounter++;
                } else {
                    this.currentFrame++;
                    frameCounter = 0; 
                }
            } else {
                this.currentFrame++;
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
