package jdk.study.nio;

import java.nio.ByteBuffer;

public class NioByteBuffer {
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[] { 1, 2, 3, 4, 5, 6 });
        bb.rewind();
        bb.get(new byte[2], 0, 1);
        System.out.println(bb.position() + "::" + bb.limit() + "::" + bb.capacity());
        bb.put((byte) 7);
        System.out.println(bb.position() + "::" + bb.limit() + "::" + bb.capacity());
        bb.get();
        System.out.println(bb.position() + "::" + bb.limit() + "::" + bb.capacity());
        ByteBuffer bb1 = bb.slice();
        System.out.println(bb1.position() + "::" + bb1.limit() + "::" + bb1.capacity());
    }
}
