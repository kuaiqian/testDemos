package simlator.bank.cup.shzlcp;

import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;
import simlator.utils.ConfigResponseCode;

@Sharable
public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    private String reqTpdu;

    private String resTpdu;

    private ConfigResponseCode configResponseCode;

    private SecurityService securityService;

    private boolean ssl = false;

    private SslContext sslContext;

    private String privateKey = "/nfs/security/pmd/keystore/MA/CPSdata/bgw/00016007/99BILL-TEST.jks";
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        if(ssl) {
            // SelfSignedCertificate ssc = new SelfSignedCertificate();
            // SslContext sslContext = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(createKeyStore(), "99bill".toCharArray());
            SslHandler sslHandler = SslContextBuilder.forServer(keyManagerFactory).build()
                    .newHandler(socketChannel.alloc());
            channelPipeline.addLast(sslHandler);
        }
        channelPipeline.addLast(new NettyDecoder(reqTpdu));
        channelPipeline.addLast(new NettyEncoder(resTpdu));
        channelPipeline.addLast(new ServerHandler(configResponseCode, securityService));

    }

    private KeyStore createKeyStore() throws Exception {
        if(privateKey == null) {
            throw new IllegalArgumentException("Keystore url may not be null");
        }
        KeyStore keystore = KeyStore.getInstance("jks");
        InputStream is = null;
        try {
            is = new URL("file:" + privateKey).openStream();
            keystore.load(is, null);
        }finally {
            if(is != null) {
                is.close();
            }
        }
        return keystore;
    }

    public String getReqTpdu() {
        return reqTpdu;
    }

    public void setReqTpdu(String reqTpdu) {
        this.reqTpdu = reqTpdu;
    }

    public String getResTpdu() {
        return resTpdu;
    }

    public void setResTpdu(String resTpdu) {
        this.resTpdu = resTpdu;
    }

    public ConfigResponseCode getConfigResponseCode() {
        return configResponseCode;
    }

    public void setConfigResponseCode(ConfigResponseCode configResponseCode) {
        this.configResponseCode = configResponseCode;
    }

    public SecurityService getSecurityService() {
        return securityService;
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public SslContext getSslContext() {
        return sslContext;
    }

    public void setSslContext(SslContext sslContext) {
        this.sslContext = sslContext;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
