package simlator.bank.cup.shzlcp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

import bgw.protocols.iso8583.Iso8583Operator;
import bgw.protocols.iso8583.Iso8583StandardFieldNoes;
import mas.base.AppExcCodes;
import steel.base.AppBizException;
import steel.base.AppRTException;
import steel.encode.HexBinrary;

/**
 * 用于计算MAC及其相关的辅助类
 */
public class MacHelper {
    private static final Logger logger = LogManager.getLogger(MacHelper.class);

    private static final int UNIT_LENGTH = 8;

    /**
     * 获取应答报文的Mac Block
     *
     * @param isoMsg
     * @return
     * @throws AppBizException
     */
    public static byte[] getMacBlock(ISOMsg isoMsg) throws AppBizException {
        isoMsg.setPackager(new ISO87TxnPackager());
        try {
            Iso8583Operator.setField(isoMsg, Iso8583StandardFieldNoes.FIELD_NO_MAC, new byte[8]);
            byte[] preMab = isoMsg.pack();
            // 把报文中的64域数据截掉
            byte[] mab = subByte(preMab, 0, preMab.length - 8);
            // 再用0补齐8的倍数就是mab
            byte[] macBlock = appendTo8Multiple(mab);
            return macBlock;
        }catch (ISOException e) {
            logger.error(e);
            throw new AppBizException(AppExcCodes.ISO8583_MAC_VERIFY_ERROR, "Error when get MAC Block");
        }
    }

    /**
     * 用ECB方式计算MAB
     *
     * @param mab
     * @return
     */
    public static String xorMabResultByECB(final byte[] mab) {
        // 对原始msg处理，生成完整的8倍数的byte
        try {
            final byte[] msg_whole = appendTo8Multiple(mab);
            int unit_number = msg_whole.length / UNIT_LENGTH;
            // 每8个字节做异或
            byte[] xorResult = subByte(msg_whole, 0, 8);
            for (int i = 1; i < unit_number; i++) {
                int start = i * UNIT_LENGTH;
                int end = start + UNIT_LENGTH;
                byte[] unit = subByte(msg_whole, start, end);
                xorResult = doXor(xorResult, unit);
            }
            // 把结果转换成16进制表示的字符串
            return HexBinrary.encodeHexBinrary(xorResult);
        }catch (Exception e) {
            throw new AppRTException("", e.getMessage(), e);
        }
    }

    /**
     * 将byte数组长度扩充至8的倍数 不足的部分用二进制0填充
     *
     * @param bs 需要扩充的数组
     * @return
     */
    private static byte[] appendTo8Multiple(byte mab[]) {
        int length = mab.length;
        int gap = 0;
        if(length % UNIT_LENGTH != 0) {
            gap = UNIT_LENGTH - length % UNIT_LENGTH;
        }
        int newlength = length + gap;
        byte bytes2Return[] = new byte[newlength];
        for (int i = 0; i < mab.length; i++) {
            bytes2Return[i] = mab[i];
        }
        return bytes2Return;
    }

    /**
     * 取byte数组的一部分返回
     *
     * @param b被取的byte数组
     * @param start开始位置
     * @param end 结束位置
     * @return
     */
    public static byte[] subByte(byte b[], int start, int end) {
        int sublength = end - start;
        byte[] result = new byte[sublength];
        System.arraycopy(b, start, result, 0, sublength);
        return result;
    }

    /**
     * 对两个byte[]进行异或
     *
     * @param b1
     * @param b2
     * @return
     */
    public static byte[] doXor(byte[] b1, byte[] b2) {
        int byte_length = 8;
        byte[] result = new byte[byte_length];
        if(b1.length != byte_length || b2.length != byte_length) {
            throw new IllegalArgumentException("Both byte array'length must = 8!");
        }
        for (int i = 0; i < b1.length; i++) {
            result[i] = (byte) (b1[i] ^ b2[i]);
        }
        return result;
    }
}
