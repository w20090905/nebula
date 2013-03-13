package jetty.server;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.handler.AbstractHandler;
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
	public void handle(String paramString, HttpServletRequest request,
			HttpServletResponse response, int paramInt) throws IOException, ServletException {
		container.handle(new Request(request), new Response(response));

		org.mortbay.jetty.Request baseRequest = (request instanceof org.mortbay.jetty.Request) ? (org.mortbay.jetty.Request) request : HttpConnection
				.getCurrentConnection().getRequest();
		baseRequest.setHandled(true);

	}

}
