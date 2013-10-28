package http.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.lang.TypeLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class StaticTemplateResouce extends AbstractResouce {
	private final Configuration cfg;

	private Map<String, Object> root = new HashMap<String, Object>();

	final TypeLoader typeLoader;
	final String theme;
	final String skin;
	final String name;
	final DataStore<Entity> attributes;

	public StaticTemplateResouce(Configuration cfg, TypeLoader typeLoader, DataStore<Entity> attributes, String theme, String skin, String name) {
		super("text/template", 1000, 1000);

		this.cfg = cfg;
		this.typeLoader = typeLoader;

		this.theme = theme;
		this.skin = skin;
		this.name = name;

		this.attributes = attributes;
	}

	protected void get(HttpServletRequest req) throws IOException {
		try {
			Template template = null;
			if ((template = cfg.getTemplate("theme/" + theme + "/" + skin + "/" + name)) != null) {
			} else if ((template = cfg.getTemplate("theme/" + theme + "/" + name)) != null) {
			} else if ((template = cfg.getTemplate("theme/" + name)) != null) {
			} else if ((template = cfg.getTemplate(name)) != null) {
			} else {
				throw new IOException(name + " can not find!");
			}

			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			Writer w = new OutputStreamWriter(bout);
			DataStore<Entity> attrs = attributes;
			root.put("attrs", attrs);
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
