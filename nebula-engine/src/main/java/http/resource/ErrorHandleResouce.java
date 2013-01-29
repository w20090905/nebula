package http.resource;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.resource.Resource;

public class ErrorHandleResouce implements Resource {
	protected Log log = LogFactory.getLog(this.getClass());

	public ErrorHandleResouce() {
	}

	@Override
	public void handle(Request req, Response resp) {
		throw new RuntimeException("not support");
	}

	public void redirectTo(Request req, Response resp, int code) {
		if (log.isTraceEnabled()) {
			log.trace("Request : " + req.getPath());
			log.trace("\tMethod" + req.getMethod());
		}
		try {

			// normal parse
			resp.setCode(302);
			resp.close();

		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
	
	public void redirectTo(Request req, Response resp, RuntimeException ex) {
		if (log.isTraceEnabled()) {
			log.trace("Request : " + req.getPath());
			log.trace("\tMethod" + req.getMethod());
		}
		try {

			// normal parse
			resp.setCode(302);
			resp.close();

		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	public void redirectTo(Request req, Response resp, Exception ex) {
		if (log.isTraceEnabled()) {
			log.trace("Request : " + req.getPath());
			log.trace("\tMethod" + req.getMethod());
		}
		try {

			// normal parse
			resp.setCode(302);
			resp.close();

		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}
