package by.andrew.zenov.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Андрей on 08.04.2016.
 */
@ContextConfiguration(locations = "classpath:rest-spring-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRest {

    @Test
    public void test(){}
}
