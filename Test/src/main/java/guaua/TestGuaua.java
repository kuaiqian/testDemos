package guaua;

import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;
import com.google.common.base.Predicates;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

public class TestGuaua {
    public static void main(String[] args) {
        // list元素还有a字符串的元素
        List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
        Iterable<String> result = Iterables.filter(names, Predicates.not(Predicates.containsPattern("a")));
        for (String element : result) {
            System.out.println(element);
        }
        // String2map
        String authRule = "authNetId=03063104|authRespCode=|resultCode=65|=111||";
        Map<String, String> map = Splitter.on("|").omitEmptyStrings().withKeyValueSeparator("=").split(authRule);
        System.out.println(map.size());
        System.out.println(map);
        String str = Joiner.on("!").join(new String[] { "1", "2" });
        System.out.println(str);
        Ordering<String> ordering = new Ordering<String>(){
            @Override
            public int compare(String left, String right) {
                return 0;
            }
        };
        Map<String, Integer> salary = Maps.newHashMap();
        salary.put("John", 1000);
        salary.put("Jane", 1500);
        salary.put(null, 500);
        String aa = Joiner.on(" , ").useForNull("null").withKeyValueSeparator(" = ").join(salary);
        System.out.println(aa);
    }
}
