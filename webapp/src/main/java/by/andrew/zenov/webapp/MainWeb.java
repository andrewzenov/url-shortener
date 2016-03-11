package by.andrew.zenov.webapp;

import by.andrew.zenov.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by Андрей on 11.03.2016.
 */
@Component(value = "web")
public class MainWeb {

    @Autowired
    private UserRestService restService;

    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("spring-context.xml");
        UserRestService userRestService= (UserRestService) context.getBean("userRestService");
        System.out.println(userRestService.getClass().getSimpleName());
        System.out.println(userRestService.getUsers());
        MainWeb mainWeb= (MainWeb) context.getBean("web");
        System.out.println(mainWeb.checAutowired());
    }

    public boolean checAutowired(){
        boolean result=true;
        try{
            restService.getUsers();
        }catch (Exception e){
            e.printStackTrace();
            result=false;
        }
        return result;
    }

}
