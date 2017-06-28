package jdk.study;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class TestSet {
    public static void main(String[] args) {
        HashSet set=new HashSet();
        set.add("abcd");
        set.add("ab");
        System.out.println(set);
        TreeSet set1=new TreeSet(set);
        System.out.println(set1);
        LinkedHashSet set2=new LinkedHashSet();
        set2.add("abcd");
        set2.add("ab");
        System.out.println(set2);
    }
}
