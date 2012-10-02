package http.engine;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.resource.Resource;

public abstract class BasicResouce implements Resource {
	protected Log log = LogFactory.getLog(this.getClass());

	protected int maxAge = 0;
	
	protected byte[] buffer;
	protected long lastModified;

	protected abstract void makeResponse();

	protected void put(Request req) {
		throw new UnsupportedOperationException("cann't support " + req.getMethod());
	}

	protected void post(Request req) {
		throw new UnsupportedOperationException("cann't support " + req.getMethod());
	}

	@Override
	public void handle(Request req, Response resp) {
		if (log.isTraceEnabled()) {
			log.trace("Request : " + req.getPath() + "\t METHOD: " + req.getMethod());
		}

		String method = req.getMethod();

		try {
			if ("GET".equals(method)) {

				//System.out.print(System.currentTimeMillis());
				if (System.currentTimeMillis() - this.lastModified > 100) {					
					makeResponse();
					//System.out.println("====" + System.currentTimeMillis());
				}

				
				// normal parse
				resp.setCode(200);
				resp.set("Cache-Control", "max-age=" + maxAge);
				resp.set("Content-Language", "en-US");
				resp.set("Content-Type", "text/html");
				resp.set("Content-Length", buffer.length);
				resp.setDate("Date", this.lastModified);
				resp.getOutputStream().write(buffer);
				resp.getOutputStream().flush();
				resp.close();
			} else if ("PUT".equals(method)) {
				this.put(req);

				makeResponse();

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
			} else if ("POST".equals(method)) {
				this.post(req);

				// normal parse
				resp.setCode(200);
				resp.set("Cache-Control", "max-age=0");
				resp.set("Content-Language", "en-US");
				resp.set("Content-Type", "text/html");
				resp.set("Content-Length", 0);
				resp.close();
			} else {
				throw new RuntimeException("Unsupport method " + method);
			}
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}
