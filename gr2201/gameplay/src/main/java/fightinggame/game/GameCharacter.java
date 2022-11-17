package fightinggame.game;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GameCharacter extends WorldEntity{
    private double weight;
    private double speed;
    private double damageModifier;
    private double precentage = 0;
    private double startX;
    private double startY;
    private int facingDirection = 1;
    private int appliedVector;
    private int jumpCounter = 0;
    private int deathCounter = 0;
    private int playerNumb;
    
    private HashMap<Integer, ActionProperties> actionHash = new HashMap<>();
    private HashMap<String, Media> audioHash = new HashMap<>();
    private ArrayList<String> availKeys;
    private Predicate<String> availKey = i -> availKeys.contains(i);
    private ActionProperties property;
    private Effectbox hurtBox;
    private Vector mainVector = new Vector();
    private Vector gravityVector = new Vector(0, 8, 0, 0, 1);
    private MediaPlayer playerAudioPlayer;

    private boolean onGround = false;
    private boolean onRight = false;
    private boolean onLeft = false;
    private boolean onTop = false; 

    public GameCharacter(PlayerProperties playerProperties, ArrayList<Integer> pos, ArrayList<String> availKeys, int playerNumb, int facingDirection) {
        super(playerProperties.getCharacterName(), pos);
        this.startX = getX();
        this.startY = getY();
        this.availKeys = availKeys;
        this.playerNumb = playerNumb;
        this.hurtBox = new Effectbox(this, this.point, false, playerProperties.getWidth(), playerProperties.getLength());
        this.weight = playerProperties.getWeight();
        this.speed = playerProperties.getSpeed();
        this.facingDirection = facingDirection;
        loadAudio(); //loads the characters audiofiles
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
        loadAudio();
        setCurrentAction(0);
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

            if (currentAction.getName().equals("DownSpecial") && onGround == true) {
                property = actionHash.get(0);
                this.currentAction = new Action(property);
                jumpCounter = 0;
            }

            if (audioHash.get(this.currentAction.getName()) != null) {
                playerAudioPlayer = new MediaPlayer(audioHash.get(this.currentAction.getName()));
                playerAudioPlayer.setVolume(0.7);
                playerAudioPlayer.play();
    
            }
  
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

    public void doAction(){
        if (currentAction.startHitBox() && appliedVector == 0) {
            appliedVector++;
            if (currentAction.isMovement()) {
                applyVectors();
            }
        }

        if (onTop) {
            clearVerticalVector();
        }

        if (onGround) {
            if (mainVector.getVy() > 0) {
                clearVerticalVector();
                jumpCounter = 0;
            }
        } else {
            if (mainVector.getVy() == 0) {
                mainVector.addVector(gravityVector);
            }
        }

        if (mainVector.getVx() < 0 && onRight) {
            clearHorisontalVector();
        } else if (mainVector.getVx() > 0 && onLeft) {
            clearHorisontalVector();
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
        }

        mainVector.applyAcceleration();
		currentAction.nextActionFrame();
	}

    public void applyVectors() {
        Vector knockback = this.currentAction.getKnockback();
        mainVector.addVector(knockback);

    }

    public void setPosition(double x, double y) {
        this.point.setX(x);
        this.point.setY(y);
    }

    public void setOnGround(boolean onGround) {
		this.onGround = onGround;
        if (onGround) {
            if (mainVector.getVy() > 0) {
                clearVerticalVector();
                jumpCounter = 0;
            }
        } 
	}

    public void setOnTop(boolean onTop) {
		this.onTop = onTop;
	}

    public void setOnLeft(boolean onLeft) {
        this.onLeft = onLeft;
    }

    public void setOnRight(boolean onRight) {
        this.onRight = onRight;
    }

    public void resetPrecentage() {
        this.precentage = 0;
    }

    public void addPrecentage(int precentage) {
        this.precentage += precentage;
    }

    public void resetAction() {
        setCurrentAction(0);
    }

    public void iterateDeathCounter() {
        this.deathCounter++;
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

    public Action getAction(int actionNumber) {
        property = actionHash.get(actionNumber);
        return new Action(property);
    }

    public Vector getVector() {
        return mainVector;
    }

    public double getPrecentage() {
        return precentage;
    }

    public int getFacingDirection() {
        return facingDirection;
    }

    public int getJumpCounter() {
        return jumpCounter;
    }

    public int getDeathCounter() {
        return deathCounter;
    }

    public int getPlayerNumb() {
        return playerNumb;
    }

    public double getStartX() {
		return startX;
	}

	public double getStartY() {
		return startY;
	}

    private void clearVectors() {
        mainVector = new Vector();
    }

    private void clearHorisontalVector() {
        mainVector.setVx(0);
        mainVector.setAx(0);
    }

    private void clearVerticalVector() {
        mainVector.setVy(0);
        mainVector.setAy(0);
    }

    private void loadAudio() {
        File[] audioFiles = new File("gr2201/fxui/src/main/resources/fightinggame/ui/Audio/" + this.name).listFiles();
        for (File audio : audioFiles) {
            audioHash.put((audio.getName()).split("\\.")[0], new Media(new File("gr2201/fxui/src/main/resources/fightinggame/ui/Audio/" + this.name + "/" + audio.getName()).toURI().toString()));
            
        }
    }


}
