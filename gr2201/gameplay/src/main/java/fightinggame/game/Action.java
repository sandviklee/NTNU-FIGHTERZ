package fightinggame.game;

import java.util.ArrayList;

public class Action {

    private Effectbox hitBox;
    private AnimationSprite sprites;
    private boolean isSelfInterruptible;
    private boolean isEnemyInterruptible;
    private int actionPriority;
    private Vector knockback;
    private ArrayList<String> effects;
    private int duration;
    private int currentTime;
    private int hitBoxStartTime;
    
    private int damage;

    /**
     * Create an Action with hitbox e.g. attack
     * @param hitbox                of the Action
     * @param spriteName            of the sprite to the action
     * @param isSelfInterruptible   to determine if it can be interrupted by own action
     * @param isEnemyInterruptible  to determine if it can be interrupted by enemy action
     * @param knockback             given by action on hurtboxes
     * @param duration              of the action
     * @param actionPriority        of the action
     * @param hitBoxStartTime       when the hitBox should be created
     * @param damage                it will give to hurtboxes on collision
     */
    public Action(Effectbox hitbox, String spriteName, boolean isSelfInterruptible, 
    boolean isEnemyInterruptible, Vector knockback, int duration, int actionPriority, 
    int hitBoxStartTime, int damage, int totalFrames, boolean animationLoop, boolean animationLoopStartTime) {
        //TODO:
    }

    /**
     * Create an Action without a Effectbox, e.g. idle, move.
     * @param spriteName            of the sprite to the action
     * @param actionPriority        of the action
     * @param duration              of the action
     * @param isSelfInterruptible   to determine if it can be interrupted by own action
     * @param isEnemyInterruptible  to determine if it can be interrupted by enemy action
     */
    public Action(String spriteName, int actionPriority, int duration, boolean isSelfInterruptible, boolean isEnemyInterruptible,
    int totalFrames, boolean animationLoop, boolean animationLoopStartTime) {
        //TODO:
    }

    /**
     * Shall mainpulate hitbox, 
     * Make Sprites iterate, 
     * update currentTime and stop action either when interrupte or when action is done
     * 
     */
    public void handleAction(){
        // TODO:

    }
    /**
     * Try to interrupt action by another action.
     * When the otherActionPriority is higher then this action stop action, else do not stop action
     * @param otherActionPriority  the value to compare with own actionPriority
     */
    public void tryInterrupt(int otherActionPriority) {
        // TODO:

    }
    /**
     * Check if Action is done
     * @return true if currentTime equal or larger then duration
     */
    private boolean isDone() {
        // TODO:
    }

    /**
     * Make sprite iterate to next frame
     */
    private void iterateSprite() {
        // TODO:
    }

}
