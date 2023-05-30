package com.bjpowernode.factory;

import com.bjpowernode.entity.Student;
import org.springframework.beans.factory.FactoryBean;

public class StudentFactory2 implements FactoryBean<Student> {


    @Override
    public Student getObject() throws Exception {
        Student student = new Student("mary", 29, null);
        return student;
    }

    @Override
    public Class<?> getObjectType() {
        return Student.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
