package jdk.study;

import jdk.study.model.CAS;
import jdk.study.model.DemoClass;

public class TestCAS {
    public static void main(String[] args) throws SecurityException, NoSuchFieldException {
        DemoClass demoClass = new DemoClass(1, 2);
        DemoClass demoClass1 = new DemoClass(2, 2);
        CAS<DemoClass> cas1 = new CAS<DemoClass>(demoClass);
        System.out.println(cas1.compareAndSet(demoClass, demoClass1));
        System.out.println(cas1);
    }
}
