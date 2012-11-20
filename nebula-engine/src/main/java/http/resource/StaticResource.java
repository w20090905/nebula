package http.resource;

import http.io.Source;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Address;

@SuppressWarnings("deprecation")
public class StaticResource extends AbstractResouce {
	private static Log log = LogFactory.getLog(StaticResource.class);

	private final Source underlySource;

	public StaticResource(Source source, String mime) {
		this(source, mime, 1L * 10L * 60L);
	}

	public StaticResource(Source source, String mime, long age) {
		super(mime, age, 1000);
		this.underlySource = source;
	}

	@Override
	protected void get(Address address) {
		if (this.lastModified == this.underlySource.getLastModified()) {
			return;
		}
		ByteArrayOutputStream bout = null;
		InputStream in = null;
		try {
			bout = new ByteArrayOutputStream();
			in = this.underlySource.getInputStream();
			byte[] thisbuffer = new byte[1024];
			int length = -1;
			while ((length = in.read(thisbuffer)) > 0) {
				bout.write(thisbuffer, 0, length);
			}
			in.close();
			this.lastModified = this.underlySource.getLastModified();
			this.cache = bout.toByteArray();
		} catch (IOException e) {
			try {
				if (bout != null) bout.close();
				if (in != null) in.close();
			} catch (Exception e2) {
			}
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}
