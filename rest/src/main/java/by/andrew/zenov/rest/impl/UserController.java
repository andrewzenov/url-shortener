package by.andrew.zenov.rest.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.andrew.zenov.IUserController;
import by.andrew.zenov.data.model.User;
import by.andrew.zenov.service.UserService;
import by.andrew.zenov.util.RestUtil;

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
		userService.insert(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
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
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void delete(@PathVariable Long id) {
		User user = userService.get(id);
		RestUtil.validation(User.class, user, id);
		userService.delete(user);
	}

}
