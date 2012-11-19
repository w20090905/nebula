package http.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import nebula.data.Entity;
import nebula.data.Store;
import nebula.data.json.JsonHelper;

import org.simpleframework.http.Address;
import org.simpleframework.http.Request;

public class EntityResouce extends AbstractResouce {
	private final JsonHelper<Entity> json;

	private final String key;
	private final Store<Entity> datas;

	public EntityResouce(JsonHelper<Entity> json, Store<Entity> datas, String key) {
		this.json = json;
		this.datas = datas;
		this.key = key;
	}

	@Override
	protected void get(Address address) {
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			Writer w = new OutputStreamWriter(bout);

			Entity data = datas.load(key);
			if (data != null) {
				json.stringifyTo(data, new OutputStreamWriter(bout));
				w.flush();
				w.close();
				this.lastModified = System.currentTimeMillis();
				this.cache = bout.toByteArray();
			} else {
				this.lastModified = System.currentTimeMillis();
				this.cache = new byte[0];
			}
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void put(Request req) {
		try {
			Entity data = datas.load(key).editable();
			if (data != null) {
				json.readFrom(data, new InputStreamReader(req.getInputStream()));
				datas.flush();
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
