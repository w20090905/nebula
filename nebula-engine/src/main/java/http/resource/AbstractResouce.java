package http.resource;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nebula.server.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jetty.io.EofException;

public abstract class AbstractResouce implements Resource {
	protected Log log = LogFactory.getLog(this.getClass());

	public AbstractResouce(final String mime, long maxAge, int delayTime) {
		super();
		this.mime = mime != null ? mime : "text/html";
		this.maxAge = maxAge;
		this.delayTime = delayTime;
	}

	private final String mime;
	private final long maxAge;
	private final int delayTime;
	protected byte[] cache;
	protected long lastModified;
	protected long lastChecked;

	protected abstract void get(HttpServletRequest req) throws IOException;

	protected void header(HttpServletRequest req) throws IOException {
		throw new UnsupportedOperationException("cann't support put " + req.getPathInfo());
	}

	protected void put(HttpServletRequest req) throws IOException {
		throw new UnsupportedOperationException("cann't support put " + req.getPathInfo());
	}

	protected String post(HttpServletRequest req) throws IOException {
		throw new UnsupportedOperationException("cann't support post " + req.getPathInfo());
	}

	protected void delete(HttpServletRequest req) throws IOException {
		throw new UnsupportedOperationException("cann't support delete " + req.getPathInfo());
	}

	static long cntEofException = 0;
	static long cntIOException = 0;

	@Override
	public void handle(HttpServletRequest req, HttpServletResponse resp) throws IOException,
			ServletException {

		long currentTimeMillis = System.currentTimeMillis();
		String method = req.getMethod();

		try {
			if ("GET".equals(method)) {
				// if (currentTimeMillis - this.lastChecked > delayTime) {
				// if (log.isTraceEnabled()) {
				// log.trace("check updated time at currentTimeMillis:\t" +
				// currentTimeMillis
				// + " - lastChecked:\t" + this.lastChecked + "  = "
				// + (currentTimeMillis - this.lastChecked) + "  delayTime:\t" +
				// delayTime);
				// }
				get(req);
				this.lastChecked = currentTimeMillis;
				// }

				// normal parse
				resp.setStatus(200);
				resp.addHeader("Cache-Control", "max-age=" + maxAge);
				resp.addHeader("Content-Language", "en-US");
				resp.addHeader("Content-Type", mime);
				resp.addIntHeader("Content-Length", cache.length);
				resp.setDateHeader("Date", this.lastModified);
				resp.getOutputStream().write(cache);
				resp.getOutputStream().flush();
			} else if ("HEADER".equals(method)) {
				System.out.println("Header Last-Modified : " + req.getParameter("Last-Modified"));
			} else if ("PUT".equals(method)) {
				this.put(req);

				get(req);

				// normal parse
				resp.setStatus(200);
				resp.addHeader("Cache-Control", "max-age=" + maxAge);
				resp.addHeader("Content-Language", "en-US");
				resp.addHeader("Content-Type", mime);
				resp.addIntHeader("Content-Length", cache.length);
				resp.setDateHeader("Date", this.lastModified);
				resp.getOutputStream().write(cache);
				resp.getOutputStream().flush();
			} else if ("POST".equals(method)) {
				String newUrl = this.post(req);

				// normal parse
				resp.setStatus(200);
				resp.addHeader("Cache-Control", "max-age=0");
				resp.addHeader("Content-Language", "en-US");
				resp.addHeader("Content-Type", "text/html");
				resp.getOutputStream().print(newUrl);
				resp.getOutputStream().flush();
			} else if ("DELETE".equals(method)) {
				this.delete(req);

				// normal parse
				resp.setStatus(200);
				resp.addHeader("Cache-Control", "max-age=0");
				resp.addHeader("Content-Language", "en-US");
				resp.addHeader("Content-Type", "text/html");
				resp.addIntHeader("Content-Length", 0);
				resp.getOutputStream().flush();

			} else {
				throw new RuntimeException("Unsupport method " + method);
			}
		} catch (EofException e) {
			log.error("EofException[ " + ++cntEofException + " ] " + req.getPathInfo());
		} catch (IOException e) {
			log.error("IOException[ " + ++cntIOException + " ] " + req.getPathInfo());
		}
	}
}
