



package http.engine;

import http.json.JSON;
import http.server.PrintObejct;

import java.io.IOException;

import nebula.SmartList;
import nebula.lang.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.resource.Resource;

public class TypeEditResouce implements Resource {
	private static Log log = LogFactory.getLog(TypeEditResouce.class);
	private final String key;
	private final SmartList<Type> types;

	@SuppressWarnings("unchecked")
	public TypeEditResouce(String path, SmartList<?> datas, String key) {
		this.types = (SmartList<Type>)datas;
		this.key = key;
	}

	@Override
	public void handle(Request req, Response resp) {
		try {
			if ("GET".equals(req.getMethod())) {
				if (log.isTraceEnabled()) {
					log.trace("Request : " + req.getPath());
					log.trace("\tkey : " + key);
				}
				// normal parse
				resp.setCode(200);
				resp.set("Cache-Control", "max-age=0");
				resp.set("Content-Language", "en-US");
				resp.set("Content-Type", "text/html");
				resp.setDate("Date", System.currentTimeMillis());
				Type type = types.get(key);
				JSON.getSerialize(Type.class).stringifyTo(type, resp.getOutputStream());

				resp.getOutputStream().flush();
				resp.close();
			}else if("PUT".equals(req.getMethod())) {
				if (log.isTraceEnabled()) {
					log.trace("Request : " + req.getPath());
					log.trace("\tkey : " + key);
				}
				// normal parse
				resp.setCode(200);
				resp.set("Cache-Control", "max-age=0");
				resp.set("Content-Language", "en-US");
				resp.set("Content-Type", "text/html");
				resp.setDate("Date", System.currentTimeMillis());
//				Type type = types.get(key);
				
				PrintObejct.print(Request.class, req);
				
				resp.getOutputStream().flush();
				resp.close();
			}
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}
