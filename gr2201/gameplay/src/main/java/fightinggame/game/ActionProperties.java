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
    private Vector knockback;
    private boolean animationLoop;
    private int animationLoopStartFrame;
    private int hitBoxStartTime;
    private int damage;

    public ActionProperties(String spriteName, int actionPriority, int duration, boolean isSelfInterruptible,
            boolean isEnemyInterruptible, int totalFrames, boolean animationLoop, int animationLoopStartFrame) {
        this.spriteName = spriteName;
        this.actionPriority = actionPriority;
        this.duration = duration;
        this.isSelfInterruptible = isSelfInterruptible;
        this.isEnemyInterruptible = isEnemyInterruptible;
        this.totalFrames = totalFrames;
        this.animationLoop = animationLoop;
        this.animationLoopStartFrame = animationLoopStartFrame;
        this.knockback = new Vector(0, 0, 0, 0, 1);
    }

    public ActionProperties(String spriteName, int actionPriority, int duration, boolean isSelfInterruptible,
    boolean isEnemyInterruptible, int totalFrames, boolean animationLoop, int animationLoopStartFrame, Vector knockBack) {
        this.spriteName = spriteName;
        this.actionPriority = actionPriority;
        this.duration = duration;
        this.isSelfInterruptible = isSelfInterruptible;
        this.isEnemyInterruptible = isEnemyInterruptible;
        this.totalFrames = totalFrames;
        this.animationLoop = animationLoop;
        this.animationLoopStartFrame = animationLoopStartFrame;
        this.knockback = knockBack;
    }

    //Add isprojectile constructor


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

    

    
}
