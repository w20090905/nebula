package http.resource.template;

import java.util.List;

import nebula.data.Broker;
import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;

import com.google.common.base.Preconditions;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.CollectionModel;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class LoadDataMethod implements TemplateMethodModel {
	DataRepos dataWareHouse;

	public LoadDataMethod(DataRepos dataWareHouse) {
		this.dataWareHouse = dataWareHouse;
	}

	@SuppressWarnings("rawtypes")
	public TemplateModel exec(List args) throws TemplateModelException {
		if (args.size() == 1) {
			Preconditions.checkNotNull(args.get(0));
			return this.doLoad((String) args.get(0));
		} else if (args.size() == 3) {
			Preconditions.checkNotNull(args.get(0));
			Preconditions.checkNotNull(args.get(1));
			Preconditions.checkNotNull(args.get(2));
			return this.doLoad((String) args.get(0), (String) args.get(1), (String) args.get(2));
		}
		return null;
	}

	public TemplateModel doLoad(String key) throws TemplateModelException {
		Broker<DataStore<Entity>> datastore = dataWareHouse.define(String.class, Entity.class, key);
		return new DatastoreTemplateHashModel(datastore.get());
	}

	public TemplateModel doLoad(String key, String classificatorName, String classificatorValue) throws TemplateModelException {
		Broker<DataStore<Entity>> datastore = dataWareHouse.define(String.class, Entity.class, key);
		List<Entity> list = datastore.get().getClassificator(classificatorName).getData(classificatorValue);
		return new CollectionModel(list, (BeansWrapper) ObjectWrapper.SIMPLE_WRAPPER);
	}
}
