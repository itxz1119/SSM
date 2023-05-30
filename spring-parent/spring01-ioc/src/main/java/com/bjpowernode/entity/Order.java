package com.bjpowernode.entity;

import lombok.Data;

@Data
public class Order {
    private String prodName;
    private Integer money;

    public Order(String prodName, Integer money) {
        this.prodName = prodName;
        this.money = money;
    }

    public Order() {
        System.out.println("Order无参构造执行了===");
    }
}
