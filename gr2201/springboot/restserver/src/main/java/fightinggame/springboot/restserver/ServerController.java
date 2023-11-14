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

import fightinggame.users.User;
import fightinggame.users.UserId;

/**
 * The {@code ServerController} to the server rest api. The
 * {@code ServerController} maps all the allowed checkpoints for the server.
 * It will handle all HTTP request made to server and delegate response to
 * service classes.
 */
@RestController
@RequestMapping(ServerController.API_USER_SERVICE_PATH)
public class ServerController {
    public static final String API_USER_SERVICE_PATH = "api/v1/user";

    @Autowired
    private ServerService serverService;

    /**
     * The {@code Serverservice} that provide the {@code ServerController} with
     * logic for getting, updating, deleting and adding Users to data base.
     * 
     * @return Serverservice
     */
    public ServerService getServerService() {
        return serverService;
    }

    /**
     * Get the user in data storage that has matching username and password
     * 
     * @param username to match with users in data base
     * @param password to match with users in data base
     * @return the user with same username and password or null
     */
    @GetMapping(path = "/{username}/{password}")
    public User getUser(@PathVariable("username") String username, @PathVariable("password") String password) {
        return getServerService().getUser(username, password);
    }

    /**
     * Add the user in data storage that has matching username and password
     * 
     * @param username to match with users in data base
     * @param password to match with users in data base
     * @return the user with same username and password or null
     */
    @PostMapping(path = "/{username}")
    public User postUser(@RequestParam("userData") String userData) {
        String username = userData.split("\\.")[0];
        String password = userData.split("\\.")[1];
        String confirmPassword = userData.split("\\.")[2];
        return getServerService().postUser(username, password, confirmPassword);
    }

    /**
     * Update user in data base if corresponding id can be found
     * 
     * @param id   the id to check with
     * @param user to change the user data with
     * @return whether the update was successful
     */
    @PutMapping(path = "/{id}")
    public boolean putUser(@PathVariable("id") String id, @RequestBody String password) {
        return getServerService().updateUser(id, password);
    }

    /**
     * Delete user in db if corrensponding id can be found
     * @param id  the id to check with
     * @return whether the deletion was successful
     */
    @DeleteMapping(path = "/{id}")
    public boolean deleteUser(@PathVariable("id") String id) {
        return getServerService().deleteUser(new UserId(id));
    }

}