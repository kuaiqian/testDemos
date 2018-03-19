package jdk.study.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TestReflect {
    private ClassLoader classLoader = getClass().getClassLoader();

    public static void main(String[] args) throws ClassNotFoundException {
        // System.out.println(Date.class.isAssignableFrom(Mydate.class));
        // System.out.println(Date.class.isAssignableFrom(java.util.Date.class));
        // System.out.println(Cloneable.class.isAssignableFrom(java.util.Date.class));
//        System.err.println(Thread.currentThread().getContextClassLoader() == new TestReflect().classLoader);
//        System.err.println(Thread.currentThread().getContextClassLoader() == ClassLoader.getSystemClassLoader());
//        Class<?> clzz = Class.forName("java.lang.String");
//        System.out.println(clzz.getMethods()[1]);
        InvocationHandler in=new Mydate();
    	Date my=(Date)Proxy.newProxyInstance(Mydate.class.getClassLoader(), new Class[] {Date.class}, in);
    	my.date();
    }
}
