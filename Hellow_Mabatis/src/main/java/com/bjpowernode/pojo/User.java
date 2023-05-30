package com.bjpowernode.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
public class User {
    private Integer id;
    private String bank_card;
    private String pwd;
    //name
    private String username;
    private Date create_date;
    private Date last_login_date;
    private Integer status;
    //private Order order;
    private List<Order> order;

    public User() {
    }


}
