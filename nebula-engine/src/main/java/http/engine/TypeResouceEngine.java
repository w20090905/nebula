package http.engine;

import javax.inject.Inject;

import nebula.frame.DataWareHouse;
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

	@Inject
	public TypeResouceEngine(DataWareHouse dataWareHouse, TypeLoader typeLoader) {
		this.dataWareHouse = dataWareHouse;
		this.typeLoader = typeLoader;
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

		if (id != null) {
			return new TypeEditResouce(typeLoader, dataWareHouse.get(typeName), id);
		} else {
			return new TypeListResouce(typeLoader, dataWareHouse.get(typeName));
		}
	}

}
