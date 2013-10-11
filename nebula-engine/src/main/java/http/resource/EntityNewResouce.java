package http.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import nebula.data.Broker;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.impl.EditableEntity;
import nebula.data.json.DataHelper;

public class EntityNewResouce extends AbstractResouce {
	private final Broker<DataHelper<Entity, Reader, Writer>> jsonHolder;

	// private final Broker<DataStore<Entity>> datastoreHolder;

	public EntityNewResouce(Broker<DataHelper<Entity, Reader, Writer>> json, Broker<DataStore<Entity>> datas) {
		super("text/json", 0, 0);
		this.jsonHolder = json;
	}

	@Override
	protected void get(HttpServletRequest req) throws IOException {
		Entity data = new EditableEntity();
		// long newModified = (Long) data.get("LastModified_");
		// if (newModified == this.lastModified) return;
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		for (Map.Entry<String, String[]> entry : req.getParameterMap().entrySet()) {
			sb.append('\"');
			sb.append(entry.getKey());
			sb.append('\"');
			sb.append(':');
			sb.append('\"');
			sb.append(entry.getValue()[0]);
			sb.append('\"');
			sb.append(',');
		}
		sb.setCharAt(sb.length() - 1, '}');

		ByteArrayOutputStream bout = null;
		try {
			bout = new ByteArrayOutputStream();
			Writer write = new OutputStreamWriter(bout);
			write.write(sb.toString());
			write.flush();
			// this.lastModified = newModified;
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
		throw new RuntimeException("Cann't find object ");
	}

	@Override
	protected void delete(HttpServletRequest req) {
		throw new RuntimeException("Cann't find object ");
	}
}
