package fightinggame.game;

import java.util.ArrayList;
import java.util.HashMap;

public class Projectile extends WorldEntity{
    private HashMap<Integer, ActionProperties> actionHash = new HashMap<>();
    private ActionProperties property;
    private Effectbox hitBox;
    private Vector mainVector;
    private int facingDirection;
    
    public Projectile(String name, ArrayList<Integer> pos, Vector knockback, Effectbox hitbox, int damage) {
        super(name, pos);
        int hitBoxWidth = (int) hitbox.getWidth();
        int hitBoxHeight = (int) hitbox.getHeight();
        
        this.hitBox = new Effectbox(this, super.point, false, hitBoxWidth, hitBoxHeight);
        this.mainVector = new Vector(knockback);
        actionHash.put(0, new ActionProperties("Projectile", 30, 2, true, 0, mainVector, hitBox, damage));
        
    }

    public void setCurrentAction(Integer actionNumber) {
        if (actionNumber != null) {
            property = actionHash.get(actionNumber);
            this.currentAction = new Action(property);     
            
        } 
    }

    public void doAction() {
        if (currentAction.getKnockback().getVx() > 0) {
            facingDirection = 1;
        } else {
            facingDirection = -1;
        }
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

    public int getFacingDirection() {
        return facingDirection;
    }


    
}
