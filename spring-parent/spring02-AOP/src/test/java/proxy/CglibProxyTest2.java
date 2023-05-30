package proxy;

import com.bjpowernode.proxy.dynamicProxy.CglibProxy2;
import com.bjpowernode.serviceProxy.UserService;
import com.bjpowernode.serviceProxy.impl.UserServiceImpl;
import org.junit.Test;

public class CglibProxyTest2 {

    @Test
    public void intercept01(){
        CglibProxy2 cglibProxy2 = new CglibProxy2();
        UserService userService = cglibProxy2.getProxy(UserServiceImpl.class);
        userService.update();
    }
}
