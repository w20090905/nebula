package http.resource;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Address;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.resource.Resource;

public abstract class AbstractResouce implements Resource {
	protected Log log = LogFactory.getLog(this.getClass());

	public AbstractResouce(String mime, long maxAge, int delayTime) {
		super();
		this.mime = mime;
		this.maxAge = maxAge;
		this.delayTime = delayTime;
	}

	private final String mime;
	private final long maxAge;
	private final int delayTime;
	protected byte[] cache;
	protected long lastModified;
	protected long lastChecked;

	protected abstract void get(Address address) throws IOException;

	protected void header(Address address) throws IOException {
		throw new UnsupportedOperationException("cann't support put " + address);
	}

	protected void put(Request req) throws IOException {
		throw new UnsupportedOperationException("cann't support put " + req.getAddress());
	}

	protected String post(Request req) throws IOException {
		throw new UnsupportedOperationException("cann't support post " + req.getAddress());
	}

	protected void delete(Address address) throws IOException {
		throw new UnsupportedOperationException("cann't support delete " + address);
	}

	@Override
	public void handle(Request req, Response resp) {
		if (log.isTraceEnabled()) {
			log.trace("Request : " + req.getPath() + "\t METHOD: " + req.getMethod());
		}

		String method = req.getMethod();

		try {
			if ("GET".equals(method)) {
			
				// System.out.print(System.currentTimeMillis());
				if (System.currentTimeMillis() - this.lastChecked > delayTime) {
					this.lastChecked = System.currentTimeMillis();
					get(req.getAddress());
					// System.out.println("====" + System.currentTimeMillis());
				}

				// normal parse
				resp.setCode(200);
				resp.set("Cache-Control", "max-age=" + maxAge);
				resp.set("Content-Language", "en-US");
				resp.set("Content-Type", mime);
				resp.set("Content-Length", cache.length);
				resp.setDate("Date", this.lastModified);
				resp.getOutputStream().write(cache);
				resp.getOutputStream().flush();
				resp.close();
			} else if ("HEADER".equals(method)) {
				System.out.println("Header Last-Modified : " + req.getValue("Last-Modified"));
			} else if ("PUT".equals(method)) {
				this.put(req);

				get(req.getAddress());

				// normal parse
				resp.setCode(200);
				resp.set("Cache-Control", "max-age=" + maxAge);
				resp.set("Content-Language", "en-US");
				resp.set("Content-Type", mime);
				resp.set("Content-Length", cache.length);
				resp.setDate("Date", this.lastModified);
				resp.getOutputStream().write(cache);
				resp.getOutputStream().flush();
				resp.close();
			} else if ("POST".equals(method)) {
				String newUrl = this.post(req);

				// normal parse
				resp.setCode(200);
				resp.set("Cache-Control", "max-age=0");
				resp.set("Content-Language", "en-US");
				resp.set("Content-Type", "text/html");
				resp.getPrintStream().print(newUrl);
				resp.close();

			} else if ("DELETE".equals(method)) {
				this.delete(req.getAddress());

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
