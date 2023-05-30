package proxy;

import com.bjpowernode.proxy.staticProxy.StaticProxy;
import com.bjpowernode.serviceProxy.impl.UserServiceImpl;
import org.junit.Test;

public class StaticProxyTest {

    @Test
    public void staticProxy() {
        StaticProxy staticProxy = new StaticProxy(new UserServiceImpl());
        staticProxy.select();
    }


}
