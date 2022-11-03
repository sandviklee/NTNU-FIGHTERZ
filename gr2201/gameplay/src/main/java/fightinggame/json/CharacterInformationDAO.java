package fightinggame.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class CharacterInformationDAO {
    private String path;
    private ObjectMapper mapper = new ObjectMapper();

    public CharacterInformationDAO() {
        this.path = "../gameplay/src/main/resources/json";
        Version version = new Version(1, 0, 0, null, null, null);
        SimpleModule module = new SimpleModule("CharacterInformationDeserializer", version);
        module.addDeserializer(HashMap.class, new CharacterInformationDeserializer());
        mapper.registerModule(module);
    }

    public HashMap<String, String> getAllCharacters() {
        
        File file = new File("./gr2201/gameplay/src/main/resources/CharacterInformation.json");
        TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {};
        HashMap<String, String> characters = new HashMap<String, String>();
        try {
            characters = mapper.readValue(file, typeRef);
            Map pls = (Map) characters.get("Sol");
            System.out.println("pls");

        
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
        return characters;
    }

    public static void main(String[] args) {
        CharacterInformationDAO dao = new CharacterInformationDAO();
        dao.getAllCharacters();
    }
}
