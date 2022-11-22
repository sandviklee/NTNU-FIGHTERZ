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
     * @param totalFrames             declares how many frames the sprite has to iterate through
     * @param animationLoop           declares if the animation should be looped
     * @param animationLoopStartFrame declares where the animation should start to loop from
     * @param holdFrame               declares how many ticks the animationspriteplayer should hold a frame
     * @throws IllegalArgumentException if the total frames or animationLoopStartFrame is negative
     */
    public AnimationSpritePlayer(int totalFrames, boolean animationLoop, int animationLoopStartFrame, int holdFrame){
        if (validInput(totalFrames)) {
            this.totalFrames = totalFrames;
        }
        else {
            throw new IllegalArgumentException("Total frames can't be negative!");
        }
        if (validInput(animationLoopStartFrame) && (animationLoopStartFrame < totalFrames)) {
            this.animationLoopStartFrame = animationLoopStartFrame;
        }
        else {
            throw new IllegalArgumentException("The animation loop can't start on a negative index!");
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
     * @param input is an integer
     * @throws IllegalArgumentException if the input is invalid
     * @return true if the input is positive, and false otherwise
     */
    private boolean validInput(int input) {
        if (input < 0) {
            throw new IllegalArgumentException("UserInput: "+ input + " is less than zero");
        } else {
            return true;
        }
    }
    /**
     * Increments the current frame.
     * When currentFrame exceeds totalFrames and animationLoop is true, jump to animationLoopStartFrame.
     * If animationLoop is false, the Action is done.
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
