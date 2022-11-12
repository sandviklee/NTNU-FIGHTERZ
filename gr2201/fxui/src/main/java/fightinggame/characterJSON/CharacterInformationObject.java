package fightinggame.characterJSON;

import java.util.HashMap;

/**
 * Contains all information relevant to display about a character on the character information page.
 * Does not include detailed information about hitboxes and damage, only a general description.
 */
public class CharacterInformationObject {
    private String name;
    private String description;
    private int difficulty;
    private String playstyle;
    private HashMap<String, String> specialActions = new HashMap<String, String>();
    
    /**
     * Constructs a CharacterInformationObject, does not check for incorrect input as this might change and is done in the DAO.
     * @param name the character name
     * @param description a general description about the character, their lore and what they do
     * @param difficulty an integer describing how difficult the character is to play
     * @param playstyle a word explaining the general playstyle/archetype this character belongs to
     */
    public CharacterInformationObject(String name, String description, int difficulty, String playstyle) {
        if (name != null && description != null && difficulty != 0 && playstyle != null) {
            this.name = name;
            this.description = description;
            this.difficulty = difficulty;
            this.playstyle = playstyle;
        } else {
            throw new IllegalArgumentException("Character lacks required fields.");
        }
    }

    /**
     * @return character name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return character description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * @return integer describing character difficulty
     */
    public int getDifficulty() {
        return difficulty;
    }
    
    /**
     * @return word describing character playstyle
     */
    public String getPlaystyle() {
        return playstyle;
    }
    
    /**
     * @return HashMap containing special action name as key and special action description as value
     */
    public HashMap<String, String> getSpecialActions() {
        return specialActions;
    }

    /**
     * Adds a special action and it's description to the saved special actions
     * @param name the action name
     * @param description a short action description
     */
    public void addSpecialAction(String name, String description){
        specialActions.put(name, description);
    }

    /**
     * toString method for debugging purposes to easier read a CharacterInformationObject
     * @return string containing all relevant information about a character's information page
     */
    @Override
    public String toString(){
        String information = name + "\n" + description + "\n" + String.valueOf(difficulty) + "\n" + playstyle + "\n";
        for (String action : specialActions.keySet()){
            information = information + action + ": " + specialActions.get(action) + "\n";
        }
        return information;
    }
}
