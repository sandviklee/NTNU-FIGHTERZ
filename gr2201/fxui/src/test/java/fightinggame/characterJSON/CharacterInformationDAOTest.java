package fightinggame.characterJSON;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CharacterInformationDAOTest {
    private String path = "./src/main/resources/fightinggame/test/CharacterInformationTest.json";

    @Test
    @DisplayName("Test the CharacterInformationDAO object can be instantied with all characters.")
    public void testInstantiateCharacterInformationDAO() throws IOException {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current absolute path is: " + s);
        
        // Test if object can be instantied from test file and contains correct amount of characters
        CharacterInformationDAO dao = new CharacterInformationDAO(path);
        assertEquals(3,dao.getAllCharacters().size());
    }

    @Test
    @DisplayName("Tests if the correct exceptions are thrown on incorrect file paths.")
    public void testJSONFilePath() throws IOException{
        String incorrectPath = ".gr2201/test.json";
        CharacterInformationDAO dao = new CharacterInformationDAO(path);
        // Test if IOException is thrown on incorrect file path
        try {
            dao.setPath(incorrectPath);
            fail("Should throw IOException when adding invalid filepath.");
        } catch (Exception e) {
            System.out.println(e.getClass() + " thrown when adding invalid filepath.");
        }
        // Test that incorrect file path is not saved to object
        assertNotEquals(incorrectPath, dao.getPath());
    }
}
