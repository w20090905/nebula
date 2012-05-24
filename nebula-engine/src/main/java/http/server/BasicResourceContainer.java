package http.server;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

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
	
	private List<ResourceEngine> engines;
	private List<Pattern> patterns;
	
	@Inject
	public BasicResourceContainer(ResourceEngine engine,Configure<BasicResourceContainer> conf) {
		super(engine);
		patterns = new CopyOnWriteArrayList<>();
		engines = new CopyOnWriteArrayList<>();
		conf.configure(this);
	}
	
	public void register(String match,ResourceEngine engine){
		patterns.add(Pattern.compile(match));
		engines.add(engine);
	}
	
	@Override
	public void handle(Request req, Response resp) {
		if(log.isTraceEnabled()){
			log.trace(req.getAddress() 
					+ " From : " + req.getClientAddress() 
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
		String path = req.getAddress().getPath().getPath();
		
		ResourceEngine engine=null;
		
		for (int i = 0; i < patterns.size(); i++) {
			if(patterns.get(i).matcher(path).matches()){
				engine = engines.get(0);	
			    engine.resolve(req.getAddress()).handle(req,resp);
			    return;
			}			
		}
		super.handle(req, resp);
	}
}
