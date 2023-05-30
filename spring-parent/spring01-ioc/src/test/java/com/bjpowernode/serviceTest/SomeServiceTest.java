package com.bjpowernode.serviceTest;

import com.bjpowernode.service.SomeService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SomeServiceTest {
   private static SomeService someService = null;

    @BeforeClass
    public static void create() {
        String resource = "applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(resource);
        //创建对象,执行SomeServiceImpl的无参构造方法
        someService = (SomeService) context.getBean("someService");
    }

    @Test
    public void doSome() {
        //执行业务方法
        someService.doSome();
    }
}
