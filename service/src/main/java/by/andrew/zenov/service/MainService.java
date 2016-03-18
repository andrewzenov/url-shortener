package by.andrew.zenov.service;

import by.andrew.zenov.data.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainService {


    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("service-spring-context.xml");

        UserService userService = (UserService) ctx.getBean("userService");

        User user = new User();

        user.setLogin("log123");
        user.setPassword("123");
        user.setEmail("email");

        System.out.println(user);

        userService.insert(user);

        List<User> users = userService.getAll();
        System.out.println(users);
    }

}

