package jdk.study;

public class TestBuMa {
    public static void main(String[] args) {
        byte a;
        byte b;
        byte c;
        a = 127;
        b = 127;
        c = 127;
        a <<= 2;
        System.out.println(a);
        System.out.println(b <<= 2);
        System.out.println(Integer.toBinaryString(c << 2));
        System.out.println("aa".indexOf(null));
    }
}
