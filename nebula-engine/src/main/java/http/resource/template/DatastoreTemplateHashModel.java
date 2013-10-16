package http.resource.template;

import nebula.data.DataStore;
import nebula.data.Entity;
import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleCollection;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;

public class DatastoreTemplateHashModel implements TemplateHashModel, TemplateCollectionModel {
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