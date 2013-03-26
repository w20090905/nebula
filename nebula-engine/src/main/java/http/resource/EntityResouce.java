package http.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Timestamp;

import nebula.data.DataHolder;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.json.DataHelper;

import org.simpleframework.http.Address;
import org.simpleframework.http.Request;

public class EntityResouce extends AbstractResouce {
	private final DataHolder<DataHelper<Entity,Reader,Writer>> jsonHolder;

	private final String key;
	private final DataHolder<DataStore<Entity>> datastoreHolder;

	public EntityResouce(DataHolder<DataHelper<Entity,Reader,Writer>> json, DataHolder<DataStore<Entity>> datas, String key) {
		super("text/json", 0, 0);
		this.jsonHolder = json;
		this.datastoreHolder = datas;
		this.key = key;
	}

	@Override
	protected void get(Address address) {
		Entity data = datastoreHolder.get().get(key);
		
		long newModified = ((Timestamp) data.get("LastModified_")).getTime();
//		if (newModified == this.lastModified) return;
		
		ByteArrayOutputStream bout = null;
		try {
			bout = new ByteArrayOutputStream();
			Writer w = new OutputStreamWriter(bout);
			jsonHolder.get().stringifyTo(data, new OutputStreamWriter(bout));
			w.flush();
			this.lastModified = newModified;
			this.cache = bout.toByteArray();
		} catch (IOException e) {
			try {
				if (bout != null) bout.close();
			} catch (Exception e2) {
			}
			log.error(e.getClass().getName(),e);
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void put(Request req) {
		try {
			Entity data = datastoreHolder.get().get(key).editable();
			if (data != null) {
				jsonHolder.get().readFrom(data, new InputStreamReader(req.getInputStream()));
				datastoreHolder.get().flush();
			} else {
				throw new RuntimeException("Cann't find object " + key);
			}
		} catch (IOException e) {
			log.error("IOException" + req.getAddress().getPath());
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void delete(Address address) {
		throw new RuntimeException("Cann't find object " + key);
	}
}
