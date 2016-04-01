package by.andrew.zenov;

import by.andrew.zenov.data.model.Link;
import by.andrew.zenov.data.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

public interface IUserController {

	public List<User> getUsers();

	public User getUser(Long id);

	public ResponseEntity<User> update(Long id, User user);

	public void delete(Long id);

	public ResponseEntity<User> createUser(User user);

	public Set<Link> getUserLinks(Long id);

}
