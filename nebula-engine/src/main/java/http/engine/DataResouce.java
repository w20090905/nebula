package http.engine;

import http.json.JsonProvider.JsonSerializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.simpleframework.http.Request;

import nebula.data.Entity;
import nebula.data.Store;

public class DataResouce extends BasicResouce {
	private final JsonSerializer<Entity> json;

	private final String key;
	private final Store<Entity> datas;

	public DataResouce(JsonSerializer<Entity> json, Store<Entity> datas, String key) {
		this.json = json;
		this.datas = datas;
		this.key = key;
	}

	protected void makeResponse() {
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			Writer w = new OutputStreamWriter(bout);

			Entity data = datas.load(key);
			if (data != null) {
				json.stringifyTo(data, bout);
				w.flush();
				w.close();
				this.lastModified = System.currentTimeMillis();
				this.buffer = bout.toByteArray();
			} else {
				this.lastModified = System.currentTimeMillis();
				this.buffer = new byte[0];
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
				json.readFrom(data, req.getInputStream());
				datas.flush();
			} else {
				throw new RuntimeException("Cann't find object " + key);
			}
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}
