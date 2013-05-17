package http.resource;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import nebula.data.Classificator;
import nebula.data.json.DataHelper;
import nebula.lang.Type;
import nebula.lang.TypeLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.FileUtil;

public class TypeListResouce extends AbstractResouce {
	private static Log log = LogFactory.getLog(TypeListResouce.class);
	private final DataHelper<Type, Reader, Writer> json;
	final TypeLoader typeLoader;
	final TypeFilterBuilder filterBuilder;

	protected byte[] cacheAll;

	public TypeListResouce(TypeLoader typeLoader, DataHelper<Type, Reader, Writer> json, TypeFilterBuilder filterBuilder) {
		super("text/json", 0, 1000);
		this.typeLoader = typeLoader;
		this.json = json;
		this.filterBuilder = filterBuilder;
	}

	@Override
	protected void get(HttpServletRequest req) {
		List<Type> dataList;

		String query = req.getQueryString();
		String classification;
		if (query != null && query.length() > 0 && (classification = req.getParameter("Standalone")) != null) {
			Classificator<String, Type> classificator = typeLoader.groupByStandalone();
			dataList = classificator.getData(classification);
		} else {
			throw new RuntimeException(
					"query != null && query.length() > 0 && (classification = req.getParameter(\"Standalone\"))!=null");
		}

		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(bout);

		boolean start = true;
		out.append('[');
		for (Type data : dataList) {
			if (!start) {
				out.append(',');
			} else {
				start = false;
			}
			json.stringifyTo(data, new OutputStreamWriter(out));
		}
		out.append(']');

		out.flush();
		out.close();
		this.cache = bout.toByteArray();

		if (req.getQueryString() == null || req.getQueryString().length() > 0) {
			this.cacheAll = bout.toByteArray();
		}
	}

	@Override
	protected String post(HttpServletRequest req) throws IOException {
		BufferedInputStream bio = new BufferedInputStream(req.getInputStream());
		if (log.isTraceEnabled()) {
			log.trace("Input stream : ");
			log.trace(FileUtil.readAllTextFrom(bio));
		}
		String newCode = FileUtil.readAllTextFrom(bio);
		Type newType = typeLoader.update(null, newCode);
		return req.getPathInfo() + newType.getName();
	}
}
