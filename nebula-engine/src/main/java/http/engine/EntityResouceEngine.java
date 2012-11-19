package http.engine;

import http.json.JsonProvider;
import http.json.JsonProvider.JsonDealer;
import http.resource.EntityListResouce;
import http.resource.EntityResouce;
import http.resource.EntityFilterBuilder;

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

public class EntityResouceEngine implements ResourceEngine {
	private Log log = LogFactory.getLog(this.getClass());
	final Persistence<Entity> persistence;
	final EntityFilterBuilder filterBuilder;

	Map<String, String> tmap = new HashMap<String, String>();

	@Inject
	public EntityResouceEngine(final Persistence<Entity> dataWareHouse, final EntityFilterBuilder filterBuilder) {
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

		Store<Entity> datas = persistence.define(Entity.class, typeName);
		JsonDealer<Entity> json = JsonProvider.getSerialize(datas.getType());

		if (id != null) {
			return new EntityResouce(json, datas, id);
		} else {
			return new EntityListResouce(json, datas, filterBuilder);
		}
	}

}
