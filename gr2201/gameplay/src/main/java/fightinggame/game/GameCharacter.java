package fightinggame.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

public class GameCharacter extends WorldEntity{
    private int weight;
    private int speed;
    private int damage;
    private HashMap<Integer, ActionProperties> actionHash = new HashMap<>();
    private ArrayList<String> availKeys;
    private Predicate<String> availKey = i -> availKeys.contains(i);
    private ActionProperties property;
    private Effectbox hurtBox;
    private Vector gravityVector = new Vector(0, 0, 0, 0, 1);
    private int facingDirection = 1;

    public GameCharacter(String name, ArrayList<Integer> pos, ArrayList<String> availKeys, ArrayList<Integer> hitBoxProperties,
    ArrayList<ActionProperties> actionP, int weight, int speed) {
        super(name, pos);
        this.availKeys = availKeys;
        this.hurtBox = new Effectbox(this, getPoint(), false, hitBoxProperties);
        for (ActionProperties p : actionP) {
            actionHash.put(actionP.indexOf(p), p);
        }
        actionHash.put(0, new ActionProperties("Idle", 0, 18, false, false, 18, true, 0));
        actionHash.put(1, new ActionProperties("Run", 0, 10, false, false, 10, true, 0, new Vector(7, 0, 0, 0, 1)));
        actionHash.put(2, new ActionProperties("Run", 0, 10, false, false, 10, true, 0, new Vector(7, 0, 0, 0, -1)));
        setCurrentAction(0);
    }

    public boolean canMove(){
        return true;
    }

    @Override
    public ArrayList<String> getAvailKeys() {
        return availKeys;
    }

    public Predicate<String> getPredicate() {
        return availKey;
    }

    public Effectbox getHurtBox() {
        return hurtBox;
    }

    public void setCurrentAction(Integer actionNumber) {
        if (actionNumber != null) {
            property = actionHash.get(actionNumber);
            this.currentAction = new Action(property);
            if (actionNumber == 1 || actionNumber == 2) {
                facingDirection = currentAction.getKnockback().getDirection();
            }
        } 
    }

    public void doAction(){
		this.point.setX(point.getX() + this.getCurrentAction().getKnockback().getVx());
        this.getCurrentAction().getKnockback().addVector(gravityVector);
		this.point.setY(point.getY() + this.getCurrentAction().getKnockback().getVy());
        hurtBox.updatePos();
		this.getCurrentAction().nextActionFrame();
	}

    public int getFacingDirection() {
        return facingDirection;
    }
}
