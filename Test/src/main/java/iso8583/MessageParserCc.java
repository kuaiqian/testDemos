package iso8583;

import iso8583.packgers.ISO87TxnPackager;

import java.util.HashMap;
import java.util.Map;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.ISOUtil;

import steel.encode.BCDASCII;

public class MessageParserCc {
    static final Map<String, ISOPackager> packagers = new HashMap<String, ISOPackager>();

    static final Map<String, String> tpdu = new HashMap<String, String>();

    static final String TPDU_REQUEST = "request";

    static final String TPDU_RESPONSE = "response";

    static final String PACK_LOGIN = "login";

    static final String PACK_TRADE = "trade";
    static {
        // packagers.put(PACK_TRADE, new ISO87LoginPackager());
        packagers.put(PACK_TRADE, new ISO87TxnPackager());
        tpdu.put(TPDU_REQUEST, "");
        tpdu.put(TPDU_RESPONSE, "");
    }

    public static void main(String[] args) throws ISOException {
        String reqMes = "0200702404800CC0881D1662262301400809772000000000002640003872041908012000313431363534323431363433383736373138303030303030303433303533313030343531313036323331353626000000000000000011230000010000001600000138720301280028202020203030303030303030303030303030303030303030202020313630383041313845";
        System.out.println(parseRequest(PACK_TRADE, reqMes));
        System.out.println("=============");
        String resMes = "0210703A00810ED08013166226230140080977200000000000264000387204152257012801280008030529003135323235373234313734373837363731383030303030303030303433303533313030343531313036323325303330353030303120202030333035303030312020202020203135360011230000010000004343555020202020202020202020202020202020202020202020202020202020202020202020202020202020303130384632304100000000000000";
        System.out.println(parseResponse(PACK_TRADE, resMes));
        // String ascii = "9B74200E1D0DD0BE8994403B84FEF59D36BBA9CEF1EBAED795ECFD0909001000A008ECC83F43F90D";
        // byte[] field62 = BCDASCII.fromASCIIToBCD(ascii, 0, ascii.length(), false);
    }

    static String parseRequest(String type, String message) throws ISOException {
        message = message.substring(tpdu.get(TPDU_REQUEST).length());
        return parse(type, message);
    }

    static String parseResponse(String type, String message) throws ISOException {
        message = message.substring(tpdu.get(TPDU_RESPONSE).length());
        return parse(type, message);
    }

    private static String parse(String type, String message) throws ISOException {
        ISOMsg isoMsg = new ISOMsg();
        ISOPackager isoPackager = packagers.get(type);
        isoMsg.setPackager(isoPackager);
        byte[] iso8583Data = BCDASCII.fromASCIIToBCD(message, 0, message.length(), false);
        isoMsg.unpack(iso8583Data);
        StringBuffer parseMsg = new StringBuffer();
        for (int i = 0; i <= 128; i++) {
            if(isoMsg.hasField(i)) {
                String field = isoMsg.getString(i);
                String annotation = isoPackager.getFieldDescription(isoMsg, i);
                if(field != null) {
                    if(i == 55) {
                        field = ISOUtil.hexString(field.getBytes());
                        field = field.replace("EFBE", "");
                        field = field.replace("EFBF", "");
                    }else {
                        // field = new String(field.getBytes("GBK"));
                    }
                }
                String msg = String.format("%02d", i) + ": [" + field + "], /*" + annotation + "*/";
                parseMsg.append(msg + "\n");
                // System.out.println(msg);
            }
        }
        return parseMsg.toString();
    }
}
