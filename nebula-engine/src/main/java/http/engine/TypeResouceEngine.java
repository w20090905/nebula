package http.engine;

import http.resource.TypeEditableResouce;
import http.resource.TypeFilterBuilder;
import http.resource.TypeListResouce;

import java.io.Reader;
import java.io.Writer;

import javax.inject.Inject;

import nebula.data.DataPersister;
import nebula.data.Entity;
import nebula.data.json.DataHelper;
import nebula.data.json.JsonHelperProvider;
import nebula.lang.EditableTypeLoader;
import nebula.lang.Type;
import nebula.lang.TypeLoader;
import nebula.server.Address;
import nebula.server.Resource;
import nebula.server.ResourceEngine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TypeResouceEngine implements ResourceEngine {
	private Log log = LogFactory.getLog(this.getClass());
	final DataPersister<Entity> dataWareHouse;
	final TypeLoader typeLoader;
	final TypeFilterBuilder filterBuilder;

	@Inject
	public TypeResouceEngine(final DataPersister<Entity> dataWareHouse, EditableTypeLoader typeLoader, TypeFilterBuilder filterBuilder) {
		this.dataWareHouse = dataWareHouse;
		this.typeLoader = typeLoader;
		this.filterBuilder = filterBuilder;
	}

	@Override
	public Resource resolve(Address target) {
		String[] path = target.getPath().getSegments();
		String typeName = path[2];
		String id = null;
		if (path.length > 3) {
			id = path[3];
		}

		if (log.isTraceEnabled()) {
			log.trace("target.getPath : " + path);
			log.trace("\ttypeName : " + typeName);
			log.trace("\tid : " + id);
		}
		DataHelper<Type,Reader,Writer> json = JsonHelperProvider.getSerialize(Type.class);

		if (id != null) {
			return new TypeEditableResouce(dataWareHouse, typeLoader, id);
		} else {
			return new TypeListResouce(typeLoader, json, filterBuilder);
		}
	}

}
