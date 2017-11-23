package jdk.study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TestList {
    public static void main(String[] args) {
        List list = new ArrayList(Collections.nCopies(4, new Date()));
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        list.add("555");
        List list1 = new ArrayList();
        ListIterator it = list.listIterator(list.size());
        while (it.hasPrevious()) {
            list1.add(it.previous());
        }
        System.out.println(list);
        System.out.println(list1);
        List ll = new LinkedList();
        ll.add("aaa");
        ll.add("bbb");
        ll.add("ccc");
        ll.add("ddd");
        Iterator iterator = ll.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
