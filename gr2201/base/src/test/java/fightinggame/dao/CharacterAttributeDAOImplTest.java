package fightinggame.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fightinggame.game.PlayerProperties;

public class CharacterAttributeDAOImplTest {

    private CharacterAttributeDAOImpl characterAttributeDAOImpl;
    private final String validCharacter = "AngryCyclist";
    private final String nonValidCharacter = "DOESNOTEXIST";
    private final int totalAmountOfCharacters = 3;

    @BeforeEach
    public void setup() {
        characterAttributeDAOImpl = new CharacterAttributeDAOImpl();
    }

    @Test
    @DisplayName("Tests if find() works correctly")
    public void testFind() {
        assertEquals(validCharacter, characterAttributeDAOImpl.find(validCharacter).getCharacterName());
        assertEquals(null, characterAttributeDAOImpl.find(nonValidCharacter));
    }

    @Test
    @DisplayName("Tests if getAll() works correctly")
    public void testGetAll() {
        assertEquals(totalAmountOfCharacters, characterAttributeDAOImpl.getAll().size());
        for (PlayerProperties playerProperty : characterAttributeDAOImpl.getAll()) {
            assertEquals(playerProperty.getCharacterName(),
                    characterAttributeDAOImpl.find(playerProperty.getCharacterName()).getCharacterName(),
                    "The CharacterAttributeDAO did not find correct PlayerProperty");
            assertEquals(playerProperty.getLength(),
                    characterAttributeDAOImpl.find(playerProperty.getCharacterName()).getLength(),
                    "The CharacterAttributeDAO did not find correct PlayerProperty");
            assertEquals(playerProperty.getWidth(),
                    characterAttributeDAOImpl.find(playerProperty.getCharacterName()).getWidth(),
                    "The CharacterAttributeDAO did not find correct PlayerProperty");
            assertEquals(playerProperty.getSpeed(),
                    characterAttributeDAOImpl.find(playerProperty.getCharacterName()).getSpeed(),
                    "The CharacterAttributeDAO did not find correct PlayerProperty");
            assertEquals(playerProperty.getWeight(),
                    characterAttributeDAOImpl.find(playerProperty.getCharacterName()).getWeight(),
                    "The CharacterAttributeDAO did not find correct PlayerProperty");
            assertEquals(playerProperty.getActionProperties().size(),
                    characterAttributeDAOImpl.find(playerProperty.getCharacterName()).getActionProperties().size(),
                    "The CharacterAttributeDAO did not find correct PlayerProperty");
        }
    }
}
