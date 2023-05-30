package com.bjpowernode.factoryTest;

import com.bjpowernode.entity.Student;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentFactoryTest {

    private static ApplicationContext context = null;

    @BeforeClass
    public static void createContext(){
        String resource = "appplicationFactory.xml";
        context = new ClassPathXmlApplicationContext(resource);

    }

    @Test
    public void getInstance(){
        Student mark = context.getBean("mark", Student.class);
        System.out.println(mark);
    }

    @Test
    public void getInstance2(){
        Student jane = context.getBean("jane", Student.class);
        System.out.println(jane);
    }

    @Test
    public void autoFactory(){
        Student student = context.getBean("student", Student.class);
        System.out.println(student);
    }
}
