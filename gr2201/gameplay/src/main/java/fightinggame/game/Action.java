package fightinggame.game;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * The {@code Action} class represents an action for a WorldEntity.
 */
public class Action {
    private String name;
    private String gameCharName;
    private Effectbox hitBox;
    private Effectbox temporary;
    private AnimationSpritePlayer sprites;
    private Projectile projectile;
    private Vector knockback;
    private int actionPriority;
    private int duration;
    private int currentTime;
    private int hitBoxStartTime;
    private int damage;
    private int holdAction;
    private int holdFrameLength = 3; //3 because the animation will then run at the best framerate.     
    private boolean isSelfInterruptible;
    private boolean isEnemyInterruptible;
    private boolean isDone;   
    private boolean movement;
    private boolean isProjectile;
    private boolean createProjectile = true; //Check for if the action has created a projectile yet. Will be set to false when the action has created a projectile.
    /**
     * Creates an Action for a GameCharacter or Projectile.
     * @param properties  declares the properties of the action it is making
     */
    public Action(ActionProperties properties) {
        this.sprites = new AnimationSpritePlayer(properties.getTotalFrames(), properties.isAnimationLoop(), properties.getAnimationLoopStartFrame() , 0);
        this.isSelfInterruptible = properties.isSelfInterruptible();
        this.isEnemyInterruptible = properties.isEnemyInterruptible();
        this.damage = properties.getDamage();
        this.duration = properties.getDuration();
        this.actionPriority = properties.getActionPriority();
        this.name = properties.getSpriteName();
        this.gameCharName = properties.getGameCharName();
        this.knockback = properties.getKnockback();
        this.temporary = properties.getHitBox();
        this.hitBoxStartTime = properties.getHitBoxStartTime();
        this.currentTime = 0;
        this.movement = properties.getIsMovement();
        this.isDone = false;
        this.isProjectile = properties.getIsProjectile();
        this.projectile = properties.getProjectile();
        this.holdAction = 0;
    }
    /**
     * Increments the sprite when the tick has reached holdFrameLength,
     * and then updates the current frame.
     * Sets isDone to true if action is finished. This is when the duration
     * of the action has been met.
     */
    public void nextActionFrame(){
        if (!isDone()) {
            if (holdAction < holdFrameLength) {
                holdAction += 1;
            } else {
                holdAction = 0;
                currentTime += 1;
                iterateSprite();
            }
        } else {
            this.isDone = true;
        }
    }
    /**
     * Try to interrupt action by another self action.
     * When the otherSelfActionPriority is higher than this action it should be able to be interrupted.
     * @param otherSelfActionPriority  the value to compare with own actionPriority
     * @return true if isSelfInterruptible is true and actionPriority is less than otherSelfAction's priority.
     */
    public boolean trySelfInterrupt(Action otherSelfAction) {
        if (isSelfInterruptible) {
            if (actionPriority < otherSelfAction.getActionPriority()) {
                return true;
            } 
        }
        return false;
    }
    /**
     * Try to interrupt current action by an enemy action.
     * When the otherEnemyActionPriority is higher than this action it should be able to be interrupted.
     * @param otherEnemyActionPriority  the value to compare with own actionPriority
     * @return true if isEnemyInterrupt is true and actionPriority is less than otherEnemyAction's priority.
     */
    public boolean tryEnemyInterrupt(Action otherEnemyAction) {
        if (isEnemyInterruptible) {
            if (actionPriority < otherEnemyAction.getActionPriority()) {
                return true;
            } 
        }
        return false;
    }
    /**
     * Getter for name.
     * @return name string
    */
    public String getName() {
        return name;
    }
    /**
     * Getter for currentFrame.
     * @return animationSprite.getCurrentFrame() frame integer
    */
    public int getCurrentFrame() {
        return sprites.getCurrentFrame();
    }
    /**
     * Getter for hitBox.
     * @return hitbox Effectbox
    */
    public Effectbox getHitBox() {
        return this.hitBox;
    }
    /**
     * Getter for knockback.
     * @return knockback Vector
    */
    public Vector getKnockback() {
        return this.knockback;
    }
    /**
     * Getter for damage.
     * @return damage integer
    */
    public int getDamage() {
        return this.damage;
    }
    /**
     * Getter for isDone.
     * @return isDone boolean
    */
    public boolean getIsDone() {
        return isDone;
    }
    /**
     * Starts the hitbox when currentTime is larger or equal to hitBoxStartTime.
     * Useful for actions that dont spawn a hitbox at the start of the action but rather at the end or in the middle of the duration.
     * This can also tell when a projectile is to be spawned.
     * @return true if currentTime of action is equal or larger than hitBoxStartTime
     */
    public boolean startHitBox() {
        if (currentTime >= hitBoxStartTime) {
            this.hitBox = temporary;
            spawnProjectile();
            return true;
        } else {
            return false;
        }
    }
    /**
     * Getter for Projectile
     * @return projectile Projectile
     */
    public Projectile getProjectile() {
        return projectile;
    }
    /**
     * Getter for isProjectile
     * @return isprojectile boolean
     */
    public boolean isProjectile() {
        return isProjectile;
    }
    /**
     * Getter for isMovement
     * @return isMovement boolean
     */
    public boolean isMovement() {
        return movement;
    }
    /**
     * Check if Action is done
     * @return true if currentTime equal or larger then duration
     */
    private boolean isDone() {
        if (currentTime >= duration) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Getter for ActionPriority
     * @return actionPriority integer
     */
    private int getActionPriority(){
        return this.actionPriority;
    }
    /**
     * Iterates the current sprite from the {@code AnimationSpritePlayer} defined in
     * the action.
     */
    private void iterateSprite() {
        sprites.next();
    }

    public int getCurrentTime() {
        return currentTime;
    }
    /**
     * Spawns a projectile when the conditions have been met.
     */
    private void spawnProjectile() {
        if ((isProjectile && currentTime == (hitBoxStartTime + 1)) && createProjectile) { 
            /*
             * HitboxStartTime + 1 because the projectile cant be made in the same tick, and therefore causes problems with rendering.
             */
            int hitboxX = (int) hitBox.getPoint().getX();
            int hitboxY = (int) hitBox.getPoint().getY();
            ArrayList<Integer> pos = new ArrayList<>(Arrays.asList(hitboxX, hitboxY));
            this.projectile = new Projectile(gameCharName + name, pos, knockback, hitBox, damage);
            createProjectile = false;
        }
    }
}
