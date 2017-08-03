package simlator.utils;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

import steel.base.AppBizException;
import steel.base.AppRTException;
import steel.util.StringUtil;

public class Iso8583PackUtils {

	public static final int FIELD_NO_MSG_TYPE = 0;
	public static final int FIELD_NO_PAN = 2;
	public static final int FIELD_NO_PROCESS_CODE = 3;
	public static final int FIELD_NO_AMOUNT = 4;
	public static final int FIELD_NO_DCC_TRADE = 6;
	public static final int FIELD_NO_8 = 8;
	public static final int FIELD_NO_DCC = 10;
	public static final int FIELD_NO_SYS_TRACE_NO = 11;
	public static final int FIELD_NO_TXN_TIME = 12;
	public static final int FIELD_NO_TXN_DATE = 13;
	public static final int FIELD_NO_EXP_DATE = 14;
	public static final int FIELD_NO_SET_DATE = 15;
	public static final int FIELD_NO_SERVICE_ENTRY_MODE = 22;
	public static final int FIELD_NO_SEQUENCE_NO = 23;
	public static final int FIELD_NO_NII = 24;
	public static final int FIELD_NO_SERVICE_CONDITION_CODE = 25;
	public static final int FIELD_NO_27 = 27;
	public static final int FIELD_NO_RESPONSE_CODE = 39;
	public static final int FIELD_NO_RESPONSE_NO = 39;
	public static final int FIELD_NO_32 = 32;
	public static final int FIELD_NO_TRACK2 = 35;
	public static final int FIELD_NO_TRACK3 = 36;
	public static final int FIELD_NO_RRN = 37;
	public static final int FIELD_NO_AUTH_CODE = 38;
	public static final int FIELD_NO_RESPONSE_DETAIL = 40;
	public static final int FIELD_NO_TERMINAL_ID = 41;
	public static final int FIELD_NO_MERCHANT_ID = 42;
	public static final int FIELD_NO_44 = 44;
	public static final int FIELD_NO_48 = 48;
	public static final int FIELD_NO_CURRENCY_CODE = 49;
	public static final int FIELD_NO_50 = 50;
	public static final int FIELD_NO_PIN = 52;
	public static final int FIELD_NO_53 = 53;
	public static final int FIELD_NO_54 = 54;
	public static final int FIELD_NO_PBOC_DATA = 55;
	public static final int FIELD_NO_57 = 57;
	public static final int FIELD_NO_58 = 58;
	public static final int FIELD_NO_60 = 60;
	public static final int FIELD_NO_61 = 61;
	public static final int FIELD_NO_62 = 62;
	public static final int FIELD_NO_63 = 63;
	public static final int FIELD_NO_MAC = 64;
	/**
	 * 签到响应消息类型
	 */
	public static final String LOGIN_TXN_REQMSGTYPE = "0800";
	public static final String LOGIN_TXN_MSGTYPE = "0810";
	/**
	 * 签退响应消息类型
	 */
	public static final String LOGOUT_TXN_REQMSGTYPE = "0820";

	public static final String LOGOUT_TXN_MSGTYPE = "0830";

	public static final String SERVICE_ENTRY_MODE_PAN_SWIPE = "02";
	public static final String SERVICE_ENTRY_MODE_PIN_ACCEPTABLE = "1";
	public static final String RESPONSE_CODE_SUCCESS = "00";
	public static final String RESPONSE_CODE_12 = "12";
	public static final String LOGOUT_TXN_MSGTYPE_att="0510";
	/**
	 * 此域目前报文中不存在，用来存放交易类型
	 */
	public static final int FIELD_NO_100 = 100;

	private static final int UNIT_LENGTH = 8;

	public static boolean isLoginTxn(ISOMsg responseIsoMsg) {
		String msgType = getFieldString(responseIsoMsg, FIELD_NO_MSG_TYPE);
		if (msgType != null && (msgType.equals(LOGIN_TXN_MSGTYPE) || msgType.equals(LOGIN_TXN_REQMSGTYPE))) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isLogOutTxn(ISOMsg isoMsg) {
		String msgType = getFieldString(isoMsg, Iso8583PackUtils.FIELD_NO_MSG_TYPE);
		if (msgType != null && (msgType.equals(LOGOUT_TXN_MSGTYPE) || msgType.equals(LOGOUT_TXN_REQMSGTYPE))) {
			return true;
		} else {
			return false;
		}

	}
	public static boolean setField(ISOMsg isoMsg, int fieldNo, String value) {
		if (value == null) {
			return false;
		}

		try {
			isoMsg.set(fieldNo, value);
		} catch (ISOException e) {
			throw new AppRTException("ISO8583_PACK_ERROR", "Fail to set iso8583 field=[" + fieldNo + "], value=["
					+ value + "].", e);
		}

		return true;
	}

	public static String getFieldString(ISOMsg isoMsg, int fieldNo) {
		return StringUtil.parseToString(isoMsg.getString(fieldNo));
	}

	public static String getMACFromISOMsg(ISOMsg isoMsg) throws AppBizException {
		try {
			String mac = getFieldString(isoMsg, FIELD_NO_MAC);
			return mac;
		} catch (Throwable e) {
			String errmsg = "Error when get MAC from ISOMsg, errmsg: " + e.getMessage();
			throw new AppBizException("MAC.ERROR", errmsg, e);
		}
	}

	public static byte[] subByte(byte b[], int start, int end) {

		int sublength = end - start;
		byte[] result = new byte[sublength];
		System.arraycopy(b, start, result, 0, sublength);
		return result;
	}

	public static byte[] ecbBlock(final byte[] mab) {
		// 将byte数组长度扩充至8的倍数 不足的部分用二进制0填充
		try {
			final byte[] msg_whole = appendTo8Multiple(mab);
			int unit_number = msg_whole.length / UNIT_LENGTH;
			// 每8
			byte[] xorResult = subByte(msg_whole, 0, 8);
			for (int i = 1; i < unit_number; i++) {
				int start = i * UNIT_LENGTH;
				int end = start + UNIT_LENGTH;
				byte[] unit = subByte(msg_whole, start, end);
				xorResult = doXor(xorResult, unit);
			}

			return xorResult;
		} catch (Exception e) {
			throw new AppRTException("", e.getMessage(), e);
		}
	}

	public static byte[] appendTo8Multiple(byte mab[]) {

		int length = mab.length;
		int gap = 0;

		if (length % UNIT_LENGTH != 0) {
			gap = UNIT_LENGTH - length % UNIT_LENGTH;
		}

		int newlength = length + gap;

		byte bytes2Return[] = new byte[newlength];

		for (int i = 0; i < mab.length; i++) {
			bytes2Return[i] = mab[i];
		}

		return bytes2Return;
	}

	public static byte[] doXor(byte[] b1, byte[] b2) {
		int byte_length = UNIT_LENGTH;
		byte[] result = new byte[byte_length];
		if (b1.length != byte_length || b2.length != byte_length) {
			throw new IllegalArgumentException("Both byte array's length must = 8!");
		}
		for (int i = 0; i < b1.length; i++) {
			result[i] = doXor(b1[i], b2[i]);
		}
		return result;

	}

	public static byte doXor(byte b1, byte b2) {
		return (byte) (b1 ^ b2);
	}
}
