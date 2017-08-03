package simlator.bank.cup.shzlcp;

import org.jpos.iso.IFB_LLLBINARY;
import org.jpos.iso.IFB_LLLCHAR;
import org.jpos.iso.IFB_LLLNUM;
import org.jpos.iso.IFB_NUMERIC;

import bgw.protocols.iso8583.packager.Iso8583BcdPackager;

public class ISO87TxnPackager extends Iso8583BcdPackager {
	public ISO87TxnPackager() {
		super();
		fld[22] = new IFB_NUMERIC(3, "POINT OF SERVICE ENTRY MODE", false);//N3
		fld[54] = new IFB_LLLCHAR(20, "ADDITIONAL AMOUNTS");//An…020
        fld[60] = new IFB_LLLNUM(17, "RESERVED PRIVATE", false);// n…011
        fld[61] = new IFB_LLLNUM(29, "RESERVED PRIVATE", false);// n…029
        fld[62] = new IFB_LLLBINARY(84, "RESERVED PRIVATE");// b...084
		fld[63] = new IFB_LLLCHAR(63, "RESERVED PRIVATE");//Ans…063
	}
}
