package jdk.study.nio;

import java.nio.ByteBuffer;

public class TestNio {

	public static void main(String[] args) {
		ByteBuffer byteBuffer=ByteBuffer.allocate(10).put("aaaaa".getBytes());
		System.out.println("remaining="+byteBuffer.remaining()+",position="+byteBuffer.position()+",limit="+byteBuffer.limit());
		byteBuffer.flip();
		byte[] a=new byte[1];
		byteBuffer.get(a);
		System.out.println("remaining="+byteBuffer.remaining()+",position="+byteBuffer.position()+",limit="+byteBuffer.limit());
		byteBuffer.compact();
		System.out.println("remaining="+byteBuffer.remaining()+",position="+byteBuffer.position()+",limit="+byteBuffer.limit());
		byteBuffer.rewind();
		System.out.println("remaining="+byteBuffer.remaining()+",position="+byteBuffer.position()+",limit="+byteBuffer.limit());

	}

}
