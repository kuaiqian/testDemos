package jdk.study;

import java.io.IOException;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import steel.encode.BCDASCII;


public class TestMain {
    public static void main(String[] args) throws JsonProcessingException, IOException {
        String aString = "41534F41303132303030303031313530303030";
        byte[] a = BCDASCII.fromASCIIToBCD(aString, 0, aString.length(), false);
        System.out.println(new String(a));
        System.out.println(1 << 0);
        System.out.println(1 << 2);
        System.out.println(1 << 3);
        System.out.println(1 << 4);
        int tail = 0;
        System.out.println("====");
        System.out.println(-1 & 15);
        System.out.println(Integer.toBinaryString(-3));
        Object aString2 = null;
        String bString = (String) aString2;
        System.out.println(bString);
        String payInfoString = "{\"appId\":\"wx17e91dae2802695e\",\"timeStamp\":\"1508203580977\",\"status\":\"0\",\"signType\":\"MD5\",\"package\":\"prepay_id=wx20171017092620ee2a43e7ba0277321832\",\"callback_url\":null,\"nonceStr\":\"1508203580977\",\"paySign\":\"842AEC41D37AD54EA7EA780D976FAA5E\"}";
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonData = (ObjectNode)mapper.readTree(payInfoString);
        jsonData.put("timeStamp", System.currentTimeMillis());
        System.out.println(mapper.writeValueAsString(jsonData));
        
        int ops=2;
        System.out.println(ops&=~2);
    }
}
