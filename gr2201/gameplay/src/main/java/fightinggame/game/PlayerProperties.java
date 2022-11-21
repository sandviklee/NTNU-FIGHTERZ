package fightinggame.game;

import java.util.ArrayList;
/**
 * The {@code PlayerProperties} class represents all the players properties.
 */
public class PlayerProperties {
    private String characterName;
    private double weight;
    private int length;
    private int width;
    private double speed;
    private ArrayList<ActionProperties> actionP;
    /**
     * Creates a struct which holds the given parameters.
     * Useful when initiating different characters with different game mechanics.
     * Mainly used in deserializing.
     * @param characterName asserts the name of the character
     * @param weight        represents how much damage a character does
     * @param length        asserts height of character
     * @param width         asserts width of character
     * @param speed         asserts character running speed
     * @param actionP       asserts characters action properties
     */
    public PlayerProperties(String characterName, double weight, int length, int width, double speed, ArrayList<ActionProperties> actionP) {
        this.characterName = characterName;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.speed = speed;
        this.actionP = actionP;
    }
    /**
     * Getter for characterName
     * @return characterName string
     */
    public String getCharacterName() {
        return characterName;
    }
    /**
     * Getter for character weight
     * @return weight double
     */
    public double getWeight() {
        return weight;
    }
    /**
     * Getter for character length (height)
     * @return length integer
     */
    public int getLength() {
        return length;
    }
    /**
     * Getter for character width
     * @return width integer
     */
    public int getWidth() {
        return width;
    }
    /**
     * Getter for character speed
     * @return speed double
     */
    public double getSpeed() {
        return speed;
    }
    /**
     * Getter for character action properties
     * @return actionP ArrayList<ActionProperties>
     */
    public ArrayList<ActionProperties> getActionProperties() {
        return actionP;
    }
}
