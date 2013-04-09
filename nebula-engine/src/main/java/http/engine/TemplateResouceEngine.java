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
import nebula.server.Address;
import nebula.server.Path;
import nebula.server.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
		Source source = loader.findSource(target.getPathInfo());
		if (source != null) {
			return new StaticEditableResource(source, TheMimeTypes.get(source));
		}

		String[] segments = target.getPath().getSegments();
		String templateTypeName = segments[0];
		if (segments.length != 2) {
			throw new RuntimeException(segments.toString());
		}

		String[] names = target.getPath().getName().split("-");
		if (names.length < 2) {
			return null;
		}
		String typeName = names[0];
		String actionName = names[1];

		return new TemplateResouce(templateConfig, typeLoader, attributes, templateTypeName, typeName, actionName);

	}

}
