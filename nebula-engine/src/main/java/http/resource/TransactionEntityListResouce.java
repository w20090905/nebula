package http.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import nebula.data.Classificator;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.Holder;
import nebula.data.json.DataHelper;
import nebula.data.util.RecentDateClassificatorFunction;

import org.joda.time.DateTime;

import util.FileUtil;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;

public class TransactionEntityListResouce extends AbstractResouce {
	private final Holder<DataHelper<Entity, Reader, Writer>> jsonHolder;
	private final Holder<DataStore<Entity>> datastoreHolder;
	final EntityFilterBuilder filterBuilder;
	private final Classificator<String, Entity> classificator;
	@SuppressWarnings("unused")
	private final Map<String, Classificator<String, Entity>> datasbufferes;
	
	private static final String DATEMATCH = "Date=";

	public TransactionEntityListResouce(Holder<DataHelper<Entity, Reader, Writer>> json,
			Holder<DataStore<Entity>> datas, final EntityFilterBuilder filterBuilder) {
		super("text/json", 0, 1000);
		this.jsonHolder = json;
		this.datastoreHolder = datas;
		this.filterBuilder = filterBuilder;

		this.classificator = this.datastoreHolder.get().classify(new Function<Entity, String>() {
			Function<DateTime, String> convertFunction = new RecentDateClassificatorFunction();

			@Override
			public String apply(Entity from) {
				return convertFunction.apply( new DateTime((Long)from.get("LastModified_")));
			}
		});
		datasbufferes = Maps.newHashMap();
	}

	protected void get(HttpServletRequest req) {
		List<Entity> dataList;

		String query = req.getQueryString();
		
		if (query== null || query.length() == 0) {
			dataList = datastoreHolder.get().all();
		} else if(query.startsWith(DATEMATCH)) {
			String classification = query.substring(DATEMATCH.length());
			dataList = classificator.getData(classification);
		}else{
			Predicate<Entity> filter = filterBuilder.buildFrom(req.getParameterMap(), null);
			dataList = datastoreHolder.get().filter(filter);
		}

		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(bout);

		boolean start = true;
		out.append('[');
		for (Entity data : dataList) {
			if (!start) {
				out.append(',');
			} else {
				start = false;
			}
			jsonHolder.get().stringifyTo(data, new OutputStreamWriter(out));
		}
		out.append(']');

		out.flush();
		out.close();
		this.lastModified = System.currentTimeMillis();
		this.cache = bout.toByteArray();
	}

	@Override
	protected String post(HttpServletRequest req) throws IOException {
		DataStore<Entity> store = datastoreHolder.get();
		InputStream in = req.getInputStream();
		if (log.isTraceEnabled()) {
			in = FileUtil.print(in);
		}
		Entity inData = jsonHolder.get().readFrom(null, new InputStreamReader(in));

		store.add(inData);
		store.flush();
		return req.getPathInfo() + inData.getID();
	}
}
