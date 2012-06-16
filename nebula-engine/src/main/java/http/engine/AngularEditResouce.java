package http.engine;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import nebula.SmartList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.resource.Resource;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

public class AngularEditResouce implements Resource {
	private static Log log = LogFactory.getLog(AngularEditResouce.class);

	private final Configuration cfg;
	private final String templateName;

	private Map<String, Object> root = new HashMap<String, Object>();

	private final String key;
	private final SmartList<?> datas;

	public AngularEditResouce(Configuration cfg, String path, SmartList<?> datas, String key) {
		this.cfg = cfg;
		this.templateName = path + "_angularjs.ftl";
		this.datas = datas;
		this.key = key;
	}

	@Override
	public void handle(Request req, Response resp) {
		try {
			if (log.isTraceEnabled()) {
				log.trace("Request : " + req.getPath());
				log.trace("\ttemplateName : " + templateName);
				log.trace("\tkey : " + key);
			}
			// normal parse
			resp.setCode(200);
			resp.set("Cache-Control", "max-age=0");
			resp.set("Content-Language", "en-US");
			resp.set("Content-Type", "text/html");
			resp.setDate("Date", System.currentTimeMillis());
			root.put("data", datas.get(key));
			cfg.getTemplate(templateName).process(root, new OutputStreamWriter(resp.getOutputStream()));
			resp.getOutputStream().flush();
			resp.close();
		} catch (TemplateException e) {
			log.error(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}
