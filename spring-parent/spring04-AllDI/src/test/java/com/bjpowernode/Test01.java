package com.bjpowernode;

import com.bjpowernode.config.ApplicationMybatis;
import com.bjpowernode.entity.Student;
import com.bjpowernode.service.StudentService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

public class Test01 {



    @Test
    public void dataSourceTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        System.out.println(dataSource);
    }

    @Test
    public void list() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationMybatis.class);
        StudentService studentService = context.getBean(StudentService.class);
        PageInfo<Student> studentPageInfo = studentService.pageList();
        System.out.println("总条数"+studentPageInfo.getTotal());
        System.out.println("总页数"+studentPageInfo.getPages());
        System.out.println("当前页"+studentPageInfo.getPageNum());
        System.out.println("=========");
        for (Student student : studentPageInfo.getList()) {
            System.out.println(student);
        }
    }

    @Test
    public void transfer(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentService studentService = context.getBean(StudentService.class);
        studentService.transfer(1,82,100.0);
    }
}
