package bill99.com.Study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioClient {

	public static void main(String[] args) throws IOException {
		Selector selector=Selector.open();
		SocketChannel socketChannel=SocketChannel.open();
		socketChannel.configureBlocking(false);
		socketChannel.connect(new InetSocketAddress("localhost", 9000));
		socketChannel.register(selector, SelectionKey.OP_CONNECT);
		while(true) {
			int n=selector.select(1000);
			if(n<0) {
				continue;
			}
			Set<SelectionKey> keys=selector.selectedKeys();
			Iterator<SelectionKey> it=keys.iterator();
			while(it.hasNext()) {
				SelectionKey key=it.next();
				it.remove();
				if(key.isConnectable()) {
					socketChannel=(SocketChannel)key.channel();
					if(socketChannel.finishConnect()) {
						ByteBuffer buffer=ByteBuffer.allocate(3);
						socketChannel.register(selector, SelectionKey.OP_READ);
						buffer.put("11".getBytes());
						buffer.flip();
						socketChannel.write(buffer);
					}
				}else if(key.isReadable()) {
					ByteBuffer buffer=ByteBuffer.allocate(103);
					 socketChannel=(SocketChannel)key.channel();
					socketChannel.read(buffer);
					buffer.flip();
					byte[] decode=new byte[buffer.remaining()];
					buffer.get(decode);
					System.out.println(new String(decode));
				}
			}
			
		}
	}

}
