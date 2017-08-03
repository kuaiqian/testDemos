package simlator.bank.cup.shzlcp;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyServer {
    private static final Logger logger = LogManager.getLogger(NettyServer.class);

    private int port;

    private ServerInitializer serverInitializer;

    @PostConstruct
    public void init() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup);
        serverBootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000);
        serverBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 10);
        serverBootstrap.option(ChannelOption.TCP_NODELAY, true);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
        serverBootstrap.childHandler(serverInitializer);
        try {
            serverBootstrap.bind(port).sync();
            logger.info("Bank Simlator success. Listening on port: " + port);
        }catch (Exception e) {
            logger.error("Bank Simlator failed. Listening on port: " + port);
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ServerInitializer getServerInitializer() {
        return serverInitializer;
    }

    public void setServerInitializer(ServerInitializer serverInitializer) {
        this.serverInitializer = serverInitializer;
    }
}
