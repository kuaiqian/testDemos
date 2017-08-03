package simlator.utils;

import org.jpos.iso.ISOException;

public interface SimplexTxnTypeRouter {
	public byte[] txnRouter(byte[] iso8583Data, String key) throws ISOException;
}
