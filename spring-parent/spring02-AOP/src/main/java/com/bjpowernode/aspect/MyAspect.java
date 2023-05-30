package com.bjpowernode.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
    //定义切入点
    @Pointcut(value = "execution(* *..serviceAspect..*.*(..))")
    public void myPointcut(){
        //无需代码
    }


    //访问权限(可省略) 方法返回值 方法声明(参数) 异常类型(可省略)
    //@Before(value = "execution(* com.bjpowernode.serviceAspect.impl.StudentServiceImpl.doSome(..))")
    //@Before(value = "execution(* com.bjpowernode.serviceAspect..*.doSome(..))")
    //@Before(value = "execution(* *..serviceAspect.impl.StudentServiceImpl.doSome(..))")
    //@Before(value = "execution(* *..serviceAspect..*.doSome(..))")
    //@Before(value = "execution(* *..serviceAspect..*.do*(..))")
    @Before(value = "execution(* *..serviceAspect..*Impl.*(..))")
    public void myBefore(JoinPoint joinPoint){
        //可以拿到目标方法的方法名
        System.out.println("前置通知=======" + joinPoint.getSignature().getName());
    }

   /* 后置通知, 不会改变目标方法的返回结果;方法抛出异常不会执行
        1.不写returning,且myAfterReturning方法没有参数时,可以用于任何方法上;
        2.写returning,且myAfterReturning方法有参数时,只能用在有返回值的方法,否则会报错;
     */
    @AfterReturning(value = "execution(* *..serviceAspect..*.doAny(..))", returning = "result")
    public int myAfterReturning(Object result){
        int i = (int) result;
        System.out.println("后置通知,获取方法的返回值" + i);
        return i+2;
    }
    /*
    * 最终通知,抛出异常也会执行
    * */
    @After(value = "myPointcut()")
    public void myAfterReturning2(){
        System.out.println("后置通知,抛出异常也执行");
    }

    /*
    * 环绕通知,增强方法有 ProceedingJoinPoint参数;
    * 可以改变目标方法的返回结果,
    * 若目标方法有返回值，则该方法的返回值就是目标方法的返回值
    * */
    @Around(value = "execution(* *..serviceAspect..*.doOther(..))")
    public int myAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕通知,在目标方法执行之前执行");
        //调用目标方法
        int i = (int) pjp.proceed();
        System.out.println("环绕通知,在目标方法执行之后执行");
        return i+2;
    }
    /*
    * 可以使用在没有返回值的目标方法上,该方法有没有返回值都可以
    * */
    @Around(value = "execution(* *..serviceAspect..*.doSome(..))")
    public void myAround2(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕通知,在目标方法执行之前执行");
        //调用目标方法,如果方法抛出异常,后置通知不会执行
        pjp.proceed();
        System.out.println("环绕通知,在目标方法执行之后执行");
    }

    @AfterThrowing(value = "execution(* *..serviceAspect..*.*(..))", throwing = "e")
    public void exception(JoinPoint joinPoint, Exception e){
        System.out.println(joinPoint.getSignature().getName() + "方法出现异常," + e.getMessage());
    }
}
