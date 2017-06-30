package jdk.study.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import jdk.study.model.DemoClass;

public class TestReference {
    static Map<String, SoftReference<DemoClass>> softMap = new HashMap<String, SoftReference<DemoClass>>();

    static Map<String, WeakReference<DemoClass>> weakMap = new HashMap<String, WeakReference<DemoClass>>();

    static Map<String, PhantomReference<DemoClass>> phantomMap = new HashMap<String, PhantomReference<DemoClass>>();

    public static void main(String[] args) {
        DemoClass demoClass = new DemoClass(0, 1);
        DemoClass demoClass1 = new DemoClass(0, 1);
        DemoClass demoClass2 = new DemoClass(0, 1);
        ReferenceQueue<DemoClass> referenceQueue = new ReferenceQueue<DemoClass>();
        PhantomReference<DemoClass> phantomReference = new PhantomReference<DemoClass>(demoClass2, referenceQueue);
        phantomMap.put("13", phantomReference);
        System.out.println("phantomReference:" + phantomMap.get("13").get());
        demoClass2 = null;
        SoftReference<DemoClass> softReference = new SoftReference<DemoClass>(demoClass);
        demoClass = null;
        WeakReference<DemoClass> weakReference = new WeakReference<DemoClass>(demoClass1);
        demoClass1 = null;
        softMap.put("11", softReference);
        weakMap.put("12", weakReference);
        System.out.println("phantomReference:" + phantomMap.get("13").get());
        System.gc();
        System.out.println("softReference:" + softMap.get("11").get());
        System.out.println("weakReference:" + weakMap.get("12").get());
        System.out.println("phantomReference:" + phantomMap.get("13").get());
    }
}
