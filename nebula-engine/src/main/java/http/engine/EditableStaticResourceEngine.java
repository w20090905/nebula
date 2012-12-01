package http.engine;

import http.io.Loader;
import http.io.Source;
import http.resource.StaticEditableResource;

import javax.inject.Inject;

import org.simpleframework.http.Address;
import org.simpleframework.http.resource.Resource;
import org.simpleframework.http.resource.ResourceEngine;

@SuppressWarnings("deprecation")
public class EditableStaticResourceEngine extends StaticResourceEngine implements ResourceEngine {
	@Inject
	public EditableStaticResourceEngine(Loader loader) {
		super(loader);
	}

	@Override
	public Resource resolve(Address target) {
		Source source = loader.findSource(target.getPath().getPath());
		return source != null ? new StaticEditableResource(source, TheMimeTypes.get(target.getPath().getExtension()))
				: null;
	}
}
