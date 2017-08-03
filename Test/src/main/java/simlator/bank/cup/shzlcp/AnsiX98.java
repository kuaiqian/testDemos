package simlator.bank.cup.shzlcp;

import steel.encode.BCDASCII;

public class AnsiX98 {
    private static void memset(byte[] buf, byte b, int size) {
        for (int i = 0; i < size; i++) {
            buf[i] = b;
        }
    }

    public static String convertFromPinBlockImpl(byte[] pinblock, String strPan) throws Exception {
        byte[] A = new byte[6];
        byte[] P = new byte[8];
        byte[] buf = new byte[16];
        byte[] pan = strPan.getBytes("GBK");
        int i, len;
        len = (pinblock[0]) & 0x0ff;
        if(len < 0 || len > 8) {
            throw new RuntimeException("The length of PIN must between 1 to 8.");
        }
        memset(buf, (byte) 'F', 14);
        System.arraycopy(pan, pan.length - 13, buf, 0, 12);
        for (i = 0; i < 14; i++) {
            if(buf[i] < '0' || (buf[i] > '9' && buf[i] != 'F')) {
                buf[i] = 'F';
            }
        }
        BCDASCII.fromASCIIToBCD(buf, 0, 12, A, 0, false);
        P[0] = pinblock[1];
        for (i = 0; i < 6; i++) {
            P[i + 1] = (byte) (((pinblock[i + 2]) & 0x0ff) ^ ((A[i]) & 0x0ff));
        }
        BCDASCII.fromBCDToASCII(P, 0, buf, 0, len * 2, false);
        return new String(buf, 0, len, "GBK");
    }

    public static String convertFromPinBlock(byte[] pinBlock, String strPan) {
        try {
            return convertFromPinBlockImpl(pinBlock, strPan);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static byte[] convertToPinBlock(String strPin, String strPan) {
        try {
            return convertToPinBlockImpl(strPin, strPan);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static byte[] convertToPinBlockImpl(String strPin, String strPan) throws Exception {
        byte A[] = new byte[6];
        byte P[] = new byte[7];
        byte buf[] = new byte[14];
        int i;
        byte pin[] = strPin.getBytes("GBK");
        byte pan[] = strPan.getBytes("GBK");
        byte pinblock[] = new byte[8];
        int pin_length = pin.length;
        if(pin_length < 0 || pin_length > 15) {
            throw new RuntimeException("The length of PIN must between 1 to 15.");
        }
        memset(buf, (byte) 'F', 14);
        System.arraycopy(pin, 0, buf, 0, pin_length);
        BCDASCII.fromASCIIToBCD(buf, 0, 14, P, 0, false);
        memset(buf, (byte) 'F', 14);
        System.arraycopy(pan, pan.length - 13, buf, 0, 12);
        for (i = 0; i < 14; i++) {
            if(buf[i] < '0' || (buf[i] > '9' && buf[i] != 'F')) {
                buf[i] = 'F';
            }
        }
        BCDASCII.fromASCIIToBCD(buf, 0, 12, A, 0, false);
        for (i = 0; i < 6; i++) {
            pinblock[i + 2] = (byte) (P[i + 1] ^ A[i]);
        }
        pinblock[0] = (byte) pin_length;
        pinblock[1] = P[0];
        return pinblock;
    }
}
