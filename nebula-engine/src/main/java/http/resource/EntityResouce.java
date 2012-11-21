package http.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Timestamp;

import nebula.data.DataHolder;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.json.JsonHelper;

import org.simpleframework.http.Address;
import org.simpleframework.http.Request;

public class EntityResouce extends AbstractResouce {
	private final JsonHelper<Entity> json;

	private final String key;
	private final DataHolder<DataStore<Entity>> datas;

	public EntityResouce(JsonHelper<Entity> json, DataHolder<DataStore<Entity>> datas, String key) {
		super("text/json", 0, 0);
		this.json = json;
		this.datas = datas;
		this.key = key;
	}

	@Override
	protected void get(Address address) {
		Entity data = datas.get().load(key);
		
		long newModified = ((Timestamp) data.get("LastModified_")).getTime();
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
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void put(Request req) {
		try {
			Entity data = datas.get().load(key).editable();
			if (data != null) {
				json.readFrom(data, new InputStreamReader(req.getInputStream()));
				datas.get().flush();
			} else {
				throw new RuntimeException("Cann't find object " + key);
			}
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void delete(Address address) {
		throw new RuntimeException("Cann't find object " + key);
	}
}
