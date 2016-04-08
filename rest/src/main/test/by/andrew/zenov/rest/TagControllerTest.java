package by.andrew.zenov.rest;

import by.andrew.zenov.data.model.Tag;
import by.andrew.zenov.service.TagService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Андрей on 05.04.2016.
 */

@ContextConfiguration(locations = "classpath:rest-spring-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TagControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
//
    private MockMvc mockMvc;
//
    private HttpMessageConverter mappingJackson2HttpMessageConverter;
//
//    private List<Tag> tagList = new ArrayList<>();
//
//    @Autowired
//    private TagService tagService;
//
    @Autowired
    private WebApplicationContext webApplicationContext;



    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
////
    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
//
//        for (Tag t : this.tagService.getAll()
//                ) {
//            tagService.delete(t);
//        }
//
//        Tag tag1=new Tag();
//        tag1.setId(1L);
//        tag1.setTitle("first-tag");
//
//        Tag tag2=new Tag();
//        tag2.setId(2L);
//        tag2.setTitle("second-tag");
//
//        Tag tag3=new Tag();
//        tag3.setId(3L);
//        tag3.setTitle("third-tag");
//
//        tagList.add(tag1);
//        tagList.add(tag2);
//        tagList.add(tag3);
//
         }

    @Test
    public void test(){}

//
//    protected String json(Object o) throws IOException {
//        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
//        this.mappingJackson2HttpMessageConverter.write(
//                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
//        return mockHttpOutputMessage.getBodyAsString();
//    }

}
