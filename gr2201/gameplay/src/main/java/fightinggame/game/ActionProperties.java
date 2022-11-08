package fightinggame.game;

public class ActionProperties {
    private String spriteName;
    private int actionPriority;
    private int duration;
    private boolean isProjectile;
    private boolean isSelfInterruptible;
    private boolean isEnemyInterruptible;
    private int totalFrames;
    private Effectbox hitBox;
    private Vector knockback = new Vector(0, 0, 0, 0, 1);
    private boolean animationLoop;
    private int animationLoopStartFrame;
    private int hitBoxStartTime;
    private int damage;
    private boolean isMovement;

    /**
     * Create ActionProperties without hitbox or knockback, e.g. "idle", "stunlock"
     * @param spriteName               of the sprite to this ActionProperties
     * @param actionPriority           of the ActionProperties
     * @param duration                 of the total time
     * @param isSelfInterruptible      or cant Action owner stop this action
     * @param isEnemyInterruptible     or cant Action other WorldEnteties stop this action
     * @param totalFrames              of the animation
     * @param animationLoop            shall it loop
     * @param animationLoopStartFrame  if it loops what frame shall it start at
     * @param isMovement               shall this move owner of Action
     */
    public ActionProperties(String spriteName, int actionPriority, int duration, boolean isSelfInterruptible,
            boolean isEnemyInterruptible, int totalFrames, boolean animationLoop, int animationLoopStartFrame, boolean isMovement) {
        this.spriteName = spriteName;
        this.actionPriority = actionPriority;
        this.duration = duration;
        this.isSelfInterruptible = isSelfInterruptible;
        this.isEnemyInterruptible = isEnemyInterruptible;
        this.totalFrames = totalFrames;
        this.animationLoop = animationLoop;
        this.animationLoopStartFrame = animationLoopStartFrame;
        this.isMovement = isMovement;
    }

    /**
     * Create ActionProperties without hitbox but has knockback, for movement actions e.g. "moveRight", "moveLeft", "jump"
     * @param spriteName               of the sprite to this ActionProperties
     * @param actionPriority           of the ActionProperties
     * @param duration                 of the total time
     * @param isSelfInterruptible      or cant Action owner stop this action
     * @param isEnemyInterruptible     or cant Action other WorldEnteties stop this action
     * @param totalFrames              of the animation
     * @param animationLoop            shall it loop
     * @param animationLoopStartFrame  if it loops what frame shall it start at
     * @param isMovement               shall move the owner of the Action
     * @param knockBack                of character that does action
     */
    public ActionProperties(String spriteName, int actionPriority, int duration, boolean isSelfInterruptible,
            boolean isEnemyInterruptible, int totalFrames, boolean animationLoop, int animationLoopStartFrame, boolean isMovement, Vector knockBack) {
        this.spriteName = spriteName;
        this.actionPriority = actionPriority;
        this.duration = duration;
        this.isSelfInterruptible = isSelfInterruptible;
        this.isEnemyInterruptible = isEnemyInterruptible;
        this.totalFrames = totalFrames;
        this.animationLoop = animationLoop;
        this.animationLoopStartFrame = animationLoopStartFrame;
        this.isMovement = isMovement;
        this.knockback = knockBack;
    }

    /**
     * Create ActionProperties with hitbox and knockback, for Attack actions e.g. "rightNormal"
     * @param spriteName               of the sprite to this ActionProperties
     * @param actionPriority           of the ActionProperties
     * @param duration                 of the total time
     * @param isSelfInterruptible      or cant Action owner stop this action
     * @param isEnemyInterruptible     or cant Action other WorldEnteties stop this action
     * @param totalFrames              of the animation
     * @param animationLoop            shall it loop
     * @param animationLoopStartFrame  if it loops what frame shall it start at
     * @param isMovement               shall this move owner of Action
     * @param knockBack                of character that does Action
     * @param hitbox                   of Action, where can this Action effect enteties
     * @param damage                   to apply to WorldEntities in hitbox
     */
    public ActionProperties(String spriteName, int actionPriority, int duration, boolean isSelfInterruptible,
            boolean isEnemyInterruptible, int totalFrames, boolean animationLoop, int animationLoopStartFrame, boolean isMovement, Vector knockBack, Effectbox hitbox, int damage) {
        this.spriteName = spriteName;
        this.actionPriority = actionPriority;
        this.duration = duration;
        this.isSelfInterruptible = isSelfInterruptible;
        this.isEnemyInterruptible = isEnemyInterruptible;
        this.totalFrames = totalFrames;
        this.animationLoop = animationLoop;
        this.animationLoopStartFrame = animationLoopStartFrame;
        this.isMovement = isMovement;
        this.knockback = knockBack;
        this.hitBox = hitbox;
        this.damage = damage;
    }

    /**
     * Create ActionProperties that shall produce an Projectile.
     * @param spriteName               of the sprite to this ActionProperties
     * @param actionPriority           of the ActionProperties
     * @param duration                 of the total time
     * @param isSelfInterruptible      or cant Action owner stop this action
     * @param isEnemyInterruptible     or cant Action other WorldEnteties stop this action
     * @param totalFrames              of the animation
     * @param animationLoop            shall it loop
     * @param animationLoopStartFrame  if it loops what frame shall it start at
     * @param isMovement               shall this move owner of Action
     * @param knockBack                of character that does Action
     * @param hitbox                   of Action, where can this Action effect enteties
     * @param damage                   to apply to WorldEntities in hitbox
     * @param isProjectile             shall it make an Projectile instance
     */
    public ActionProperties(String spriteName, int actionPriority, int duration, boolean isSelfInterruptible,
            boolean isEnemyInterruptible, int totalFrames, boolean animationLoop, int animationLoopStartFrame, boolean isMovement, Vector knockBack, Effectbox hitbox, int damage, boolean isProjectile) {
        this.spriteName = spriteName;
        this.actionPriority = actionPriority;
        this.duration = duration;
        this.isSelfInterruptible = isSelfInterruptible;
        this.isEnemyInterruptible = isEnemyInterruptible;
        this.totalFrames = totalFrames;
        this.animationLoop = animationLoop;
        this.animationLoopStartFrame = animationLoopStartFrame;
        this.isMovement = isMovement;
        this.knockback = knockBack;
        this.hitBox = hitbox;
        this.damage = damage;
        this.isProjectile = isProjectile;
    }

    public Effectbox getHitBox() {
        return hitBox;
    }

    public Vector getKnockback() {
        return knockback;
    }

    public int getHitBoxStartTime() {
        return hitBoxStartTime;
    }

    public int getDamage() {
        return damage;
    }

    public String getSpriteName() {
        return spriteName;
    }

    public int getActionPriority() {
        return actionPriority;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isSelfInterruptible() {
        return isSelfInterruptible;
    }

    public boolean isEnemyInterruptible() {
        return isEnemyInterruptible;
    }

    public int getTotalFrames() {
        return totalFrames;
    }

    public boolean isAnimationLoop() {
        return animationLoop;
    }

    public int getAnimationLoopStartFrame() {
        return animationLoopStartFrame;
    }

    public boolean getIsProjectile() {
        return isProjectile;
    }

    public boolean getIsMovement() {
        return isMovement;
    }    
}
