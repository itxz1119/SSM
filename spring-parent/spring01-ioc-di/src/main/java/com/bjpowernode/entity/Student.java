package com.bjpowernode.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Data
//可以指定value(id的值),不写默认为类型首字母小写
@Component
public class Student {
    @Value("rose")
    private String name;
    @Value("26")
    private Integer age;
    //默认按类型自动装配
    //@Autowired
    //默认按属性名自动装配,注入失败会再次用 按类型注入
    @Resource
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
