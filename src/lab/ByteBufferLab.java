package lab;

import java.nio.ByteBuffer;

public class ByteBufferLab {
    public static void main(String[] args) {

        ByteBuffer buffer =
                ByteBuffer.allocate(10);

        print("초기", buffer);

        buffer.put((byte) 10);
        buffer.put((byte) 20);
        buffer.put((byte) 30);

        print("3개 write 후", buffer);

        buffer.flip();

        print("flip 후", buffer);

        System.out.println(buffer.get());
        System.out.println(buffer.get());

        print("2개 read 후", buffer);

        buffer.compact();

        print("compact 후", buffer);
    }

    static void print(
            String name,
            ByteBuffer buffer
    ) {

        System.out.printf(
                "%s -> position=%d, limit=%d, capacity=%d%n",
                name,
                buffer.position(),
                buffer.limit(),
                buffer.capacity()
        );
    }
}
