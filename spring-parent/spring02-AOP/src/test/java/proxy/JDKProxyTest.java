package proxy;

import com.bjpowernode.proxy.dynamicProxy.JDKProxy;
import com.bjpowernode.serviceProxy.UserService;
import com.bjpowernode.serviceProxy.impl.UserServiceImpl;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class JDKProxyTest {

    @Test
    public void invoke01(){
        JDKProxy jdkProxy = new JDKProxy();
        UserService userService = (UserService) jdkProxy.getProxy(UserServiceImpl.class);
        userService.save();
    }

    @Test
    public void invoke02(){
        Class userServiceImpl = UserServiceImpl.class;
        JDKProxy jdkProxy = new JDKProxy(userServiceImpl);
        UserService userService =(UserService)
                Proxy.newProxyInstance(userServiceImpl.getClassLoader(), userServiceImpl.getInterfaces(), jdkProxy);
        userService.remove();
    }
}
