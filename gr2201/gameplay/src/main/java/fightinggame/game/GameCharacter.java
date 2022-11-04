package fightinggame.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

public class GameCharacter extends WorldEntity{
    private int weight;
    private int speed;
    private int damage;
    private int startpositionY;
    private HashMap<Integer, ActionProperties> actionHash = new HashMap<>();
    private ArrayList<String> availKeys;
    private Predicate<String> availKey = i -> availKeys.contains(i);
    private ActionProperties property;
    private Effectbox hurtBox;
    private Vector mainVector = new Vector();
    private Vector gravityVector = new Vector(0, 10, 0, 0, 1);
    private int facingDirection = 1;
    private boolean onGround = true;
    private int jumpCounter = 0;

    public GameCharacter(String name, ArrayList<Integer> pos, ArrayList<String> availKeys, ArrayList<Integer> hitBoxProperties,
    ArrayList<ActionProperties> actionP, int weight, int speed) {
        super(name, pos);
        this.availKeys = availKeys;
        this.hurtBox = new Effectbox(this, getPoint(), false, hitBoxProperties);
        this.startpositionY = pos.get(1) + hitBoxProperties.get(1)/2;
        for (ActionProperties p : actionP) {
            actionHash.put(actionP.indexOf(p), p);
        }
        actionHash.put(0, new ActionProperties("Idle", 0, 18, false, false, 18, true, 0));
        actionHash.put(1, new ActionProperties("Jump", 2, 2, false, false, 5, true, 0, new Vector(0, 48, 0 , -4, -1))); 
        actionHash.put(2, new ActionProperties("Run", 1, 10, false, false, 10, true, 0, new Vector(8, 0, 0, 0, 1)));
        actionHash.put(3, new ActionProperties("Run", 1, 10, false, false, 10, true, 0, new Vector(8, 0, 0, 0, -1)));
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
            clearVectors();
            if (actionNumber == 1) {
                jumpCounter++;
            }
            property = actionHash.get(actionNumber);
            this.currentAction = new Action(property);
            if (actionNumber == 2 || actionNumber == 3) {
                facingDirection = currentAction.getKnockback().getDirection();
            }
            applyVectors();
        } 
    }

    public Action getAction(int actionNumber) {
        property = actionHash.get(actionNumber);
        return new Action(property);
    }

    public void applyVectors() {
        Vector knockback = this.currentAction.getKnockback();
        mainVector.addVector(knockback);

    }

    public void clearGravityVector() {
        mainVector.removeVector(gravityVector);
    }

    public void setOnGround(boolean onGround) {
		this.onGround = onGround;
        if (onGround && mainVector.getVy() > 0) {
            jumpCounter = 0;
            clearGravityVector();
        }
        
        //mainVector = new Vector(mainVector.getVx(), 0, 0, 0, facingDirection);
	}

    private void clearVectors() {
        mainVector = new Vector();
    }

    public void doAction(){
        if (!onGround && mainVector.getVy() == 0) { //NB DETTE ER BARE FOR TESTING AV NÅR DET SKAL LEGGES TIL GRAVITASJONSVEKTOREN
            mainVector.addVector(gravityVector);
        }
		point.setX(point.getX() + mainVector.getVx());
		point.setY(point.getY() + mainVector.getVy());
        hurtBox.updatePos();
        mainVector.applyAcceleration();
		currentAction.nextActionFrame();
	}

    public int getActionPriority() {
        return currentAction.getActionPriority();
    }

    public int getFacingDirection() {
        return facingDirection;
    }

    public boolean getOnGround() {
        return onGround;
    }

    public int getJumpCounter() {
        return jumpCounter;
    }
}
