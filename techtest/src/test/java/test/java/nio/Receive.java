/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 *
 * @author hadeslee
 */
public class Receive {

    public static void main(String[] args) throws Exception {
        boolean b = true;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.socket().bind(new InetSocketAddress(8888));
        ss.configureBlocking(false);
        Selector se = Selector.open();
        ss.register(se, SelectionKey.OP_ACCEPT);
        while (se.select() > 0) {
            Set<SelectionKey> set = se.selectedKeys();
            System.out.println("进入一个循环,大小是:" + set.size());
            for (SelectionKey key : set) {
                int ops = key.readyOps();
                System.out.println("ops=" + ops);
                if ((ops & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                    SocketChannel sc = ss.accept();
                    System.err.println("有新的连接了" + sc);
                    System.err.println("地址是:" + sc.socket());
                    sc.configureBlocking(false);
                    sc.register(se, SelectionKey.OP_READ);
                }
                if ((ops & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                    System.err.println("有新的读取");
                    SocketChannel sc = (SocketChannel) key.channel();
                    System.out.println(sc.isConnected());
                    sc.read(buffer);
                    buffer.flip();
                    //System.out.println(new String(buffer.array()));
                    Thread.sleep(5);
                    if (b) {
                        b = false;
                        sc.write(buffer);
                    }

                }
            }
            set.clear();
            System.out.println("退出循环");
        }

    }
}