package fightinggame.game;

import java.util.ArrayList;
import java.util.Arrays;
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
    private Vector gravityVector = new Vector(0, 12, 0, 0, 1);
    private int facingDirection = 1;
    private int appliedVector;
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
        actionHash.put(0, new ActionProperties("Idle", 0, 18, false, false, 18, true, 0, true));
        actionHash.put(1, new ActionProperties("Jump", 3, 2, false, false, 5, true, 0, true, new Vector(0, 48, 0 , -4, -1))); 
        actionHash.put(2, new ActionProperties("Run", 1, 10, false, false, 10, true, 0, true, new Vector(8, 0, 0, 0, 1)));
        actionHash.put(3, new ActionProperties("Run", 1, 10, false, false, 10, true, 0, true, new Vector(8, 0, 0, 0, -1)));
        //RUNJUMP
        actionHash.put(4, new ActionProperties("Jump", 3, 2, false, false, 10, true, 0, true, new Vector(8, -48, 0, 4, 1)));
        actionHash.put(5, new ActionProperties("Jump", 3, 2, false, false, 10, true, 0, true, new Vector(8, 48, 0, -4, -1)));
        //actionHash.put(6, new ActionProperties("SideSpecial", 2, 100000, false, false, 19, true, 13, true, new Vector(13, 0, 0, 0, 1)));

        actionHash.put(6, new ActionProperties("SideSpecial", 2, 100000, 7, false, false, 19, true, 13, true, new Vector(18, 0, 0, 0, 1), new Effectbox(this, new Point(0, -12), false, new ArrayList<>(Arrays.asList(240, 180))), 10));
        actionHash.put(7, new ActionProperties("SideSpecial", 2, 100000, 7, false, false, 19, true, 13, true, new Vector(18, 0, 0, 0, -1), new Effectbox(this, new Point(0, -12), false, new ArrayList<>(Arrays.asList(240, 180))), 10));
        actionHash.put(8, new ActionProperties("AttackSlash", 2, 8, 3, false, false, 10, false, 10, false, new Vector(12, 0, -2, 0, 1), new Effectbox(this, new Point(65, 0), false, new ArrayList<>(Arrays.asList(60, 80))), 10));
        actionHash.put(9, new ActionProperties("AttackSlash", 2, 8, 3, false, false, 10, false, 10, false, new Vector(12, 0, -2, 0, -1), new Effectbox(this, new Point(-65, 0), false, new ArrayList<>(Arrays.asList(60, 80))), 10));
        
        
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
            if (getAction(actionNumber).getName().equals("Jump")) {
                jumpCounter++;
            }
            property = actionHash.get(actionNumber);
            this.currentAction = new Action(property);
            if (actionNumber == 2 || actionNumber == 3 || actionNumber == 4 || actionNumber == 5) {
                facingDirection = currentAction.getKnockback().getDirection();
            }
            appliedVector = 0;
            if (currentAction.startHitBox()) {
                appliedVector += 1;
                applyVectors();
            }            
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
        mainVector.setVy(0);
        //mainVector.removeVector(gravityVector);
        

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
        if (currentAction.startHitBox() && appliedVector == 0) {
            appliedVector++;
            applyVectors();
        }
        if (!onGround && mainVector.getVy() == 0) { //NB DETTE ER BARE FOR TESTING AV NÅR DET SKAL LEGGES TIL GRAVITASJONSVEKTOREN
            mainVector.addVector(gravityVector);
        }
		point.setX(point.getX() + mainVector.getVx());
		point.setY(point.getY() + mainVector.getVy());
        hurtBox.updatePos();
        Effectbox currentHitBox = currentAction.getHitBox();
        if (currentHitBox != null) {
            currentHitBox.getPoint().setX(point.getX() + currentHitBox.getOffsetX());
            currentHitBox.getPoint().setY(point.getY() + currentHitBox.getOffsetY());
            currentAction.getHitBox().updatePos();
        }
        
        mainVector.applyAcceleration();
		currentAction.nextActionFrame();
	}

    public Vector getVector() {
        return mainVector;
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
