package http.engine;

import http.resource.EntityFilterBuilder;
import http.resource.EntityListResouce;
import http.resource.EntityResouce;

import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import nebula.data.DataHolder;
import nebula.data.DataPersister;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.json.DataHelper;
import nebula.data.json.JsonHelperProvider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Address;
import org.simpleframework.http.resource.Resource;
import org.simpleframework.http.resource.ResourceEngine;

public class EntityResouceEngine implements ResourceEngine {
	private Log log = LogFactory.getLog(this.getClass());
	final DataPersister<Entity> persistence;
	final EntityFilterBuilder filterBuilder;

	Map<String, String> tmap = new HashMap<String, String>();

	@Inject
	public EntityResouceEngine(final DataPersister<Entity> dataWareHouse, final EntityFilterBuilder filterBuilder) {
		this.persistence = dataWareHouse;
		this.filterBuilder = filterBuilder;
	}

	@Override
	public Resource resolve(Address target) {
		String[] path = target.getPath().getSegments();
		String typeName = path[1];
		String id = null;
		if (path.length > 2) {
			id = path[2];
		}

		if (log.isTraceEnabled()) {
			log.trace("target.getPath : " + path);
			log.trace("\ttypeName : " + typeName);
			log.trace("\tid : " + id);
		}

		DataHolder<DataStore<Entity>> storeHolder = persistence.define(Entity.class, typeName);
		DataHolder<DataHelper<Entity,Reader,Writer>> jsonHolder = JsonHelperProvider.getHelper(storeHolder);

		if (id != null) {
			return new EntityResouce(jsonHolder, storeHolder, id);
		} else {
			return new EntityListResouce(jsonHolder, storeHolder, filterBuilder);
		}
	}

}
