package http.engine;

import http.json.JSON;

import java.io.IOException;

import nebula.SmartList;
import nebula.lang.SystemTypeLoader;
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
	final SystemTypeLoader typeLoader;

	@SuppressWarnings("unchecked")
	public TypeEditResouce(SystemTypeLoader typeLoader, String path, SmartList<?> datas, String key) {
		this.types = (SmartList<Type>) datas;
		this.key = key;
		this.typeLoader = typeLoader;
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
			} else if ("POST".equals(req.getMethod())) {
				if (log.isTraceEnabled()) {
					log.trace("Request : " + req.getPath());
					log.trace("\tkey : " + key);
				}

				Type type = types.get(key);
				JSON.getSerialize(Type.class).readFrom(type, req.getInputStream());
				typeLoader.reload(type);
				
				type = types.get(key);

				// normal parse
				resp.setCode(200);
				resp.set("Cache-Control", "max-age=0");
				resp.set("Content-Language", "en-US");
				resp.set("Content-Type", "text/html");
				resp.setDate("Date", System.currentTimeMillis());
				JSON.getSerialize(Type.class).stringifyTo(type, resp.getOutputStream());

				resp.close();
			}
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}
