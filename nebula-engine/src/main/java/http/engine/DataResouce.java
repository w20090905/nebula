package http.engine;

import http.json.JSON.JsonSerializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import nebula.SmartList;

public class DataResouce extends BasicResouce {
	@SuppressWarnings("rawtypes")
	private final JsonSerializer<Map> json;

	private final String key;
	private final SmartList<Map<String, String>> datas;
	

	@SuppressWarnings("rawtypes")
	public DataResouce(JsonSerializer<Map> json, SmartList<Map<String, String>> datas, String key) {
		this.json = json;
		this.datas = datas;
		this.key = key;
	}

	protected void make() {
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			Writer w = new OutputStreamWriter(bout);

			Map<String, String> data = datas.get(key);
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
}
