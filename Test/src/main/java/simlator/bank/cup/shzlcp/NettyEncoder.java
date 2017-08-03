package simlator.bank.cup.shzlcp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import steel.encode.BCDASCII;

/*
 * 出口encoder
 * @author chen.cheng
 */
public class NettyEncoder extends MessageToByteEncoder<Object> {
    private static final Logger logger = LogManager.getLogger(NettyEncoder.class);

    private String resTpdu;

    public NettyEncoder(String resTpdu) {
        this.resTpdu = resTpdu;
    }

    @Override
    protected void encode(ChannelHandlerContext context, Object request, ByteBuf out) throws Exception {
        ISOMsg requestIsoMsg = (ISOMsg) request;
        requestIsoMsg.setPackager(new ISO87TxnPackager());
        byte[] iso8583Data = requestIsoMsg.pack();
        byte[] bytesSend = getIso8583DataToSend(ISOUtil.hex2byte(resTpdu), iso8583Data);
        logger.info("cup shzlcp request=[" + BCDASCII.fromBCDToASCIIString(bytesSend, 0, bytesSend.length * 2, false)
                + "]");
        out.writeBytes(bytesSend);
    }

    private byte[] getIso8583DataToSend(byte[] tpduBytes, byte[] iso8583Data) {
        byte[] commData = new byte[iso8583Data.length + 2 + tpduBytes.length];
        int len = iso8583Data.length + tpduBytes.length;
        commData[0] = (byte) (len / 256);
        commData[1] = (byte) (len % 256);
        System.arraycopy(tpduBytes, 0, commData, 2, tpduBytes.length);
        System.arraycopy(iso8583Data, 0, commData, 2 + tpduBytes.length, iso8583Data.length);
        return commData;
    }
}
