package fightinggame.game;

import java.util.ArrayList;

public class PlayerProperties {
    private String characterName;
    private double weight;
    private int length;
    private int width;
    private double speed;
    private ArrayList<ActionProperties> actionP;

    public PlayerProperties(String characterName, double weight, int length, int width, double speed,  ArrayList<ActionProperties> actionP) {
        this.characterName = characterName;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.speed = speed;
        this.actionP = actionP;
    }

    public String getCharacterName() {
        return characterName;
    }

    public double getWeight() {
        return weight;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public double getSpeed() {
        return speed;
    }

    public ArrayList<ActionProperties> getActionP() {
        return actionP;
    }

    
}
