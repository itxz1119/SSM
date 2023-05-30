package com.bjpowernode.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Student {
    private String name;
    private Integer age;

    private Date birthday;
    private School school;


    public Student(String name, Integer age, School school) {
        System.out.println("有参构造执行了===========");
        this.name = name;
        this.age = age;
        this.school = school;
    }

    public Student() {
        System.out.println("Student无参构造执行了===========");
    }
}
