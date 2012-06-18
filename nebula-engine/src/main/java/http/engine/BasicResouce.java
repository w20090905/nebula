package http.engine;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.resource.Resource;

public abstract class BasicResouce implements Resource {
	protected Log log = LogFactory.getLog(this.getClass());

	protected byte[] buffer;
	protected Long lastModified;

	protected abstract void make();

	@Override
	public void handle(Request req, Response resp) {
		try {
			if (log.isTraceEnabled()) {
				log.trace("Request : " + req.getPath());
			}

			make();
			
			// normal parse
			resp.setCode(200);
			resp.set("Cache-Control", "max-age=0");
			resp.set("Content-Language", "en-US");
			resp.set("Content-Type", "text/html");
			resp.set("Content-Length", buffer.length);
			resp.setDate("Date", this.lastModified);			
			resp.getOutputStream().write(buffer);
			resp.getOutputStream().flush();
			resp.close();
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}
