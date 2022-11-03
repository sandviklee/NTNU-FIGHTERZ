package fightinggame.json;

import java.util.HashMap;

public class CharacterInformationObject {
    private String name;
    private String description;
    private int difficulty;
    private String playstyle;
    private HashMap<String, String> specialActions;

    public CharacterInformationObject(String name, String description, int difficulty, String playstyle) {
        System.out.println(name + description + playstyle);
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.playstyle = playstyle;
        this.specialActions = new HashMap<String, String>();
    }

    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int getDifficulty() {
        return difficulty;
    }
    
    public String getPlaystyle() {
        return playstyle;
    }
    
    public HashMap<String, String> getSpecialActions() {
        return specialActions;
    }

    public void addSpecialAction(String name, String description){
        specialActions.put(name, description);
    }
}
