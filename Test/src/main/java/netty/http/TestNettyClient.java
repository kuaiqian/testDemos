package netty.http;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.util.AttributeKey;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLException;

public class TestNettyClient {
    public static AttributeKey<String> REFERENCE = AttributeKey.valueOf("reference");

    public static void main(String[] args) throws SSLException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        final SslContext sslCtx = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
        EventLoopGroup group = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class).group(group).handler(new NettyHttpClientInitializer(sslCtx));
        Task task = new Task(bootstrap);
        for (int i = 0; i < 5; i++) {
            executorService.execute(task);
        }
        TimeUnit.SECONDS.sleep(10);
        executorService.shutdown();
        group.shutdownGracefully();
    }

    static class Task implements Runnable {
        private Bootstrap bootstrap;

        public Task(Bootstrap bootstrap) {
            super();
            this.bootstrap = bootstrap;
        }

        @Override
        public void run() {
            // Start the client.
            ChannelFuture f = null;
            DefaultFullHttpRequest request = null;
            try {
                f = bootstrap.connect("192.168.47.11", 8081).sync();
                URI uri = new URI("https://192.168.47.11:8082");
                String msg = "Are you ok?";
                request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, uri.toASCIIString(),
                        Unpooled.wrappedBuffer(msg.getBytes("UTF-8")));
            }catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }catch (URISyntaxException e) {
                e.printStackTrace();
            }
            // 构建http请求
            request.headers().set(HttpHeaders.Names.HOST, "127.0.0.1");
            request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
            request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, request.content().readableBytes());
            // 发送http请求
            f.channel().attr(REFERENCE).set(String.valueOf(System.currentTimeMillis()));
            f.channel().writeAndFlush(request).addListener(new ChannelFutureListener(){
                @Override
                public void operationComplete(ChannelFuture paramF) throws Exception {
                    if(paramF.isSuccess()) {
                        // System.out.println("complete success");
                    }else {
                        System.out.println(paramF.cause());
                    }
                }
            });
            try {
                f.channel().closeFuture().sync();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
