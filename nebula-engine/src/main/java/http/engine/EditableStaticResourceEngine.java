package http.engine;

import httpd.io.Loader;
import httpd.io.Source;

import javax.inject.Inject;

import org.simpleframework.http.Address;
import org.simpleframework.http.resource.Resource;
import org.simpleframework.http.resource.ResourceEngine;

public class EditableStaticResourceEngine extends StaticResourceEngine  implements ResourceEngine {
	@Inject
	public EditableStaticResourceEngine(Loader loader) {
		super(loader);
	}

	@Override
	public Resource resolve(Address target) {
		Source source = loader.findSource(target.getPath().getPath());
		if (source != null) {
			return new EditableStaticResource(source,TheMimeTypes.get(target.getPath().getExtension()));
		}
		return p404;
	}
}
