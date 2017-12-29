package spring.aop;

import java.rmi.RemoteException;

import org.springframework.aop.framework.ProxyFactory;

public class TestAop {
    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new Man("xiao ming"));
        proxyFactory.addAdvice(new AopIntecept());
        Human man = (Human) proxyFactory.getProxy();
        man.eat(0);
    }
}
