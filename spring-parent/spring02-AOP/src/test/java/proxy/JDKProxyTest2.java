package proxy;

import com.bjpowernode.proxy.dynamicProxy.JDKProxy2;
import com.bjpowernode.serviceProxy.UserService;
import com.bjpowernode.serviceProxy.impl.UserServiceImpl;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class JDKProxyTest2 {

    @Test
    public void invoke01(){
        Class userServiceImpl = UserServiceImpl.class;
        JDKProxy2 jdkProxy = new JDKProxy2(userServiceImpl);
        UserService userService = jdkProxy.getProxy();
        userService.save();
    }

    @Test
    public void invoke02(){
        Class userServiceImpl = UserServiceImpl.class;
        JDKProxy2 jdkProxy = new JDKProxy2(userServiceImpl);
        UserService userService =(UserService)
                Proxy.newProxyInstance(userServiceImpl.getClassLoader(), userServiceImpl.getInterfaces(), jdkProxy);
        userService.remove();
    }
}
