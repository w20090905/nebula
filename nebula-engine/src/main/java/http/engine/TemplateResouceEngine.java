package http.engine;

import http.io.Loader;
import http.io.Source;
import http.resource.StaticResource;
import http.resource.StaticTemplateResouce;
import http.resource.TypeTemplateResouce;

import javax.inject.Inject;

import nebula.data.Broker;
import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.impl.TypeDatastore;
import nebula.lang.Type;
import nebula.lang.TypeLoader;
import nebula.server.Resource;
import freemarker.template.Configuration;

@SuppressWarnings("deprecation")
public class TemplateResouceEngine extends StaticResourceEngine {
	private final long age;

	private final Configuration templateConfig;
	final Broker<DataStore<Entity>> attributes;
	final TypeLoader typeLoader;
	final DataRepos dataWareHouse;
	final TypeDatastore typeBrokers;

	@Inject
	public TemplateResouceEngine(Loader resourceLoader, TypeLoader typeLoader, TypeDatastore typeBrokers,final DataRepos dataWareHouse, Configuration cfg) {
		super(resourceLoader);
		this.templateConfig = cfg;
		this.typeLoader = typeLoader;
		this.attributes = dataWareHouse.define(String.class, Entity.class, "Attribute");
		this.dataWareHouse = dataWareHouse;
		this.age = 0;// 30L * 24L * 60L * 60L;
		this.typeBrokers = typeBrokers;
	}

	@Override
	public Resource resolve(String path) {
		String extension = path.substring(path.lastIndexOf('.') + 1);

		Source source = loader.findSource(path);
		if (source != null) {
			return new StaticResource(source, TheMimeTypes.get(extension), this.age);
		}

		String theme = "$";
		String skin = "$";

		int last = path.lastIndexOf('/');

		int prev = path.indexOf('/') + 1;

		int next = path.indexOf("/", prev);
		if (0 < next && next <= last) {
			prev = next + 1;
		}

		next = path.indexOf("/", prev);
		if (0 < next && next <= last) {
			theme = path.substring(prev, next);
			prev = next + 1;
		}

		next = path.indexOf("/", prev);
		if (0 < next && next <= last) {
			skin = path.substring(prev, next);
			prev = next + 1;
		}

		String name = path.substring(prev);

		if ((source = loader.findSource("/" + "theme/" + theme + "/" + skin + "/" + name)) != null) {
			return new StaticResource(source, TheMimeTypes.get(extension), this.age);
		} else if ((source = loader.findSource("/" + "theme/" + theme + "/" + name)) != null) {
			return new StaticResource(source, TheMimeTypes.get(extension), this.age);
		} else if ((source = loader.findSource("/" + "theme/" + name)) != null) {
			return new StaticResource(source, TheMimeTypes.get(extension), this.age);
		} else if ((source = loader.findSource("/" + "default/" + name)) != null) {
			return new StaticResource(source, TheMimeTypes.get(extension), this.age);
		} else if ((source = loader.findSource("/" + name)) != null) {
			return new StaticResource(source, TheMimeTypes.get(extension), this.age);
		}

		int idxType = 0;
		if (name.indexOf('/', prev) > 0) {
			return new StaticTemplateResouce(templateConfig, typeLoader, attributes, theme, skin, name);
		} else if ((idxType = name.indexOf("-")) > 0) {
			String typeName;
			String layoutName;
			String actionName;

			typeName = name.substring(0, idxType);
			int idxType2 = -1;
			if ((idxType2 = name.indexOf("-", idxType + 1)) > 0) {
				layoutName = name.substring(idxType + 1, idxType2);
				actionName = name.substring(idxType2 + 1);
			} else {
				layoutName = null;
				actionName = name.substring(idxType + 1);
			}
			Broker<Type> type = typeBrokers.getBroker(typeName);
					
			return new TypeTemplateResouce(templateConfig, typeLoader, type,dataWareHouse, attributes, theme, skin, typeName, layoutName, actionName);
		} else {
			return new StaticTemplateResouce(templateConfig, typeLoader, attributes, theme, skin, name);
		}

	}

}
