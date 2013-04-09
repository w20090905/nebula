package http.startup;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.SessionHandler;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class JettyServer {

	public static void main(String[] args) {

		try {
			Server server = new Server(80);
			Injector injector = Guice.createInjector(new ConfigModule());
			Handler handler = injector.getInstance(Handler.class);
			SessionHandler sessionHandler = new SessionHandler();
			sessionHandler.setHandler(handler);

			server.setHandler(sessionHandler);

			server.start();
			server.join();

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
