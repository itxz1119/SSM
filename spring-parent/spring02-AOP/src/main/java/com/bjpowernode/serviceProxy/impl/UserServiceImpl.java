package com.bjpowernode.serviceProxy.impl;

import com.bjpowernode.serviceProxy
        .UserService;

public class UserServiceImpl implements UserService {
    @Override
    public int save() {
        System.out.println("增加方法...");
        return 1;
    }

    @Override
    public void update() {
        System.out.println("修改...");
    }

    @Override
    public void remove() {
        System.out.println("删除方法...");
    }

    @Override
    public void select() {
        System.out.println("查询方法...");
    }
}
