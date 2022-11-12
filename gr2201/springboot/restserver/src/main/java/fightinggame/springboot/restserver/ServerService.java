package fightinggame.springboot.restserver;

import org.springframework.stereotype.Service;

import fightinggame.dbaccess.UserDAO;
import fightinggame.dbaccess.UserDAOImpl;
import fightinggame.users.LoginSignup;
import fightinggame.users.User;
import fightinggame.users.UserId;


@Service
public class ServerService {
    private UserDAO dao = new UserDAOImpl();
    
    /**
     * Try to get user from data storage
     * @param username  of the corrensponding user
     * @param password  of the corrensponding user
     * @return user or null
     */
    public User getUser(String username, String password) {
        return LoginSignup.logIn(username, password);
    }

    /**
     * Adds a new user from data storage
     * @param username         of the new user
     * @param password         of the new user
     * @param confirmPassword  of the new user
     * @return the added user
     */
    public User postUser(String username, String password, String confirmPassword) {
        return LoginSignup.signUp(username, password, confirmPassword);
    }

    /**
     * Update user from data storage
     * @param id    of the user to update
     * @param user  of the new user values to change data storage
     * @return whether or not user was updated
     */
    public boolean updateUser(UserId id, User user){
        return dao.updateUser(id, user.getUserData());
    }
    /**
     * Delete user from data storage
     * @param id  of the user 
     * @return whether or not user was updated
     */
    public boolean deleteUser(UserId id){
        return dao.deleteUser(id);
    }
}
