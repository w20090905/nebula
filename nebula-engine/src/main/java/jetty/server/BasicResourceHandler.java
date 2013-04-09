package jetty.server;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.handler.AbstractHandler;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;

public class BasicResourceHandler extends AbstractHandler {
	final Container container;

	@Inject
	public BasicResourceHandler(Container container) {
		this.container = container;
	}

	@Override
	public void handle(String arg0, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		container.handle(new Request(request), new Response(response));

		baseRequest.setHandled(true);
		
	}

}
