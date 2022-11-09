package fightinggame.game;

public class Action {
    private String name;
    private Effectbox hitBox;
    private AnimationSprite sprites;
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
    private int holdFrameLength = 4;


    /**
     * Create an Action without a Effectbox, e.g. idle, move.
     * @param spriteName            of the sprite to the action
     * @param actionPriority        of the action
     * @param duration              of the action
     * @param isSelfInterruptible   to determine if it can be interrupted by own action
     * @param isEnemyInterruptible  to determine if it can be interrupted by enemy action
     */
    public Action(ActionProperties p) {
        this.sprites = new AnimationSprite(p.getTotalFrames(), p.isAnimationLoop(), p.getAnimationLoopStartFrame(), holdFrameLength);
        this.isSelfInterruptible = p.isSelfInterruptible();
        this.isEnemyInterruptible = p.isEnemyInterruptible();
        this.duration = p.getDuration();
        this.actionPriority = p.getActionPriority();
        this.name = p.getSpriteName();
        this.knockback = p.getKnockback();
        this.hitBox = p.getHitBox();
        this.hitBoxStartTime = p.getHitBoxStartTime();
        this.currentTime = 0;
        this.isDone = false;
    }

    /**
     * Shall manipulate hitbox, 
     * Make Sprites iterate, 
     * update currentTime and stop action either when interrupte or when action is done
     * changes isDone to true if action is interrupted or done.
     */
    public void nextActionFrame(){
        if (!isDone()) {
            if (holdAction < holdFrameLength) {
                holdAction += 1;
            } else {
                holdAction = 0;
                currentTime += 1;
            }
            iterateSprite();
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
    public void trySelfInterrupt(int otherSelfActionPriority) {
        if (isSelfInterruptible) {
            if (actionPriority <= otherSelfActionPriority) {
                currentTime = duration;
            } 
        }
    }

    /**
     * Try to interrupt action by another enemy action.
     * When the otherEnemyActionPriority is higher then this action stop action, else do not stop action
     * Needs to be specified when the other action is aquired (if it is an enemy or self)
     * @param otherEnemyActionPriority  the value to compare with own actionPriority
     */
    public void tryEnemyInterrupt(int otherEnemyActionPriority) {
        if (isEnemyInterruptible) {
            if (actionPriority <= otherEnemyActionPriority) {
                currentTime = duration;
            } 
        }
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

    
    public int getActionPriority(){
        return this.actionPriority;
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
            return true;
        } else {
            return false;
        }
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
     * Make sprite iterate to next frame
     */
    private void iterateSprite() {
        sprites.next();
    }

}
