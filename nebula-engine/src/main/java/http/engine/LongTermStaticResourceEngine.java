package http.engine;

import http.io.Loader;
import http.io.Source;

import java.util.Hashtable;
import java.util.StringTokenizer;

import javax.inject.Inject;

import org.simpleframework.http.Address;
import org.simpleframework.http.resource.Resource;

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
	public Resource resolve(Address target) {
		Source source = loader.findSource(target.getPath().getPath());
		if (source != null) {
			return new StaticResource(source,TheMimeTypes.get(target.getPath().getExtension()),age);
		}
		return p404;
	}
}
