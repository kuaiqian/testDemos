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
        for (String object : list) {
            for (String st : list1) {
                if("bb".equals(st)) {
                    break;
                }
                System.out.println(st);
            }
            System.out.println(object);
        }
    }
}
