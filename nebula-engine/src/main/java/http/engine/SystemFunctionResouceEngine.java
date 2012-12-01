package http.engine;

import http.resource.LoginListResouce;

import javax.inject.Inject;

import nebula.data.DataHolder;
import nebula.data.DataPersister;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.json.JsonHelper;
import nebula.data.json.JsonHelperProvider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Address;
import org.simpleframework.http.resource.Resource;
import org.simpleframework.http.resource.ResourceEngine;

public class SystemFunctionResouceEngine implements ResourceEngine {
	private Log log = LogFactory.getLog(this.getClass());
	DataPersister<Entity> persistence;

	@Inject
	public SystemFunctionResouceEngine(DataPersister<Entity> dataWareHouse) {
		this.persistence = dataWareHouse;
	}

	@Override
	public Resource resolve(Address target) {
		String[] path = target.getPath().getSegments();
		String id = null;
		if (path.length > 2) {
			id = path[2];
		}

		if (log.isTraceEnabled()) {
			log.trace("target.getPath : " + path);
			log.trace("\tid : " + id);
		}

		JsonHelper<Entity> json = JsonHelperProvider.getSerialize(Entity.class);
		DataHolder<DataStore<Entity>> users = persistence.define(Entity.class, "User");	
		DataHolder<DataStore<Entity>> datas = persistence.define(Entity.class, "UserAccessLog");		
		return new LoginListResouce(json, users,datas);
	}

}
