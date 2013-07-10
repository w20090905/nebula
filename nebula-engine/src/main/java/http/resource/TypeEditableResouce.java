package http.resource;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import nebula.data.DataRepos;
import nebula.data.json.DataHelper;
import nebula.data.json.JsonHelperProvider;
import nebula.lang.Type;
import nebula.lang.TypeLoader;
import util.FileUtil;

public class TypeEditableResouce extends AbstractResouce {
	private final DataHelper<Type, Reader, Writer> json;
	private final String key;
	final TypeLoader typeLoader;
	final DataRepos dataWareHouse;

	public TypeEditableResouce(final DataRepos dataWareHouse, TypeLoader typeLoader, String key) {
		super("text/json", 60 * 60, 1000);
		this.dataWareHouse = dataWareHouse;
		this.key = key;
		this.typeLoader = typeLoader;
		this.json = JsonHelperProvider.getSerialize(Type.class);
	}

	@Override
	protected void get(HttpServletRequest req) throws IOException {
		Type data = typeLoader.findType(key);
		long newModified = data.getLastModified();
		// if (newModified == this.lastModified) return;

		ByteArrayOutputStream bout = null;
		try {
			bout = new ByteArrayOutputStream();
			Writer w = new OutputStreamWriter(bout);
			json.stringifyTo(data, new OutputStreamWriter(bout));
			w.flush();
			this.lastModified = newModified;
			this.cache = bout.toByteArray();
		} finally {
			try {
				if (bout != null) bout.close();
			} catch (Exception e2) {
			}
		}
	}

	@Override
	protected void put(HttpServletRequest req) throws IOException {
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
