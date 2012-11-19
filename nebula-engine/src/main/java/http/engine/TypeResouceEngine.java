package http.engine;

import http.json.JsonProvider;
import http.json.JsonProvider.JsonDealer;
import http.resource.TypeEditableResouce;
import http.resource.TypeFilterBuilder;
import http.resource.TypeListResouce;

import javax.inject.Inject;

import nebula.frame.DataWareHouse;
import nebula.lang.EditableTypeLoader;
import nebula.lang.Type;
import nebula.lang.TypeLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Address;
import org.simpleframework.http.resource.Resource;
import org.simpleframework.http.resource.ResourceEngine;

public class TypeResouceEngine implements ResourceEngine {
	private Log log = LogFactory.getLog(this.getClass());
	final DataWareHouse dataWareHouse;
	final TypeLoader typeLoader;
	final TypeFilterBuilder filterBuilder;

	@Inject
	public TypeResouceEngine(DataWareHouse dataWareHouse, EditableTypeLoader typeLoader, TypeFilterBuilder filterBuilder) {
		this.dataWareHouse = dataWareHouse;
		this.typeLoader = typeLoader;
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
		JsonDealer<Type> json = JsonProvider.getSerialize(Type.class);

		if (id != null) {
			return new TypeEditableResouce(typeLoader, id);
		} else {
			return new TypeListResouce(typeLoader, json, filterBuilder);
		}
	}

}
