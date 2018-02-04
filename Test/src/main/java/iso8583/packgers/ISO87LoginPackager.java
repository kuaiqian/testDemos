package iso8583.packgers;

import org.jpos.iso.IFB_LLLBINARY;
import org.jpos.iso.IFB_LLLCHAR;
import org.jpos.iso.IFB_LLLNUM;
import org.jpos.iso.IFB_NUMERIC;

import bgw.protocols.iso8583.packager.Iso8583BcdPackager;

public class ISO87LoginPackager extends Iso8583BcdPackager {
    public ISO87LoginPackager() {
        super();
        fld[48] = new IFB_LLLNUM(62, "Additional Data - Private", pad);
        fld[54] = new IFB_LLLCHAR(20, "Balance Amount");
        fld[60] = new IFB_LLLNUM(11, "Reserved Private", pad);
        fld[61] = new IFB_NUMERIC(29, "Original Message", pad);
        fld[62] = new IFB_LLLBINARY(56, "Field No.62");
        fld[63] = new IFB_LLLCHAR(63, "Reserved Private");
        setFieldPackager(fld);
    }
}
