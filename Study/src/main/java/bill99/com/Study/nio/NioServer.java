package bill99.com.Study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

	public static void main(String[] args) throws IOException {
		Selector selector = Selector.open();
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		serverSocketChannel.bind(new InetSocketAddress(9000), 1);
		while (true) {
			try {
				if (selector.selectNow() < 0) {
					continue;
				}
				Set<SelectionKey> selectKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectKeys.iterator();
				while (iterator.hasNext()) {
					handleKey(selector, iterator);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void handleKey(Selector selector, Iterator<SelectionKey> iterator)
			throws IOException, ClosedChannelException {
		final SelectionKey key = iterator.next();
		iterator.remove();
		if (key.isAcceptable()) {
			ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
			SocketChannel socketChannel = ssc.accept();
			socketChannel.configureBlocking(false);
			socketChannel.register(selector, SelectionKey.OP_READ);
		} else if (key.isReadable()) {
			ByteBuffer buffer = ByteBuffer.allocate(103);
			SocketChannel socketChannel = (SocketChannel) key.channel();
			try {
				int r = socketChannel.read(buffer);
				buffer.flip();
				byte[] decode = new byte[buffer.remaining()];
				buffer.get(decode);
				String request=new String(decode);
				System.out.println(request);
				ByteBuffer buffer1 = ByteBuffer.allocate(103);
				buffer1.put(("huifu:"+request).getBytes());
				buffer1.flip();
				socketChannel.write(buffer1);
			} catch (Exception e) {
				e.printStackTrace();
				if (socketChannel != null) {
					socketChannel.close();
				}
			}
		}
	}

}
