package http.resource;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nebula.server.Address;
import nebula.server.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ErrorHandleResouce implements Resource {
	protected Log log = LogFactory.getLog(this.getClass());

	public ErrorHandleResouce() {
	}

	@Override
	public void handle(Address target, HttpServletRequest req, HttpServletResponse resp) throws IOException,
			ServletException {
		throw new RuntimeException("not support");
	}

	public void redirectTo(HttpServletRequest req, HttpServletResponse resp, int code) throws IOException {
		// normal parse
		resp.setStatus(302);
		resp.flushBuffer();

	}

	public void redirectTo(HttpServletRequest req, HttpServletResponse resp, RuntimeException ex) throws IOException {
		// normal parse
		resp.setStatus(302);
		resp.getOutputStream().flush();
	}

	public void redirectTo(HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
		resp.setStatus(302);
		resp.flushBuffer();

	}
}
