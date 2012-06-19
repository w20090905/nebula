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

public class DataResouceEngine implements ResourceEngine {
	private Log log = LogFactory.getLog(this.getClass());
	Persistence<Entity> persistence;

	Map<String, String> tmap = new HashMap<String, String>();

	@Inject
	public DataResouceEngine(Persistence<Entity> dataWareHouse) {
		this.persistence = dataWareHouse;
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

		JsonSerializer<Entity> json = JsonProvider.getSerialize(Entity.class);
		Store<Entity> datas = persistence.define(Entity.class, typeName);

		if (id != null) {
			return new DataResouce(json, datas, id);
		} else {
			return new DataListResouce(json, datas);
		}
	}

}
