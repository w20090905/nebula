package http.engine;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.resource.Resource;

public class RedirectResouce implements Resource {
	protected Log log = LogFactory.getLog(this.getClass());

	final String redirectTo;

	public RedirectResouce(String redirectTo) {
		this.redirectTo = redirectTo;
	}

	@Override
	public void handle(Request req, Response resp) {
		if (log.isTraceEnabled()) {
			log.trace("Request : " + req.getPath());
			log.trace("\tMethod" + req.getMethod());
		}
		try {

			// normal parse
			resp.setCode(302);
			resp.set("location", this.redirectTo);
			resp.close();

		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
	
	public void redirectTo(Request req, Response resp,String redirectTo){
		if (log.isTraceEnabled()) {
			log.trace("Request : " + req.getPath());
			log.trace("\tMethod" + req.getMethod());
		}
		try {

			// normal parse
			resp.setCode(302);
			resp.set("location", redirectTo);
			resp.close();

		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}
