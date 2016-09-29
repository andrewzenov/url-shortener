package by.andrew.zenov.rest;


import by.andrew.zenov.data.model.User;
import by.andrew.zenov.rest.impl.UserController;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import utils.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Андрей on 27.09.2016.
 */
@ContextConfiguration(locations = "classpath:rest-spring-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

    private static MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    private MockMvc mockMvc;

    @Mock
    private UserController userControllerMock;

    private List<User> userList;

    @Before
    public void setup() throws Exception {

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(userControllerMock).build();

        userList = new ArrayList<>();

        User user1 = TestUtils.createUser(1L);
        User user2 = TestUtils.createUser(2L);
        User user3 = TestUtils.createUser(3L);

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        when(userControllerMock.getUsers()).thenReturn(userList);

        for (long i = 0; i < userList.size(); i++) {
            when(userControllerMock.getUser(i)).thenReturn(userList.get((int) i));
            when(userControllerMock.getUserLinks(i)).thenReturn(userList.get((int) i).getLinks());
        }

        doNothing().when(userControllerMock).delete(anyLong());

    }

    @Test
    public void getUsersTest() throws Exception {

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].login", is("user_1")))
                .andExpect(jsonPath("$[0].password", is("password_1")))
                .andExpect(jsonPath("$[0].email", is("email_1")))

                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].login", is("user_2")))
                .andExpect(jsonPath("$[1].password", is("password_2")))
                .andExpect(jsonPath("$[1].email", is("email_2")))

                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].login", is("user_3")))
                .andExpect(jsonPath("$[2].password", is("password_3")))
                .andExpect(jsonPath("$[2].email", is("email_3")));

        verify(userControllerMock, times(1)).getUsers();
    }

    @Test
    public void getUserByIdTest() throws Exception {

        for (int i = 0; i < userList.size(); i++) {

            User user = userList.get(i);
            mockMvc.perform(get("/users/{id}", i))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(contentType))
                    .andExpect(jsonPath("$.id", is(user.getId().intValue())))
                    .andExpect(jsonPath("$.login", is(user.getLogin())))
                    .andExpect(jsonPath("$.password", is(user.getPassword())))
                    .andExpect(jsonPath("$.email", is(user.getEmail())));
        }
        verify(userControllerMock, times(userList.size())).getUser(anyLong());
    }

    @Test
    public void deleteUserByIdTest() throws Exception {

        mockMvc.perform(delete("/users/{id}", Long.valueOf((long) Math.random())))
                .andExpect(status().isOk());
        verify(userControllerMock, times(1)).delete(anyLong());
    }

    @Test
    public void getUserLinksTest() throws Exception {

        for (int i = 0; i < userList.size(); i++) {

            mockMvc.perform(get("/users/{id}/links", i))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(contentType))
                    .andExpect(jsonPath("$", Matchers.hasSize(1)));
        }
        verify(userControllerMock, times(userList.size())).getUserLinks(anyLong());
    }


}
