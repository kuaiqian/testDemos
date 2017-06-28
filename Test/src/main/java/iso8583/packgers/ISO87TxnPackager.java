package iso8583.packgers;

import org.jpos.iso.IFB_LLLCHAR;
import org.jpos.iso.IFB_LLLNUM;
import org.jpos.iso.IFB_NUMERIC;

import bgw.protocols.iso8583.packager.IFB_LLLCHAR_GBK;
import bgw.protocols.iso8583.packager.Iso8583BcdPackager;

public class ISO87TxnPackager extends Iso8583BcdPackager {
    public ISO87TxnPackager() {
        super();
        fld[22] = new IFB_NUMERIC(3, "POINT OF SERVICE ENTRY MODE", false);
        fld[60] = new IFB_LLLNUM(11, "RESERVED PRIVATE", false);
        fld[61] = new IFB_LLLNUM(999, "RESERVED PRIVATE", true);
        fld[62] = new IFB_LLLCHAR_GBK(999, "RESERVED PRIVATE");
        fld[63] = new IFB_LLLCHAR(999, "RESERVED PRIVATE");
        setFieldPackager(fld);
    }
}
