package http.engine;

import http.io.Loader;
import http.io.Source;
import http.resource.StaticEditableResource;

import javax.inject.Inject;

import nebula.server.Address;
import nebula.server.Resource;
import nebula.server.ResourceEngine;


@SuppressWarnings("deprecation")
public class EditableStaticResourceEngine extends StaticResourceEngine implements ResourceEngine {
	@Inject
	public EditableStaticResourceEngine(Loader loader) {
		super(loader);
	}

	@Override
	public Resource resolve(Address target) {
		Source source = loader.findSource(target.getPathInfo());
		return source != null ? new StaticEditableResource(source, TheMimeTypes.get(target.getPath().getExtension()))
				: null;
	}
}
