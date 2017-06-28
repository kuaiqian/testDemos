package iso8583;
import java.util.HashMap;
import java.util.Map;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.ISOUtil;

import iso8583.packgers.ISO87TxnPackager;
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
        tpdu.put(TPDU_REQUEST, "003B6000060000600100000000");
        tpdu.put(TPDU_RESPONSE, "003B6000060000600100000000");
    }

    public static void main(String[] args) throws ISOException {
        String reqMes = "0069600006110060010000000004007024048004C08019164367450001975057200000000000000008000126180201200632383838383630303037333731323131313131313131313131313131313135360011111000291430001610002900012606263945383238394341";
        System.out.println(parseRequest(PACK_TRADE, reqMes));
        System.out.println("=============");
        String resMes = "005A60110000066001000000000410603800800AC08001164367450001975057030000000098104941062006373137313130323435323134303030303030303638343130353131303034353131303031323135363643353244313537";
        System.out.println(parseResponse(PACK_TRADE, resMes));
        String ascii="9B74200E1D0DD0BE8994403B84FEF59D36BBA9CEF1EBAED795ECFD0909001000A008ECC83F43F90D";
        byte[] field62 = BCDASCII.fromASCIIToBCD(ascii, 0, ascii.length(), false);
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
