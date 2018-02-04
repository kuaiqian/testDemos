package jdk.study;

import java.util.List;

import com.google.inject.internal.Lists;

public class TestLoop {
    /**
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("11", "22", "33");
        List<String> list1 = Lists.newArrayList("aaa", "bb", "cc");
        System.out.println("begin");
        for (String object : list) {
        	ok:
            for (String st : list1) {
                System.out.println(st);
                if("bb".equals(st)) {
                	break ok;
                }
            }
            System.out.println(object);
        }
    }
}
