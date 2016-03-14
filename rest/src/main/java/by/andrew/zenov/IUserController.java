package by.andrew.zenov;

import by.andrew.zenov.data.model.User;

import java.util.List;

/**
 * Created by Андрей on 10.03.2016.
 */
public interface IUserController {

    public List<User> getUsers();

    public User getUser(Long id);

    public String getHello();
}
