package fightinggame.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

public class GameCharacter extends WorldEntity{
    private int weight;
    private int health;
    private HashMap<Integer, ActionProperties> actionHash = new HashMap<>();
    private ArrayList<String> availKeys;
    private Predicate<String> availKey = i -> availKeys.contains(i);
    private ActionProperties property;

    public GameCharacter(String name, ArrayList<Integer> pos, ArrayList<String> availKeys) {
        super(name, pos);
        this.availKeys = availKeys;
        actionHash.put(0, new ActionProperties("Idle", 0, 18, false, false, 18, true, 0));
        actionHash.put(1, new ActionProperties("Run", 0, 10, false, false, 10, true, 0, new Vector(12, 0, 0, 0, 1)));
        
        setCurrentAction(0);
    }

    public boolean canMove(){
        return true;
    }

    @Override
    public Predicate<String> getPredicate() {
        return availKey;
    }

    @Override
    public void setCurrentAction(Integer actionNumber) {
        if (actionNumber != null) {
            property = actionHash.get(actionNumber);
            if (property.getKnockback() != null) {
                this.currentAction = new Action(property.getSpriteName(), property.getActionPriority(), property.getDuration(), property.isSelfInterruptible(), 
                property.isEnemyInterruptible(), property.getTotalFrames(), property.isAnimationLoop(), property.getAnimationLoopStartFrame(), property.getKnockback());
            } else {
                this.currentAction = new Action(property.getSpriteName(), property.getActionPriority(), property.getDuration(), property.isSelfInterruptible(), 
                property.isEnemyInterruptible(), property.getTotalFrames(), property.isAnimationLoop(), property.getAnimationLoopStartFrame());
            }
        } 
    }

    public void heal(int healing){
        this.health -= healing;
    }

    public void hurt(int damage){
        this.health += damage;
    }

}
