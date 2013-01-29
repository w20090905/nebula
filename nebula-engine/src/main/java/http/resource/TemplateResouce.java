package http.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import nebula.data.DataHolder;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.lang.TypeLoader;

import org.simpleframework.http.Address;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TemplateResouce extends AbstractResouce {
	private final Configuration cfg;
	private final String templateName;

	private Map<String, Object> root = new HashMap<String, Object>();

	final TypeLoader typeLoader;
	final String templateTypeName;
	final String typeName;
	final String actionName;
	final DataHolder<DataStore<Entity>>  attributes;
	
	public TemplateResouce(Configuration cfg,TypeLoader typeLoader,DataHolder<DataStore<Entity>>  attributes, String templateTypeName, String typeName,
			String actionName) {
		super("text/template", 0, 0);//TODO 
		
		this.cfg = cfg;
		this.typeLoader = typeLoader;

		this.templateTypeName = templateTypeName;
		this.typeName = typeName;
		this.actionName = actionName;

		this.templateName = templateTypeName + "-" + actionName + ".ftl";
		this.attributes = attributes;
	}

	protected void get(Address address) {
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			Writer w = new OutputStreamWriter(bout);
			
			root.put("type", typeLoader.findType(this.typeName));
	
			DataStore<Entity> attrs = attributes.get();
			
			root.put("attrs", attrs);
			Template template = cfg.getTemplate(templateName);
			template.process(root, w);
			w.flush();
			w.close();
			
			super.lastModified = System.currentTimeMillis();
			super.cache = bout.toByteArray();
		} catch (TemplateException e) {
			log.error(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}
