package http.engine;

import httpd.io.Loader;
import httpd.io.Source;

import javax.inject.Inject;

import nebula.frame.DataWareHouse;
import nebula.lang.TypeLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Address;
import org.simpleframework.http.Path;
import org.simpleframework.http.resource.Resource;

import freemarker.template.Configuration;

@SuppressWarnings("deprecation")
public class TemplateResouceEngine extends StaticResourceEngine {
	private Log log = LogFactory.getLog(this.getClass());

	private final Configuration cfg;
	DataWareHouse dataWareHouse;
	final TypeLoader typeLoader;

	@Inject
	public TemplateResouceEngine(Loader resourceLoader,TypeLoader typeLoader, Configuration cfg, DataWareHouse dataWareHouse) {
		super(resourceLoader);
		this.cfg = cfg;
		this.dataWareHouse = dataWareHouse;
		this.typeLoader = typeLoader;
	}

	@Override
	public Resource resolve(Address target) {
		Path path = target.getPath();

		Source source = loader.findSource(path.getPath());
		if (source != null) {
			return new EditableStaticResource(source, TheMimeTypes.get(source));
		}

		String[] segments = target.getPath().getSegments();
		String templateTypeName = segments[0];
		if (segments.length != 2) {
			throw new RuntimeException(segments.toString());
		}

		String[] names = path.getName().split("-");
		String typeName = names[0];
		String actionName = names[1];

		if (log.isTraceEnabled()) {
			log.trace("target.getPath : " + path);
			log.trace("\ttemplateTypeName : " + templateTypeName);
			log.trace("\ttypeName : " + typeName);
			log.trace("\tactionName : " + actionName);
		}

		return new TemplateResouce(cfg,typeLoader , templateTypeName, typeName,
				actionName);

	}

}
