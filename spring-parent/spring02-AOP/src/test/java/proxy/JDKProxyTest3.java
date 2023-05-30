package proxy;

import com.bjpowernode.proxy.dynamicProxy.JDKProxy3;
import com.bjpowernode.serviceProxy.UserService;
import com.bjpowernode.serviceProxy.impl.UserServiceImpl;
import org.junit.Test;

public class JDKProxyTest3 {


    @Test
    public void invoke01(){
        JDKProxy3 jdkProxy3 = new JDKProxy3();
        UserService userService = jdkProxy3.getProxy(UserServiceImpl.class);
        userService.save();
    }
}
