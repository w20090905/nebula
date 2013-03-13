package http.startup;

import jetty.server.BasicResourceHandler;

import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.simpleframework.http.core.Container;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class JettyServer {

	public static void main(String[] args) {

		try {
			Server server = new Server(80);
			Injector injector = Guice.createInjector(new ConfigModule());
			Container container = injector.getInstance(Container.class);

			Handler handler = new BasicResourceHandler(container);

			server.setHandler(handler);

			server.start();
			server.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
