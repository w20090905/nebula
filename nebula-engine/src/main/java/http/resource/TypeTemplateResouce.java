package http.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import nebula.data.DataHolder;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.lang.TypeLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TypeTemplateResouce extends AbstractResouce {
	private final Configuration cfg;
	// private final String templateName;

	private Map<String, Object> root = new HashMap<String, Object>();

	final TypeLoader typeLoader;
	final String theme;
	final String skin;
	final String typeName;
	final String actionName;
	final String name;
	final DataHolder<DataStore<Entity>> attributes;

	public TypeTemplateResouce(Configuration cfg, TypeLoader typeLoader, DataHolder<DataStore<Entity>> attributes,
			String theme, String skin, String typeName, String actionName) {
		super("text/template", 0, 0);// TODO

		this.cfg = cfg;
		this.typeLoader = typeLoader;

		this.theme = theme;
		this.skin = skin;
		this.typeName = typeName;
		this.actionName = actionName;
		this.name = actionName + ".ftl";
		// this.templateName = templateTypeName + "-" + actionName + ".ftl";
		this.attributes = attributes;
	}

	protected void get(HttpServletRequest req) throws IOException {
		try {
			TemplateLoader loader = cfg.getTemplateLoader();
			String templateName = null;

			if (loader.findTemplateSource( theme + "/" + skin + "/" + name) != null) {
				templateName = theme + "/" + skin + "/" + name;
			} else if (loader.findTemplateSource( theme + "/" + name) != null) {
				templateName = theme + "/" + name;
			} else if (loader.findTemplateSource(name) != null) {
				templateName = name;
			} else {
				throw new IOException(name + " can not find!");
			}

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
			throw new IOException(e);
		}
	}
}
