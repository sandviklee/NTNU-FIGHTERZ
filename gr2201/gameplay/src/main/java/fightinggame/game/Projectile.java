package fightinggame.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Projectile extends WorldEntity{
    private HashMap<Integer, ActionProperties> actionHash = new HashMap<>();
    private ActionProperties property;
    private Effectbox hitBox;
    private Vector mainVector;

    public Projectile(String name, ArrayList<Integer> pos, Vector knockback, Effectbox hitbox, int damage) {
        super(name, pos);
        int hitBoxWidth = (int) hitbox.getWidth();
        int hitBoxHeight = (int) hitbox.getHeight();
        ArrayList<Integer> hitBoxProps = new ArrayList<>(Arrays.asList(hitBoxWidth, hitBoxHeight));
        this.hitBox = new Effectbox(this, point, false, hitBoxProps);
        this.mainVector = new Vector(knockback.getVx(), knockback.getVy(), 0, 0);
        actionHash.put(0, new ActionProperties("Projectile", 20, 2, true, 0, mainVector, hitBox, damage));
        
    }

    public void setCurrentAction(Integer actionNumber) {
        if (actionNumber != null) {
            
            property = actionHash.get(actionNumber);
            this.currentAction = new Action(property);     
            
        } 
    }

    public void doAction() {
        if (currentAction != null && !currentAction.getIsDone()) {
            point.setX(point.getX() + currentAction.getKnockback().getVx());
            point.setY(point.getY() + currentAction.getKnockback().getVy());
            
            hitBox.updatePos();
            currentAction.nextActionFrame();
        }

    }

    public Effectbox getHitBox() {
        return hitBox;
    }
    
}
