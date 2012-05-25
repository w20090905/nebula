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

public class DataListResouce implements Resource {
	private static Log log = LogFactory.getLog(DataListResouce.class);
	
	private final Configuration cfg;
	private final String templateName;
	private final SmartList<?> datalist;
	private Map<String,Object> root = new HashMap<String, Object>();
	public DataListResouce(Configuration cfg,String templateName,SmartList<?> datas){
		this.cfg = cfg;
		this.templateName = templateName + "_list.ftl";;
		this.datalist = datas;
	}
	
	@Override
	public void handle(Request req, Response resp) {
		try {if(log.isTraceEnabled()){
			log.trace("Request : " + req.getPath());
			log.trace("\ttemplateName : " + templateName);
		}
        // normal parse
		resp.setCode(200);
        resp.set("Cache-Control", "max-age=0");
        resp.set("Content-Language", "en-US");
        resp.set("Content-Type", "text/html");
        resp.setDate("Date", System.currentTimeMillis());
        root.put("data", datalist);
		cfg.getTemplate(templateName).process(root,new OutputStreamWriter(resp.getOutputStream()));
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
