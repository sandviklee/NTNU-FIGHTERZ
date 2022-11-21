package fightinggame.game;
/**
 * The {@code ActionProperties} class represents a Struct or Structure for all the {@code Action} variables.
 * It holds getters for the variables.
 */
public class ActionProperties {
    private String spriteName;
    private String gameCharName;
    private Effectbox hitBox;
    private Vector knockback;
    private Projectile projectile;
    private boolean isProjectile;
    private boolean isSelfInterruptible;
    private boolean isEnemyInterruptible;
    private boolean isMovement;
    private boolean animationLoop;
    private int totalFrames;
    private int actionPriority;
    private int duration;
    private int animationLoopStartFrame;
    private int hitBoxStartTime;
    private int damage;
    /**
     * Create ActionProperties for Actions without hitbox or knockback, e.g. "idle", "stunlock"
     * @param spriteName               asserts the name of the Action
     * @param actionPriority           asserts the Action priority
     * @param duration                 asserts the total duration of the Action
     * @param isSelfInterruptible      asserts if the Action should be able to be interrupted by another Action done by the same owner.
     * @param isEnemyInterruptible     asserts if the Action should be able to be interrupted by another Action done by another owner.
     * @param totalFrames              asserts the total frames of the animation
     * @param animationLoop            asserts if the animation should loop
     * @param animationLoopStartFrame  asserts from which fram the animation should start to loop
     * @param isMovement               asserts whether the owner of this Action should move with the knockback or not
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
        this.knockback = new Vector(); //Sets the action knockback to nullvector.
        this.hitBoxStartTime = 0; //Sets the hitboxStartTime to 0, even if it doesnt have a hitbox.
        this.isProjectile = false; //Sets isProjectile to false, because the action doesn't spawn a Projectile.
    }
    /**
     * Create ActionProperties without hitbox but has knockback, for movement Actions e.g. "moveRight", "moveLeft", "jump"
     * @param spriteName               asserts the name of the Action
     * @param actionPriority           asserts the Action priority
     * @param duration                 asserts the total duration of the Action
     * @param isSelfInterruptible      asserts if the Action should be able to be interrupted by another Action done by the same owner.
     * @param isEnemyInterruptible     asserts if the Action should be able to be interrupted by another Action done by another owner.
     * @param totalFrames              asserts the total frames of the animation
     * @param animationLoop            asserts if the animation should loop
     * @param animationLoopStartFrame  asserts from which fram the animation should start to loop
     * @param isMovement               asserts whether the owner of this Action should move with the knockback or not
     * @param knockBack                asserts the knockback of the action
     */
    public ActionProperties(String spriteName, int actionPriority, int duration, boolean isSelfInterruptible, boolean isEnemyInterruptible, 
            int totalFrames, boolean animationLoop, int animationLoopStartFrame, boolean isMovement, Vector knockBack) {
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
        this.hitBoxStartTime = 0; //Sets the hitboxStartTime to 0, even if it doesnt have a hitbox.
        this.isProjectile = false; //Sets isProjectile to false, because the action doesn't spawn a Projectile.   
    }
    /**
     * Create ActionProperties with hitbox and knockback, for Actions e.g. "rightNormal"
     * @param spriteName               asserts the name of the Action
     * @param actionPriority           asserts the Action priority
     * @param duration                 asserts the total duration of the Action
     * @param isSelfInterruptible      asserts if the Action should be able to be interrupted by another Action done by the same owner.
     * @param isEnemyInterruptible     asserts if the Action should be able to be interrupted by another Action done by another owner.
     * @param totalFrames              asserts the total frames of the animation
     * @param animationLoop            asserts if the animation should loop
     * @param animationLoopStartFrame  asserts from which fram the animation should start to loop
     * @param isMovement               asserts whether the owner of this Action should move with the knockback or not
     * @param knockBack                asserts the knockback of the action
     * @param hitbox                   asserts the hitbox of the action
     * @param hitboxStartTime          asserts when the hitbox should appear
     * @param damage                   asserts the damage of the Action
     */
    public ActionProperties(String spriteName, int actionPriority, int duration, int hitBoxStartTime, boolean isSelfInterruptible,
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
        this.hitBoxStartTime = hitBoxStartTime;
        this.damage = damage;
        this.isProjectile = false; //This Action does not spawn a projectile.
    }
    /**
     * Create ActionProperties that produces a projectile.
     * @param spriteName               asserts the name of the Action
     * @param gameCharName             asserts the name of the character
     * @param actionPriority           asserts the Action priority
     * @param duration                 asserts the total duration of the Action
     * @param isSelfInterruptible      asserts if the Action should be able to be interrupted by another Action done by the same owner.
     * @param isEnemyInterruptible     asserts if the Action should be able to be interrupted by another Action done by another owner.
     * @param totalFrames              asserts the total frames of the animation
     * @param animationLoop            asserts if the animation should loop
     * @param animationLoopStartFrame  asserts from which fram the animation should start to loop
     * @param isMovement               asserts whether the owner of this Action should move with the knockback or not
     * @param knockBack                asserts the knockback of the action
     * @param hitbox                   asserts the hitbox of the action
     * @param hitboxStartTime          asserts when the hitbox should appear
     * @param damage                   asserts the damage of the Action
     * @param isProjectile             asserts if the Action should produce a projectile
     */
    public ActionProperties(String spriteName, String gameCharName, int actionPriority, int duration, int hitBoxStartTime, boolean isSelfInterruptible, 
            boolean isEnemyInterruptible, int totalFrames, boolean animationLoop, int animationLoopStartFrame, boolean isMovement, Vector knockBack, Effectbox hitbox, int damage, boolean isProjectile) {
        this.spriteName = spriteName;
        this.gameCharName = gameCharName;
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
        this.hitBoxStartTime = hitBoxStartTime;
        this.isProjectile = isProjectile;
    }
    /**
     * Create ActionProperties for a Projectile itself.
     * @param spriteName               asserts the name of the Action
     * @param duration                 asserts the total duration of the Action
     * @param totalFrames              asserts the total frames of the animation
     * @param animationLoop            asserts if the animation should loop
     * @param animationLoopStartFrame  asserts from which fram the animation should start to loop
     * @param knockBack                asserts the knockback of the action
     * @param hitbox                   asserts the hitbox of the action
     * @param damage                   asserts the damage of the Action
     */
    public ActionProperties(String spriteName, int duration, int totalFrames, boolean animationLoop, int animationLoopStartFrame, Vector knockBack, Effectbox hitbox, int damage) {
        this.spriteName = spriteName;
        this.duration = duration;
        this.totalFrames = totalFrames;
        this.animationLoop = animationLoop;
        this.animationLoopStartFrame = animationLoopStartFrame;
        this.knockback = knockBack;
        this.hitBox = hitbox;
        this.damage = damage;
        this.actionPriority = 0; //A Projectile does not need any actionPriority, because it only has one action.
        this.isSelfInterruptible = false; //A Projectile should not be interruptable
        this.isEnemyInterruptible = false; //A Projectile should not be interruptable
        this.hitBoxStartTime = 0; //The hitbox should appear when it gets spawned.
        this.isProjectile = false; //The projectile should not spawn a projectile itself
    }
    /**
     * Getter for hitBox
     * @return hitBox Effectbox
     */
    public Effectbox getHitBox() {
        return hitBox;
    }
    /**
     * Getter for knockback
     * @return knockback Vector
     */
    public Vector getKnockback() {
        return knockback;
    }
    /**
     * Getter for hitBoxStartTime
     * @return hitBoxStartTime Integer.
     */
    public int getHitBoxStartTime() {
        return hitBoxStartTime;
    }
    /**
     * Getter for damage
     * @return damage Integer.
     */
    public int getDamage() {
        return damage;
    }
    /**
     * Getter for spriteName
     * @return spriteName String
     */
    public String getSpriteName() {
        return spriteName;
    }
    /**
     * Getter for gameCharName
     * @return gameCharName String
     */
    public String getGameCharName() {
        return gameCharName;
    }
    /**
     * Getter for actionPriority
     * @return actionPriority Integer
     */
    public int getActionPriority() {
        return actionPriority;
    }
    /**
     * Getter for duration
     * @return duration Integer
     */
    public int getDuration() {
        return duration;
    }  
    /**
     * Getter for isSelfInterruptible
     * @return isSelfInterruptible boolean
     */
    public boolean isSelfInterruptible() {
        return isSelfInterruptible;
    }
    /**
     * Getter for isEnemyInterruptible
     * @return isEnemyInterruptible boolean
     */
    public boolean isEnemyInterruptible() {
        return isEnemyInterruptible;
    }
    /**
     * Getter for totalFrames
     * @return totalFrames Integer
     */
    public int getTotalFrames() {
        return totalFrames;
    }
    /**
     * Getter for animationLoop
     * @return animationLoop boolean
     */
    public boolean isAnimationLoop() {
        return animationLoop;
    }
    /**
     * Getter for animationLoopStartFrame
     * @return
     */
    public int getAnimationLoopStartFrame() {
        return animationLoopStartFrame;
    }
    /**
     * Getter for isProjectile
     * @return isProjectile boolean
     */
    public boolean getIsProjectile() {
        return isProjectile;
    }
    /**
     * Getter for IsMovement
     * @return isMovement boolean
     */
    public boolean getIsMovement() {
        return isMovement;
    }
    /**
     * Getter for Projectile
     * @return projectile Projectile
     */
    public Projectile getProjectile() {
        return projectile;
    }  
}
