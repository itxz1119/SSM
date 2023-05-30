package com.bjpowernode.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {


    /**
     * @param proxy  代理对象
     * @param method 目标方法
     * @param args   目标方法的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK代理对象" + method.getName());
        //调用目标方法  反射;
        //必须要有返回值,否则会报空指针异常
        Object result = method.invoke(targetClass.newInstance(), args);
        return result;
    }

    private Class targetClass;
    public JDKProxy(Class targetClass) {
        this.targetClass = targetClass;
    }

    public JDKProxy() {

    }

    /*
     * 获取代理对象方法
     * */
    public Object getProxy(Class targetClass) {
        this.targetClass = targetClass;
        /*
         * 获取代理对象Proxy.newProxyInstance()
         * targetClass.getClassLoader():目标类的类加载器
         * Class<?>[] interfaces 目标类的父接口类型数组
         * InvocationHandler h 代理类对象
         * */
        return Proxy.newProxyInstance(targetClass.getClassLoader(),
                targetClass.getInterfaces(), this);
    }


}
