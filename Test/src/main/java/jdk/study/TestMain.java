package jdk.study;

import steel.encode.BCDASCII;

public class TestMain {
    public static void main(String[] args) {
        String aString = "41534F41303132303030303031313530303030";
        byte[] a = BCDASCII.fromASCIIToBCD(aString, 0, aString.length(), false);
        System.out.println(new String(a));
        System.out.println(1 << 0);
        System.out.println(1 << 2);
        System.out.println(1 << 3);
        System.out.println(1 << 4);
    }
}
