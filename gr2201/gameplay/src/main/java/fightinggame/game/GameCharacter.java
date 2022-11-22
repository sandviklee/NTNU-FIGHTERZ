package fightinggame.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
/**
 * The {@code GameCharacter} class represents a GameCharacter WorldEntity.
 * A GameCharacter is the only WorldEntity that is playable. 
 */
public class GameCharacter extends WorldEntity{
    private double weight; //Not implemented yet. Shall change the movement knockback of the character.
    private double speed; //Not implemented yet. Shall change the movement knockback of the character.
    private double damageModifier; //Not implemented yet. Shall change the damage of the character.
    private double percentage = 0;
    private double startX;
    private double startY;
    private int idle = 0;
    private int hitStun = 1;
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
    /**
     * Makes a GameCharacter with all the needed properties.
     * @param playerProperties declares the properties of this character
     * @param pos              declares the startingposition
     * @param availKeys        declares the available key inputs
     * @param playerNumb       declares the number of this player
     * @param facingDirection  declares the starting facingdirection
     */
    public GameCharacter(PlayerProperties playerProperties, HashMap<String, Media> soundFX, ArrayList<Integer> pos, ArrayList<String> availKeys, int playerNumb, int facingDirection) {
        super(playerProperties.getCharacterName(), pos);
        this.startX = getX();
        this.startY = getY();
        this.availKeys = availKeys;
        this.playerNumb = playerNumb;
        this.hurtBox = new Effectbox(this, this.point, false, playerProperties.getWidth(), playerProperties.getLength());
        this.weight = playerProperties.getWeight();
        this.speed = playerProperties.getSpeed();
        this.facingDirection = facingDirection;
        this.audioHash = soundFX; //loads the characters audiofiles
        // Add all action found in playerproperties to a hashmap that maps them to a number
        for (ActionProperties property : playerProperties.getActionProperties()) {
            actionHash.put(playerProperties.getActionProperties().indexOf(property), property);
        }
        /*
         * Every action for game character or projectile, has its actions mapped like in their actionhash. 
         * We did this because when you look in the World class, you see that when an action
         * gets set through keyinput, it uses the keyinput index of the available keys for that 
         * character.
         */
        setCurrentAction(idle); //Idle = 0
    }
    /**
     * Makes a Dummy GameCharacter.
     * @param name declares name of character
     * @param pos  declares position of character
     */
    public GameCharacter(String name, ArrayList<Integer> pos, int facingDirection) {
        super(name, pos);
        this.startX = getX();
        this.startY = getY();
        this.weight = 0;
        this.speed = 0;
        this.playerNumb = 0;
        this.hurtBox = new Effectbox(this, point, false, 80, 172);
        int idleActionDuration = 13;
        int hitStunActionDuration = 6;
        int idleActionPriority = 1;
        int hitStunActionPriority = 4;
        this.actionHash.put(idle, new ActionProperties("Idle", idleActionPriority, idleActionDuration, false, true, idleActionDuration, false, 0, false));
        this.actionHash.put(hitStun, new ActionProperties("HitStun", hitStunActionPriority, hitStunActionDuration, false, true, hitStunActionDuration, false, 0, true));
        this.availKeys = new ArrayList<>();
        this.facingDirection = facingDirection;
        setCurrentAction(idle); //Idle = 0
    }
    /** 
     * Sets the current action of the character.
     * @param actionNumber sets the correct action, mapped like in the HashMap of Actionproperties.
     * @throws MediaException if it is not possible to play audio.
     */
    public void setCurrentAction(Integer actionNumber) {
        if (actionNumber != null) {
            /*
             * The purpose of clearing the vector is so that if you stop moving
             * the vectors should disappear immediately and not multiply. But since the vector 
             * clearing happens every time a new action is set, it is also
             * possible to have actions where the action holds a vector a long time.
             */
            clearVectors(); //Clears all the vectors from the last action.
            Action newAction = getAction(actionNumber);
            /*
             * If the Action has a velocity in the Y direction and
             * the action waiting to be set is a movement action
             * the jumpCounter should be incremented.
             * 
             * The jump counter is there because we have to set a limit 
             * to how many times a character can jump in the air.
             */
            if (newAction.getKnockback().getVy() != 0 && newAction.isMovement()) {
                jumpCounter++;
            } 
            property = actionHash.get(actionNumber);
            this.currentAction = new Action(property);
            /*
             * If the Action is "DownSpecial" and the character is on ground, it should not let the player constantly 
             * apply this action when the purpose is to get to the ground faster. Therefore it stunlocks the character
             * in the Idle animation. This stops the player from possibly "glitching" through the ground.
             */
            if (currentAction.getName().equals("DownSpecial") && onGround == true) {
                property = actionHash.get(0);
                this.currentAction = new Action(property);
                jumpCounter = 0;
            }
            /*
             * If you are playing on a device which supports audio, the Actions audio will
             * be played when you do an action.
             * 
             * If not then the MediaException will pop up.
             */
            if (audioHash.get(this.currentAction.getName()) != null) {
                try {
                    playerAudioPlayer = new MediaPlayer(audioHash.get(this.currentAction.getName()));
                    playerAudioPlayer.setVolume(0.7);
                    playerAudioPlayer.play();
        
                } catch (MediaException e) {
                    System.out.println("Since you dont have the correct Media codec. You cant play audio. Error: " + e);
                }
            }
            /*
             * If the current action set is a Projectile Action, AKA if the action
             * should be able to spawn a projectile, then the knockback of the move should be 
             * set to the facing direction of the player.
             * This makes it so that for example the Angry Cyclists Wheel gets thrown in the correct
             * direction.
             */
            if (currentAction.isProjectile()) {
                currentAction.getKnockback().setDirection(facingDirection);
            }
            /*
             * If the current action and the facing direction is not the same, then change the facing direction
             * of the character. 
             * For example if you run to the left, then the character should now be facing the left after the
             * running action.
             */
            if (currentAction.getKnockback().getDirection() != 0 && currentAction.getKnockback().getVy() == 0) {
                facingDirection = currentAction.getKnockback().getDirection();
            }
            /*
             * AppliedVector is a counter that checks whether the character has applied the 
             * Actions knockback or not to the main Vector of the character.
             * The Action should also only apply the knockback to the character and move it if isMovement is true.
             */
            appliedVector = 0;   
            if (currentAction.startHitBox()) {
                appliedVector++;
                if (currentAction.isMovement()) {
                    applyVectors();
                }
            }  
        } 
    }
    /**
     * Executes the current action the player has gotten from setCurrentAction.
     * Checks different variables like onTop to set the correct updating position.
     */
    public void doAction(){
        /*
         * Does the same check as in setActions method. This is for checking every tick, since doAction
         * is applied every tick of the world. Therefore if the Action knockback has not yet been set,
         * set it to the character. 
         * This is especially useful when the hitbox does not start at the beginning of the Action.
         */
        if (currentAction.startHitBox() && appliedVector == 0) {
            appliedVector++;
            if (currentAction.isMovement()) {
                applyVectors();
            }
        }
        /*
         * When you hit your head, your upwards velocity should be removed. 
         */
        if (onTop) {
            clearVerticalVector();
        }
        /*
         * When you hit the ground you should stop falling. Therefore there is a check
         * for whether you are on ground or not. If you are not on ground, you should apply 
         * the gravity Vector. If you are on ground you should stop falling, therefore clear 
         * the vertical vector and reset the jump counter. 
         */
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
        /*
         * If the character is getting hit on the Right or the Left it should clear the
         * horizontalvectors. This is so the character doesn't move into the walls.
         */
        if (mainVector.getVx() < 0 && onRight) {
            clearHorizontalVector();
        } else if (mainVector.getVx() > 0 && onLeft) {
            clearHorizontalVector();
        } 
        /*
         * The way this game updates the position of a character or projectile is by updating the 
         * position of the point. This is why in Effectbox we stated that the effectbox needs to be updated
         * as the point of the character is the same as the point in the effectbox.
         */
		point.setX(point.getX() + mainVector.getVx());
		point.setY(point.getY() + mainVector.getVy());
        hurtBox.updatePos();
        /*
         * If the Action has a hitbox, the point of that hitbox should be updated
         * to the current position of the character + the hitbox offset.
         */
        Effectbox currentHitBox = currentAction.getHitBox();
        if (currentHitBox != null) {
            currentHitBox.getPoint().setX(point.getX() + Math.abs(currentHitBox.getOffsetX())*facingDirection);
            currentHitBox.getPoint().setY(point.getY() + currentHitBox.getOffsetY());
            if (!currentAction.isProjectile()) { //If the current action is a projectile action, dont update the hitbox of the gamecharacter action.
                currentAction.getHitBox().updatePos();
            }
        }
        /*
         * When everything has been checked the main Vector should apply its acceleration
         * and the current Action duration should be incremented.
         */
        mainVector.applyAcceleration();
		currentAction.nextActionFrame();
	}
    /**
     * Applies the vector from the action.
     */
    public void applyVectors() {
        Vector knockback = this.currentAction.getKnockback();
        mainVector.addVector(knockback);
    }
    /**
     * Sets the position of the character.
     * @param x
     * @param y
     */
    public void setPosition(double x, double y) {
        this.point.setX(x);
        this.point.setY(y);
    }
    /**
     * Sets the onGround property to true or false if character touches the ground.
     * @param onGround boolean
     */
    public void setOnGround(boolean onGround) {
		this.onGround = onGround;
        if (onGround) {
            if (mainVector.getVy() > 0) {
                clearVerticalVector();
                jumpCounter = 0;
            }
        } 
	}
    /**
     * Setter for onTop
     * A boolean holding whether the character is colliding with something
     * on top of it.
     * @param onTop boolean
     */
    public void setOnTop(boolean onTop) {
		this.onTop = onTop;
	}
    /**
     * Setter for onLeft
     * A boolean holding whether the character is colliding with something
     * on the left of it.
     * @param onLeft boolean
     */
    public void setOnLeft(boolean onLeft) {
        this.onLeft = onLeft;
    }
    /**
     * Setter for onRight
     * A boolean holding whether the character is colliding with something
     * on the right of it.
     * @param onRight boolean
     */
    public void setOnRight(boolean onRight) {
        this.onRight = onRight;
    }
    /**
     * Resets the percentage of the character
     */
    public void resetPercentage() {
        this.percentage = 0;
    }
    /**
     * Adds percentage to the character.
     * @param percentage integer
     */
    public void addPercentage(int percentage) {
        this.percentage += percentage;
    }
    /**
     * ResetAction resets the current action to "Idle" action.
     */
    public void resetAction() {
        setCurrentAction(0);
    }
    /**
     * Iterates the deathCounter of the character.
     */
    public void incrementDeathCounter() {
        this.deathCounter++;
    }
    /**
     * Getter for available keys from the character.
     * @return availKeys ArrayList<String>
     */
    public ArrayList<String> getAvailKeys() {
        return availKeys;
    }
    /**
     * Getter for availKeys predicate
     * @return availKey Predicate
     */
    public Predicate<String> getPredicate() {
        return availKey;
    }
    /**
     * Getter for character Hurtbox
     * @return hurtBox Effectbox
     */
    public Effectbox getHurtBox() {
        return hurtBox;
    }
    /**
     * Getter for character Actions
     * @param actionNumber integer
     * @return action that is specified by the action number
     */
    public Action getAction(int actionNumber) {
        property = actionHash.get(actionNumber);
        return new Action(property);
    }
    /**
     * Getter for main Vector
     * @return mainVector Vector
     */
    public Vector getVector() {
        return mainVector;
    }
    /**
     * Getter for character damaged percentage
     * @return percentage double
     */
    public double getPercentage() {
        return percentage;
    }
    /**
     * Getter for facing direction of character
     * @return facingDirection integer
     */
    public int getFacingDirection() {
        return facingDirection;
    }
    /**
     * Getter for JumpCounter
     * @return jumpCounter integer
     */
    public int getJumpCounter() {
        return jumpCounter;
    }
    /**
     * Getter for DeathCounter
     * @return deathCounter integer
     */
    public int getDeathCounter() {
        return deathCounter;
    }
    /**
     * Getter for player number
     * @return playerNumb integer
     */
    public int getPlayerNumb() {
        return playerNumb;
    }
    /**
     * Getter for character X start position
     * @return startX double
     */
    public double getStartX() {
		return startX;
	}
    /**
     * Getter for character Y start position
     * @return startY double
     */
	public double getStartY() {
		return startY;
	}
    /**
     * Clears the main Vector
     */
    private void clearVectors() {
        mainVector = new Vector();
    }
    /**
     * Clears the horizontal velocity and acceleration
     */
    private void clearHorizontalVector() {
        mainVector.setVx(0);
        mainVector.setAx(0);
    }
    /**
     * Clears the vertical velocity and acceleration
     */
    private void clearVerticalVector() {
        mainVector.setVy(0);
        mainVector.setAy(0);
    }
}
