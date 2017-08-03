package jdk.study.jvm;

public class TestPerm {
    public static void main(String[] args) {
        String str1 = new StringBuilder("asaaa").toString();
        System.out.println(str1.intern() == str1);
    }
}
