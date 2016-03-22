package by.andrew.zenov.rest.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import by.andrew.zenov.IUserController;
import by.andrew.zenov.data.model.User;
import by.andrew.zenov.service.UserService;

/**
 * Created by Андрей on 10.03.2016.
 */

@RestController(value = "userController")
@RequestMapping("/users")
public class UserController implements IUserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public List<User> getUsers() {
		return userService.getAll();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		validateUser(user);
		userService.insert(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public User getUser(@PathVariable Long id) {
		User user = userService.get(id);
		validateUser(user, id);
		return user;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
		user.setId(id);
		validateUser(user, id);
		userService.update(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void delete(@PathVariable Long id) {
		User user = userService.get(id);
		validateUser(user, id);
		userService.delete(user);
	}

	private void validateUser(User user) {
		if (user == null) {
			throw new UserNotFoundException();
		}
	}

	private void validateUser(User user, Long id) {
		if (user == null) {
			throw new UserNotFoundException(id);
		}
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	class UserNotFoundException extends RuntimeException {

		public UserNotFoundException(Long userId) {
			super("could not find user with id '" + userId + "'.");
		}

		public UserNotFoundException() {
			super("could not find user");
		}
	}

}
