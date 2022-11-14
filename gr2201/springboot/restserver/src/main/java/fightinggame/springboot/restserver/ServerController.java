package fightinggame.springboot.restserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fightinggame.users.User;
import fightinggame.users.UserId;

/**
 * The ServerController to the server rest api.
 */

@RestController
@RequestMapping(ServerController.API_USER_SERVICE_PATH)
public class ServerController {
    public static final String API_USER_SERVICE_PATH = "api/v1/user";

    @Autowired
    private ServerService serverService;

    /**
     * The Serverservice that gets all the methods required to change db
     * @return Serverservice
     */
    public ServerService getServerService() {
        return serverService;
    }

    /**
     * Get the user in data storage that has matching username and password
     * @param username  to match with users in db
     * @param password  to match with users in db
     * @return the user with same username and password or null
     */
    @GetMapping(path = "/{username}/{password}")
    public User getUser(@PathVariable("username") String username, @PathVariable("password") String password) {
        return getServerService().getUser(username, password);
    }

    /**
     * Get the user in data storage that has matching username and password
     * @param username  to match with users in db
     * @param password  to match with users in db
     * @return the user with same username and password or null
     */
    @PostMapping(path = "/{username}/{password}/{confirmPassword}")
    public User postUser(@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("confirmPassword") String confirmPassword) {
        return getServerService().postUser(username, password, confirmPassword);
    }

    /**
     * Update user in db if corrensponding id can be found
     * @param id    the id to check with    
     * @param user  to change the user data with
     * @return whether the update was successful
     */
    @PutMapping(path = "/{id}")
    public boolean putUser(@PathVariable("id") UserId id, @RequestBody User user) {
        return getServerService().updateUser(id, user);
    }

    /**
     * Delete user in db if corrensponding id can be found
     * @param id  the id to check with
     * @return whether the deletion was successful
     */
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable("id") UserId id) {
        return getServerService().deleteUser(id);
    }

}
