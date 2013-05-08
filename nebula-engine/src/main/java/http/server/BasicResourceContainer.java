package http.server;

import http.resource.ErrorHandleResouce;
import http.resource.RedirectCurrentUserResouce;
import http.resource.RedirectResouce;
import http.startup.Configurable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nebula.data.Entity;
import nebula.server.Resource;
import nebula.server.ResourceEngine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.google.common.collect.Maps;

public class BasicResourceContainer extends AbstractHandler {
	private Log log = LogFactory.getLog(this.getClass());

	final private Map<String, Resource> cachedLinks = new HashMap<String, Resource>();
	final private Map<String, ResourceEngine> engines;

	final ResourceEngine defaultEngine;
	final RedirectCurrentUserResouce redirectCurrentUserResouce;
	final RedirectResouce redirectToLoginResouce;
	final ErrorHandleResouce errorHandleResource;

	@Inject
	public BasicResourceContainer(final Configurable<BasicResourceContainer> conf) {
		engines = Maps.newHashMap();
		conf.configure(this);

		this.defaultEngine = engines.get("*");
		this.errorHandleResource = new ErrorHandleResouce();
		this.redirectCurrentUserResouce = new RedirectCurrentUserResouce();
		this.redirectToLoginResouce = new RedirectResouce("/login.html");

		cachedLinks.put("/", redirectCurrentUserResouce);
	}

	public void register(String match, ResourceEngine engine) {
		// String regexMatch = match.replace(".", "[\\.]").replace("*", ".*");
		for (String key : match.split(" ")) {
			if (key.length() > 0) {
				engines.put(key, engine);
			}
		}

	}

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		if (log.isDebugEnabled()) {
			log.debug(req.getPathInfo());
		}
		try {

			String path = req.getPathInfo();

			// this.redirectToLoginResource.redirectTo(req, resp, "/u/" +
			// currentUser.getID());
			// return;
			Entity currentUser = (Entity) req.getSession().getAttribute("#currentUser");
			if (currentUser != null) {
			} else {
				if ("/".equals(path)) {
					redirectToLoginResouce.handle(req, resp);
					return;
				}

				int idx = path.indexOf('/', 1);
				if (idx < 0) {

				} else {
					String key = path.substring(1, idx);
					if ("js css e font img images f".indexOf(key) > 0) {

					} else {
						redirectToLoginResouce.handle(req, resp);
						return;
					}
				}
			}

			Resource res = cachedLinks.get(path);
			if (res != null) {
				res.handle(req, resp);
				return;
			} else {
				int idx = path.indexOf('/', 1);
				if (idx < 0) {
					res = defaultEngine.resolve(req);
				} else {
					res = resolve(path.substring(1, idx), req);
				}
				res.handle(req, resp);
			}
			return;

		} catch (RuntimeException e) {
			log.error(e.getClass().getName(), e);
			// this.errorHandleResource.redirectTo(req, resp, e);
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		} catch (ServletException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	long lockcount = 0;

	final ReentrantLock lock = new ReentrantLock();

	private Resource resolve(String key, HttpServletRequest req) {
		Resource res = null;
		String path = req.getPathInfo();
		lock.lock();
		long lockIndex = lockcount++;
		try {
			if (log.isTraceEnabled()) {
				log.trace("<<< begin lock for resolve Resource  : " + lockIndex);
			}

			res = cachedLinks.get(path);
			if (res != null) return res;

			ResourceEngine engine = this.engines.get(key);
			if (engine != null) res = engine.resolve(req);
			else defaultEngine.resolve(req);
			if (res != null) return res;
			res = defaultEngine.resolve(req);
			if (res != null) cachedLinks.put(path, res);
		} finally {
			lock.unlock();
			if (log.isTraceEnabled()) {
				log.trace(">>> unlock lock for resolve Resource  : " + lockIndex);
			}
		}

		if (res != null) {
		} else throw new RuntimeException("");
		return res;
	}
}
