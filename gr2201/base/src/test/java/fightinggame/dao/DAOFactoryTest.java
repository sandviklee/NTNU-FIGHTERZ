package fightinggame.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DAOFactoryTest {

    @Test
    @DisplayName("Check if all getters give correct instances")
    public void DAOFactoryGetters() {

        assertEquals(CharacterAttributeDAOImpl.class, DAOFactory.getCharacterAttributeDAO().getClass(),
                "Wrong class was initiated");
        assertEquals(CharacterInformationDAOImpl.class, DAOFactory.getCharacterInfoDAO().getClass(),
                "Wrong class was initiated");
        assertEquals(UserDAOImpl.class, DAOFactory.getUserDAO().getClass(),
                "Wrong class was initiated");
    }
}
