package by.andrew.zenov;

import by.andrew.zenov.data.model.User;

import java.util.List;

import org.springframework.http.ResponseEntity;

/**
 * Created by Андрей on 10.03.2016.
 */
public interface IUserController {

	public List<User> getUsers();

	public User getUser(Long id);

	public ResponseEntity<User> update(Long id, User user);

	public void delete(Long id);

	public ResponseEntity<User> createUser(User user);

}
