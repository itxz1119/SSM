package com.bjpowernode.proxy.dynamicProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        try {
            System.out.println("Cglib动态代理...前置");
            Object result = method.invoke(targetClass.newInstance(), objects);
            System.out.println("Cglib动态代理...返回" + result);
            return result;
        } catch (Exception e) {
            System.out.println("Cglib动态代理...异常通知");
            throw new RuntimeException(e);
        }finally {
            System.out.println("Cglib动态代理...最终通知");
        }
    }
    private Class targetClass;

    public <T> T getProxy(Class targetClass){
        this.targetClass = targetClass;
        Enhancer enhancer = new Enhancer();
        //设置代理对象为目标对象的一个子类
        enhancer.setSuperclass(targetClass);
        //设置回调，调用intercept方法
        enhancer.setCallback(this);
        //创建代理对象，并且返回
        return (T) enhancer.create();
    }

}
