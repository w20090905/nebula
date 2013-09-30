package http.startup;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.SessionHandler;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class JettyServer {

	public static void main(String[] args) {
		System.out.println("Nebula 0.1 (C) 2013-2013\n" + "(Command line options: [-p port] [--licence])\n");

		// Defaults
		int port = 80;

		// Show licence if requested
		for (int i = 0; i < args.length; ++i)
			if (args[i].equalsIgnoreCase("-p")) port = Integer.parseInt(args[i + 1]);
			else if (args[i].toLowerCase().endsWith("licence")) {
				System.out.println(LICENCE + "\n");
				break;
			}

		try {
			Server server = new Server(port);
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

	private static final String LICENCE = "Copyright (C) 2013-2013 by Jixian\n";
}
