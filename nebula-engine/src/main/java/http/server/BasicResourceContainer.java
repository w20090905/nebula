package http.server;

import http.startup.Configurable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Address;
import org.simpleframework.http.Cookie;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.resource.Resource;
import org.simpleframework.http.resource.ResourceContainer;
import org.simpleframework.http.resource.ResourceEngine;

public class BasicResourceContainer extends ResourceContainer {
	private Log log = LogFactory.getLog(this.getClass());

	private List<ResourceEngine> engines;
	private List<Pattern> patterns;
 
	ReentrantLock lock = new ReentrantLock();
	private Map<String, Resource> cachedLinks = new HashMap<String, Resource>();

	@Inject
	public BasicResourceContainer(ResourceEngine engine,
			Configurable<BasicResourceContainer> conf) {
		super(engine);
		patterns = new CopyOnWriteArrayList<>();
		engines = new CopyOnWriteArrayList<>();
		conf.configure(this);
	}

	public void register(String match, ResourceEngine engine) {
		match = match.replace(".", "[\\.]").replace("*", ".*");

		patterns.add(0, Pattern.compile(match));
		engines.add(0, engine);
	}

	@Override
	public void handle(Request req, Response resp) {
		try {
			
			String path = req.getAddress().getPath().getPath();
			Resource res = cachedLinks.get(path);
			if (res != null) {
				res.handle(req, resp);
				return;
			}

			if (res == null) {
				if (log.isTraceEnabled()) {
					log.trace(req.getAddress() + " From : "
							+ req.getClientAddress() + " Method : "
							+ req.getMethod());
					log.trace("\tAccept : " + req.getValue("Accept")
							+ " Accept-Charset : "
							+ req.getValue("Accept-Charset")
							+ " Accept-Encoding : "
							+ req.getValue("Accept-Encoding")
							+ " User-Agent : " + req.getValue("User-Agent")
							+ " Locales : " + req.getLocales());
					log.trace("\tCookies: ");
					for (Cookie cookie : req.getCookies()) {
						log.trace("\t\t " + cookie.toString());
					}
				}

				res = resolve(req.getAddress());
			}
			res.handle(req, resp);
			return;

		} catch (RuntimeException e) {
			e.printStackTrace();
			log.error(e);
			throw e;
		}
	}

	long lockcount=0;
	private Resource resolve(Address address) {

		lock.lock();
		
		System.out.println("lock: " + ++lockcount);
		String path = address.getPath().getPath();
		Resource res = cachedLinks.get(path);
		if (res != null) {
			return res;
		}

		ResourceEngine engine = null;

		for (int i = 0; i < patterns.size(); i++) {
			if (patterns.get(i).matcher(path).matches()) {
				engine = engines.get(i);
				res = engine.resolve(address);
				if (res != null) {
					break;
				}
			}
		}
		cachedLinks.put(path, res);
		lock.unlock();
		return res;
	}
}
