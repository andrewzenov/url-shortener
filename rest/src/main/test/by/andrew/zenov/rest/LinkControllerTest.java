package by.andrew.zenov.rest;

import by.andrew.zenov.data.model.Link;
import by.andrew.zenov.rest.impl.LinkController;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Андрей on 28.09.2016.
 */

@ContextConfiguration(locations = "classpath:rest-spring-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class LinkControllerTest {

    private static MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    private MockMvc mockMvc;

    @Mock
    private LinkController linkControllerMock;

    private List<Link> linkList;

    @Before
    public void setup() throws Exception {

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(linkControllerMock).build();

        linkList = new ArrayList<>();

        for (long i = 0; i < 3; i++) {
            Link link = TestUtils.createLink("" + i, i, i);
            linkList.add(link);
        }

        when(linkControllerMock.getLinks()).thenReturn(linkList);

        for (long i = 0; i < linkList.size(); i++) {
            when(linkControllerMock.getLink("" + i)).thenReturn(linkList.get((int) i));
//            when(linkControllerMock.getUserLinks(i)).thenReturn(linkList.get((int) i).getLinks());
        }

        doNothing().when(linkControllerMock).delete(anyString());

    }

    @Test
    public void getLinksTest() throws Exception {
        mockMvc.perform(get("/links"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].shortUrl", is("0")))
                .andExpect(jsonPath("$[0].sourceUrl", is("source for 0")))
                .andExpect(jsonPath("$[0].description", is("description of 0")))
                .andExpect(jsonPath("$[0].clickCount", is(linkList.get(0).getClickCount().intValue())))
//                .andExpect(jsonPath("$[0].tags", is(linkList.get(0).getTags())))

                .andExpect(jsonPath("$[1].shortUrl", is("1")))
                .andExpect(jsonPath("$[1].sourceUrl", is("source for 1")))
                .andExpect(jsonPath("$[1].description", is("description of 1")))
                .andExpect(jsonPath("$[1].clickCount", is(linkList.get(1).getClickCount().intValue())))
//                .andExpect(jsonPath("$[1].tags", is(linkList.get(1).getTags())))

                .andExpect(jsonPath("$[2].shortUrl", is("2")))
                .andExpect(jsonPath("$[2].sourceUrl", is("source for 2")))
                .andExpect(jsonPath("$[2].description", is("description of 2")))
                .andExpect(jsonPath("$[2].clickCount", is(linkList.get(2).getClickCount().intValue())))
//                .andExpect(jsonPath("$[2].tags", is(linkList.get(2).getTags())))
        ;

        verify(linkControllerMock, times(1)).getLinks();
    }

    public void getLinksByTagTest() throws Exception {

        for (long i = 0; i < linkList.size(); i++) {
            mockMvc.perform(get("/links/tag/{id}", i))
                    .andExpect(status().isOk());
        }
    }
}
