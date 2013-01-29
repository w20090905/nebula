package http.server;

import http.resource.ErrorHandleResouce;
import http.resource.RedirectResouce;
import http.startup.Configurable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

import javax.inject.Inject;

import nebula.data.Entity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Address;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.resource.Resource;
import org.simpleframework.http.resource.ResourceEngine;
import org.simpleframework.util.lease.LeaseException;

public class BasicResourceContainer implements Container {
	private Log log = LogFactory.getLog(this.getClass());

	final private Map<String, Resource> cachedLinks = new HashMap<String, Resource>();
	final private List<Pair> pairsForEngine;

	final ResourceEngine defaultEngine;
	final Resource redirectToLoginResource;
	final ErrorHandleResouce errorHandleResource;

	private class Pair {
		public Pair(Pattern pattern, ResourceEngine engine) {
			super();
			this.pattern = pattern;
			this.engine = engine;
		}

		Pattern pattern;
		ResourceEngine engine;
	}

	@Inject
	public BasicResourceContainer(final Configurable<BasicResourceContainer> conf) {
		pairsForEngine = new CopyOnWriteArrayList<Pair>();
		conf.configure(this);

		this.defaultEngine = pairsForEngine.get(pairsForEngine.size() - 1).engine;
		this.errorHandleResource = new ErrorHandleResouce();
		this.redirectToLoginResource = new RedirectResouce("login.html");
		cachedLinks.put("/", redirectToLoginResource);
	}

	public void register(String match, ResourceEngine engine) {
		String regexMatch = match.replace(".", "[\\.]").replace("*", ".*");
		pairsForEngine.add(0, new Pair(Pattern.compile(regexMatch), engine));
	}

	@Override
	public void handle(Request req, Response resp) {
		if(log.isDebugEnabled()){
			log.debug("# Request\t: " + req.getAddress().getPath() + " Method\t: " + req.getMethod());
		}
		try {
			Entity currentUser = (Entity) req.getSession().get("#currentUser");
			if (currentUser == null) {
				// if(req.getAddress().getPath().getPath().equals("/loginzice.html")){
				//
				// }
				// if(req.getAddress().getPath().getExtension().equals("html")
				// &&
				// !req.getAddress().getPath().getPath().equals("/loginzice.html")){
				// redirectToLoginResource.handle(req, resp);
				// return;
				// }
				//
				// String path = req.getAddress().getPath().getPath();
				// Resource res = cachedLinks.get(path);
				// if (res == null) {
				// res = resolve(req.getAddress());
				// }
				//
				// if (res instanceof StaticResource) {
				// res.handle(req, resp);
				// return;
				// }else if (res instanceof LoginListResouce) {
				// res.handle(req, resp);
				// return;
				// } else {
				// redirectToLoginResource.handle(req, resp);
				// return;
				// }
			}

			String path = req.getAddress().getPath().getPath();
			Resource res = cachedLinks.get(path);
			if (res != null) {
				res.handle(req, resp);
				return;
			} else {
				res = resolve(req.getAddress());
			}
			res.handle(req, resp);
			return;

		} catch (RuntimeException e) {
			e.printStackTrace();
			log.error(e);
			this.errorHandleResource.redirectTo(req, resp, e);
		} catch (LeaseException e) {
			e.printStackTrace();
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	long lockcount = 0;

	final ReentrantLock lock = new ReentrantLock();

	private Resource resolve(Address address) {
		Resource res = null;
		String path = address.getPath().getPath();
		lock.lock();
		long lockIndex = lockcount++;
		if (log.isDebugEnabled()) {
			log.debug("<<< begin lock for resolve Resource  : " + lockIndex);
		}
		try {
			res = cachedLinks.get(path);
			if (res != null) return res;

			for (Pair pair : this.pairsForEngine) {
				if (pair.pattern.matcher(path).matches()) {
					res = pair.engine.resolve(address);
					if (res != null) break;
				}
			}
		} finally {
			lock.unlock();
			if (log.isDebugEnabled()) {
				log.debug(">>> unlock lock for resolve Resource  : " + lockIndex);
			}
		}

		if (res != null) cachedLinks.put(path, res);
		else throw new RuntimeException("");
		return res;
	}
}
