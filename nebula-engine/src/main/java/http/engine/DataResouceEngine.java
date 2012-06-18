package http.engine;

import http.json.JSON;
import http.json.JSON.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import nebula.SmartList;
import nebula.frame.DataWareHouse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Address;
import org.simpleframework.http.resource.Resource;
import org.simpleframework.http.resource.ResourceEngine;

public class DataResouceEngine implements ResourceEngine {
	private Log log = LogFactory.getLog(this.getClass());
	DataWareHouse dataWareHouse;

	Map<String, String> tmap = new HashMap<String, String>();

	@Inject
	public DataResouceEngine(DataWareHouse dataWareHouse) {
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

		@SuppressWarnings("rawtypes")
		JsonSerializer<Map> json = JSON.getSerialize(Map.class);
		@SuppressWarnings("unchecked")
		SmartList<Map<String, String>> datas = (SmartList<Map<String, String>>) dataWareHouse.get(typeName);

		if (id != null) {
			return new DataResouce(json, datas, id);
		} else {
			return new DataListResouce(json, datas);
		}
	}

}
