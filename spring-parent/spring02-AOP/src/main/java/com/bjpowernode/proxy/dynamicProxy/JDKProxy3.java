package com.bjpowernode.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy3 implements InvocationHandler {

    private Class targetClass;

    public <T> T getProxy(Class targetClass){
        this.targetClass = targetClass;
        return (T) Proxy.newProxyInstance(targetClass.getClassLoader(), targetClass.getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前置通知====");
        Object result = method.invoke(targetClass.newInstance(), args);
        return result;
    }
}
