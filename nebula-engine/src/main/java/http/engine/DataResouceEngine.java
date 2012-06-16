package http.engine;

import javax.inject.Inject;

import nebula.frame.DataWareHouse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Address;
import org.simpleframework.http.resource.Resource;
import org.simpleframework.http.resource.ResourceEngine;

import freemarker.template.Configuration;

public class DataResouceEngine implements ResourceEngine {
	private Log log = LogFactory.getLog(this.getClass());

	private final Configuration cfg;
	DataWareHouse dataWareHouse;

	@Inject
	public DataResouceEngine(Configuration cfg, DataWareHouse dataWareHouse) {
		this.cfg = cfg;
		this.dataWareHouse = dataWareHouse;
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
			return new DataResouce(cfg, typeName, dataWareHouse.get(typeName), id);
		} else {
			return new DataListResouce(cfg, typeName, dataWareHouse.get(typeName));
		}
	}

}
