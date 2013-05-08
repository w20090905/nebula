package http.engine;

import http.io.Loader;
import http.io.Source;
import http.resource.StaticResource;

import javax.inject.Inject;

import nebula.server.Resource;


@SuppressWarnings("deprecation")
public class LongTermStaticResourceEngine extends StaticResourceEngine {
	private final long age;
	@Inject
	public LongTermStaticResourceEngine(Loader loader) {
		this(loader, 30L * 24L * 60L * 60L);
	}
	
	public LongTermStaticResourceEngine(Loader loader,long age) {
		super(loader);
		this.age = age;
	}
	
	@Override
	public Resource resolve(String path) {
		String extension = path.substring(path.lastIndexOf('.') + 1);
		
		Source source = loader.findSource(path);
		if (source != null) {
			return new StaticResource(source,TheMimeTypes.get(extension),age);
		}
		return null;
	}
}
