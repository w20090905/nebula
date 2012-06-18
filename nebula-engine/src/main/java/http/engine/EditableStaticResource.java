package http.engine;

import httpd.io.Source;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import util.FileUtil;

@SuppressWarnings("deprecation")
public class EditableStaticResource extends BasicResouce {
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
				this.make();
				// normal parse
				resp.set("Cache-Control", "max-age=0");
				resp.set("Content-Language", "en-US");
				resp.set("Content-Type", mime);
				resp.setContentLength(this.buffer.length);
				resp.setDate("Date", System.currentTimeMillis());
				resp.setDate("Last-Modified", this.lastModified);

				resp.getOutputStream().write(buffer);
				resp.getOutputStream().flush();
				resp.close();
				// max-age
				log.debug("Load contents to client by [" + req.getPath() + "]");
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

				this.make();
				// normal parse
				resp.set("Cache-Control", "max-age=0");
				resp.set("Content-Language", "en-US");
				resp.set("Content-Type", mime);
				resp.setContentLength(this.buffer.length);
				resp.setDate("Date", System.currentTimeMillis());
				resp.setDate("Last-Modified", this.lastModified);

				resp.getOutputStream().write(buffer);
				resp.getOutputStream().flush();
				resp.close();
				// max-age
				log.debug("Load file [" + file.getName() + "] contents to client by [" + req.getPath() + "]");

			}
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void make() {
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			InputStream in = this.underlySource.getInputStream();
			byte[] thisbuffer = new byte[1024];
			int length = -1;
			while ((length = in.read(thisbuffer)) > 0) {
				bout.write(thisbuffer, 0, length);
			}
			in.close();
			this.lastModified = this.underlySource.getLastModified();
			this.buffer = bout.toByteArray();
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

}
