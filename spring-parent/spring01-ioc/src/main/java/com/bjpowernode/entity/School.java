package com.bjpowernode.entity;

import lombok.Data;

@Data
public class School {
    private String name;
    private String address;

    public School(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public School() {
        System.out.println("School无参构造方法执行了====");
    }
}
