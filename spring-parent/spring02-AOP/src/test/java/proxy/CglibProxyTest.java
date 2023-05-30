package proxy;

import com.bjpowernode.proxy.dynamicProxy.CglibProxy;
import com.bjpowernode.serviceProxy.UserService;
import com.bjpowernode.serviceProxy.impl.UserServiceImpl;
import org.junit.Test;

public class CglibProxyTest {

    @Test
    public void intercept01(){
        CglibProxy cglibProxy = new CglibProxy();
        UserService userService = cglibProxy.getProxy(UserServiceImpl.class);
        userService.save();
    }
}
