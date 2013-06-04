package http.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import nebula.data.DataPersister;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.Holder;
import nebula.lang.Type;
import nebula.lang.TypeLoader;
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
		return datastore.all().size() < 1;
	}

	@Override
	public TemplateModelIterator iterator() throws TemplateModelException {
		return new SimpleCollection(datastore.all().iterator()).iterator();
	}

}

class DataPersisterTemplateHashModel implements TemplateHashModel {
	DataPersister<Entity> dataWareHouse;

	public DataPersisterTemplateHashModel(DataPersister<Entity> dataWareHouse) {
		this.dataWareHouse = dataWareHouse;
	}

	@Override
	public TemplateModel get(String key) throws TemplateModelException {
		Holder<DataStore<Entity>> datastore = dataWareHouse.define(String.class, Entity.class, key);
		return new DatastoreTemplateHashModel(datastore.get());
	}

	@Override
	public boolean isEmpty() throws TemplateModelException {
		return false;
	}

}

public class TypeTemplateResouce extends AbstractResouce {
	private final Configuration cfg;
	// private final String templateName;

	private Map<String, Object> root = new HashMap<String, Object>();
	final DataPersister<Entity> dataWareHouse;
	final TypeLoader typeLoader;
	final String theme;
	final String skin;
	final String typeName;
	final String actionName;
	final String name;
	final Holder<DataStore<Entity>> attributes;
	TemplateHashModel dataWareHouseModel;

	public TypeTemplateResouce(Configuration cfg, TypeLoader typeLoader, DataPersister<Entity> dataWareHouse,
			Holder<DataStore<Entity>> attributes, String theme, String skin, String typeName, String actionName) {
		super("text/template", 0, 0);// TODO

		this.cfg = cfg;
		this.typeLoader = typeLoader;

		this.theme = theme;
		this.skin = skin;
		this.typeName = typeName;
		this.actionName = actionName;

		this.dataWareHouse = dataWareHouse;

		this.dataWareHouseModel = new DataPersisterTemplateHashModel(dataWareHouse);

		Type type = this.typeLoader.findType(typeName);

		String dataType = (String) type.getStandalone().name().toLowerCase();
		String style = (String) type.getAttrs().get("Style");

		this.name = dataType + "_" + style + "_" + actionName + ".ftl";
		// this.templateName = templateTypeName + "-" + actionName + ".ftl";
		this.attributes = attributes;
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

			root.put("type", typeLoader.findType(this.typeName));

			DataStore<Entity> attrs = attributes.get();

			root.put("attrs", attrs);
			root.put("alldatas", dataWareHouseModel);
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
}
