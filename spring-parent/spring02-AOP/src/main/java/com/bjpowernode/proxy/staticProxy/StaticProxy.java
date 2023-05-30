package com.bjpowernode.proxy.staticProxy;

import com.bjpowernode.serviceProxy.UserService;
import com.bjpowernode.serviceProxy.impl.UserServiceImpl;

/**
 * 代理类要和目标对象实现同一个接口;
 * 实现原理: 相当于又写了一个类,重写接口的方法
 */
public class StaticProxy implements UserService {
    private UserServiceImpl userService;

    public StaticProxy(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public int save() {
        System.out.println("save()...");
        return userService.save();
    }

    @Override
    public void update() {
        System.out.println("update()...");
        userService.update();
    }

    @Override
    public void remove() {
        System.out.println("remove()...");
        userService.remove();
    }

    @Override
    public void select() {
        System.out.println("select()...");
        userService.select();
    }
}
