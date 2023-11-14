package fightinggame.dao;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fightinggame.utils.CharacterInformationObject;
import fightinggame.utils.ReadWriteToFile;
import fightinggame.utils.json.characterinfo.CharacterInformationModule;

/**
 * {@code CharacterInformationDAOImpl} is following the Database Access Object
 * pattern.
 * The class shall make it possible to interact with the data storage system
 * without knowing the implementation (Information hiding).
 * It shall not make the CharacterInformation objects, but allow access to their
 * data.
 */
public class CharacterInformationDAOImpl implements RODAO<CharacterInformationObject, String> {
    private String fileName;
    private ObjectMapper mapper;
    private ArrayList<CharacterInformationObject> characters;
    private ReadWriteToFile readWriteToFile;

    /**
     * Constructs CharacterInfromationDAO and throws IOException if it cannot access
     * 
     * @throws IOException              if file containing characters cannot be
     *                                  found or data is otherwise incorrect
     * @throws IllegalArgumentException if file containing character contains
     *                                  invalid data and cannot be deserialized
     */
    public CharacterInformationDAOImpl() {
        this.mapper = new ObjectMapper();
        this.characters = new ArrayList<CharacterInformationObject>();
        this.fileName = "characterInformation.json";
        mapper.registerModule(new CharacterInformationModule());
        this.readWriteToFile = new ReadWriteToFile();
    }

    /**
     * Fetches all characters from file in path field provided above, and saves
     * characters in DAO object.
     * 
     * @throws IOException              if file containing characters cannot be
     *                                  found or data is otherwise incorrect
     * @throws IllegalArgumentException if file containing character contains
     *                                  invalid data and cannot be deserialized
     * @throws NullPointerException     if JSON does not provide all necessary
     *                                  fields for character
     */
    private void fetchAllCharacters() throws IOException, IllegalArgumentException, NullPointerException {
        String allCharactersInfoJson = readWriteToFile.readFromFile(fileName);
        ArrayList<CharacterInformationObject> charactersFromJson = deserializeCharacterInformtion(
                allCharactersInfoJson);

        for (CharacterInformationObject character : charactersFromJson) {
            if (character == null) {
                throw new IllegalArgumentException("Invalid JSON input, could not create character.");
            }
            this.characters.add(character);
        }
    }

    private ArrayList<CharacterInformationObject> deserializeCharacterInformtion(String rawJson) {
        ArrayList<CharacterInformationObject> res = new ArrayList<>();

        try {
            CharacterInformationObject[] characterInformations = mapper.readValue(rawJson,
                    CharacterInformationObject[].class);
            for (CharacterInformationObject characterInformation : characterInformations) {
                res.add(characterInformation);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Changes file path of where to find character information, for testing
     * purposes
     * 
     * @param path The new path to use instead of standard file path
     * @throws IOException if new path is invalid
     */
    public void setPath(String path) throws IOException {
        try {
            this.readWriteToFile.setPath(path);
            fetchAllCharacters();
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    @Override
    public ArrayList<CharacterInformationObject> getAll() {
        try {
            fetchAllCharacters();
        } catch (IllegalArgumentException | NullPointerException | IOException e) {
            e.printStackTrace();
        }

        return characters;
    }

    @Override
    public CharacterInformationObject find(String name) {
        try {
            fetchAllCharacters();
        } catch (IllegalArgumentException | NullPointerException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (characters.stream().noneMatch(c -> !(c.getName().equals(name)))) {
            throw new IllegalArgumentException("Could not load character, name does not exist.");
        }
        for (CharacterInformationObject characterInformationObject : characters) {
            if (characterInformationObject.getName().equals(name)) {
                return characterInformationObject;
            }
        }
        return null;
    }
}
