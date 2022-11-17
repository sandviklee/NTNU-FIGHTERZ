package fightinggame.characterJSON;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * CharacterInformationDAO is following the Database Access Object partern.
 * The class shall make it possible to interact with the data storage system without knowing the implementation (Information hiding).
 * It shall not make the CharacterInformation objects, but allow access to their data.
 */
public class CharacterInformationDAO {
    private String path;
    private ObjectMapper mapper = new ObjectMapper();
    private HashMap<String, CharacterInformationObject> characters = new HashMap<String, CharacterInformationObject>();

    /**
	 * Contructs CharacterInfromationDAO and throws IOException if it cannot access
	 * @throws IOException if file containing characters cannot be found or data is otherwise incorrect
	 * @throws IllegalArgumentException if file containing character contains invalid data and cannot be deserialized
     */
    public CharacterInformationDAO(String path) throws IOException, IllegalArgumentException {
        this.path = path;
        Version version = new Version(1, 0, 0, null, null, null);
        SimpleModule module = new SimpleModule("CharacterInformationDeserializer", version);
        module.addDeserializer(CharacterInformationObject.class, new CharacterInformationDeserializer());
        mapper.registerModule(module);
        fetchAllCharacters();
    }

    /**
     * Fetches all characters from file in path field provided above, and saves characters in DAO object.
     * @throws IOException if file containing characters cannot be found or data is otherwise incorrect
     * @throws IllegalArgumentException if file containing character contains invalid data and cannot be deserialized
     * @throws NullPointerException if JSON does not provide all necessary fields for character
     */
    private void fetchAllCharacters() throws IOException, IllegalArgumentException, NullPointerException {
        ArrayList<CharacterInformationObject> characters = new ArrayList<CharacterInformationObject>();
        File file = new File(path);
            TypeReference<ArrayList<CharacterInformationObject>> typeRef = new TypeReference<ArrayList<CharacterInformationObject>>() {};
            characters = mapper.readValue(file, typeRef);

        for (CharacterInformationObject character : characters) {
            if (character == null){
                throw new IllegalArgumentException("Invalid JSON input, could not create character.");
            }
            String name = character.getName();
            this.characters.put(name, character);
        }
    }

    /**
     * Returns all characters saved in DAO object
     * @return HashMap with character name as key and CharacterInformationObject as value
     */
    public HashMap<String, CharacterInformationObject> getAllCharacters() {
        return this.characters;
    }

    /**
     * Finds specific character by name in DAO object characters HashMap
     * @param name Name of character to find in HashMap
     * @return CharacterInformationObject containing all information about the character
     * @throws IllegalArgumentException if character name does not exist in characters HashMap
     */
    public CharacterInformationObject getCharacter(String name) {
        if (!characters.keySet().contains(name)){
            throw new IllegalArgumentException("Could not load character, name does not exist.");
        }
        return this.characters.get(name);
    }

    /**
     * Changes file path of where to find character information, for testing purposes
     * @param path The new path to use instead of standard file path
     * @throws IOException if new path is invalid
     */
    public void setPath(String path) throws IOException{
        String oldpath = this.path;
        try {
            this.path = path;
            fetchAllCharacters();
        } catch (IOException e) {
            this.path  = oldpath;
            throw new IOException(e);
        }
    }

    /**
     * @return file path of character information JSON
     */
    public String getPath() {
        return this.path;
    }
}
