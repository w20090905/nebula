package http.engine;

import http.io.Loader;
import http.io.Source;
import http.resource.StaticEditableResource;

import javax.inject.Inject;

import nebula.server.Resource;
import nebula.server.ResourceEngine;


@SuppressWarnings("deprecation")
public class EditableStaticResourceEngine extends StaticResourceEngine implements ResourceEngine {
	@Inject
	public EditableStaticResourceEngine(Loader loader) {
		super(loader);
	}

	@Override
	public Resource resolve(String path) {
		String extension = path.substring(path.lastIndexOf('.') + 1);
		Source source = loader.findSource(path);
		return source != null ? new StaticEditableResource(source, TheMimeTypes.get(extension))
				: null;
	}
}
