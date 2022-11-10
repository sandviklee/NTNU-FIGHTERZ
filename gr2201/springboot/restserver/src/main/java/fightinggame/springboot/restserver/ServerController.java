package fightinggame.springboot.restserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fightinggame.dbaccess.UserDAO;
import fightinggame.dbaccess.UserDAOImpl;
import fightinggame.users.LoginSignup;
import fightinggame.users.User;

/**
 * The service implementation.
 */

@RestController
public class ServerController {
    
    private UserDAO dao = new UserDAOImpl();

    @Autowired
    private ServerService serverService;

    public ServerService getServerService() {
        return serverService;
    }


    /**
     * Get the user in data storage that has matching username and password
     * @param username  to match with users in db
     * @param password  to match with users in db
     * @return the user with same username and password or null
     */
    @GetMapping(path = "/user/{username}/{password}")
    public User getUser(@PathVariable("username") String username, @PathVariable("password") String password) {
        return LoginSignup.logIn(username, password);
    }

    /**
     * Get the user in data storage that has matching username and password
     * @param username  to match with users in db
     * @param password  to match with users in db
     * @return the user with same username and password or null
     */
    @GetMapping(path = "/user/{username}/{password}/{confirmPassword}")
    public User putUser(@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("confirmPassword") String confirmPassword) {
        return LoginSignup.signUp(username, password, confirmPassword);
    }





  


}
