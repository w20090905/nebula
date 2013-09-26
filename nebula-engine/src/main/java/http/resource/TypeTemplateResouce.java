package http.resource;

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
import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleCollection;
import freemarker.template.Template;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;

class DatastoreTemplateHashModel implements TemplateHashModel, TemplateCollectionModel {
	DataStore<Entity> datastore;

	public DatastoreTemplateHashModel(DataStore<Entity> dataWareHouse) {
		this.datastore = dataWareHouse;
	}

	@Override
	public TemplateModel get(String key) throws TemplateModelException {
		Entity entity = datastore.get(key);
		return ObjectWrapper.SIMPLE_WRAPPER.wrap(entity);
	}

	@Override
	public boolean isEmpty() throws TemplateModelException {
		return datastore.listAll().size() < 1;
	}

	@Override
	public TemplateModelIterator iterator() throws TemplateModelException {
		return new SimpleCollection(datastore.listAll().iterator()).iterator();
	}

}

class DataPersisterTemplateHashModel implements TemplateHashModel {
	DataRepos dataWareHouse;

	public DataPersisterTemplateHashModel(DataRepos dataWareHouse) {
		this.dataWareHouse = dataWareHouse;
	}

	@Override
	public TemplateModel get(String key) throws TemplateModelException {
		Broker<DataStore<Entity>> datastore = dataWareHouse.define(String.class, Entity.class, key);
		return new DatastoreTemplateHashModel(datastore.get());
	}

	@Override
	public boolean isEmpty() throws TemplateModelException {
		return false;
	}

}

public class TypeTemplateResouce extends AbstractResouce {
	final Configuration cfg;
	// private final String templateName;

	TemplateHashModel dataWareHouseModel;

	final Map<String, Object> root = new HashMap<String, Object>();
	final DataRepos dataWareHouse;
	final Broker<DataStore<Entity>> attributes;
	// final TypeLoader typeLoader;
	final String theme;
	final String skin;
	final Broker<Type> type;
	// final String actionName;
	final String name;

	public TypeTemplateResouce(Configuration cfg, DataRepos dataWareHouse, Broker<DataStore<Entity>> attributes, String theme, String skin, Broker<Type> type,
			String specName, String layoutName, String actionName) {
		this(cfg, dataWareHouse, attributes, theme, skin, type, makeName(type, specName, layoutName, actionName));
	}

	private static String makeName(Broker<Type> type, String specName, String layout, String actionName) {
		String entityType = (String) type.get().getStandalone().name().toLowerCase();

		layout = layout != null ? layout : (String) type.get().getAttrs().get("Layout");

		String name = entityType + "_" + layout.toLowerCase() + "_" + actionName.toLowerCase() + ".ftl";
		name = specName != null ? specName + "_" + name : name;
		return name;
	}

	public TypeTemplateResouce(Configuration cfg, DataRepos dataWareHouse, Broker<DataStore<Entity>> attributes, String theme, String skin, Broker<Type> type,
			String name) {
		super("text/template", 0, 0);// TODO Not realized TypeTemplateResouce
										// super("text/template", 0, 0)

		this.cfg = cfg;

		this.dataWareHouse = dataWareHouse;
		this.dataWareHouseModel = new DataPersisterTemplateHashModel(dataWareHouse);
		this.attributes = attributes;

		this.theme = theme;
		this.skin = skin;

		this.type = type;
		this.name = name;
	}

	protected void fillData() {
		root.put("type", layout(type.get()));

		DataStore<Entity> attrs = attributes.get();

		root.put("attrs", attrs);
		root.put("alldatas", dataWareHouseModel);
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
