package fightinggame.game;

import java.util.ArrayList;
import java.util.Arrays;

public class Action {
    private String name;
    private String gameCharName;
    private Effectbox hitBox;
    private Effectbox temporary;
    private AnimationSpritePlayer sprites;
    private Projectile projectile;
    private boolean isSelfInterruptible;
    private boolean isEnemyInterruptible;
    private int actionPriority;
    private Vector knockback;
    private int duration;
    private int currentTime;
    private int hitBoxStartTime;
    private int damage;
    private boolean isDone;
    private int holdAction = 0;
    private int holdFrameLength = 3;        
    private boolean movement;
    private boolean isProjectile;
    private boolean createProjectile = true;


    /**
     * Create an Action for GameCharacter or Projectile.
     * @param properties  of the action it is making
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
    }

    /**
     * Iterates the sprite when the tick has reached holdFrameLength,
     * updates currentTime.
     * Asserts isDone true if action is done.
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
     * When the otherSelfActionPriority is higher then this action stop action, else do not stop action
     * 
     * @param otherSelfActionPriority  the value to compare with own actionPriority
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
     * Try to interrupt action by another enemy action.
     * When the otherEnemyActionPriority is higher then this action stop action, else do not stop action
     * Needs to be specified when the other action is aquired (if it is an enemy or self)
     * @param otherEnemyActionPriority  the value to compare with own actionPriority
     */
    public boolean tryEnemyInterrupt(Action otherSelfAction) {
        if (isEnemyInterruptible) {
            if (actionPriority < otherSelfAction.getActionPriority()) {
                return true;
            } 
        }
        return false;
    }

    /**
     * Getter for name.
     * @return name String.
    */
    public String getName() {
        return name;
    }

    /**
     * Getter for currentFrame.
     * @return animationSprite.getCurrentFrame() frame integer.
    */
    public int getCurrentFrame() {
        return sprites.getCurrentFrame();
    }

    /**
     * Getter for hitBox.
     * @return hitbox Effectbox.
    */
    public Effectbox getHitBox() {
        return this.hitBox;
    }

    /**
     * Getter for knockback.
     * @return knockback Vector.
    */
    public Vector getKnockback() {
        return this.knockback;
    }

    /**
     * Getter for damage.
     * @return damage integer.
    */
    public int getDamage() {
        return this.damage;
    }

    /**
     * Getter for isDone.
     * @return isDone boolean.
    */
    public boolean getIsDone() {
        return isDone;
    }

    public boolean startHitBox() {
        if (currentTime >= hitBoxStartTime) {
            this.hitBox = temporary;
            if ((isProjectile && currentTime == (hitBoxStartTime + 1)) && createProjectile) { //hitboxStartTime + 1 because the projectile cant be made in the same tick, and therefore causes problems with rendering.
                int hitboxX = (int) hitBox.getPoint().getX();
                int hitboxY = (int) hitBox.getPoint().getY();
                ArrayList<Integer> pos = new ArrayList<>(Arrays.asList(hitboxX, hitboxY));
                this.projectile = new Projectile(gameCharName + name, pos, knockback, hitBox, damage);
                createProjectile = false;
            }

            return true;
        } else {
            return false;
        }
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public boolean isProjectile() {
        return isProjectile;
    }

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

    private int getActionPriority(){
        return this.actionPriority;
    }

    /**
     * Make sprite iterate to next frame
     */
    private void iterateSprite() {
        sprites.next();
    }
}
