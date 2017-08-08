package securechat;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.util.NumberUtils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public class StringEncoderSelf2 extends MessageToMessageEncoder<CharSequence> {

    // @Override
    // public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    // System.out.println("self2: " + cause);
    // }

    @Override
    protected void encode(ChannelHandlerContext paramChannelHandlerContext, CharSequence paramI, List<Object> paramList)
            throws Exception {
        Number number = NumberUtils.parseNumber((String) paramI, BigDecimal.class);
        System.out.println("number=" + number.toString());
        paramList.add(paramI);
    }
}
