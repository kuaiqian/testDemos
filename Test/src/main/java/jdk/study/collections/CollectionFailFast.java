package jdk.study.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CollectionFailFast {
    public static void main(String[] args) {
        iteratorFailFast();

    }

    private static void iteratorFailFast() {
        List<Integer> list = new ArrayList<Integer>(2);
        list.add(2);
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            Integer num = it.next();
            if(num == 2) {
                // casue modCount !=exceptedModcount
                list.remove(num);
            }
        }
    }
}
