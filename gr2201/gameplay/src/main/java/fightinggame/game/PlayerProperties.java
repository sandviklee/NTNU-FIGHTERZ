package fightinggame.game;

import java.util.ArrayList;

public class PlayerProperties {
    private String characterName;
    private int weight;
    private int length;
    private int width;
    private int speed;
    private ArrayList<ActionProperties> actionP;

    public PlayerProperties(String characterName, int weight, int length, int width, int speed,  ArrayList<ActionProperties> actionP) {
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

    public int getWeight() {
        return weight;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getSpeed() {
        return speed;
    }

    public ArrayList<ActionProperties> getActionP() {
        return actionP;
    }

    
}
