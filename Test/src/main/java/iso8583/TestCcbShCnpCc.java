package iso8583;

import org.apache.commons.codec.DecoderException;
import org.jpos.iso.ISOUtil;

import s3.util.ECB;
import s3.util.TDES;
import steel.base.AppBizException;
import steel.encode.BCDASCII;

public class TestCcbShCnpCc {
    static String data = "0200702406C020C09A311662148302589373320000000000000000010000082409051000000006376214830258937332D2409220113310022494903236323038333234383132333130353435313130303136313536E32D2DE9555EFEDC260000000000000001459F2701809F101307010103A02002010A010000000000665678F09F3704BDC1A15E9F3602004D9505000004E0009A031706219C01009F02060000000000015F2A02015682027C009F1A0201569F03060000000000009F3303E0E9C89F34034203009F3501229F1E0830303030303030318408A0000003330101019F090200209F4104000000069F26088DD098B527D037850061413230353630313030323032303230323030303030303330324A364E4C3030393839303837303330303639333733333230353030383230313730343138001422000001000600";

    static String key = "CE73E5F2C170EC32";

    public static void main(String[] args) throws AppBizException, DecoderException {
        // byte[] mab=BCDASCII.fromASCIIToBCD(data, 0, data.length(), false);
        // byte[] mak=BCDASCII.fromASCIIToBCD(key, 0, key.length(), false);
        byte[] mab = ISOUtil.hex2byte(data);
        byte[] mak = ISOUtil.hex2byte(key);
        System.out.println(mab.length + "mak=" + mak.length);
        byte[] result = ECB.encrypt(mak, mab);// 3634453131373936
        System.out.println(BCDASCII.fromBCDToASCIIString(result, 0, 2 * result.length, false));
        // String strHeader = "60010000000";
        // byte[] headerByte = BCDASCII.fromASCIIToBCD(strHeader, 0, strHeader.length(), false);
        // byte[] headerByte1 = ISOUtil.hex2byte(strHeader);
        // System.out.println("111");
        // System.out.println(ISOUtil.hexString(headerByte1).equals(strHeader) + "====");
        // byte[] headerByte2 = HexBinrary.decodeHexBinrary(strHeader);
        // System.out.println("222");
        // byte[] headerByte3 = Hex.decodeHex(strHeader.toCharArray());
        //
        // for (int i = 0; i < headerByte1.length; i++) {
        // System.out.println(headerByte[i] == headerByte1[i]);
        // System.out.println(headerByte[i] == headerByte2[i]);
        // System.out.println(headerByte[i] == headerByte3[i]);
        // }
        System.out.println(TDES.des3Encrypt("CE73E5F2C170EC32", "38ADAE75F8E9161A649423F770EA7A67"));
    }

    private static byte[] appendTo8Multiple(byte mab[]) {
        int length = mab.length;
        int gap = 0;
        if(length % 8 != 0) {
            gap = 8 - length % 8;
        }
        int newlength = length + gap;
        byte bytes2Return[] = new byte[newlength];
        for (int i = 0; i < mab.length; i++) {
            bytes2Return[i] = mab[i];
        }
        return bytes2Return;
    }
}
