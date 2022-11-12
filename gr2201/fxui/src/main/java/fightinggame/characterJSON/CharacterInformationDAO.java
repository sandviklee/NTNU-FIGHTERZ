package fightinggame.characterJSON;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class CharacterInformationDAO {
    private String path = "./src/main/resources/fightinggame/characterJSON/";
    private ObjectMapper mapper = new ObjectMapper();
    private HashMap<String, CharacterInformationObject> characters = new HashMap<String, CharacterInformationObject>();

    public CharacterInformationDAO() {
        Version version = new Version(1, 0, 0, null, null, null);
        SimpleModule module = new SimpleModule("CharacterInformationDeserializer", version);
        module.addDeserializer(CharacterInformationObject.class, new CharacterInformationDeserializer());
        mapper.registerModule(module);
        fetchAllCharacters();
    }

    private void fetchAllCharacters() {
        ArrayList<CharacterInformationObject> characters = new ArrayList<CharacterInformationObject>();
        try {
            File file = new File(path + "CharacterInformation.json");
            TypeReference<ArrayList<CharacterInformationObject>> typeRef = new TypeReference<ArrayList<CharacterInformationObject>>() {};
            characters = mapper.readValue(file, typeRef);
        //  TODO: HANDLE EXCEPTIONS IN CONTROLLER INSTEAD, JUST COPY THIS AND FROM HERE SEND ALL EXCEPTIONS
        } catch (DatabindException e) {
            System.out.println("DatabindException: The input JSON structure does not match expected structure.");
        } catch (StreamReadException e) {
            System.out.println("StreamReadException: he input contains invalid content for JSON.");
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: The file could not be found.");
        } catch (IOException e) {
            System.out.println("IOException: Could not read character information.");
        }
        for (CharacterInformationObject character : characters) {
            String name = character.getName();
            this.characters.put(name, character);
        }
    }

    public HashMap<String, CharacterInformationObject> getAllCharacters() {
        return this.characters;
    }

    public CharacterInformationObject getCharacter(String name) {
        if (!characters.keySet().contains(name)){
            throw new IllegalArgumentException("Could not load character, name does not exist.");
        }
        return this.characters.get(name);
    }

    public static void main(String[] args) {
        CharacterInformationDAO dao = new CharacterInformationDAO();
        System.out.println(dao.getCharacter("Ram"));
    }
}
