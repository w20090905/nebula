package nebula.compiler;

import java.io.File;
import java.io.FileInputStream;
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
		this.load("nebula.properties");
	}

	public void load(String name) {
		try {
			
			Enumeration<URL> resources = this.getClass().getClassLoader().getResources(name);

			while (resources.hasMoreElements()) {
				URL url = resources.nextElement();
				if ("jar".equals(url.getProtocol())) {
					source.appendClassPath(new JarClassPath(url.getPath().substring(5).split("!")[0]));
				} else {
					source.appendClassPath(new DirResourcePath(new File(url.getPath()).getParentFile().getPath()));
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
				JarEntry entry =   entries.nextElement();
				if (entry.getName().endsWith(".nebula")) {
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

		if (log.isTraceEnabled()) {
			if (inputStream != null)
				log.trace("search class data : " + name + " succeed!");
			else
				log.trace("search class data : " + name + " fail!");
		}
		return inputStream;
	}
}
