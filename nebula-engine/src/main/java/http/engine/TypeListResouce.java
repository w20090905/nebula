package http.engine;

import http.json.JSON;

import java.io.IOException;
import java.io.PrintStream;

import nebula.SmartList;
import nebula.lang.SystemTypeLoader;
import nebula.lang.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.resource.Resource;

public class TypeListResouce implements Resource {
	private static Log log = LogFactory.getLog(TypeListResouce.class);
	private final SmartList<Type> types;
	final SystemTypeLoader typeLoader;

	@SuppressWarnings("unchecked")
	public TypeListResouce(SystemTypeLoader typeLoader, SmartList<?> datas) {
		this.types = (SmartList<Type>) datas;
		this.typeLoader = typeLoader;
	}

	@Override
	public void handle(Request req, Response resp) {
		try {
			if ("GET".equals(req.getMethod())) {
				if (log.isTraceEnabled()) {
					log.trace("Request : " + req.getPath());
				}
				// normal parse
				resp.setCode(200);
				resp.set("Cache-Control", "max-age=0");
				resp.set("Content-Language", "en-US");
				resp.set("Content-Type", "text/html");
				resp.setDate("Date", System.currentTimeMillis());
				PrintStream out = resp.getPrintStream();
				boolean start=true;
				out.append('[');
				for(Type type: types){
					if(!start){
						out.append(',');
					}else{
						start=false;
					}
					JSON.getSerialize(Type.class).stringifyTo(type, out);	
				}
				out.append(']');

				out.flush();
				out.close();
				resp.close();
			} else if ("POST".equals(req.getMethod())) {
				resp.setCode(404);
			}
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}
