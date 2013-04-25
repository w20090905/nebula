package http.engine;

import http.resource.EntityFilterBuilder;
import http.resource.EntityListResouce;
import http.resource.EntityResouce;
import http.resource.TypeEditableResouce;
import http.resource.TypeFilterBuilder;
import http.resource.TypeListResouce;

import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import nebula.data.DataHolder;
import nebula.data.DataPersister;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.json.DataHelper;
import nebula.data.json.JsonHelperProvider;
import nebula.lang.EditableTypeLoader;
import nebula.lang.Type;
import nebula.lang.TypeLoader;
import nebula.server.Resource;
import nebula.server.ResourceEngine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EntityResouceEngine implements ResourceEngine {
	private Log log = LogFactory.getLog(this.getClass());
	final DataPersister<Entity> persistence;
	final TypeLoader typeLoader;
	final EntityFilterBuilder entityFilterBuilder;
	final TypeFilterBuilder typeFilterBuilder;

	Map<String, String> tmap = new HashMap<String, String>();

	@Inject
	public EntityResouceEngine(final DataPersister<Entity> dataWareHouse, EditableTypeLoader typeLoader,
			final EntityFilterBuilder entityFilterBuilder, TypeFilterBuilder typeFilterBuilder) {
		this.persistence = dataWareHouse;
		this.entityFilterBuilder = entityFilterBuilder;
		this.typeFilterBuilder = typeFilterBuilder;
		this.typeLoader = typeLoader;
	}

	@Override
	public Resource resolve(HttpServletRequest req) {
		String path = req.getPathInfo();

		String[] paths = path.split("/");
		String typeName = paths[2];
		String id = null;
		if (paths.length > 3) {
			id = paths[3];
		}

		if (log.isTraceEnabled()) {
			log.trace("target.getPath : " + path);
			log.trace("\ttypeName : " + typeName);
			log.trace("\tid : " + id);
		}

		if ("Type".equals(typeName)) {
			DataHelper<Type, Reader, Writer> json = JsonHelperProvider.getSerialize(Type.class);

			if (id != null) {
				return new TypeEditableResouce(persistence, typeLoader, id);
			} else {
				return new TypeListResouce(typeLoader, json, typeFilterBuilder);
			}
		} else {

			DataHolder<DataStore<Entity>> storeHolder = persistence.define(Entity.class, typeName);
			DataHolder<DataHelper<Entity, Reader, Writer>> jsonHolder = JsonHelperProvider.getHelper(storeHolder);

			if (id != null) {
				return new EntityResouce(jsonHolder, storeHolder, id);
			} else {
				return new EntityListResouce(jsonHolder, storeHolder, entityFilterBuilder);
			}
		}
	}

}
