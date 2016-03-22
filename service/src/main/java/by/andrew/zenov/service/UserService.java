package by.andrew.zenov.service;

import java.util.List;

import by.andrew.zenov.data.model.User;

public interface UserService {

    User get(Long id);

    List<User> getAll();

    void delete(Long id);
    
    void delete(User user);

    void insert(User user);

    void update(User user);

}
