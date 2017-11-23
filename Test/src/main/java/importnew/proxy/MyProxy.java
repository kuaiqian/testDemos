package importnew.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxy<T> implements InvocationHandler {
    private T source;

    private ClassLoader classLoader = getClass().getClassLoader();

    public MyProxy(T source) {
        super();
        this.source = source;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke");
        return method.invoke(source, args);
    }

    @SuppressWarnings("unchecked")
    public T getProxy() {
        System.out.println(classLoader.toString());
        return (T) Proxy.newProxyInstance(classLoader, source.getClass().getInterfaces(), this);
    }
}
