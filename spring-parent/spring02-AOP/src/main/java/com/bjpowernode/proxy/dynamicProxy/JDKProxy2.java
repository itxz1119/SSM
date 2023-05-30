package com.bjpowernode.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy2 implements InvocationHandler {

    private Class targetClass;

    public JDKProxy2(Class targetClass) {
        this.targetClass = targetClass;
    }

    public JDKProxy2() {

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前置通知====");
        //调用目标方法
        Object result = method.invoke(targetClass.newInstance(), args);
        //后置通知
        System.out.println("后置通知====");
        return result;
    }

    /*
    * 得到目标类对象
    * */
    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(targetClass.getClassLoader(), targetClass.getInterfaces(), this);
    }

}
