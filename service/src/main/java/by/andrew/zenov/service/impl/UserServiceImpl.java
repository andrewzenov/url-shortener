package by.andrew.zenov.service.impl;

import by.andrew.zenov.data.dao.UserDao;
import by.andrew.zenov.data.model.User;
import by.andrew.zenov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    @Transactional
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        userDao.update(user);
    }

}
