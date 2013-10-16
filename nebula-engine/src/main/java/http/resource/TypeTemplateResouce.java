package http.resource;

import http.resource.template.LoadDataMethod;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import nebula.data.Broker;
import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.lang.Field;
import nebula.lang.Type;


import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateMethodModel;

public class TypeTemplateResouce extends AbstractResouce {
	final Configuration cfg;
	// private final String templateName;

	TemplateMethodModel dataWareHouseModel;

	final Map<String, Object> root = new HashMap<String, Object>();
	final DataRepos dataWareHouse;
	final Broker<DataStore<Entity>> attributes;
	final String path;
	// final TypeLoader typeLoader;
	final String theme;
	final String skin;
	final Broker<Type> type;
	// final String actionName;
	final String name;

	public TypeTemplateResouce(Configuration cfg, DataRepos dataWareHouse, Broker<DataStore<Entity>> attributes, String path, String theme, String skin,
			Broker<Type> type, String specName, String layoutName, String actionName) {
		this(cfg, dataWareHouse, attributes, path, theme, skin, type, makeName(type, specName, layoutName, actionName));
	}

	private static String makeName(Broker<Type> type, String specName, String layout, String actionName) {
		String entityType = (String) type.get().getStandalone().name().toLowerCase();

		layout = layout != null ? layout : (String) type.get().getAttrs().get("Layout");

		String name = entityType + "_" + layout.toLowerCase() + "_" + actionName.toLowerCase() + ".ftl";
		name = specName != null ? specName + "_" + name : name;
		return name;
	}

	public TypeTemplateResouce(Configuration cfg, DataRepos dataWareHouse, Broker<DataStore<Entity>> attributes, String path, String theme, String skin,
			Broker<Type> type, String name) {
		super("text/template", 0, 0);// TODO Not realized TypeTemplateResouce
										// super("text/template", 0, 0)

		this.cfg = cfg;

		this.dataWareHouse = dataWareHouse;
		this.dataWareHouseModel = new LoadDataMethod(dataWareHouse);
		this.attributes = attributes;

		this.path = path;

		this.theme = theme;
		this.skin = skin;

		this.type = type;
		this.name = name;

		root.put("alldatas", dataWareHouseModel);
		root.put("_path", path);
		root.put("_theme", this.theme);
		root.put("_skin", this.skin);
		root.put("_spec", "admin");
	}

	protected void fillData() {
		root.put("type", layout(type.get()));
		DataStore<Entity> attrs = attributes.get();
		root.put("attrs", attrs);
	}

	protected void get(HttpServletRequest req) throws IOException {
		try {
			TemplateLoader loader = cfg.getTemplateLoader();
			String templateName = null;

			if (loader.findTemplateSource(theme + "/" + skin + "/" + name) != null) {
				templateName = theme + "/" + skin + "/" + name;
			} else if (loader.findTemplateSource(theme + "/" + name) != null) {
				templateName = theme + "/" + name;
			} else if (loader.findTemplateSource(name) != null) {
				templateName = name;
			} else {
				throw new IOException(name + " can not find!");
			}

			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			Writer w = new OutputStreamWriter(bout);

			fillData();

			Template template = cfg.getTemplate(templateName);
			template.process(root, w);
			w.flush();
			w.close();

			super.lastModified = System.currentTimeMillis();
			super.cache = bout.toByteArray();
		} catch (TemplateException e) {
			log.error("Template prase error", e);
			throw new IOException(e);
		}
	}

	private Type layout(Type type) {
		List<Field> fields = type.getFields();
		Field lastField = null;
		for (int i = 0; i < fields.size(); i++) {
			Field field = fields.get(i);
			if (field.getAttrs().containsKey("SingleLine")) {
				if (lastField != null) {
					lastField.getAttrs().remove("HasFollowing");
				}
				lastField = null;
			} else {
				if (lastField != null) {
					if (field.getAttrs().containsKey("ShouldBeLeader")) {
						lastField.getAttrs().remove("HasFollowing");
					} else {
						lastField.getAttrs().put("HasFollowing", "HasFollowing");
					}
				}
				lastField = field;
			}
		}
		return type;
	}
}
