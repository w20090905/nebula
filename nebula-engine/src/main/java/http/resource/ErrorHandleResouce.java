package http.resource;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Address;
import org.simpleframework.http.resource.Resource;

public class ErrorHandleResouce implements Resource {
	protected Log log = LogFactory.getLog(this.getClass());

	public ErrorHandleResouce() {
	}

	@Override
	public void handle(Address target, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		throw new RuntimeException("not support");
	}

	public void redirectTo(HttpServletRequest req, HttpServletResponse resp, int code) {
		if (log.isTraceEnabled()) {
			log.trace("Request : " + req.getPathInfo());
			log.trace("\tMethod" + req.getMethod());
		}
		try {

			// normal parse
			resp.setStatus(302);
			resp.flushBuffer();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void redirectTo(HttpServletRequest req, HttpServletResponse resp, RuntimeException ex) {
		if (log.isTraceEnabled()) {
			log.trace("\tMethod" + req.getMethod());
		}
		try {

			// normal parse
			resp.setStatus(302);
			resp.getOutputStream().flush();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void redirectTo(HttpServletRequest req, HttpServletResponse resp, Exception ex) {
		if (log.isTraceEnabled()) {
			log.trace("\tMethod" + req.getMethod());
		}
		try {

			// normal parse
			resp.setStatus(302);
			resp.flushBuffer();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
