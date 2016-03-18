package by.andrew.zenov.rest.impl;

import by.andrew.zenov.IUserController;
import by.andrew.zenov.data.model.User;
import by.andrew.zenov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

/**
 * Created by Андрей on 10.03.2016.
 */

@RestController(value = "userController")
@RequestMapping("/users")
public class UserController implements IUserController {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("rest-spring-context.xml");
		UserController controller = (UserController) context.getBean("userController");
		User user = new User();
		user.setLogin("11");
		user.setPassword("11");
		user.setEmail("11");

		controller.add(user);
	}

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/first", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getHello() {
		return userService.get(1L).getLogin();
	}

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public List<User> getUsers() {
		return userService.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public User getUser(@PathVariable Long id) {
		User user = userService.get(id);
		validateUser(user, id);
		return user;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {

		if (user != null) {
			user.setEmail("notNull");
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/str", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getString() {
		return "Hello. This is class UserRestServiceImp and method getString";
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> add(@RequestBody User user) {
		// userService.insert(user);
		if (user != null) {
			user.setEmail(user.getEmail() + "!");
		}
		HttpHeaders httpHeaders = new HttpHeaders();
		// httpHeaders.setLocation(ServletUriComponentsBuilder
		// .fromCurrentRequest().path("/login")
		// .buildAndExpand(user.getLogin()).toUri());
		return new ResponseEntity<>(user, httpHeaders, HttpStatus.OK);
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
	}
}
