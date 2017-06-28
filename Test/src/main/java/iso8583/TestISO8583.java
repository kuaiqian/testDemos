package iso8583;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.packager.ISO87BPackager;

import steel.encode.BCDASCII;

public class TestISO8583 {

	public static void main(String[] args) throws ISOException, UnsupportedEncodingException {
		ISOMsg isoMsg=new ISOMsg();
		isoMsg.setPackager(new ISO87BPackager());
		isoMsg.set(0, "1");
		isoMsg.set(2, "2");
		isoMsg.set(3, "3");
		isoMsg.set(13, "4");
		isoMsg.dump(new PrintStream(System.out), "");
		System.out.println(BCDASCII.fromBCDToASCIIString(isoMsg.pack(),0,isoMsg.pack().length*2,false));
		
		byte[] b="aAaa".getBytes("gbk");
		byte[] c=ISOUtil.str2bcd("aAaa", false, new byte[(b.length+1)/2], 0);
		System.out.println(ISOUtil.bcd2str(c, 0, c.length*2, false));
		
		System.out.println(BCDASCII.fromBCDToASCIIString(b, 0, b.length*2, false));
		
		System.out.println(BCDASCII.fromASCIIToBCD("6141", 0, "6141".length()/2, false)[0]);
	}

}
