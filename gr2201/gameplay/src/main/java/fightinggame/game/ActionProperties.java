package fightinggame.game;

public class ActionProperties {
    private String spriteName;
    private int actionPriority;
    private int duration;
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
        this.knockback = null;
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


    public ActionProperties(Effectbox hitbox, String spriteName, Vector knockback, int actionPriority, int duration, boolean isSelfInterruptible, 
    boolean isEnemyInterruptible, int totalFrames, boolean animationLoop, int animationLoopStartFrame,
    int hitBoxStartTime, int damage) {
        this.hitBox = hitbox;
        this.isSelfInterruptible = isSelfInterruptible;
        this.isEnemyInterruptible = isEnemyInterruptible;
        this.knockback = knockback;
        this.duration = duration;
        this.actionPriority = actionPriority;
        this.hitBoxStartTime = hitBoxStartTime;
        this.damage = damage;
        this.spriteName = spriteName;
        this.animationLoop = animationLoop;
        this.animationLoopStartFrame = animationLoopStartFrame;
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

    

    
}
