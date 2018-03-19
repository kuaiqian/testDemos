package bill99.com.Study.collection;

import java.util.Map;
import java.util.TreeMap;

public class TestTreeMap {

	public static void main(String[] args) {
			Map<Demo,String> map=new TreeMap<Demo, String>();
			map.put(new Demo(), "111");
			map.put(new Demo(), "222");
			map.put(new Demo(), "333");
			System.out.println(map.size());
			System.out.println(map.toString());
	}

}
