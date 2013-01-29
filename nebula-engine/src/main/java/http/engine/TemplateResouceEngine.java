package http.engine;

import http.io.Loader;
import http.io.Source;
import http.resource.StaticEditableResource;
import http.resource.TemplateResouce;

import javax.inject.Inject;

import nebula.data.DataHolder;
import nebula.data.DataPersister;
import nebula.data.DataStore;
import nebula.data.Entity;
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

	private final Configuration templateConfig;
	final DataHolder<DataStore<Entity>> attributes;
	final TypeLoader typeLoader;

	@Inject
	public TemplateResouceEngine(Loader resourceLoader, TypeLoader typeLoader,
			final DataPersister<Entity> dataWareHouse, Configuration cfg) {
		super(resourceLoader);
		this.templateConfig = cfg;
		this.typeLoader = typeLoader;
		this.attributes = dataWareHouse.define(Entity.class, "Attribute");
	}

	@Override
	public Resource resolve(Address target) {
		Path path = target.getPath();

		Source source = loader.findSource(path.getPath());
		if (source != null) {
			return new StaticEditableResource(source, TheMimeTypes.get(source));
		}

		String[] segments = target.getPath().getSegments();
		String templateTypeName = segments[0];
		if (segments.length != 2) {
			throw new RuntimeException(segments.toString());
		}

		String[] names = path.getName().split("-");
		if (names.length < 2) {
			return null;
		}
		String typeName = names[0];
		String actionName = names[1];

		if (log.isTraceEnabled()) {
			log.trace("target.getPath : " + path);
			log.trace("\ttemplateTypeName : " + templateTypeName);
			log.trace("\ttypeName : " + typeName);
			log.trace("\tactionName : " + actionName);
		}

		return new TemplateResouce(templateConfig, typeLoader, attributes, templateTypeName, typeName, actionName);

	}

}
