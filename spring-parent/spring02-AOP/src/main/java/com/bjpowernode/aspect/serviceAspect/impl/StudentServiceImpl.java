package com.bjpowernode.aspect.serviceAspect.impl;

import com.bjpowernode.aspect.serviceAspect.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public void doSome(String name, int age) {
        //int i = 10/0;
        System.out.println("doSome(String name, int age)方法执行");
    }

    @Override
    public void doSome() {
        System.out.println("doSome()方法执行");
    }

    @Override
    public int doAny(int id) {
        System.out.println("doAny(int id)方法执行");
        return id;
    }

    @Override
    public int doOther(int id) {
        System.out.println("doOther(int id)方法执行");
        return id;
    }
}
