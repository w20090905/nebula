package http.resource;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Address;
import org.simpleframework.http.resource.Resource;

public class RedirectResouce implements Resource {
	protected Log log = LogFactory.getLog(this.getClass());

	final String redirectTo;

	public RedirectResouce(String redirectTo) {
		this.redirectTo = redirectTo;
	}

	@Override
	public void handle(Address target, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (log.isTraceEnabled()) {
			log.trace("\tMethod" + req.getMethod());
		}
		try {

			// normal parse
			resp.setStatus(302);
			resp.addHeader("location", this.redirectTo);
			resp.flushBuffer();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void redirectTo(HttpServletRequest req, HttpServletResponse resp,String redirectTo){
		if (log.isTraceEnabled()) {
			log.trace("\tMethod" + req.getMethod());
		}
		try {

			// normal parse
			resp.setStatus(302);
			resp.addHeader("location", redirectTo);
			resp.flushBuffer();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
