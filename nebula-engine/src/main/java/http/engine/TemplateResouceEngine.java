package http.engine;

import http.io.Loader;
import http.io.Source;
import http.resource.StaticResource;
import http.resource.StaticTemplateResouce;
import http.resource.TypeTemplateResouce;

import javax.inject.Inject;

import nebula.data.DataHolder;
import nebula.data.DataPersister;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.lang.TypeLoader;
import nebula.server.Resource;
import freemarker.template.Configuration;

@SuppressWarnings("deprecation")
public class TemplateResouceEngine extends StaticResourceEngine {
	private final long age;

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
		this.age = 30L * 24L * 60L * 60L;
	}

	@Override
	public Resource resolve(String path) {
		String extension = path.substring(path.lastIndexOf('.') + 1);

		Source source = loader.findSource(path);
		if (source != null) {
			return new StaticResource(source, TheMimeTypes.get(extension),this.age);
		}

		String theme = "$";
		String skin = "$";

		int last = path.lastIndexOf('/');
		
		int prev = path.indexOf('/') +1;

		int next = path.indexOf("/", prev);
		if (0< next && next <= last) {
			prev = next+1;
		}
		
		next = path.indexOf("/", prev);
		if (0 < next  && next <= last) {
			theme = path.substring(prev, next);
			prev = next+1;
		}

		next = path.indexOf("/", prev);
		if (0 < next  && next <= last) {
			skin = path.substring(prev, next);
			prev = next+1;
		}

		String name = path.substring(prev);

		if ((source = loader.findSource("/" + "theme/" + theme + "/" + skin + "/" + name)) != null) {
			return new StaticResource(source, TheMimeTypes.get(extension),this.age);
		} else if ((source = loader.findSource("/" + "theme/" + theme + "/" + name)) != null) {
			return new StaticResource(source, TheMimeTypes.get(extension),this.age);
		} else if ((source = loader.findSource("/" + "theme/" + name)) != null) {
			return new StaticResource(source, TheMimeTypes.get(extension),this.age);
		} else if ((source = loader.findSource("/" + "default/" + name)) != null) {
			return new StaticResource(source, TheMimeTypes.get(extension),this.age);
		} else if ((source = loader.findSource("/" + name)) != null) {
			return new StaticResource(source, TheMimeTypes.get(extension),this.age);
		}

		int idxType = 0;
		if (name.indexOf('/', prev) > 0) {
			return new StaticTemplateResouce(templateConfig, typeLoader, attributes, theme, skin, name);
		} else if ((idxType = name.indexOf("-")) > 0) {
			String typeName = name.substring(0, idxType);
			String actionName = name.substring(idxType+1);
			return new TypeTemplateResouce(templateConfig, typeLoader, attributes, theme, skin, typeName, actionName);
		} else {
			return new StaticTemplateResouce(templateConfig, typeLoader, attributes, theme, skin, name);
		}

	}

}
