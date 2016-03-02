package by.andrew.zenov.data.test;

import by.andrew.zenov.data.TestUtil;
import by.andrew.zenov.data.dao.UserDao;
import by.andrew.zenov.data.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@ContextConfiguration(locations = "classpath:data-spring-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestUserDao {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TestUtil testUtil;

    @Test
    @Transactional
    @Rollback(true)
    public void testInsertUser() {
        User user = testUtil.createUser();

        int startCount = userDao.getAll().size();
        userDao.insert(user);
        int endCount = userDao.getAll().size();

        Assert.assertEquals(1, endCount - startCount);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetById() {
        User user = testUtil.createUser();
        userDao.insert(user);
        User userDb = userDao.getAll().get(0);
        Long id = userDb.getId();

        Assert.assertEquals(userDb, userDao.get(id));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetAll() {

        Set<String> loginSet = new HashSet<>();
        int size = testUtil.getRandomInteger(0, 100);

        User user;
        for (int i = 0; i < size; i++) {
            user = testUtil.createUser();
            if (loginSet.add(user.getLogin())) {
                userDao.insert(user);
            }
        }

        Assert.assertEquals(loginSet.size(), userDao.getAll().size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateUser() {
        User user = testUtil.createUser();
        userDao.insert(user);

        String login = testUtil.getRandomString(20);
        String password = testUtil.getRandomString(40);
        String email = testUtil.getRandomString(60);

        User userDb = userDao.getAll().get(0);
        userDb.setLogin(login);
        userDb.setPassword(password);
        userDb.setEmail(email);

        userDao.update(userDb);

        User userDb2 = userDao.getAll().get(0);

        Assert.assertEquals(userDb, userDb2);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDeleteUser() {
        User user = testUtil.createUser();
        userDao.insert(user);
        int size = userDao.getAll().size();
        User userDb = userDao.getAll().get(0);
        userDao.delete(userDb);

        Assert.assertEquals(size - 1, userDao.getAll().size());
    }

}
