package http.engine;

import http.resource.LoginListResouce;
import http.resource.SignupResouce;

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
import nebula.server.Resource;
import nebula.server.ResourceEngine;

public class SystemFunctionResouceEngine implements ResourceEngine {
	final private Map<String, Resource> cachedLinks = new HashMap<String, Resource>();
	DataPersister<Entity> persistence;

	@Inject
	public SystemFunctionResouceEngine(DataPersister<Entity> dataWareHouse) {
		this.persistence = dataWareHouse;
		DataHelper<Entity, Reader, Writer> json = JsonHelperProvider.getSerialize(Entity.class);
		DataHolder<DataStore<Entity>> users = persistence.define(Entity.class, "User");
		DataHolder<DataStore<Entity>> datas = persistence.define(Entity.class, "UserAccessLog");

		cachedLinks.put("login", new LoginListResouce(json, users, datas));
		cachedLinks.put("signup", new SignupResouce(json, users));
	}

	@Override
	public Resource resolve(String path) {
		String[] paths = path.split("/");
		String id = null;
		if (paths.length > 2) {
			id = paths[2];
		}
		return cachedLinks.get(id);
	}

}
