package by.andrew.zenov.rest;

import by.andrew.zenov.data.model.Tag;
import by.andrew.zenov.service.TagService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Андрей on 05.04.2016.
 */

@ContextConfiguration(locations = "classpath:rest-spring-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TagControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private TagService tagServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private List<Tag> tagList;


    @Before
    public void setup() throws Exception {
        tagServiceMock = Mockito.mock(TagService.class);
        Mockito.reset(tagServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        tagList = new ArrayList<>();

        Tag tag1 = new Tag();
        tag1.setId(1L);
        tag1.setTitle("first-tag");

        Tag tag2 = new Tag();
        tag2.setId(2L);
        tag2.setTitle("second-tag");

        Tag tag3 = new Tag();
        tag3.setId(3L);
        tag3.setTitle("third-tag");

        tagList.add(tag1);
        tagList.add(tag2);
        tagList.add(tag3);

        when(tagServiceMock.getAll()).thenReturn(tagList);

    }

    @Test
    public void test() throws Exception {
        mockMvc.perform(get("/tags"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect((ResultMatcher) jsonPath("$", 2))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("first-tag")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("second-tag")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].title", is("third-tag")));

    }

//
//    protected String json(Object o) throws IOException {
//        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
//        this.mappingJackson2HttpMessageConverter.write(
//                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
//        return mockHttpOutputMessage.getBodyAsString();
//    }

}
