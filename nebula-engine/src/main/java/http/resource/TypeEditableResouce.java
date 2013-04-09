package http.resource;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import nebula.data.DataPersister;
import nebula.data.Entity;
import nebula.data.json.DataHelper;
import nebula.data.json.JsonHelperProvider;
import nebula.lang.Type;
import nebula.lang.TypeLoader;

import org.simpleframework.http.Address;

import util.FileUtil;

public class TypeEditableResouce extends AbstractResouce {
	private final DataHelper<Type,Reader,Writer> json;
	private final String key;
	final TypeLoader typeLoader;
	final DataPersister<Entity> dataWareHouse;

	public TypeEditableResouce(final DataPersister<Entity> dataWareHouse, TypeLoader typeLoader, String key) {
		super("text/json", 60 * 60, 1000);
		this.dataWareHouse = dataWareHouse;
		this.key = key;
		this.typeLoader = typeLoader;
		this.json = JsonHelperProvider.getSerialize(Type.class);
	}

	@Override
	protected void get(Address address) throws IOException {
		Type data = typeLoader.findType(key);
		long newModified = data.getLastModified();
		if (newModified == this.lastModified) return;
		
		ByteArrayOutputStream bout = null;
		try {
			bout = new ByteArrayOutputStream();
			Writer w = new OutputStreamWriter(bout);
			json.stringifyTo(data, new OutputStreamWriter(bout));
			w.flush();
			this.lastModified = newModified;
			this.cache = bout.toByteArray();
		} catch (IOException e) {
			try {
				if (bout != null) bout.close();
			} catch (Exception e2) {
			}
			log.error("IOException",e);
			throw new RuntimeException(e);
		}
	}

	protected void put(Address target, HttpServletRequest req) throws IOException {
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
			dataWareHouse.reload(Entity.class,type.getName());
		} else {
			if (log.isTraceEnabled()) {
				log.trace("No Change:");
				log.trace(type.getCode());
			}
		}

	}
}
