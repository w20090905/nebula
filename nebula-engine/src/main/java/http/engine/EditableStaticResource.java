package http.engine;

import httpd.io.Source;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.resource.Resource;

import util.FileUtil;

public class EditableStaticResource implements Resource {
	private static Log log = LogFactory.getLog(EditableStaticResource.class);

	private final Source underlySource;
	private final String mime;

	public EditableStaticResource(Source source, String mime) {
		this.underlySource = source;
		this.mime = mime;

	}

	@Override
	public void handle(Request req, Response resp) {
		try {

			if ("GET".equals(req.getMethod())) {
				// normal parse
				resp.set("Cache-Control", "max-age=0");
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
			} else if ("PUT".equals(req.getMethod())) {
				BufferedInputStream bio = new BufferedInputStream(req.getInputStream());

				if (log.isTraceEnabled()) {
					log.trace("Input stream : ");
					log.trace(FileUtil.readAllTextFrom(bio));
				}

				Object realObject = underlySource.getRealObject();
				File file = null;
				if (realObject instanceof File) {
					file = (File) realObject;
				} else if (realObject instanceof URL) {
					URL url = (URL) realObject;
					file = new File(url.getFile());
				}

				file = FileUtil.replace(file, bio);

				// normal parse
				resp.set("Cache-Control", "max-age=0");
				resp.set("Content-Language", "en-US");
				resp.set("Content-Type", mime);
				// resp.setContentLength((int) underlySource.getLength());
				resp.setDate("Date", System.currentTimeMillis());
				resp.setDate("Last-Modified", file.lastModified());
				resp.set("ETag", "\"" + (file.lastModified() + System.currentTimeMillis()) + "\"");
				// max-age
				log.debug("Load file [" + file.getName() + "] contents to client by [" + req.getPath() + "]");
				InputStream in = new FileInputStream(file);
				OutputStream out = resp.getOutputStream();
				resp.setContentLength((int) file.length());
				byte[] buffer = new byte[1024];
				int length = -1;
				while ((length = in.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}
				in.close();
				out.close();
			}
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

}
