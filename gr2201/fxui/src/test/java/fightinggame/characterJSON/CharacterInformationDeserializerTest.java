package fightinggame.characterJSON;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class CharacterInformationDeserializerTest {
    private SimpleModule module = new SimpleModule("CharacterInformationDeserializer", new Version(1, 0, 0, null, null, null));
    private ObjectMapper mapper = new ObjectMapper();
    TypeReference<ArrayList<CharacterInformationObject>> typeRefMultiple = new TypeReference<ArrayList<CharacterInformationObject>>() {};
    private TypeReference<CharacterInformationObject> typeRefSingle = new TypeReference<CharacterInformationObject>() {};

    private final String correctJsonFormat = "{\"characterName\":\"Char\",\"characterDescription\":\"Desc\",\"difficulty\":3,\"playstyle\":\"Style\",\"specialActions\":[{\"specialActionName\":\"SpN1\",\"specialActionDescription\":\"SpD1\"},{\"specialActionName\":\"SpN2\",\"specialActionDescription\":\"SpD2\"},{\"specialActionName\":\"SpN3\",\"specialActionDescription\":\"SpD3\"},{\"specialActionName\":\"SpN4\",\"specialActionDescription\":\"SpD4\"}]}";;
    private final String incorrectJsonDifficulty = "{\"characterName\":\"Char\",\"characterDescription\":\"Desc\",\"difficulty\":5,\"playstyle\":\"Style\",\"specialActions\":[{\"specialActionName\":\"SpN1\",\"specialActionDescription\":\"SpD1\"},{\"specialActionName\":\"SpN2\",\"specialActionDescription\":\"SpD2\"},{\"specialActionName\":\"SpN3\",\"specialActionDescription\":\"SpD3\"},{\"specialActionName\":\"SpN4\",\"specialActionDescription\":\"SpD4\"}]}";;
    private final String incorrectJsonSpecialActions = "{\"characterName\":\"Char\",\"characterDescription\":\"Desc\",\"difficulty\":3,\"playstyle\":\"Style\",\"specialActions\":[{\"specialActionName\":\"SpN1\",\"specialActionDescription\":\"SpD1\"},{\"specialActionName\":\"SpN2\",\"specialActionDescription\":\"SpD2\"},{\"specialActionName\":\"SpN3\",\"specialActionDescription\":\"SpD3\"}]}";;
    private final String incorrectJsonLackingFields = "{\"characterDescription\":\"Desc\",\"difficulty\":3,\"playstyle\":\"Style\",\"specialActions\":[{\"specialActionName\":\"SpN1\",\"specialActionDescription\":\"SpD1\"},{\"specialActionName\":\"SpN2\",\"specialActionDescription\":\"SpD2\"},{\"specialActionName\":\"SpN3\",\"specialActionDescription\":\"SpD3\"},{\"specialActionName\":\"SpN4\",\"specialActionDescription\":\"SpD4\"}]}";;

    @BeforeEach
    public void setup() {
        module.addDeserializer(CharacterInformationObject.class, new CharacterInformationDeserializer());
        mapper.registerModule(module);
    }

    @Test
    @DisplayName("Tests if the deserializer deserializes to correct CharacterInformationObject given json strings properly")
    public void testDeserializeJSONString() throws IOException {
        // Test if deserializer manages to create object from correct data
        CharacterInformationObject character = mapper.readValue(correctJsonFormat, typeRefSingle);
        assertEquals("Char", character.getName(), "Character name does not match.");
        assertEquals("Desc",character.getDescription(), "Character description does not match.");
        assertEquals(3, character.getDifficulty(), "Character difficulty does not match.");
        assertEquals("Style",character.getPlaystyle(), "Character playstyle does not match.");
        assertEquals(4,character.getSpecialActions().size(), "Character does not have correct amount of special actions.");
        assertEquals("SpD1", character.getSpecialActions().get("SpN1"), "Special Action name does not match description.");
    }

    @Test
    @DisplayName("Test if deserializer throws exceptions at invalid data.")
    public void testExceptionsJSONString() throws IOException {
        // Test if deserializer throws error if provided incorrect difficulty
        try {
            mapper.readValue(incorrectJsonDifficulty, typeRefSingle);
            fail("Did not throw IllegalArgumentException when creating an object with invalid difficulty.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getClass() + " thrown when providing incorrect number for difficulty.");
        }
        // Test if deserializer throws error provided incorrect amount of special actions
        try {
            mapper.readValue(incorrectJsonSpecialActions, typeRefSingle);
            fail("Did not throw IllegalArgumentException when creating an object with invalid amount of special actions.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getClass() + " thrown when providing incorrect amount of special attacks.");
        }
        // Test if deserializer throws error if JSON lacks required fields for character
        try {
            mapper.readValue(incorrectJsonLackingFields, typeRefSingle);
            fail("Did not throw Null when creating object with incorrect amount of fields.");
        } catch (NullPointerException e) {
            System.out.println(e.getClass() + " thrown when not providing all required fields for character.");
        }
    }

    //TODO: Test array of multiple objects?
}
