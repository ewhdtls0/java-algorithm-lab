import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NioLab {

    void main() throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel server = ServerSocketChannel.open();

        server.bind(new InetSocketAddress(8080));
        server.configureBlocking(false);
        server.register(
                selector,
                SelectionKey.OP_ACCEPT
        );

        System.out.println("NIO server started on port 8080");
        while (true) {
            int readyChannels = selector.select();
            
            if (readyChannels == 0) {
                continue;
            }
            
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                
                if (key.isAcceptable()) {
                    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();

                    SocketChannel client = serverChannel.accept();
                    
                    client.configureBlocking(false);
                    
                    client.register(selector, SelectionKey.OP_READ);

                    System.out.println("client connected: " + client.getRemoteAddress());
                }
                
                if (key.isReadable()) {
                    SocketChannel client = (SocketChannel) key.channel();

                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    int read = client.read(buffer);
                    
                    if (read == -1) {
                        System.out.println("client disconnected");
                        
                        key.cancel();
                        client.close();

                        iterator.remove();
                        continue;
                    }

                    buffer.flip();

                    String message = StandardCharsets.UTF_8.decode(buffer).toString();

                    System.out.println("received: " + message);

                    ByteBuffer response = ByteBuffer.wrap(
                            ("echo: " + message).getBytes(StandardCharsets.UTF_8));

                    while (response.hasRemaining()) {
                        client.write(response);
                    }
                }

                iterator.remove();
            }
        }
    }
}
