package http.engine;

import javax.inject.Inject;

import nebula.SmartList;
import nebula.frame.DataWareHouse;
import nebula.lang.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Address;
import org.simpleframework.http.Path;
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
		Path pathPath = target.getPath();
		String name = pathPath.getName();
		String path = pathPath.getDirectory();
		path = path.substring(3,path.length()-1);
		path = path.replace('/', '.');
		
		if(log.isTraceEnabled()){
			log.trace("target.getPath : " + pathPath);
			log.trace("\tpath : " + path);
			log.trace("\tname : " + name);
		}

		if (name != null) {
			return new DataResouce(cfg, path, dataWareHouse.get(path), name);
		} else {
			return new DataListResouce(cfg, path, dataWareHouse.get(path));
		}
	}

}
