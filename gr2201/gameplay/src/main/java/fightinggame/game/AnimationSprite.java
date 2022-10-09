package fightinggame.game;
/**
 * AnimationSprite shall represent a sprite and its change for 
 */
public class AnimationSprite {
    private String spriteName;
    private int currentFrame;
    private int totalFrames;
    private int animationLoopStartTime;
    private boolean animationLoop;

    /**
     * Make AnimationSprite with given attributes
     * @param spriteName
     * @param totalFrames
     * @param animationLoop
     * @param animationLoopStartTime
     */
    public AnimationSprite(String spriteName, int totalFrames, boolean animationLoop, int animationLoopStartTime){
        // TODO:
    }

    /**
     * Next increments currentFrame.
     * When currentFrame exceeds totalFrames either jump to animationLoopStartTime if animationLoop is true else stop
     */
    public void next(){
        //TODO:
    }
    /**
     * Jump to frame if it exists.
     * @param frame index
     */
    private void jump(int frame){
        //TODO:
    }

    /**
     * Check if AnimationSprites has a next frame.
     * @return true if it has a next frame else false.
     */
    private boolean hasNext(){
        //TODO:
        return true;
    }

}
