package com.bjpowernode.entityTest;

import com.bjpowernode.entity.Student3;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentTest03 {

    private static ApplicationContext context = null;

    @BeforeClass
    public static void create() {
        String resource = "applicationContext3.xml";
        context = new ClassPathXmlApplicationContext(resource);

    }

    @Test
    public void setStudent() {
        Student3 student3 = context.getBean("student", Student3.class);
        System.out.println(student3);
    }
}
