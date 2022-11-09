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

/**
 * The service implementation.
 */

@RestController
public class ServerController {
    
    private UserDAO dao = new UserDAOImpl();

    @Autowired
    private ServerService serverService;

    // @GetMapping(path = "/user/{id}")
    // public AbstractTodoList getTodoList(@PathVariable("id") String id) {
    //     dao.findUser()
    //     checkTodoList(todoList, name);
    //     return todoList;
    // }

  

}
