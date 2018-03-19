package jdk.study.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Mydate implements Date,InvocationHandler {
	@Override
	public String date() {
		return "111";
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before");
		Object o= method.invoke(this, args);
		System.out.println("after");
		return o;
	}
	
	
}
