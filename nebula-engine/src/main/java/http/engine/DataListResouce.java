package http.engine;

import http.json.JSON.JsonSerializer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import nebula.SmartList;

public class DataListResouce extends BasicResouce {
	@SuppressWarnings("rawtypes")
	private final JsonSerializer<Map> json;
	private final SmartList<Map<String, String>> datas;

	@SuppressWarnings("rawtypes")
	public DataListResouce(JsonSerializer<Map> json, SmartList<Map<String, String>> datas) {
		this.json = json;
		this.datas = datas;
	}

	protected void make() {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(bout);

		boolean start = true;
		out.append('[');
		for (Map<String, String> data : datas) {
			if (!start) {
				out.append(',');
			} else {
				start = false;
			}
			json.stringifyTo(data, out);
		}
		out.append(']');

		out.flush();
		out.close();
		this.lastModified = System.currentTimeMillis();
		this.buffer = bout.toByteArray();
	}
}
