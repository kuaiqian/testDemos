package jdk.study;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

import jdk.study.model.DemoClass;
import sun.misc.Unsafe;

public class TestNewInstance {
    public static void main(String[] args) {
        // testNewObject();
        // testNewInstance();
        // testConstructor();
        // testConstructorWithParameterTypes();
        testUnsafeAllocateInstance();
    }

    public static void testUnsafeAllocateInstance() {
        Unsafe unsafe = UnsafeUtility.getUnsafe();
        try {
            DemoClass obj = (DemoClass) unsafe.allocateInstance(DemoClass.class);
            System.out.println(obj.getValue1());
            System.out.println(obj.getValue2());
            obj.setValue1(1);
            obj.setValue2(2);
            System.out.println(obj.getValue1());
            System.out.println(obj.getValue2());
        }catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void testNewObject() {
        DemoClass obj = new DemoClass(1, 2);
        System.out.println(obj.getValue1());
        System.out.println(obj.getValue2());
    }

    public static void testNewInstance() {
        try {
            DemoClass obj = DemoClass.class.newInstance();
            System.out.println(obj.getValue1());
            System.out.println(obj.getValue2());
        }catch (InstantiationException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void testConstructor() {
        try {
            Class[] cls = new Class[] { int.class, int.class };
            Constructor c = DemoClass.class.getDeclaredConstructor(cls);
            DemoClass obj = (DemoClass) c.newInstance(1, 0);
            System.out.println(obj.getValue1());
            System.out.println(obj.getValue2());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testConstructorWithParameterTypes() {
        try {
            Constructor[] c = DemoClass.class.getDeclaredConstructors();
            Type[] parameterTypes = c[0].getGenericParameterTypes();
            // 判断type类型，依次设置默认值
            DemoClass obj = (DemoClass) c[0].newInstance(0, 0);
            System.out.println(obj.getValue1());
            System.out.println(obj.getValue2());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class UnsafeUtility {
        private static Unsafe unsafe;
        static {
            try {
                Field f = Unsafe.class.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                unsafe = (Unsafe) f.get(null);
            }catch (Exception e) {
            }
        }

        public static Unsafe getUnsafe() {
            return unsafe;
        }
    }
}
