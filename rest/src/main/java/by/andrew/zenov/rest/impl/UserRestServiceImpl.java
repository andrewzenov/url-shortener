package by.andrew.zenov.rest.impl;

import by.andrew.zenov.UserRestService;
import by.andrew.zenov.data.model.User;
import by.andrew.zenov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Андрей on 10.03.2016.
 */

@Service(value = "userRestService")
@Path("/users")
public class UserRestServiceImpl implements UserRestService {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("rest-spring-context.xml");

        UserService userService = (UserService) ctx.getBean("userService");
        System.out.println(userService.get(1L));
        UserRestService userRestService = (UserRestService) ctx.getBean("userRestService");
        System.out.println(userRestService.getHello());
    }

    @Autowired
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHello() {
        return userService.get(1L).getLogin();
    }

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        return userService.getAll();
    }

    @GET
    @Path("{id: \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") Long id) {
        return userService.get(id);
    }

    @GET
    @Path("/str")
    @Produces(MediaType.APPLICATION_JSON)
    public String getString() {
        return "Hello. This is class UserRestServiceImp and method getString";
    }

}
