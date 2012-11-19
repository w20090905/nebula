package http.resource;

import http.json.JsonProvider;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import nebula.lang.Type;
import nebula.lang.TypeLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import util.FileUtil;

public class TypeEditableResouce extends AbstractResouce{
	private static Log log = LogFactory.getLog(TypeEditableResouce.class);
	private final String key;
	final TypeLoader typeLoader;

	public TypeEditableResouce(TypeLoader typeLoader, String key) {
		this.key = key;
		this.typeLoader = typeLoader;
	}

	@Override
	public void handle(Request req, Response resp) {
		try {
			if ("GET".equals(req.getMethod())) {
				if (log.isTraceEnabled()) {
					log.trace("Request : " + req.getPath()+ "\tkey : " + key);
				}

				if (cache == null) {
					this.get(req);
				}

				// normal parse
				resp.setCode(200);
				resp.set("Cache-Control", "max-age=0");
				resp.set("Content-Language", "en-US");
				resp.set("Content-Type", "text/json");
				resp.set("Content-Length", cache.length);
				resp.setDate("Date", System.currentTimeMillis());
				resp.getOutputStream().write(cache);
				resp.getOutputStream().flush();
				resp.close();
			} else if ("POST".equals(req.getMethod()) || "PUT".equals(req.getMethod())) {
				if (log.isTraceEnabled()) {
					log.trace("Request : " + req.getPath());
					log.trace("\tkey : " + key);
				}
				BufferedInputStream bio = new BufferedInputStream(req.getInputStream());
				if (log.isTraceEnabled()) {
					log.trace("Input stream : ");
					log.trace(FileUtil.readAllTextFrom(bio));
				}

				Type type = typeLoader.findType(key);
				String oldCode = type.getCode();

				JsonProvider.getSerialize(Type.class).readFrom(type, new InputStreamReader(req.getInputStream()));
				// System.out.println(type.toString());
				if (!oldCode.equals(type.getCode())) {
					if (log.isTraceEnabled()) {
						log.trace("Replace:");
						log.trace(oldCode);
						log.trace("  with: ");
						log.trace(type.getCode());
					}

					typeLoader.update(type);
				} else {
					if (log.isTraceEnabled()) {
						log.trace("No Change:");
						log.trace(type.getCode());
					}
				}
				
				this.get(req);

				// normal parse
				resp.setCode(200);
				resp.set("Cache-Control", "max-age=0");
				resp.set("Content-Language", "en-US");
				resp.set("Content-Type", "text/json");
				resp.set("Content-Length", cache.length);
				resp.setDate("Date", System.currentTimeMillis());
				resp.getOutputStream().write(cache);
				resp.getOutputStream().flush();
				resp.close();
			}
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void get(Request req) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Type type = typeLoader.findType(key);
			JsonProvider.getSerialize(Type.class).stringifyTo(type, new OutputStreamWriter(out));
			out.flush();
			this.lastModified = System.currentTimeMillis();
			this.cache = out.toByteArray();
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}		
	}
}
