package nebula.compiler;

import java.io.File;
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

	public SystemTypeLoader() {
		super(BootstrapTypeLoader.getInstance());
		this.load("nebula.properties");
	}	
	
	public void load(String name) {
		try {
			Enumeration<URL> resources = this.getClass().getClassLoader().getResources(name);

			while (resources.hasMoreElements()) {
				URL url = resources.nextElement();
				// PrintObejct.print(URL.class, url);
				// System.out.println("Protocol " + url.getProtocol() );

				if ("jar".equals(url.getProtocol())) {
					String jarPath = url.getPath().substring(5).split("!")[0];
					loadJar(new File(jarPath));
				} else {
					File file = new File(url.getPath());
					loadFolder(file.getParentFile(), file.getParentFile());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadJar(File f) {
		if (log.isTraceEnabled()) {
			log.trace(f.getPath());
		}
		try {
			JarFile jf = new JarFile(f);
			Enumeration<JarEntry> en = jf.entries();
			while (en.hasMoreElements()) {
				String name = en.nextElement().getName();
				if (name.endsWith(".nebula")) {
					name = name.substring(0, name.length() - ".nebula".length()).replace('\\', '.').replace('/', '.');
					this.findType(name);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void loadFolder(File root, File d) {
		if (log.isTraceEnabled()) {
			log.trace(root.getPath());
		}
		try {
			if (!d.exists() || !d.isDirectory())
				return;

			int iStart = root.getPath().length() + 1;

			for (File f : d.listFiles()) {
				if (f.isFile() && f.getName().endsWith(".nebula")) {
					String name = f.getPath();

					name = name.substring(iStart, name.length() - ".nebula".length()).replace('\\', '.')
							.replace('/', '.');
					this.findType(name);
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
		ClassLoader clzLoader = this.getClass().getClassLoader();
		InputStream is = clzLoader.getResourceAsStream(name);
		if (log.isTraceEnabled()) {
			if (is != null)
				log.trace("search class data : " + name + " succeed!");
			else
				log.trace("search class data : " + name + " fail!");
		}
		return is;
	}
}
