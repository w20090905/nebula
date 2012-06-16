package http.engine;

import http.json.JSON;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import nebula.SmartList;
import nebula.lang.Type;
import nebula.lang.TypeLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.resource.Resource;

import util.FileUtil;

public class TypeEditResouce implements Resource {
	private static Log log = LogFactory.getLog(TypeEditResouce.class);
	private final String key;
	private final SmartList<Type> types;
	final TypeLoader typeLoader;

	byte[] jsonType = null;

	@SuppressWarnings("unchecked")
	public TypeEditResouce(TypeLoader typeLoader, SmartList<?> datas, String key) {
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

				if (jsonType == null) {
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					Type type = typeLoader.findType(key);
					JSON.getSerialize(Type.class).stringifyTo(type, out);
					out.flush();
					this.jsonType = out.toByteArray();
				}

				// normal parse
				resp.setCode(200);
				resp.set("Cache-Control", "max-age=0");
				resp.set("Content-Language", "en-US");
				resp.set("Content-Type", "text/json");
				resp.set("Content-Length", jsonType.length);
				resp.setDate("Date", System.currentTimeMillis());
				resp.getOutputStream().write(jsonType);
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

				JSON.getSerialize(Type.class).readFrom(type, req.getInputStream());
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

				ByteArrayOutputStream out = new ByteArrayOutputStream();
				type = typeLoader.findType(key);
				JSON.getSerialize(Type.class).stringifyTo(type, out);
				out.flush();
				this.jsonType = out.toByteArray();

				// normal parse
				resp.setCode(200);
				resp.set("Cache-Control", "max-age=0");
				resp.set("Content-Language", "en-US");
				resp.set("Content-Type", "text/json");
				resp.set("Content-Length", jsonType.length);
				resp.setDate("Date", System.currentTimeMillis());
				resp.getOutputStream().write(jsonType);
				resp.getOutputStream().flush();
				resp.close();
			}
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}
