package http.startup;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.session.SessionHandler;

public class JettySessionServer extends AbstractHandler {
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
//
//		response.setContentType("text/html;charset=utf-8");
//		response.setStatus(HttpServletResponse.SC_OK);
//		System.out.println("getContextPath\t:\t" + request.getContextPath());
//		System.out.println("getPathInfo\t:\t" + request.getPathInfo());
//		System.out.println("getContentType\t:\t" + request.getContentType());
//		System.out.println("getAuthType\t:\t" + request.getAuthType());
//		System.out.println("getPathTranslated\t:\t" + request.getPathTranslated());
//		System.out.println("getQueryString\t:\t" + request.getQueryString());
//		HttpSession session = request.getSession();
//		baseRequest.setHandled(true);
//		response.getWriter().println("<h1>Hello World</h1>");
	}

	public static void main(String[] args) throws Exception {
		Server server = new Server(9090);
		SessionHandler sessionHandler = new SessionHandler();
		sessionHandler.setHandler(new JettySessionServer());
		server.setHandler(sessionHandler);

		server.start();
		server.join();
	}
}
