package com.bjpowernode.entityTest;

import com.bjpowernode.controller.StudentController;
import com.bjpowernode.entity.Student;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentTest {

    private static ApplicationContext context = null;


    @BeforeClass
    public static void create() {
        String resource = "applicationContext.xml";
        context = new ClassPathXmlApplicationContext(resource);
    }

    @Test
    public void setStudent(){
        Student student = context.getBean("student", Student.class);
        System.out.println(student);
    }

    @Test
    public void findStu(){
        Student student = context.getBean("student", Student.class);
        StudentController studentController = context.getBean(StudentController.class);
        studentController.save(student);
    }




}
