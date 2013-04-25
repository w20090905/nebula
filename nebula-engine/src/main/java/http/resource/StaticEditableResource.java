package http.resource;

import http.io.Source;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import util.FileUtil;

@SuppressWarnings("deprecation")
public class StaticEditableResource extends AbstractResouce {

	private final Source underlySource;

	public StaticEditableResource(Source source, String mime) {
		super(mime, 0, 100);
		this.underlySource = source;
	}

	@Override
	protected void put(HttpServletRequest req) throws IOException {

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
		} else {
			throw new UnsupportedOperationException("File Type");
		}

		file = FileUtil.saveTo(bio, file);

	}

	@Override
	protected void get(HttpServletRequest req) {
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
			log.error(e.getClass().getName(), e);
			throw new RuntimeException(e);
		}
	}

}
