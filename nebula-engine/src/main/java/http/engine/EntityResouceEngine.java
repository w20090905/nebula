package http.engine;

import http.resource.AttachedEntityListResouce;
import http.resource.EntityListResouce;
import http.resource.EntityResouce;
import http.resource.TxEntityResource;
import http.resource.TypeEditableResouce;
import http.resource.TypeListResouce;

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
import nebula.lang.EditableTypeLoader;
import nebula.lang.Type;
import nebula.lang.TypeLoader;
import nebula.lang.TypeStandalone;
import nebula.server.Resource;
import nebula.server.ResourceEngine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EntityResouceEngine implements ResourceEngine {
	private static final int TYPENAME = 2;
	private static final int ID = 3;
	private static final int ATTACHTO_TYPENAME = 2;
	private static final int ATTACHTO_ID = 3;
	private static final int ATTACH_TYPENAME = 4;

	private Log log = LogFactory.getLog(this.getClass());
	final DataRepos persistence;
	final TypeLoader typeLoader;
	final TypeDatastore typeBrokers;

	Map<String, String> tmap = new HashMap<String, String>();

	@Inject
	public EntityResouceEngine(final DataRepos dataWareHouse, EditableTypeLoader typeLoader, TypeDatastore typeBrokers) {
		this.persistence = dataWareHouse;
		this.typeLoader = typeLoader;
		this.typeBrokers = typeBrokers;
	}

	@Override
	public Resource resolve(String path) {
		if (log.isTraceEnabled()) {
			log.trace("resolve path : " + path);
		}

		String[] paths = path.split("/");

		String typeName = null;

		switch (paths.length) {
		case 5:
			return makeResouce(paths[ATTACHTO_TYPENAME], paths[ATTACHTO_ID], paths[ATTACH_TYPENAME]);
		case 4:
			typeName = paths[TYPENAME];

			if ("Type".equals(typeName)) {
				return makeTypeResouce(typeName, paths[ID]);
			} else {
				return makeResouce(typeName, paths[ID]);
			}
		case 3:
			typeName = paths[TYPENAME];
			if ("Type".equals(typeName)) {
				return makeTypeResouce(typeName);
			} else {
				return makeResouce(typeName);
			}
		default:
			return null;
		}
	}

	private Resource makeTypeResouce(String typeName) {
		DataHelper<Type, Reader, Writer> json = JsonHelperProvider.getSimpleSerialize(Type.class);
		return new TypeListResouce(typeLoader, json);
	}

	private Resource makeTypeResouce(String typeName, String id) {
		return new TypeEditableResouce(persistence, typeLoader, id);
	}

	private Resource makeResouce(String typeName) {
		Broker<Type> typeBroker = typeBrokers.getBroker(typeName);
		Broker<DataStore<Entity>> storeHolder = persistence.define(Long.class, Entity.class, typeName);
		Broker<DataHelper<Entity, Reader, Writer>> jsonHolder = JsonHelperProvider.getSimpleHelper(typeBroker);
		return new EntityListResouce(jsonHolder, storeHolder);
	}

	private Resource makeResouce(String typeName, String id) {
		Broker<Type> typeBroker = typeBrokers.getBroker(typeName);
		Broker<DataStore<Entity>> storeHolder = persistence.define(Long.class, Entity.class, typeName);
		Broker<DataHelper<Entity, Reader, Writer>> jsonHolder = JsonHelperProvider.getHelper(typeBroker);

		if (typeBroker.get().getStandalone() == TypeStandalone.Transaction) {
			return new TxEntityResource(jsonHolder, storeHolder, id);
		} else {
			return new EntityResouce(jsonHolder, storeHolder, id);
		}
	}

	private Resource makeResouce(String attachToTypeName, String attachToID, String typeName) {
		Broker<Type> typeBroker = typeBrokers.getBroker(typeName);
		Broker<DataStore<Entity>> storeHolder = persistence.define(Long.class, Entity.class, typeName);
		Broker<DataHelper<Entity, Reader, Writer>> jsonHolder = JsonHelperProvider.getSimpleHelper(typeBroker);
		return new AttachedEntityListResouce(jsonHolder, storeHolder, attachToTypeName, attachToID);
	}
}
