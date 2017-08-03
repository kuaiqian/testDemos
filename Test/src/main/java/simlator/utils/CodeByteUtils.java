package simlator.utils;

public class CodeByteUtils {
	
	public static final int UNIT_LENGTH = 8;
	
	/**
	 * 取byte数组的一部分返回
	 * @param start	开始位置
	 * @param b		被取的byte数组
	 * @param end	结束位置
	 * @return
	 */
	public static byte[] subByte(byte b[],int start,int end){
		int sublength = end - start;
		byte [] result = new byte[sublength];

		for(int i = 0;i<sublength;i++){
			result[i] = b[start+i];
		}
		return result;
	}
	
	
	/**
	 * 将byte数组长度扩充至8的倍数
	 * 不足的部分用二进制0填充
	 *
	 * @param bs	需要扩充的数组
	 * @return
	 */
	public static byte[] appendTo8Multiple(byte mab[]){
		int length = mab.length;
		int gap = 0;

		if(length%UNIT_LENGTH != 0){
			gap = UNIT_LENGTH - length%UNIT_LENGTH;
		}

		int newlength = length + gap;
		byte bytes2Return[] = new byte[newlength];
		for(int i = 0;i<mab.length;i++){
			bytes2Return[i] = mab[i];
		}

		return bytes2Return;
	}
	
	/**
	 * 对byte数组做xor操作
	 *
	 * @param b1  byte数组，长度要求为8
	 * @param b2  byte数组，长度要求为8
	 * 由于Des的输入参数必须为64位，所以本方法规定b1和b2的长度必须为8
	 *
	 * @return 两个byte数组xor后的结果
	 *
	 * @exception IllegalArgumentException when
	 *   参数b1和b2的长度 != 8
	 */
	public static byte[] doXor(byte[] b1,byte[] b2){
		int byte_length = 8;
		byte [] result = new byte[byte_length];
		if(b1.length != byte_length || b2.length != byte_length){
			throw new IllegalArgumentException ("Both byte array'length must = 8!");
		}
		for(int i = 0;i<b1.length;i++){
			result[i] = doXor(b1[i],b2[i]);
		}
		return result;
	}
	
	/**
	 * 对byte做xor操作
	 * @param b1
	 * @param b2
	 * @return 返回两个byte xor后的结果
	 */
	public static byte doXor(byte b1,byte b2){
		return (byte)(b1 ^ b2);
	}
	
}
