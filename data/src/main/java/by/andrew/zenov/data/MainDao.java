package by.andrew.zenov.data;

import by.andrew.zenov.data.dao.UserDao;
import by.andrew.zenov.data.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainDao {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("data-spring-context.xml");

        UserDao userDao = (UserDao) ctx.getBean("userDao");

        User user1 = new User();
        user1.setLogin("login");
        user1.setPassword("123");
        user1.setEmail("email");

        System.out.println(user1);

        User user = userDao.get(1L);
        System.out.println(user);

        List<User> users = userDao.getAll();
        System.out.println(users);
    }
}
