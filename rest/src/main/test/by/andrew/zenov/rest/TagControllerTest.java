package by.andrew.zenov.rest;

import by.andrew.zenov.data.model.Tag;
import by.andrew.zenov.rest.impl.TagController;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Андрей on 05.04.2016.
 */

@ContextConfiguration(locations = "classpath:rest-spring-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TagControllerTest {

    private static MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    private MockMvc mockMvc;

    @Mock
    private TagController tagServiceMock;

    private List<Tag> tagList;

    @Before
    public void setup() throws Exception {

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(tagServiceMock).build();

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

        Tag tag4 = new Tag();
        tag4.setId(4L);
        tag4.setTitle("fourth-tag");

        when(tagServiceMock.getTags()).thenReturn(tagList);

        when(tagServiceMock.getTag(0L)).thenReturn(tagList.get(0));
        when(tagServiceMock.getTag(1L)).thenReturn(tagList.get(1));
        when(tagServiceMock.getTag(2L)).thenReturn(tagList.get(2));

        doNothing().when(tagServiceMock).delete(anyLong());

//        when(tagServiceMock.createTag(any())).thenReturn(tag4);

    }

    @Test
    public void getTagsTest() throws Exception {

        mockMvc.perform(get("/tags"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("first-tag")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("second-tag")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].title", is("third-tag")));

        verify(tagServiceMock, times(1)).getTags();
    }

    @Test
    public void getTagByIdTest() throws Exception {

        for (int i = 0; i < tagList.size(); i++) {
            mockMvc.perform(get("/tags/{id}", i))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(contentType))
                    .andExpect(jsonPath("$.id", is(tagList.get(i).getId().intValue())))
                    .andExpect(jsonPath("$.title", is(tagList.get(i).getTitle())));
        }

        verify(tagServiceMock, times(tagList.size())).getTag(anyLong());

    }

    @Test
    public void deleteTagByIdTest() throws Exception {

        mockMvc.perform(delete("/tags/{id}", anyLong()))
                .andExpect(status().isOk());
        verify(tagServiceMock, times(1)).delete(anyLong());
    }

    @Test
    public void createTagTest() throws Exception {

        mockMvc.perform(post("/tags/create"))
                .andReturn();
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(contentType))
//                .andExpect(jsonPath("$.id", is(4)))
//                .andExpect(jsonPath("$.title", is("fourth-tag")));

        verify(tagServiceMock, times(1)).createTag(any());
    }

}
