package com.bjpowernode.entity;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private Order order;

    public User(String username, String password) {
        System.out.println("User有参构造执行了===========");
        this.username = username;
        this.password = password;
    }

    public User() {
        System.out.println("User无参构造执行了===========");
    }
}
