package fightinggame.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

public class GameCharacter extends WorldEntity{
    private double weight;
    private double speed;
    private int damage;
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

    public GameCharacter(PlayerProperties playerProperties, ArrayList<Integer> pos, ArrayList<String> availKeys) {
        super(playerProperties.getCharacterName(), pos);
        this.availKeys = availKeys;
        this.hurtBox = new Effectbox(this, super.getPoint(), false, playerProperties.getWidth(), playerProperties.getLength());
        this.weight = playerProperties.getWeight();
        this.speed = playerProperties.getSpeed();

        // Add all action found in playerproperties to a hashmap that maps them to a number
        for (ActionProperties property : playerProperties.getActionProperties()) {
            actionHash.put(playerProperties.getActionProperties().indexOf(property), property);
        }
        setCurrentAction(0);
    }

    public GameCharacter(String name, ArrayList<Integer> pos) {
        super(name, pos);
        this.weight = 0;
        this.speed = 0;
        this.hurtBox = new Effectbox(this, point, false, 80, 172);
        this.actionHash.put(0, new ActionProperties("Idle", 1, 13, false, true, 13, false, 0, false));
        this.availKeys = new ArrayList<>();
        this.facingDirection = -1;
        setCurrentAction(0);
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
            Action newAction = getAction(actionNumber);
            if (newAction.getKnockback().getVy() != 0 && newAction.isMovement()) {
                jumpCounter++;
            }
            property = actionHash.get(actionNumber);
            this.currentAction = new Action(property);

            if (currentAction.isProjectile()) {
                currentAction.getKnockback().setDirection(facingDirection);
            }

            if (currentAction.getKnockback().getDirection() != 0 && currentAction.getKnockback().getVy() == 0) {
                facingDirection = currentAction.getKnockback().getDirection();
            }
            
            appliedVector = 0;   
            if (currentAction.startHitBox()) {
                appliedVector++;
                if (currentAction.isMovement()) {
                    applyVectors();
                }
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
            if (currentAction.isMovement()) {
                applyVectors();
            }
        }
        if (!onGround && mainVector.getVy() == 0) { //NB DETTE ER BARE FOR TESTING AV NÅR DET SKAL LEGGES TIL GRAVITASJONSVEKTOREN
            mainVector.addVector(gravityVector);
        }
		point.setX(point.getX() + mainVector.getVx());
		point.setY(point.getY() + mainVector.getVy());
        hurtBox.updatePos();
        Effectbox currentHitBox = currentAction.getHitBox();


        if (currentHitBox != null) {
            currentHitBox.getPoint().setX(point.getX() + Math.abs(currentHitBox.getOffsetX())*facingDirection);
            currentHitBox.getPoint().setY(point.getY() + currentHitBox.getOffsetY());
            if (!currentAction.isProjectile()) { //If the current action is a projectile action, dont update the hitbox of the gamechar action.
                currentAction.getHitBox().updatePos();
            }
            
            //System.out.println(currentHitBox.getPosX());
        }

        mainVector.applyAcceleration();
		currentAction.nextActionFrame();
	}

    public Vector getVector() {
        return mainVector;
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
