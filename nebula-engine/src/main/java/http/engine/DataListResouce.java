package http.engine;

import http.json.JsonProvider.JsonSerializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import nebula.data.Entity;
import nebula.data.Store;

import org.simpleframework.http.Request;

import util.FileUtil;

public class DataListResouce extends BasicResouce {
	private final JsonSerializer<Entity> json;
	private final Store<Entity> datas;

	public DataListResouce(JsonSerializer<Entity> json, Store<Entity> datas) {
		this.json = json;
		this.datas = datas;
	}

	protected void makeResponse() {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(bout);

		boolean start = true;
		out.append('[');
		for (Entity data : datas.all()) {
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

	@Override
	protected void post(Request req) {
		try {
			Entity data = datas.createNew();
			InputStream in = req.getInputStream();
			if (log.isTraceEnabled()) {
				in = FileUtil.print(in);
			}
			json.readFrom(data, in);
			datas.add(data);
			datas.flush();
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}
