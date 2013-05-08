package http.engine;

import http.io.Loader;
import http.io.Source;
import http.resource.StaticResource;

import java.util.Hashtable;
import java.util.StringTokenizer;

import javax.inject.Inject;

import nebula.server.Resource;
import nebula.server.ResourceEngine;


@SuppressWarnings("deprecation")
public class StaticResourceEngine implements ResourceEngine {
	protected final Loader loader;

//	protected Resource p404;
	@Inject
	public StaticResourceEngine(Loader loader) {
		this.loader = loader;
//		Source source = loader.findSource("/404.html");		
//		p404 =  new StaticResource(source,TheMimeTypes.get("html"));
	}

	@Override
	public Resource resolve(String path) {
		String extension = path.substring(path.lastIndexOf('.') + 1);
		Source source = loader.findSource(path);
		if (source != null) {
			return new StaticResource(source,TheMimeTypes.get(extension));
		}
		return null;
	}
	
	/**
     * Hashtable mapping (String)FILENAME_EXTENSION -> (String)MIME_TYPE
     */
    protected static Hashtable<String, String> TheMimeTypes = new Hashtable<String, String>();
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
				+ "class      application/octet-stream " 
				+ "otf          font/opentype "
				+ "eot         application/vnd.ms-fontobject "
				+ "svg         image/svg+xml "
				+ "ttf          font/ttf "
				+ "woff       font/x-woff ");
		//@formatter:on

		while (st.hasMoreTokens())
			TheMimeTypes.put(st.nextToken(), st.nextToken());
	}

}
