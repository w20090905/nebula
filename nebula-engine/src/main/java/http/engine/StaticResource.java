package http.engine;

import http.io.Source;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.resource.Resource;

public class StaticResource implements Resource {
	private static Log log = LogFactory.getLog(StaticResource.class);
	
	private final Source underlySource;
    private final String mime;
    private final long age;
	public StaticResource(Source source, String mime){
		this(source, mime, 1L * 10L * 60L);
	}
	public StaticResource(Source source, String mime,long age){
		this.underlySource = source;        
		this.mime = mime;
		this.age =age;
	}

	@Override
	public void handle(Request req, Response resp) {
		try {			
     
            // normal parse
            resp.set("Cache-Control", "max-age=" + age);
            resp.set("Content-Language", "en-US");
            resp.set("Content-Type", mime);
            // resp.setContentLength((int) underlySource.getLength());
            resp.setDate("Date", System.currentTimeMillis());
            resp.setDate("Last-Modified", this.underlySource.getLastModified());
            resp.set("ETag", "\"" + (this.underlySource.getLastModified() + System.currentTimeMillis()) + "\"");
            // max-age
            log.debug("Load file [" + underlySource.getName() + "] contents to client by [" + req.getPath() + "]");
            InputStream in = underlySource.getInputStream();
            OutputStream out = resp.getOutputStream();
            resp.setContentLength((int) underlySource.getLength());
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }       
            in.close();
            out.close();
		} catch (IOException e) {
			log.error(req.getAddress());
			log.error(e);
			throw new RuntimeException(e);
		} catch (NullPointerException e) {
			log.error(req.getAddress());
			log.error(e);
			throw e;
		}
	}    

}
