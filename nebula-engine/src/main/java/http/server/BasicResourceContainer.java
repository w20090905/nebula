package http.server;

import http.resource.ErrorHandleResouce;
import http.resource.RedirectCurrentUserResouce;
import http.resource.RedirectResouce;
import http.startup.Configurable;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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
import org.eclipse.jetty.util.URIUtil;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.Maps;

public class BasicResourceContainer extends AbstractHandler {
	private Log log = LogFactory.getLog(this.getClass());

	final private LoadingCache<String, Resource> cachedLinks;
	final private Map<String, ResourceEngine> engines;

	final ResourceEngine defaultEngine;
	final RedirectCurrentUserResouce redirectCurrentUserResouce;
	final RedirectResouce redirectToLoginResouce;
	final ErrorHandleResouce errorHandleResource;
	CacheLoader<String, Resource> loader = new CacheLoader<String, Resource>() {
		public Resource load(String path) throws Exception {
			log.info("load resource - " + path);
			Resource res = null;
			int idx = path.indexOf('/', 1);
			if (idx > 0) {
				String key = path.substring(1, idx);
				ResourceEngine engine = engines.get(key);
				if (engine != null) res = engine.resolve(path);
				if (res != null) return res;
				res = defaultEngine.resolve(path);
			} else {
				res = defaultEngine.resolve(path);
			}
			return res;
		}
	};
	RemovalListener<String, Resource> removalListener = new RemovalListener<String, Resource>() {
		public void onRemoval(RemovalNotification<String, Resource> removal) {
			log.info("remove " + removal.getKey());
		}
	};

	@Inject
	public BasicResourceContainer(final Configurable<BasicResourceContainer> conf) {
		engines = Maps.newHashMap();
		conf.configure(this);

		this.defaultEngine = engines.get("*");
		this.errorHandleResource = new ErrorHandleResouce();
		this.redirectCurrentUserResouce = new RedirectCurrentUserResouce();
		this.redirectToLoginResouce = new RedirectResouce("/login.html");
		cachedLinks = CacheBuilder.newBuilder().build(loader);
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
			log.debug("client request "+req.getMethod() +" - " + req.getPathInfo() + " - " + URIUtil.decodePath(req.getQueryString()));
		}
		try {
			String path = req.getPathInfo();

			Entity currentUser = (Entity) req.getSession().getAttribute("#currentUser");
			if (currentUser != null) {
				Resource res = cachedLinks.get(path);
				res.handle(req, resp);
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
				Resource res = cachedLinks.get(path);
				res.handle(req, resp);
			}

			return;

		} catch (ExecutionException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		} catch (ServletException e) {
			log.error(e);
		} catch (RuntimeException e) {
			log.error(e.getClass().getName(), e);
		}
		resp.setStatus(400);
		resp.flushBuffer();
	}
}
