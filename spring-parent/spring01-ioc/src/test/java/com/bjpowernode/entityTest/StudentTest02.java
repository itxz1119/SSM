package com.bjpowernode.entityTest;

import com.bjpowernode.entity.Student;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentTest02 {

    private static Student student = null;

    @BeforeClass
    public static void create() {
        String resource = "applicationContext2.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(resource);
        student = (Student) context.getBean("myStudent");
    }
    @Test
    public void setStudent(){
        System.out.println(student);

    }
}
