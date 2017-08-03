package simlator.utils;

import org.apache.log4j.Logger;

import s3.util.AnsiX99;
import s3.util.ECB;
import s3.util.SimpleDESCrypto;
import steel.base.AppRTException;
import steel.encode.BCDASCII;
import steel.encode.HexBinrary;

/**
 * 列不同的加密算法
 * @author sait.xu
 *
 */
public class SecurityUtils {
	
	protected static final Logger logger = Logger.getLogger(SecurityUtils.class);
	
	
	
	public static byte[] ECBData(String masterKey, String PKeyDiv, byte[] encKey, byte[] encData, boolean padding) {
		byte[] pkey = BCDASCII.fromASCIIToBCD(masterKey, 0, 16, false);
		SimpleDESCrypto crypto = new SimpleDESCrypto(pkey, false);
		byte[] key = crypto.decrypt(encKey);
		return ECB.encrypt(key, encData);
	}
	
	/**
	 * AnsiX9.9算法
	 * @param masterKey
	 * @param encKey
	 * @param encData
	 * @return
	 */
	public static byte[] Ansx99(String masterKey, byte[] encKey, byte[] encData) {
		byte[] pkey = BCDASCII.fromASCIIToBCD(masterKey, 0, 16, false);
		SimpleDESCrypto crypto = new SimpleDESCrypto(pkey, false);
		byte[] key = crypto.decrypt(encKey);
		return AnsiX99.encode(key, encData);
	}
	
	
	public static byte[] desCbc(byte[] mak, final byte[] mab){
		//对原始msg处理，生成完整的8倍数的byte
		try{
			final byte[] msg_whole = CodeByteUtils.appendTo8Multiple(mab);
			int unit_number = msg_whole.length/CodeByteUtils.UNIT_LENGTH;
			//每8个字节做异或
			byte [] xorResult1 = CodeByteUtils.subByte(msg_whole, 0, 8);
			//生成Des加密器
			SimpleDESCrypto des = new SimpleDESCrypto(mak, false);
			byte[] desdata1 = des.encrypt(xorResult1);
			
			for(int i = 1;i<unit_number;i++){
				int start = i * CodeByteUtils.UNIT_LENGTH;
				int end = start + CodeByteUtils.UNIT_LENGTH;
				byte [] unit = CodeByteUtils.subByte(msg_whole, start, end);
				desdata1 = CodeByteUtils.doXor(desdata1, unit);
				desdata1 = des.encrypt(desdata1);
			}
			
			//把结果转换成16进制表示的字符串
			String xorResultHex = HexBinrary.encodeHexBinrary(desdata1);
			return xorResultHex.getBytes();
		}catch(Exception e){
			throw new AppRTException("", e.getMessage(), e);
		}
	}
	
	
	public static byte[] desCbc2(byte[] mak, final byte[] mab){
		//对原始msg处理，生成完整的8倍数的byte
		try{
			final byte[] msg_whole = CodeByteUtils.appendTo8Multiple(mab);
			int unit_number = msg_whole.length/CodeByteUtils.UNIT_LENGTH;
			//每8个字节做异或
			byte [] xorResult1 = CodeByteUtils.subByte(msg_whole, 0, 8);
			//生成Des加密器
			SimpleDESCrypto des = new SimpleDESCrypto(mak, false);
			byte[] desdata1 = des.encrypt(xorResult1);
			
			for(int i = 1;i<unit_number;i++){
				int start = i * CodeByteUtils.UNIT_LENGTH;
				int end = start + CodeByteUtils.UNIT_LENGTH;
				byte [] unit = CodeByteUtils.subByte(msg_whole, start, end);
				desdata1 = CodeByteUtils.doXor(desdata1, unit);
				desdata1 = des.encrypt(desdata1);
			}
			
			return desdata1;
		}catch(Exception e){
			throw new AppRTException("", e.getMessage(), e);
		}
	}
	
}
