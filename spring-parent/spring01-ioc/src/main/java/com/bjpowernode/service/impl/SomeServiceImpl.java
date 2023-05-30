package com.bjpowernode.service.impl;

import com.bjpowernode.service.SomeService;

public class SomeServiceImpl implements SomeService {

    public SomeServiceImpl() {
        System.out.println("SomeServiceImpl无参构造执行了.......");
    }

    @Override
    public void doSome() {
        System.out.println("doSome方法======");
    }
}
