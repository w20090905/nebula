package http.resource;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import nebula.data.json.JsonEntityHelperProvider;
import nebula.lang.Type;
import nebula.lang.TypeLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Address;
import org.simpleframework.http.Request;

import util.FileUtil;

public class TypeEditableResouce extends AbstractResouce {
	private static Log log = LogFactory.getLog(TypeEditableResouce.class);
	private final String key;
	final TypeLoader typeLoader;

	public TypeEditableResouce(TypeLoader typeLoader, String key) {
		this.key = key;
		this.typeLoader = typeLoader;
	}

	@Override
	protected void get(Address address) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Type type = typeLoader.findType(key);
		JsonEntityHelperProvider.getSerialize(Type.class).stringifyTo(type, new OutputStreamWriter(out));
		out.flush();
		this.lastModified = System.currentTimeMillis();
		this.cache = out.toByteArray();
	}

	protected void put(Request req) throws IOException {
		BufferedInputStream bio = new BufferedInputStream(req.getInputStream());
		if (log.isTraceEnabled()) {
			log.trace("Input stream : ");
			log.trace(FileUtil.readAllTextFrom(bio));
		}

		Type type = typeLoader.findType(key);		
		String oldCode = type.getCode();
		String newCode = FileUtil.readAllTextFrom(bio);

		if (!oldCode.equals(newCode)) {
			if (log.isTraceEnabled()) {
				log.trace("Replace:");
				log.trace(oldCode);
				log.trace("  with: ");
				log.trace(type.getCode());
			}
			type = typeLoader.update(type, newCode);
			
		} else {
			if (log.isTraceEnabled()) {
				log.trace("No Change:");
				log.trace(type.getCode());
			}
		}

	}
}
