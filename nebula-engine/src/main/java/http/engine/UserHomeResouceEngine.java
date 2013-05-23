package http.engine;

import http.io.Loader;
import http.io.Source;
import http.resource.StaticEditableResource;
import http.resource.StaticTemplateResouce;

import javax.inject.Inject;

import nebula.data.Holder;
import nebula.data.DataPersister;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.lang.TypeLoader;
import nebula.server.Resource;
import freemarker.template.Configuration;

@SuppressWarnings("deprecation")
public class UserHomeResouceEngine extends StaticResourceEngine {
	private final Configuration templateConfig;
	final Holder<DataStore<Entity>> attributes;
	final TypeLoader typeLoader;

	@Inject
	public UserHomeResouceEngine(Loader resourceLoader, TypeLoader typeLoader,
			final DataPersister<Entity> dataWareHouse, Configuration cfg) {
		super(resourceLoader);
		this.templateConfig = cfg;
		this.typeLoader = typeLoader;
		this.attributes = dataWareHouse.define(String.class, Entity.class, "Attribute");
	}

	@Override
	public Resource resolve(String path) {
		// HttpSession session = path.getSession();
		String theme = "angularjs";// (String) session.getAttribute("Theme");
		String skin = "clean";// (String) session.getAttribute("Skin"); TODO
		String extension = "text/html";
		String[] segs = path.split("/");
		String name = "index.html";

		if (segs.length > 3) {
			name = "index" + segs[3] + ".html";
		}

		Source source = null;
		if ((source = loader.findSource("/" + "theme/" + theme + "/" + skin + "/" + name)) != null) {
			return new StaticEditableResource(source, TheMimeTypes.get(extension));
		} else if ((source = loader.findSource("/" + "theme/" + theme + "/" + name)) != null) {
			return new StaticEditableResource(source, TheMimeTypes.get(extension));
		} else if ((source = loader.findSource("/" + "theme/" + name)) != null) {
			return new StaticEditableResource(source, TheMimeTypes.get(extension));
		} else if ((source = loader.findSource("/" + "default/" + name)) != null) {
			return new StaticEditableResource(source, TheMimeTypes.get(extension));
		} else if ((source = loader.findSource("/" + name)) != null) {
			return new StaticEditableResource(source, TheMimeTypes.get(extension));
		}

		return new StaticTemplateResouce(templateConfig, typeLoader, attributes, theme, skin, name);
	}

}
