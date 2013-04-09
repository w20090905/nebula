package test.httpd;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Nio2ServerHTTPD {

  public static void main(String args[]) throws Exception {
              
    ExecutorService executorService =
        Executors.newCachedThreadPool(
            Executors.defaultThreadFactory());

    AsynchronousChannelGroup threadGroup =
        AsynchronousChannelGroup
            .withCachedThreadPool(executorService, 1);

    final AsynchronousServerSocketChannel server =
        AsynchronousServerSocketChannel.open(threadGroup);

    server.bind(new InetSocketAddress(8080));

    server.accept(null,        new CompletionHandler<AsynchronousSocketChannel,Void>() {

      ByteBuffer readBuffer = ByteBuffer.allocate(1024);
      byte[] request = new byte[1024];

      final ByteBuffer helloRequest =
          ByteBuffer.wrap("/csp/hello".getBytes());

      public void completed(AsynchronousSocketChannel result, Void attachment) {
        try {
          readBuffer.clear();
          result.read(readBuffer).get();
          readBuffer.position(4);
          readBuffer.get(request, 0, 10);

          if (ByteBuffer.wrap(request, 0, 10)
              .compareTo(helloRequest) == 0) {
        	  
        	 for (int i = 0; i < 1000; i++) {
				i+=1;
				i-=1;
			}
        	  
            result.write(ByteBuffer.wrap(("HTTP/1.1 200 OK\n"
                + "Content-Type: text/html; charset=utf-8\n"
                + "Content-Length: 20\n\n"
                + "<h1>HELLO WORLD" +
                "</h1>").getBytes()));
          }
        }
        catch (Exception e) {
          System.out.println(e.toString());
        }
        finally {
          try {
            result.close();
            server.accept(null, this);
          }
          catch (Exception e) {
            System.out.println(e.toString());
          }
        }
      }

      public void failed(Throwable exc, Void attachment) {
        throw new UnsupportedOperationException("Not supported yet.");
      }
    });

    // Wait
    System.in.read();

    server.close();
  }
}