package proxy;

import com.bjpowernode.proxy.dynamicProxy.CglibProxy3;
import com.bjpowernode.serviceProxy.UserService;
import com.bjpowernode.serviceProxy.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;

public class CglibProxyTest3 {

    @Test
    public void intercept01(){
        CglibProxy3 cglibProxy3 = new CglibProxy3();
        UserService userService = cglibProxy3.getProxy(UserServiceImpl.class);
        userService.save();
    }


    @Test
    public void intercept02(){
        Class<UserServiceImpl> userServiceClass = UserServiceImpl.class;
        CglibProxy3 cglibProxy3 = new CglibProxy3();
        cglibProxy3.setTargetClass(userServiceClass);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(userServiceClass);
        enhancer.setCallback(cglibProxy3);
        UserService userService = (UserService) enhancer.create();
        userService.save();
    }
}
