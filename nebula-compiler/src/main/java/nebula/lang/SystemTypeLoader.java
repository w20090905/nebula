package nebula.lang;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SystemTypeLoader extends TypeLoader {
	private Log log = LogFactory.getLog(this.getClass());
	final ResourcePoolTail source;

	public SystemTypeLoader() {
		super(BootstrapTypeLoader.getInstance());
		this.source = new ResourcePoolTail();
		this.loadAll("nebula.properties");
	}

	public ResourcePath insertResourcePath(ResourcePath cp) {
		return source.insertResourcePath(cp);
	}

	public ResourcePath appendResourcePath(ResourcePath cp) {
		return source.appendResourcePath(cp);
	}

	public ResourcePath insertResourcePath(String pathname) {
		return source.insertResourcePath(pathname);
	}

	public ResourcePath appendResourcePath(String pathname) {
		return source.appendResourcePath(pathname);
	}

	private void loadAll(String name) {
		try {

			Enumeration<URL> resources = this.getClass().getClassLoader().getResources(name);

			while (resources.hasMoreElements()) {
				URL url = resources.nextElement();
				if ("jar".equals(url.getProtocol())) {
					source.appendResourcePath(new JarClassPath(url.getPath().substring(5).split("!")[0]));
				} else {
					source.appendResourcePath(new DirResourcePath(new File(url.getPath()).getParentFile().getPath()));
				}
			}

			resources = this.getClass().getClassLoader().getResources(name);
			while (resources.hasMoreElements()) {
				URL url = resources.nextElement();
				if ("jar".equals(url.getProtocol())) {
					loadJar(new File(url.getPath().substring(5).split("!")[0]));
				} else {
					loadFolder(new File(url.getPath()).getParentFile());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadJar(File f) {
		if (log.isTraceEnabled()) {
			log.trace(f.getPath());
		}
		try {
			JarFile jf = new JarFile(f);
			Enumeration<JarEntry> entries = jf.entries();
			while (entries.hasMoreElements()) {
				JarEntry entry = entries.nextElement();
				if (entry.getName().endsWith(".nebula")) {
					if (log.isTraceEnabled()) {
						log.trace("** Load type from " + entry.getName());
					}
					super.defineNebula(jf.getInputStream(entry));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void loadFolder(File root) {
		if (log.isTraceEnabled()) {
			log.trace(root.getPath());
		}
		loadFolder(root, root);
	}

	public void reload(File file) {
		try {
			super.defineNebula(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void reload(URL url) {
		try {
			super.defineNebula(url.openStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void loadFolder(File root, File d) {
		try {
			if (!d.exists() || !d.isDirectory())
				return;

			for (File f : d.listFiles()) {
				if (f.isFile() && f.getName().endsWith(".nebula")) {
					super.defineNebula(new FileInputStream(f));
				} else if (f.isDirectory()) {
					loadFolder(root, f);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected InputStream loadClassData(String name) {
		InputStream inputStream = source.openResouce(name, ".nebula");
		return inputStream;
	}
}
