package http.startup;


import http.server.BasicResourceContainer;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerServer;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class NanoServer {
    private static final Log log = LogFactory.getLog(NanoServer.class);

    public static void main(String[] list) throws Exception {
    	
        int port = 80;
        Injector injector =  Guice.createInjector(new ConfigModule());
                
        BasicResourceContainer container =(BasicResourceContainer)injector.getInstance(Container.class);

        ContainerServer server =  new ContainerServer(container, 6);
        Connection connection = new SocketConnection(server);
        SocketAddress address1 = new InetSocketAddress(port);
        connection.connect(address1);
        log.info("== listen at " + port);
    }
}
