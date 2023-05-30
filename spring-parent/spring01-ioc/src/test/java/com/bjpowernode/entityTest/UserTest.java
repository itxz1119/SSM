package com.bjpowernode.entityTest;

import com.bjpowernode.entity.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {

    private static User user = null;

    @BeforeClass
    public static void createClass(){
        String resource = "applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(resource);
        user = (User) context.getBean("myUser");
    }

    @Test
    public void user(){
        System.out.println(user);
    }

}
