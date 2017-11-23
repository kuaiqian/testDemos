package jdk.study.reflect;

public class TestReflect {
    private ClassLoader classLoader = getClass().getClassLoader();

    public static void main(String[] args) throws ClassNotFoundException {
        // System.out.println(Date.class.isAssignableFrom(Mydate.class));
        // System.out.println(Date.class.isAssignableFrom(java.util.Date.class));
        // System.out.println(Cloneable.class.isAssignableFrom(java.util.Date.class));
        System.err.println(Thread.currentThread().getContextClassLoader() == new TestReflect().classLoader);
        System.err.println(Thread.currentThread().getContextClassLoader() == ClassLoader.getSystemClassLoader());
        Class<?> clzz = Class.forName("java.lang.String");
        System.out.println(clzz.getMethods()[1]);
    }
}
