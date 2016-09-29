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
import utils.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    private TagController tagControllerMock;

    private List<Tag> tagList;

    @Before
    public void setup() throws Exception {

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(tagControllerMock).build();

        tagList = new ArrayList<>();

        for (long i = 1; i < 4; i++) {
            Tag tag = TestUtils.createTag(i);
            tagList.add(tag);
        }

        when(tagControllerMock.getTags()).thenReturn(tagList);

        when(tagControllerMock.getTag(0L)).thenReturn(tagList.get(0));
        when(tagControllerMock.getTag(1L)).thenReturn(tagList.get(1));
        when(tagControllerMock.getTag(2L)).thenReturn(tagList.get(2));

        doNothing().when(tagControllerMock).delete(anyLong());

//        doNothing().when(tagControllerMock).createTag(newTag);

    }

    @Test
    public void getTagsTest() throws Exception {

        mockMvc.perform(get("/tags"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("tag_1_title")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("tag_2_title")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].title", is("tag_3_title")));

        verify(tagControllerMock, times(1)).getTags();
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

        verify(tagControllerMock, times(tagList.size())).getTag(anyLong());

    }

    @Test
    public void deleteTagByIdTest() throws Exception {
        mockMvc.perform(delete("/tags/{id}", Long.valueOf((long) Math.random())))
                .andExpect(status().isOk());
        verify(tagControllerMock, times(1)).delete(anyLong());
    }

//    @Test
//    public void createTagTest(Tag tag) throws Exception {
//
//        mockMvc.perform(post("/tags/create/"));
//
//        verify(tagControllerMock, times(1)).createTag(any());
//    }

}
