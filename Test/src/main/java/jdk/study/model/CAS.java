package jdk.study.model;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class CAS<V> {
    static Unsafe unsafe = null;

    private static long valueOffset;
    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        }catch (Exception e) {
            e.printStackTrace();
        }
        try {
            valueOffset = unsafe.objectFieldOffset(CAS.class.getDeclaredField("value"));
        }catch (SecurityException e) {
            e.printStackTrace();
        }catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private volatile V value;

    public CAS(V value) {
        this.value = value;
    }

    public boolean compareAndSet(V v1, V v2) {
        return unsafe.compareAndSwapObject(this, valueOffset, v1, v2);
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
