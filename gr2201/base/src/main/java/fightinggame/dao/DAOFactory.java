package fightinggame.dao;

import fightinggame.users.User;
import fightinggame.users.UserId;
import fightinggame.game.PlayerProperties;
import fightinggame.utils.CharacterInformationObject;

/**
 * The class {@code DAOFactory} will create wanted DAO for other classes.
 */
public class DAOFactory {
    /**
     * Will get the DAO for users. This DAO can do all the CRUD
     * actions on the users in data storage.
     * 
     * @return UserDaoImplemention.
     */
    public static CRUDDAO<User, UserId> getUserDAO() {
        return new UserDAOImpl();
    }

    /**
     * Get DAO for reading {@code PlayerProperties} from data storage.
     * 
     * @return CharacterAttributeDAOImpl
     */
    public static RODAO<PlayerProperties, String> getCharacterAttributeDAO() {
        return new CharacterAttributeDAOImpl();
    }

    /**
     * Get DAO for reading {@code CharacterInformationObject} from data storage.
     * 
     * @return CharacterInformationDAOImpl
     */
    public static RODAO<CharacterInformationObject, String> getCharacterInfoDAO() {
        return new CharacterInformationDAOImpl();
    }
}

