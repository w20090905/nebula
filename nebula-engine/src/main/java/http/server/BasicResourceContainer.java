package http.server;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Cookie;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.resource.ResourceContainer;
import org.simpleframework.http.resource.ResourceEngine;

public class BasicResourceContainer extends ResourceContainer {
	private Log log = LogFactory.getLog(this.getClass());
	@Inject
	public BasicResourceContainer(ResourceEngine engine) {
		super(engine);
	}
	
	@Override
	public void handle(Request req, Response resp) {
		if(log.isTraceEnabled()){
			log.trace(req.getAddress() 
					+  " From : " + req.getClientAddress() 
					+ " Method : " + req.getMethod());
			log.trace("\tAccept : " + req.getValue("Accept") 
					+ " Accept-Charset : " + req.getValue("Accept-Charset") 
					+ " Accept-Encoding : " + req.getValue("Accept-Encoding") 
					+ " User-Agent : " + req.getValue("User-Agent") 
					+ " Locales : " + req.getLocales());
			log.trace("\tCookies: ");
			for(Cookie cookie: req.getCookies()){
				log.trace("\t\t " + cookie.toString());
			}
		}

		super.handle(req, resp);
	}
}
