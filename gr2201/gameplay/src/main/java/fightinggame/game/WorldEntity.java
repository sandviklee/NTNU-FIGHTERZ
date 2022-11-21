package fightinggame.game;

import java.util.ArrayList;
import java.util.function.Predicate;
/**
 * The {@code WorldEntity} abstract class is meant to represent all enities in the game and will have both common and uncommon methods and attributes between them.
 */
public abstract class WorldEntity {
	/*
	 * Many of the methods in this abstract class are here to be overridden. The reason behind this
	 is because many of them dont belong in for example terrain, and only in game character or projectile.
	 * Therefore we use this class as a representation rather than for functionality. 
	 */
	protected int id;
	protected Effectbox hitBox;
	protected Action currentAction = null;
	protected String name;
	protected Point point;
	/**
	 * The super for all WorldEntities like GameCharacter, Projectile and Terrain.
	 * @param name asserts the name
	 * @param pos  asserts the position
	 */
	public WorldEntity(String name, ArrayList<Integer> pos) {
		this.name = name;
		this.point = new Point((double) pos.get(0), (double) pos.get(1));
	}
	/**
	 * Setter for onGround
	 * Only here to be Overriden.
	 * @param b assert onGround boolean
	 */
	public void setOnGround(boolean b) {}
	/**
	 * Setter for onRight
	 * Only here to be Overriden.
	 * @param b assert Right boolean
	 */
	public void setOnRight(boolean b) {}
	/**
	 * Setter for onLeft
	 * Only here to be Overriden.
	 * @param b assert Left boolean
	 */
	public void setOnLeft(boolean b) {}
	/**
	 * Setter for onTop
	 * Only here to be Overriden.
	 * @param b assert Top boolean
	 */
	public void setOnTop(boolean b) {}
	/**
	 * Adds precentage
	 * Only here to be Overriden.
	 * @param precentage assert precentage integer
	 */
	public void addPrecentage(int precentage) {}
	/**
	 * Setter for current Action
	 * Only here to be Overriden.
	 * @param actionNumber asserts action number in actionhash
	 */
	public void setCurrentAction(Integer actionNumber) {}
	/**
	 * Do action
	 * Only here to be Overriden.
	 */
	public void doAction() {}
	/**
	 * Getter for Action
	 * Only here to be Overriden.
	 * @param actionNumber index integer
	 */
	public Action getAction(int actionNumber) {
        return null;
    }
	/**
	 * Getter for Vector
	 * Only here to be Overriden.
	 */
	public Vector getVector() {
		return null;
	}
	/**
	 * Getter for predicate
	 * Only here to be Overriden.
	 */
	public Predicate<String> getPredicate() {
		return null;
	}
	/**
	 * Getter for available keys
	 * Only here to be Overriden.
	 */
	public ArrayList<String> getAvailKeys() {
		return null;
	}
	/**
	 * Getter for X position of Point
	 * @return x point position double
	 */
    public double getX() {
		return point.getX();
	}
	/**
	 * Getter for Y position of Point
	 * @return y point position double
	 */
	public double getY() {
		return point.getY();
	}
	/**
	 * Getter for character starting X postition
	 * Only here to be Overriden.
	 * @return startX integer
	 */
	public double getStartX() {
		return 0;
	}
	/**
	 * Getter for character starting Y postition
	 * Only here to be Overriden.
	 * @return startY integer
	 */
	public double getStartY() {
		return 0;
	}
	/**
	 * Getter for precentage
	 * Only here to be Overriden.
	 * @return precentage double
	 */
	public double getPrecentage() {
		return 0;
	}
	/**
	 * Getter for death counter
	 * Only here to be Overriden.
	 * @return deathCounter integer
	 */
	public int getDeathCounter() {
        return 0;
    }
	/**
	 * Getter for facing direction
	 * Only here to be Overriden.
	 * @return facingDirection integer
	 */
	public int getFacingDirection() {
        return 0;
    }
	/**
	 * Getter for player number
	 * Only here to be Overriden.
	 * @return playernumber integer
	 */
	public int getPlayerNumb() {
		return 0;
	}
	/**
	 * Getter for name
	 * @return name String
	 */
	public String getName() {
		return name;
	}
	/**
	 * Getter for Hurtbox
	 * Only here to be Overriden.
	 * @return hurtbox Effectbox
	 */
	public Effectbox getHurtBox() {
        return null;
    }
	/**
	 * Getter for Hitbox
	 * Only here to be Overriden.
	 * @return hitbox Effectbox
	 */
	public Effectbox getHitBox() {
        return null;
    }
	/**
	 * Getter for current action
	 * @return currentAction Action
	 */
	public Action getCurrentAction() {
		return currentAction;
	}
	/**
	 * Getter for JumpCounter
	 * Only here to be Overriden.
	 */
	public int getJumpCounter() {
        return 0;
    }
}
