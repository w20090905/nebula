package http.resource;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nebula.server.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RedirectResouce implements Resource {
	protected Log log = LogFactory.getLog(this.getClass());

	final String redirectTo;

	public RedirectResouce(String redirectTo) {
		this.redirectTo = redirectTo;
	}

	@Override
	public void handle(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if ("XMLHttpRequest".equalsIgnoreCase(req.getHeader("X-Requested-With"))) {
			// normal parse
			resp.setStatus(403);
			resp.addHeader("WWW-Authenticate", this.redirectTo);
			resp.flushBuffer();
		} else {
			// normal parse
			resp.setStatus(302);
			resp.addHeader("location", this.redirectTo);
			resp.flushBuffer();
		}
	}

	public void redirectTo(HttpServletRequest req, HttpServletResponse resp, String redirectTo) throws IOException {
		if ("XMLHttpRequest".equalsIgnoreCase(req.getHeader("X-Requested-With:XMLHttpRequest"))) {
			// normal parse
			resp.setStatus(403);
			resp.addHeader("WWW-Authenticate", this.redirectTo);
			resp.flushBuffer();
		} else {
			// normal parse
			resp.setStatus(302);
			resp.addHeader("location", redirectTo);
			resp.flushBuffer();
		}
	}
}
