package securechat;

import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public class StringEncoderSelf extends MessageToMessageEncoder<CharSequence> {
    @Override
    protected void encode(ChannelHandlerContext paramChannelHandlerContext, CharSequence paramI, List<Object> paramList)
            throws Exception {
        paramList.add(paramI);
    }

}
