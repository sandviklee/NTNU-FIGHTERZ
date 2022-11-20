package fightinggame.game;
/**
 * The {@code AnimationSpritePlayer} class represents an AnimationPlayer for sprites. 
 */
public class AnimationSpritePlayer {
    private int frameCounter = 0;
    private int currentFrame = 0;
    private int totalFrames;
    private int animationLoopStartFrame;
    private int holdFrame;
    private boolean animationLoop;

    /**
     * Creates AnimationSpritePlayer with the given variables.
     * @param totalFrames
     * @param animationLoop
     * @param animationLoopStartFrame
     * @param holdFrame
     */
    public AnimationSpritePlayer(int totalFrames, boolean animationLoop, int animationLoopStartFrame, int holdFrame){
        if (validUserInput(totalFrames)) {
            this.totalFrames = totalFrames;
        }
        else {
            throw new IllegalArgumentException("Can not have negativ total frames");
        }
        if (validUserInput(animationLoopStartFrame) && (animationLoopStartFrame < totalFrames)) {
            this.animationLoopStartFrame = animationLoopStartFrame;
        }
        else {
            throw new IllegalArgumentException("Can not have negativ total frames");
        }
        this.animationLoop = animationLoop;
        this.holdFrame = holdFrame;
        
    }

    /**
     * Getter for current Frame in animation.
     * @return currentFrame integer
     */
    public int getCurrentFrame() {
        return currentFrame;
    }

    /**
     * Checks if the userinput is valid.
     * @param userInput integer
     * @return true if the userInput is positive, and false otherwise
     */
    private boolean validUserInput(int userInput) {
        if (userInput < 0) {
            throw new IllegalArgumentException("UserInput: "+ userInput + " is less than zero");
        } else {
            return true;
        }
    }

    /**
     * Increments the current frame.
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
     * @param frame index integer
     */
    private void jump(int frame){
        if (frame >= 0 && frame <= totalFrames) {
            this.currentFrame = frame;
        }
    }

    /**
     * Check if AnimationSprites has a next frame.
     * @return true if it has a next frame, otherwise false
     */
    private boolean hasNext(){
        if (currentFrame >= totalFrames) {
            return false;
        } else {
            return true;
        }
    }        
}
