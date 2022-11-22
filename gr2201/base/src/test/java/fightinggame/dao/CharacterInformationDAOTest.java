package fightinggame.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fightinggame.utils.CharacterInformationObject;

public class CharacterInformationDAOTest {
    private String path = "../base/src/main/resources/CharacterInformationTest.json";
    private CharacterInformationDAOImpl dao;

    @BeforeEach
    public void setup() {
        dao = new CharacterInformationDAOImpl();

        try {
            (dao).setPath(path);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Test the CharacterInformationDAO object can be instantied with all characters.")
    public void testInstantiateCharacterInformationDAO() throws IOException {
        // Test if object can be instantied from test file and contains correct amount
        // of characters
        CharacterInformationDAOImpl dao = new CharacterInformationDAOImpl();
        assertEquals(4, dao.getAll().size(), "Not correct amount of characters");
    }

    @Test
    @DisplayName("Test the CharacterInformationDAO can get characters.")
    public void testGetCharacter() {
        ArrayList<CharacterInformationObject> characters = dao.getAll();

        for (CharacterInformationObject character : characters) {
            assertTrue(characters.contains(dao.find(character.getName())),
                    "This character does exist, but cant be found");
        }
    }

    @Test
    @DisplayName("Tests if the correct exceptions are thrown on incorrect file paths.")
    public void testJSONFilePath() throws IOException {
        String incorrectPath = ".gr2201/test.json";
        CharacterInformationDAOImpl dao = new CharacterInformationDAOImpl();
        // Test if IOException is thrown on incorrect file path
        try {
            dao.setPath(incorrectPath);
            fail("Should throw IOException when adding invalid filepath.");
        } catch (Exception e) {
            System.out.println(e.getClass() + " thrown when adding invalid filepath.");
        }
    }
}
