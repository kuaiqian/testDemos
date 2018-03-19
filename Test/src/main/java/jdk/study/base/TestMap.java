package jdk.study.base;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class TestMap {
  public static void main(String[] args) {
      Map map = new HashMap();
      Map map1 = new Hashtable<>();
      Map map2 = new TreeMap();
      
      Map<String,Integer> linkedmap=new LinkedHashMap<>(16, 0.75f, true);
      linkedmap.put("11", 1);
      linkedmap.put("13", 3);
      linkedmap.put("12", 2);
      linkedmap.put("14", 4);
      for (Entry<String, Integer> it : linkedmap.entrySet()) {
		System.out.println("key="+it.getKey()+",value="+it.getValue());
	}
		System.out.println("first binali");

      linkedmap.get("11");
      linkedmap.get("11");
      linkedmap.get("11");
      for (Entry<String, Integer> it : linkedmap.entrySet()) {
  		System.out.println("key="+it.getKey()+",value="+it.getValue());
  	}
}
}
