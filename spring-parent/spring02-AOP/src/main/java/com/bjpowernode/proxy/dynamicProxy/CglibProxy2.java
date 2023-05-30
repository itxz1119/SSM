package com.bjpowernode.proxy.dynamicProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy2 implements MethodInterceptor {

    private Class targetClass;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        try {
            System.out.println("前置通知===");
            Object result = method.invoke(targetClass.newInstance(), objects);
            System.out.println("后置通知===");
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T getProxy(Class targetClass){
        this.targetClass = targetClass;
        Enhancer enhancer = new Enhancer();
        //设置代理对象为目标类的子类
        enhancer.setSuperclass(targetClass);
        //回调函数,调用intercept()方法
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }
}
