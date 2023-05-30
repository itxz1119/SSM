package com.bjpowernode.factory;

import com.bjpowernode.entity.Student;

public class StudentFactory {

    public static Student getInstance(){
        Student mark = new Student("mark", 20, null);
        return mark;
    }
    public  Student getInstance2(){
        Student jane = new Student("jane", 26, null);
        return jane;
    }

}
