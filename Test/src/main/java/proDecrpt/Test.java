package proDecrpt;

import java.io.UnsupportedEncodingException;

import security.PANUtils;
import steel.encode.BCDASCII;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // 身份证号或者卡号
        // 解密
        /*
         * String olddate="C736812941D5E715B2AE17044DD250A9DAE42B7FC388B26F"; System.out.println("加密密文="+olddate);
         * String data = PANUtils.decryptPAN(olddate); byte[] b = BCDASCII.fromASCIIToBCD(data.getBytes(), 0,
         * data.length(),false); System.out.println("解密数据="+new String(b, "GBK"));
         */
        // 加密
        /*
         * // String tmpText = BCDASCII.fromBCDToASCIIString((new String(b, "GBK")).getBytes(), 0,(new String(b,
         * "GBK")).length() * 2, false); String tmpText = BCDASCII.fromBCDToASCIIString("6253624014020315".getBytes(),
         * 0,"6253624014020315".length() * 2, false); System.out.println("加密数据1="+PANUtils.encryptPAN(tmpText));// 账户号码
         * System.out.println("交易表解密数据="+ PANUtils.decryptPAN("0BB71A5264AA4ECF3D1FDCFF652689A0")); // String tmpText2 =
         * BCDASCII.fromBCDToASCIIString("6217850100006124302".getBytes(), 0,"6217850100006124302".length() * 2, false);
         * System.out.println("加密数据2="+PANUtils.encryptPAN(tmpText2));// 账户号码
         */
        // txn
        // System.out.println(PANUtils.decryptPAN("979C9EF30CF2FB00"));
        // 6215591303001352393
        // 6222021001094886030
        // System.out.println("交易表解密数据="+ PANUtils.decryptPAN("1549825461AF2A0E0770B024C43E8016"));
        // String tmpText2 = BCDASCII.fromBCDToASCIIString("6214834171147219".getBytes(), 0,"6214834171147219".length()
        // * 2, false);
        // System.out.println("加密数据2="+PANUtils.encryptPAN(tmpText2));// 账户号码
        /*
         * List<String> list = readTxt.readTxt("D:\\info\\3.txt"); //List<String> outlist = new ArrayList<String>(); for
         * (int i = 0; i < list.size(); i++) { try { String t=list.get(i); String[] a =t.split(","); String data =
         * PANUtils.decryptPAN(a[4].replace("#","")); byte[] b = BCDASCII.fromASCIIToBCD(data.getBytes(), 0,
         * data.length(),false); System.out.println(a[0]+","+a[1]+","+a[2]+","+a[3]+","+new String(b, "GBK")); } catch
         * (Exception e) { //e.printStackTrace(); } }
         */
        String[] decData = { "CBDAEEA9E869B45A" };
        for (String arg : decData) {
            String data = PANUtils.decryptPAN(arg);
            byte[] b = BCDASCII.fromASCIIToBCD(data.getBytes(), 0, data.length(), false);
            System.out.println("解密数据=" + new String(b, "GBK"));
        }
    }
}
