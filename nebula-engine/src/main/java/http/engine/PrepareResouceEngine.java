package http.engine;

import http.json.JsonProvider;
import http.json.JsonProvider.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import nebula.data.Entity;
import nebula.data.Persistence;
import nebula.data.Store;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Address;
import org.simpleframework.http.resource.Resource;
import org.simpleframework.http.resource.ResourceEngine;

public class PrepareResouceEngine implements ResourceEngine {
	private Log log = LogFactory.getLog(this.getClass());
	Persistence<Entity> persistence;

	Map<String, String> tmap = new HashMap<String, String>();

	@Inject
	public PrepareResouceEngine(Persistence<Entity> dataWareHouse) {
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

		JsonSerializer<Entity> json = JsonProvider.getSerialize(Entity.class);
		Store<Entity> users = persistence.define(Entity.class, "User");	
		Store<Entity> datas = persistence.define(Entity.class, "UserAccessLog");		
		return new LoginListResouce(json, users,datas);
	}

}
