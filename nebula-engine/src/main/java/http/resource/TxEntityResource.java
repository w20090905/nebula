package http.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.json.DataHelper;

public class TxEntityResource extends AbstractResouce {
	private final DataHelper<Entity, Reader, Writer> jsonHolder;

	private final Long key;
	private final DataStore<Entity> datastoreHolder;

	public TxEntityResource(DataHelper<Entity, Reader, Writer> json, DataStore<Entity> datas, String key) {
		this(json, datas, Long.parseLong(key));
	}

	public TxEntityResource(DataHelper<Entity, Reader, Writer> json, DataStore<Entity> datas, Long key) {
		super("text/json", 0, 0);
		this.jsonHolder = json;
		this.datastoreHolder = datas;
		this.key = key;
	}

	@Override
	protected void get(HttpServletRequest req) throws IOException {
		Entity data = datastoreHolder.get(key);

		long newModified = (Long) data.get("LastModified_");
		// if (newModified == this.lastModified) return;

		ByteArrayOutputStream bout = null;
		try {
			bout = new ByteArrayOutputStream();
			Writer write = new OutputStreamWriter(bout);
			jsonHolder.stringifyTo(data, new OutputStreamWriter(bout));
			write.flush();
			this.lastModified = newModified;
			this.cache = bout.toByteArray();
		} finally {
			try {
				if (bout != null) bout.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	protected void put(HttpServletRequest req) throws IOException {
		Entity data = datastoreHolder.get(key).editable();
		if (data != null) {
			jsonHolder.readFrom(data, new InputStreamReader(req.getInputStream()));
			datastoreHolder.flush();
		} else {
			throw new RuntimeException("Cann't find object " + key);
		}
	}

	@Override
	protected void delete(HttpServletRequest req) {
		throw new RuntimeException("Cann't find object " + key);
	}
}
