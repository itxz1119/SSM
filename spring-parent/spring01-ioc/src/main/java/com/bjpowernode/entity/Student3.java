package com.bjpowernode.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Data
public class Student3 {
    private String name;
    private Integer age;

    private Date birthday;
    private List list;
    private Map<String,Object> map;
    private String[] array;
    private Properties prop;



    public Student3(String name, Integer age) {
        System.out.println("有参构造执行了===========");
        this.name = name;
        this.age = age;
    }

    public Student3() {
        System.out.println("Student无参构造执行了===========");
    }
}
