package http.server;

import httpd.io.Loader;
import httpd.io.Source;

import java.util.Hashtable;
import java.util.StringTokenizer;

import javax.inject.Inject;

import org.simpleframework.http.Address;
import org.simpleframework.http.resource.Resource;
import org.simpleframework.http.resource.ResourceEngine;

public class StaticResourceEngine implements ResourceEngine {
	private final Loader loader;

	private Resource p404;
	@Inject
	public StaticResourceEngine(Loader loader) {
		this.loader = loader;
		Source source = loader.findSource("/404.html");		
		p404 =  new StaticResource(source,TheMimeTypes.get("html"));
	}

	@Override
	public Resource resolve(Address target) {
		Source source = loader.findSource(target.getPath().getPath());
		if (source != null) {
			return new StaticResource(source,TheMimeTypes.get(target.getPath().getExtension()));
		}
		return p404;
	}
	
	/**
     * Hashtable mapping (String)FILENAME_EXTENSION -> (String)MIME_TYPE
     */
    private static Hashtable<String, String> TheMimeTypes = new Hashtable<String, String>();
	static {
		//@formatter:off
		StringTokenizer st = new StringTokenizer(
				  "htm        text/html " 
				+ "css        text/css "
				+ "js         application/x-javascript " 
				+ "html       text/html " 
				+ "txt        text/plain "
				+ "java       text/plain " 
				+ "ftl        text/plain " 
				+ "asc        text/plain "
				+ "gif        image/gif " 
				+ "jpg        image/jpeg " 
				+ "jpeg       image/jpeg "
				+ "png        image/png " 
				+ "mp3        audio/mpeg " 
				+ "m3u        audio/mpeg-url "
				+ "pdf        application/pdf " 
				+ "doc        application/msword " 
				+ "ogg        application/x-ogg "
				+ "zip        application/octet-stream " 
				+ "exe        application/octet-stream "
				+ "class      application/octet-stream ");
		//@formatter:on
		while (st.hasMoreTokens())
			TheMimeTypes.put(st.nextToken(), st.nextToken());
	}

}
