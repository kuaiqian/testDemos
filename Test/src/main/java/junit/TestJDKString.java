package junit;

import java.util.Arrays;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import steel.encode.BCDASCII;

import com.google.common.collect.Sets;

public class TestJDKString {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println(String.format("%-20s", "123333"));
        System.out.println(String.format("%6s", "1"));
        System.out.println(String.format("%016d", 11));
        System.out.println(String.format("%016o", 11));
        System.out.println(String.format("%06d", 1));
        System.out.println(String.format("%0$6d", 1));
        System.out.println(String.format("%-3s", ObjectUtils.toString(null)));
        int a = 5;
        System.out.println(a--);
        System.out.println(++a);
        System.out.println(a++);
        System.out.println(--a);
        if(a++ > 5) {
            System.out.println("yes");
        }
        System.out.println(a);
        System.out.println(a-- + --a);
        Object aObject = null;
        System.out.println("_" + aObject);
        String string = "1234567812345678";
        String key = "12345678912345678123456789123456";
        System.out.println(string.getBytes().length);
        System.out.println(key.getBytes().length);
        System.out.println(des3(string, key));
        System.out.println(-1 << 29);
        System.out.println(Arrays.toString("asd|\n|as".split("\n|\\|")));
        Integer a1 = 1;
        Integer a2 = 1;
        Integer b1 = 200;
        Integer b2 = 200;
        Integer c1 = Integer.valueOf(1);
        // Integer c2 = new Integer(1); 官方不推荐这种建对象的方法喔
        Integer c2 = Integer.valueOf(1);
        Integer d1 = Integer.valueOf(200);
        Integer d2 = Integer.valueOf(200);
        System.out.println("a1==a2?" + (a1 == a2));
        System.out.println("b1==b2?" + (b1 == b2));
        System.out.println("c1==c2?" + (c1 == c2));
        System.out.println("d1==d2?" + (d1 == d2));
        String fieldValue = ",";
        Set<String> setFields = Sets.newHashSet(StringUtils.split(fieldValue, ","));
        System.out.println(setFields.contains("ccb") + "::" + setFields);
    }

    static String des3(String msg, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        StringBuffer des3Key = new StringBuffer(key);
        des3Key.append(key.substring(0, 16));
        byte[] keyInBCD = BCDASCII.fromASCIIToBCD(des3Key.toString(), 0, des3Key.toString().length(), false);
        byte[] msgInBCD = BCDASCII.fromASCIIToBCD(msg, 0, msg.length(), false);
        SecretKey secretKey = new SecretKeySpec(keyInBCD, "DESede");
        cipher.init(2, secretKey);
        System.out.println(msgInBCD.length);
        byte[] resultInBCD = cipher.doFinal(msgInBCD);
        String retnStr = BCDASCII.fromBCDToASCIIString(resultInBCD, 0, resultInBCD.length * 2, false);
        return retnStr;
    }
}
