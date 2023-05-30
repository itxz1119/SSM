package com.bjpowernode.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class School {
    @Value("郑州大学")
    private String name;
    @Value("郑州")
    private String address;

    public School(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public School() {
        System.out.println("School无参构造方法执行了====");
    }
}
