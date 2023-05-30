package com.bjpowernode.proxy.dynamicProxy;

import lombok.Data;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Data
public class CglibProxy3 implements MethodInterceptor {

    private Class targetClass;

    public <T> T getProxy(Class targetClass){
        this.targetClass = targetClass;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib前置通知====");
        Object result = method.invoke(targetClass.newInstance(), objects);
        return result;
    }
}
