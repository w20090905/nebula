package http.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import nebula.data.Holder;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.json.DataHelper;


public class EntityResouce extends AbstractResouce {
	private final Holder<DataHelper<Entity, Reader, Writer>> jsonHolder;

	private final String key;
	private final Holder<DataStore<Entity>> datastoreHolder;

	public EntityResouce(Holder<DataHelper<Entity, Reader, Writer>> json, Holder<DataStore<Entity>> datas,
			String key) {
		super("text/json", 0, 0);
		this.jsonHolder = json;
		this.datastoreHolder = datas;
		this.key = key;
	}

	@Override
	protected void get(HttpServletRequest req) throws IOException {
		Entity data = datastoreHolder.get().get(key);

		long newModified = ((DateTime) data.get("LastModified_")).getMillis();
		// if (newModified == this.lastModified) return;

		ByteArrayOutputStream bout = null;
		try {
			bout = new ByteArrayOutputStream();
			Writer write = new OutputStreamWriter(bout);
			jsonHolder.get().stringifyTo(data, new OutputStreamWriter(bout));
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
		Entity data = datastoreHolder.get().get(key).editable();
		if (data != null) {
			jsonHolder.get().readFrom(data, new InputStreamReader(req.getInputStream()));
			datastoreHolder.get().flush();
		} else {
			throw new RuntimeException("Cann't find object " + key);
		}
	}

	@Override
	protected void delete(HttpServletRequest req) {
		throw new RuntimeException("Cann't find object " + key);
	}
}
