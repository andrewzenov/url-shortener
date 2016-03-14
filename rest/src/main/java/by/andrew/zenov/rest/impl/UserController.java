package by.andrew.zenov.rest.impl;

import by.andrew.zenov.IUserController;
import by.andrew.zenov.data.model.User;
import by.andrew.zenov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Андрей on 10.03.2016.
 */


@RestController
public class UserController implements IUserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/first", method = RequestMethod.GET, headers = "Accept=application/json")
    public String getHello() {
        return userService.get(1L).getLogin();
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<User> getUsers() {
        return userService.getAll();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public User getUser(@PathVariable Long id) {
        return userService.get(id);
    }

    @RequestMapping(value = "/str", method = RequestMethod.GET, headers = "Accept=application/json")
    public String getString() {
        return "Hello. This is class UserRestServiceImp and method getString";
    }

}
