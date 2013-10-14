package http.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import nebula.data.Broker;
import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.impl.EditableEntity;
import nebula.data.json.DataHelper;
import nebula.lang.NebulaNative;
import nebula.lang.RuntimeContext;
import nebula.lang.Type;

public class AttachedEntityNewResouce extends AbstractResouce {
	private final Broker<DataHelper<Entity, Reader, Writer>> jsonHolder;

	// private final Broker<DataStore<Entity>> datastoreHolder;
	final RuntimeContext context;
	final DataRepos dataRepos;
	final Broker<Type> typeBroker;final String attachedToTypeName;
	final String attachToID;

	public AttachedEntityNewResouce(final RuntimeContext context, final DataRepos dataRepos,Broker<DataHelper<Entity, Reader, Writer>> json, Broker<Type> typeBroker,Broker<DataStore<Entity>> datas,final String attachedToTypeName,
			final String attachToID) {
		super("text/json", 0, 0);
		this.jsonHolder = json;
		this.context = context;
		this.dataRepos = dataRepos;
		this.typeBroker = typeBroker;
		this.attachedToTypeName = attachedToTypeName;
		this.attachToID = attachToID;
	}

	@Override
	protected void get(HttpServletRequest req) throws IOException {
		Entity data = new EditableEntity();
		for (Map.Entry<String, String[]> entry : req.getParameterMap().entrySet()) {
			data.put(entry.getKey(), entry.getValue()[0]);
		}
		NebulaNative.ctor(context, dataRepos, data, typeBroker.get());
		// long newModified = (Long) data.get("LastModified_");
		// if (newModified == this.lastModified) return;
		/*StringBuilder sb = new StringBuilder();
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
		sb.setCharAt(sb.length() - 1, '}');*/
		


		ByteArrayOutputStream bout = null;
		try {
			bout = new ByteArrayOutputStream();
			Writer write = new OutputStreamWriter(bout);
			jsonHolder.get().stringifyTo(data, new OutputStreamWriter(bout));
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
