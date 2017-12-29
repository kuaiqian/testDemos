package spring.aop;

import org.aopalliance.intercept.ConstructorInterceptor;
import org.aopalliance.intercept.ConstructorInvocation;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AopIntecept implements MethodInterceptor, ConstructorInterceptor {
    @Override
    public Object invoke(MethodInvocation paramMethodInvocation) throws Throwable {
        System.out.println("方法执行前");
        Object result = paramMethodInvocation.proceed();
        System.out.println("方法执行后");
        return result;
    }

    @Override
    public Object construct(ConstructorInvocation paramConstructorInvocation) throws Throwable {
        System.out.println("构造前");
        Object result = paramConstructorInvocation.proceed();
        System.out.println("构造后");
        return result;
    }
}
