package simlator.bank.cup.shzlcp;

import java.util.Date;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

import com.bill99.seashell.common.util.DateUtil;

import bgw.protocols.iso8583.Iso8583Operator;
import bgw.protocols.iso8583.Iso8583StandardFieldNoes;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import simlator.utils.ConfigResponseCode;
import simlator.utils.TxnConfig;
import steel.encode.BCDASCII;

public class ServerHandler extends SimpleChannelInboundHandler<Object> {
    private Logger logger = LogManager.getLogger(getClass());

    private ConfigResponseCode configResponseCode;

    private SecurityService securityService;

    public ServerHandler() {
        super();
    }

    public ServerHandler(ConfigResponseCode configResponseCode, SecurityService securityService) {
        super();
        this.configResponseCode = configResponseCode;
        this.securityService = securityService;
    }

    private void waitSleep() {
        // 休眠时间 让执行线程休眠，达到超时的目的，让消费类的交易可以冲正
        Long strSleepTime = configResponseCode.getLongSleepTime();
        if(null != strSleepTime && strSleepTime > 0) {
            try {
                Thread.sleep(strSleepTime * 1000);
            }catch (InterruptedException e) {
                logger.error(e.getMessage(), e.getCause());
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext context, Object request) throws Exception {
        ISOMsg isoMsg = (ISOMsg) request;
        String txnType = isoMsg.getMTI();
        String processCode = isoMsg.getString(3);
        waitSleep();
        Map<String, TxnConfig> retCode = configResponseCode.getTxnTypesResultCode();
        ISOMsg resIsoMsg = new ISOMsg();
        commonParse(isoMsg, resIsoMsg);
        if(txnType.equals("0800")) {
            // 签到
            processLogin(isoMsg, resIsoMsg);
            TxnConfig txnconfig = retCode.get("0800");
            resIsoMsg.set(39, txnconfig.getResultCode());
        }else if(txnType.equals("0200") && processCode.equals("000000")) {
            // 消费交易位图定义
            processPur(isoMsg, resIsoMsg);
            TxnConfig txnconfig = retCode.get("0200");
            resIsoMsg.set(39, txnconfig.getResultCode());
        }else if(txnType.equals("0400") && processCode.equals("000000")) {
            // 冲正消费交易位图定义
            processReverse(isoMsg, resIsoMsg);
            TxnConfig txnconfig = retCode.get("0400");
            resIsoMsg.set(39, txnconfig.getResultCode());
        }else if(txnType.equals("0200") && processCode.equals("200000")) {
            // 撤消消费交易位图定义
            processCancel(isoMsg, resIsoMsg);
            TxnConfig txnconfig = retCode.get("200000");
            resIsoMsg.set(39, txnconfig.getResultCode());
        }
        String macKey = retCode.get("MAC").getResultCode();
        if(!txnType.equals("0800")) {
            boolean verify = securityService.verifyMacByHsm(macKey, isoMsg);
            if(!verify) {
                resIsoMsg.set(39, "A0");
            }
            // 成功时才有mac域
            if("00".equals(resIsoMsg.getString(39)) || "10".equals(resIsoMsg.getString(39))) {
                byte[] macBytes = securityService.calMacByHsm(macKey, resIsoMsg);
                Iso8583Operator.setField(resIsoMsg, Iso8583StandardFieldNoes.FIELD_NO_MAC, macBytes);
            }
        }
        context.write(resIsoMsg);
    }

    @Override
    public void userEventTriggered(final ChannelHandlerContext ctx, Object evt) throws Exception {
        ctx.pipeline().get(SslHandler.class).handshakeFuture().addListener(new GenericFutureListener<Future<Channel>>(){
            @Override
            public void operationComplete(Future<Channel> future) throws Exception {
                if(future.isSuccess()) {
                    logger.info("ssl handeshake success");
                }
            }
        });
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("cup shzlcp error=[{}]", cause);
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    /**
     * 撤销
     *
     * @param isoMsg
     * @param resIsoMsg
     * @throws ISOException
     */
    private void processCancel(ISOMsg isoMsg, ISOMsg resIsoMsg) throws ISOException {
        resIsoMsg.setMTI("0210");
        resIsoMsg.set(2, isoMsg.getString(2));
        resIsoMsg.set(3, "200000");
        resIsoMsg.set(4, isoMsg.getString(4));
        resIsoMsg.set(14, isoMsg.getString(14));
        resIsoMsg.set(15, isoMsg.getString(13));
        resIsoMsg.set(25, isoMsg.getString(25));
        resIsoMsg.set(32, "00092900");
        resIsoMsg.set(37, String.format("%1$012d", new Random().nextInt(1000000)));
        resIsoMsg.set(44, "92080000   01022900   ");
        resIsoMsg.set(49, isoMsg.getString(49));
    }

    /**
     * 冲正
     *
     * @param isoMsg
     * @param resIsoMsg
     * @throws ISOException
     */
    private void processReverse(ISOMsg isoMsg, ISOMsg resIsoMsg) throws ISOException {
        resIsoMsg.setMTI("0410");
        resIsoMsg.set(2, isoMsg.getString(2));
        resIsoMsg.set(3, isoMsg.getString(3));
        resIsoMsg.set(4, isoMsg.getString(4));
        resIsoMsg.set(14, isoMsg.getString(14));
        resIsoMsg.set(15, isoMsg.getString(13));
        resIsoMsg.set(25, isoMsg.getString(25));
        resIsoMsg.set(32, "00092900");
        resIsoMsg.set(37, String.format("%1$012d", new Random().nextInt(1000000)));
        resIsoMsg.set(44, "92080000   01022900   ");
        resIsoMsg.set(49, isoMsg.getString(49));
    }

    /**
     * 消费
     *
     * @param isoMsg
     * @param resIsoMsg
     * @throws ISOException
     */
    private void processPur(ISOMsg isoMsg, ISOMsg resIsoMsg) throws ISOException {
        resIsoMsg.setMTI("0210");
        resIsoMsg.set(2, isoMsg.getString(2));
        resIsoMsg.set(3, isoMsg.getString(3));
        resIsoMsg.set(4, isoMsg.getString(4));
        resIsoMsg.set(14, isoMsg.getString(14));
        // 结算日期
        resIsoMsg.set(15, isoMsg.getString(13));
        resIsoMsg.set(25, isoMsg.getString(25));
        resIsoMsg.set(32, "00092900");
        resIsoMsg.set(37, String.format("%1$012d", new Random().nextInt(1000000)));
        resIsoMsg.set(38, String.format("%1$06d", new Random().nextInt(1000000)));
        resIsoMsg.set(44, "92080000   01022900   ");
        resIsoMsg.set(49, isoMsg.getString(49));
        resIsoMsg.set(63, "CUP                                        ÃâÇ©Ãû              ");
    }

    /**
     * 签到
     *
     * @param isoMsg
     * @param resIsoMsg
     * @throws ISOException
     */
    private void processLogin(ISOMsg isoMsg, ISOMsg resIsoMsg) throws ISOException {
        String filed62 = "B0654ADF4CAD23F68E05F02842DFEE0E675E3C142A2CB5C0363840D90000000000000000A41347C090F1F88F5F058329CA1941F46A30BE3CA88D56CA";
        resIsoMsg.setMTI("0810");
        resIsoMsg.set(32, "00092900");
        resIsoMsg.set(37, String.format("%1$012d", new Random().nextInt(1000000)));
        resIsoMsg.set(62, BCDASCII.fromASCIIToBCD(filed62, 0, filed62.length(), false));
    }

    private void commonParse(ISOMsg isoMsg, ISOMsg resIsoMsg) throws ISOException {
        resIsoMsg.set(11, isoMsg.getString(11));
        resIsoMsg.set(12, DateUtil.formatDateTime("HHmmss", new Date()));
        resIsoMsg.set(13, DateUtil.formatDateTime("MMdd", new Date()));
        resIsoMsg.set(41, isoMsg.getString(41));
        resIsoMsg.set(42, isoMsg.getString(42));
        resIsoMsg.set(60, isoMsg.getString(60));
    }

    public static void main(String[] args) {
        System.out.println();
    }
}