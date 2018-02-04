package jdk.study.new7;

import java.util.ArrayList;
import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        // heap pollution 1.7 below is ok
        // HashMap<String, String>[] array = new HashMap<String, String>[2];
        // byte nByte = (byte)0b0001;
        // short nShort = (short)0B0010;
        // int nInt = 0b0011;
        // long nLong = 0b0100L;
        // //类型推理
        // Map<String, List<String>> myMap = new HashMap<>();
        // 协变
        // String str="one";
        // switch(str){
        // case "one":
        // System.err.println("1");
        // break;
        // case "two":
        // System.out.println("2");
        // break;
        // default :
        // System.out.println("err");
        // }
        // 1.6不支持泛型协变，支持数组泛型协变
        List<Animal> cats = new ArrayList<Animal>();
        check(cats);
        Animal[] catsArr = new Animal[2];
        check(catsArr);
        // Runnable r = () ->{System.out.println("lambdas");};
        // r.run();
        // long a=LocalTime.now().toNanoOfDay()/1000;
        // long b =System.currentTimeMillis();
        // System.out.println("a="+a+",b="+b);
    }

    public static <T> void check(List<T> cats) {
        System.out.println("List<? extends Animal>");
    }

    public static <T> void check(T[] cats) {
        System.out.println("Animal[]");
    }
}
