package jdk.study;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

public class TestSSLJDK {
    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {
        String host = "127.0.0.1";
        int port = 8992;
        try {
            System.out.println("Locating socket factory for SSL...");
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, InsecureTrustManagerFactory.INSTANCE.getTrustManagers(), null);
            SSLSocketFactory factory = sslContext.getSocketFactory();
            System.out.println("Creating secure socket to " + host + ":" + port);
            final SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
            System.out.println("Enabling all available cipher suites...");
            String[] suites = socket.getSupportedCipherSuites();
            socket.setEnabledCipherSuites(suites);
            System.out.println("Registering a handshake listener...");
            socket.addHandshakeCompletedListener(new MyHandshakeListener());
            System.out.println("Starting handshaking...");
            new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        socket.startHandshake();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            socket.startHandshake();
            System.out.println("Just connected to " + socket.getRemoteSocketAddress());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MyHandshakeListener implements HandshakeCompletedListener {
    @Override
    public void handshakeCompleted(HandshakeCompletedEvent e) {
        System.out.println("Handshake succesful!");
        System.out.println("Using cipher suite: " + e.getCipherSuite());
    }
}
