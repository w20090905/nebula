package http.engine;

import http.io.Loader;
import http.io.Source;
import http.resource.StaticEditableResource;

import javax.inject.Inject;

import org.simpleframework.http.Address;
import org.simpleframework.http.resource.Resource;
import org.simpleframework.http.resource.ResourceEngine;

@SuppressWarnings("deprecation")
public class StaticEditableResourceEngine extends StaticResourceEngine  implements ResourceEngine {
	@Inject
	public StaticEditableResourceEngine(Loader loader) {
		super(loader);
	}

	@Override
	public Resource resolve(Address target) {
		Source source = loader.findSource(target.getPath().getPath());
		if (source != null) {
			return new StaticEditableResource(source,TheMimeTypes.get(target.getPath().getExtension()));
		}
		return p404;
	}
}
