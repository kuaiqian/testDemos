package simlator.bank.cup.shzlcp;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import steel.encode.BCDASCII;

/**
 * 入口decoder
 *
 * @author chen.cheng
 */

public class NettyDecoder extends ByteToMessageDecoder {
    private static final Logger logger = LogManager.getLogger(NettyDecoder.class);

    private String reqTpdu;

    public NettyDecoder(String reqTpdu) {
        this.reqTpdu = reqTpdu;
    }

    @Override
    protected void decode(ChannelHandlerContext context, ByteBuf byteBuffer, List<Object> out) throws Exception {
        // 2字节报文长度
        int readableLen = byteBuffer.readableBytes();
        if(readableLen < 2) {
            return;
        }
        byteBuffer.markReaderIndex();
        byte[] bytesRecv = new byte[2];
        byteBuffer.readBytes(bytesRecv);
        int tcpDataLength = (((bytesRecv[0]) & 0x0ff) * 256) + ((bytesRecv[1]) & 0x0ff);
        if(readableLen < (2 + tcpDataLength)) {
            logger.warn("recieve bytes less than tcp data length");
            byteBuffer.resetReaderIndex();
            return;
        }
        byte[] tcpData = new byte[tcpDataLength];
        byteBuffer.readBytes(tcpData);
        // 报文长度=tpdu+iso8583
        // 去掉tpdu+报文头
        byte[] iso8583Data = new byte[tcpDataLength - ISOUtil.hex2byte(reqTpdu).length];
        System.arraycopy(tcpData, ISOUtil.hex2byte(reqTpdu).length, iso8583Data, 0, iso8583Data.length);
        ISOMsg request = new ISOMsg();
        request.setPackager(new ISO87TxnPackager());
        request.unpack(iso8583Data);
        logger.info("cup shzlcp request=[" + BCDASCII.fromBCDToASCIIString(bytesRecv, 0, 4, false)
                + BCDASCII.fromBCDToASCIIString(tcpData, 0, tcpData.length * 2, false) + "]");
        out.add(request);
    }
}
