package iso8583.packgers;

import org.jpos.iso.IFB_LLLBINARY;
import org.jpos.iso.IFB_LLLCHAR;
import org.jpos.iso.IFB_LLLNUM;

import bgw.protocols.iso8583.packager.Iso8583BcdPackager;

public class ISO87LoginPackager extends Iso8583BcdPackager {
    public ISO87LoginPackager() {
        super();
        fld[48] = new IFB_LLLNUM(999, "ADITIONAL DATA - PRIVATE", false);
        fld[60] = new IFB_LLLNUM(11, "RESERVED PRIVATE", false);
        fld[62] = new IFB_LLLBINARY(56, "RESERVED PRIVATE");
        fld[63] = new IFB_LLLCHAR(80, "RESERVED PRIVATE");
        setFieldPackager(fld);
    }
}
