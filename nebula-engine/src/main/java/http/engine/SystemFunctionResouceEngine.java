package http.engine;

import http.HTTP;
import http.resource.LoginListResouce;
import http.resource.SignupResouce;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.impl.TypeDatastore;
import nebula.server.Resource;
import nebula.server.ResourceEngine;

public class SystemFunctionResouceEngine implements ResourceEngine {
	final private Map<String, Resource> cachedLinks = new HashMap<String, Resource>();
	final DataRepos repos;
	final TypeDatastore types;

	@Inject
	public SystemFunctionResouceEngine(DataRepos repos, TypeDatastore types) {
		this.repos = repos;
		this.types = types;

		DataStore<Entity> users = repos.define(String.class, Entity.class, HTTP.Type_User);
		DataStore<Entity> userAccessLogs = repos.define(Long.class, Entity.class, HTTP.Type_UserAccessLog);

		cachedLinks.put(HTTP.Href_Login, new LoginListResouce(users, userAccessLogs));
		cachedLinks.put(HTTP.Href_Signup, new SignupResouce(users, userAccessLogs));
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
