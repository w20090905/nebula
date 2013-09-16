package http.engine;

import http.resource.LoginListResouce;
import http.resource.SignupResouce;

import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import nebula.data.Broker;
import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.impl.TypeDatastore;
import nebula.data.json.DataHelper;
import nebula.data.json.JsonHelperProvider;
import nebula.lang.Type;
import nebula.server.Resource;
import nebula.server.ResourceEngine;

public class SystemFunctionResouceEngine implements ResourceEngine {
	final private Map<String, Resource> cachedLinks = new HashMap<String, Resource>();
	final DataRepos persistence;
	final TypeDatastore typeBrokers;

	@Inject
	public SystemFunctionResouceEngine(DataRepos dataWareHouse, TypeDatastore typeBrokers) {
		this.persistence = dataWareHouse;
		this.typeBrokers = typeBrokers;
		
		String typeUserName = "User";
		String typeUserAccessLogName = "UserAccessLog";
		Broker<Type> typeBrokerUser = typeBrokers.getBroker(typeUserName);
		Broker<Type> typeBrokerAccessLog = typeBrokers.getBroker(typeUserAccessLogName);
		Broker<DataHelper<Entity, Reader, Writer>> jsonBrokerUser = JsonHelperProvider.getHelper(typeBrokerUser);
		Broker<DataHelper<Entity, Reader, Writer>> jsonBrokerAccessLog = JsonHelperProvider.getHelper(typeBrokerAccessLog);
		Broker<DataStore<Entity>> users = persistence.define(String.class, Entity.class, "User");
		Broker<DataStore<Entity>> datas = persistence.define(Long.class, Entity.class, "UserAccessLog");

		cachedLinks.put("login", new LoginListResouce(jsonBrokerUser, users, datas));
		cachedLinks.put("signup", new SignupResouce(jsonBrokerAccessLog, users));
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
