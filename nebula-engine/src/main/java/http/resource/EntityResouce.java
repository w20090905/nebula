package http.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.impl.EditableEntity;
import nebula.data.json.DataHelper;
import nebula.lang.Field;
import nebula.lang.NebulaNative;
import nebula.lang.RuntimeContext;
import nebula.lang.Type;

public class EntityResouce extends AbstractResouce {
	private final DataHelper<Entity, Reader, Writer> json;
	final DataRepos dataRepos;
	private final String key;
	private final Type type;
	private final DataStore<Entity> datastore;

	public EntityResouce(final DataRepos dataRepos, DataHelper<Entity, Reader, Writer> json, DataStore<Entity> datas, Type type, String key) {
		super("text/json", 1, 1);
		this.dataRepos = dataRepos;
		this.json = json;
		this.datastore = datas;
		this.type = type;
		this.key = key;
	}

	@Override
	protected void get(HttpServletRequest req) throws IOException {
		Entity data;
		String action = req.getParameter("$action");
		long newModified;
		if (action == null) {
			data = datastore.get(key);
			newModified = (Long) data.get("LastModified_");
		} else {
			EditableEntity editableData = new EditableEntity();
			for (Map.Entry<String, String[]> e : req.getParameterMap().entrySet()) {
				if (e.getKey().startsWith("data.")) {
					String fieldName = e.getKey().substring("data.".length());
					Field field = type.getField(fieldName);
					switch (field.getType().getRawType()) {
					case String:
					case Text:
						editableData.put(fieldName, e.getValue()[0]);
						break;
					case Long:
						editableData.put(fieldName, Long.parseLong(e.getValue()[0]));
						break;
					case Boolean:
						editableData.put(fieldName, Boolean.parseBoolean(e.getValue()[0]));
						break;
					case Date:
					case Datetime:
					case Decimal:
					case Time:
					case Timestamp:
					default:
					}
				}
			}
			RuntimeContext context = new RuntimeContext();
			NebulaNative.execOnChangeMethod(context, dataRepos, editableData, type, action.substring("data.".length()));
			data = editableData;
			newModified = System.currentTimeMillis();
		}

		ByteArrayOutputStream bout = null;
		try {
			bout = new ByteArrayOutputStream();
			Writer write = new OutputStreamWriter(bout);
			json.stringifyTo(data, new OutputStreamWriter(bout));
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

		Entity data = datastore.get(key).editable();
		if (data != null) {
			json.readFrom(data, new InputStreamReader(req.getInputStream()));
			String action = req.getParameter("$action");
			if (action != null && !"save".equals(action)) {
				RuntimeContext context = new RuntimeContext();
				NebulaNative.execMethod(context, dataRepos, data, type, action);
			}
			datastore.flush();
		} else {
			throw new RuntimeException("Cann't find object " + key);
		}
	}

	@Override
	protected void delete(HttpServletRequest req) {
		throw new RuntimeException("Cann't find object " + key);
	}
}
