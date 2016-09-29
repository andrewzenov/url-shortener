package by.andrew.zenov.rest.impl;

import by.andrew.zenov.IUserController;
import by.andrew.zenov.data.model.Link;
import by.andrew.zenov.data.model.User;
import by.andrew.zenov.service.UserService;
import by.andrew.zenov.util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController(value = "userController")
@RequestMapping("/users")
public class UserController implements IUserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    public List<User> getUsers() {
        return userService.getAll();
    }

    @Override
    @RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        RestUtil.validation(User.class, user);
        user.setId(null);
        userService.insert(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public User getUser(@PathVariable Long id) {
        User user = userService.get(id);
        RestUtil.validation(User.class, user, id);
        return user;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        RestUtil.validation(User.class, user, id);
        user.setId(id);
        userService.update(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void delete(@PathVariable Long id) {
        User user = userService.get(id);
        RestUtil.validation(User.class, user, id);
        userService.delete(user);
    }

    @Override
    @RequestMapping(value = "/{id}/links", method = RequestMethod.GET, headers = "Accept=application/json")
    public Set<Link> getUserLinks(@PathVariable Long id) {
        User user = userService.get(id);
        RestUtil.validation(User.class, user, id);
        Set<Link> links = user.getLinks();
        return links;
    }

}