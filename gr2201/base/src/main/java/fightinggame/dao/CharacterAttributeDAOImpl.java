package fightinggame.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fightinggame.game.PlayerProperties;
import fightinggame.utils.ReadWriteToFile;
import fightinggame.utils.json.characterattributes.GameplayModule;

/**
 * The {@code CharacterAttributeDAOImpl} is the data access object for the files
 * containing {@code PlayerProperties}. {@code CharacterAttributeDAOImpl} can
 * {@link #getAll()} {@code PlayerProperties} in file, but when only specific
 * {@code PlayerProperties} for certain characters are needed one can
 * {@link #find(String)} those with given name.
 */
public class CharacterAttributeDAOImpl implements RODAO<PlayerProperties, String> {
    private ObjectMapper mapper;
    private ReadWriteToFile readWriteToFile;
    private String filename;

    /**
     * Constructs a CharacterAttributeDAOImpl with the correct ObjectMapper and
     * filename.
     */
    public CharacterAttributeDAOImpl() {
        this.mapper = new ObjectMapper();
        this.readWriteToFile = new ReadWriteToFile();
        mapper.registerModule(new GameplayModule());
        this.filename = "characters.json";
    }

    /**
     * Convert Json to {@code PlayerProperties}
     * 
     * @param rawJson to convert
     * @return the {@code PlayerProperties} made from json.
     */
    private ArrayList<PlayerProperties> deserializerPlayerProperties(String rawJson) {
        ArrayList<PlayerProperties> res = new ArrayList<>();
        try {
            PlayerProperties[] playerProperties = mapper.readValue(rawJson, PlayerProperties[].class);
            for (PlayerProperties playerProperty : playerProperties) {
                res.add(playerProperty);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ArrayList<PlayerProperties> getAll() {
        try {
            String fileJson = readWriteToFile.readFromFile(this.filename);
            return this.deserializerPlayerProperties(fileJson);
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound at " + filename);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return new ArrayList<PlayerProperties>();
    }

    @Override
    public PlayerProperties find(String id) {
        for (PlayerProperties character : getAll()) {
            if (character.getCharacterName().equals(id)) {
                return character;
            }
        }
        return null;
    }
}

