package http.engine;

import http.io.Loader;
import http.io.Source;
import http.resource.AttachedTypeTemplateResouce;
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
import nebula.lang.TypeStandalone;
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
	public TemplateResouceEngine(Loader resourceLoader, TypeLoader typeLoader, TypeDatastore typeBrokers, final DataRepos dataWareHouse, Configuration cfg) {
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

		String[] names = null;

		if (name.indexOf('/', prev) > 0) {
			return new StaticTemplateResouce(templateConfig, typeLoader, attributes, theme, skin, name);
		} else if ((names = name.split("-")).length > 0) {
			String specName = null;
			final int start;
			if ("setting info admin dev profile".indexOf(names[0]) >= 0) {
				start = 1;
				specName = names[0];
			} else {
				start = 0;
			}

			String attachedTypeName;
			String typeName;
			String layoutName;
			String actionName;
			Broker<Type> type;
			Broker<Type> attachedType;
			switch (names.length - start) {
			default:
			case 4:
				attachedTypeName = names[start + 0];
				typeName = names[start + 1];
				layoutName = names[start + 2];
				actionName = names[start + 3];
				attachedType = typeBrokers.getBroker(attachedTypeName);
				type = typeBrokers.getBroker(typeName);
				if (specName == null && type.get().getStandalone() == TypeStandalone.Master) {
					specName = "setting";
				}
				return new AttachedTypeTemplateResouce(templateConfig, dataWareHouse, attributes, theme, skin, attachedType, type, specName, layoutName,
						actionName);
			case 3:
				typeName = names[start + 0];
				layoutName = names[start + 1];
				actionName = names[start + 2];
				type = typeBrokers.getBroker(typeName);
				if (type.get().getAttrs().containsKey(Type.ATTACH_TO)) {
					attachedTypeName = (String) type.get().getAttrs().get(Type.ATTACH_TO);
					attachedType = typeBrokers.getBroker(attachedTypeName);
					if (specName == null && type.get().getStandalone() == TypeStandalone.Master) {
						specName = "setting";
					}
					return new AttachedTypeTemplateResouce(templateConfig, dataWareHouse, attributes, theme, skin, attachedType, type, specName, layoutName,
							actionName);
				}
				return new TypeTemplateResouce(templateConfig, dataWareHouse, attributes, theme, skin, type, specName, layoutName, actionName);

			case 2:
				typeName = names[start + 0];
				layoutName = null;
				actionName = names[start + 1];

				type = typeBrokers.getBroker(typeName);
				if (type.get().getAttrs().containsKey(Type.ATTACH_TO)) {
					attachedTypeName = (String) type.get().getAttrs().get(Type.ATTACH_TO);
					attachedType = typeBrokers.getBroker(attachedTypeName);
					if (specName == null && type.get().getStandalone() == TypeStandalone.Master) {
						specName = "setting";
					}
					return new AttachedTypeTemplateResouce(templateConfig, dataWareHouse, attributes, theme, skin, attachedType, type, specName, layoutName,
							actionName);
				}
				return new TypeTemplateResouce(templateConfig, dataWareHouse, attributes, theme, skin, type, specName, layoutName, actionName);
			}

		} else {
			return new StaticTemplateResouce(templateConfig, typeLoader, attributes, theme, skin, name);
		}

	}

}
