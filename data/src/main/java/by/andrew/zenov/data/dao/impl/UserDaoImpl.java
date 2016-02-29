package by.andrew.zenov.data.dao.impl;

import by.andrew.zenov.data.dao.UserDao;
import by.andrew.zenov.data.model.User;
import org.springframework.stereotype.Repository;

@Repository(value = "userDao")
public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {

    public UserDaoImpl() {
        setEntityClass(User.class);
    }
}
