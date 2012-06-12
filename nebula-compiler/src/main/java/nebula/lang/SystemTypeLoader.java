package nebula.lang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SystemTypeLoader extends TypeLoader {
	private Log log = LogFactory.getLog(this.getClass());
	final ResourcePoolTail source;

	public SystemTypeLoader() {
		super(BootstrapTypeLoader.getInstance());
		this.source = new ResourcePoolTail();
		this.init("nebula.properties");
	}

	public SystemTypeLoader(TypeLoader parent) {
		super(parent);
		this.source = new ResourcePoolTail();
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

	private void init(String name) {
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

			// resources = this.getClass().getClassLoader().getResources(name);
			// while (resources.hasMoreElements()) {
			// URL url = resources.nextElement();
			// if ("jar".equals(url.getProtocol())) {
			// loadJar(new File(url.getPath().substring(5).split("!")[0]));
			// } else {
			// loadFolder(new File(url.getPath()).getParentFile());
			// }
			// }

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// private void loadJar(File f) {
	// if (log.isTraceEnabled()) {
	// log.trace(f.getPath());
	// }
	// try {
	// JarFile jf = new JarFile(f);
	// Enumeration<JarEntry> entries = jf.entries();
	// while (entries.hasMoreElements()) {
	// JarEntry entry = entries.nextElement();
	// if (entry.getName().endsWith(".nebula")) {
	// if (log.isTraceEnabled()) {
	// log.trace("** Load type from " + entry.getName());
	// }
	// List<Type> typeList = super.defineNebula(jf.getInputStream(entry));
	// for (Type type : typeList) {
	// type.underlyingSource = entry;
	// }
	// }
	// }
	// } catch (Exception e) {
	// throw new RuntimeException(e);
	// }
	// }

	public void loadFolder(File root) {
		source.appendResourcePath(new DirResourcePath(root.getAbsolutePath()));
		if (log.isTraceEnabled()) {
			log.trace(root.getPath());
		}
		loadFolder(root, root);
	}

	protected void reload(File file) {
		try {
			List<Type> typeList = super.defineNebula(new FileInputStream(file));
			for (Type type : typeList) {
				type.underlyingSource = file;
				type.text = readAllTextFrom(file);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public Type reload(Type oldType) {
		try {
			List<Type> typeList = tryDefineNebula(new StringReader(oldType.text));
			File file = (File)oldType.underlyingSource;

			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

			String abpath = file.getAbsolutePath();
			File newFile = new File(abpath + "." +  format.format(new Date(file.lastModified())));
			
			file.renameTo(newFile);

			file = new File(abpath);
			Writer w = new FileWriter(file);
			w.write(oldType.text);
			w.close();

			this.reload(file);
			
			return typeList.get(0);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
//	public Type reload(Reader is) {
//		List<Type> typeList = defineNebula(is);
//		for (Type type : typeList) {
//			type.text = readAllTextFrom(is);
//		}
//		return typeList.get(0);
//	}

	public void reload(URL url) {
		try {
			List<Type> typeList = super.defineNebula(url.openStream());
			for (Type type : typeList) {
				type.underlyingSource = url;
				type.text = readAllTextFrom(url);
			}
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
					List<Type> typeList = super.defineNebula(new FileInputStream(f));
					for (Type type : typeList) {
						type.underlyingSource = f;
						type.text = readAllTextFrom(f);
					}
				} else if (f.isDirectory()) {
					loadFolder(root, f);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// TODO move to other place
	private String readAllTextFrom(File file) {
		try {
			return readAllTextFrom(new FileReader(file));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// TODO move to other place
	private String readAllTextFrom(URL url) {
		try {
			return readAllTextFrom(new InputStreamReader(url.openStream()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String readAllTextFrom(Reader reader) {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = null;
		try {
			in = new BufferedReader(reader);
			String textLine = null;
			while ((textLine = in.readLine()) != null) {
				sb.append(textLine);
				sb.append('\n');
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		return sb.toString();
	}

	@Override
	protected InputStream loadClassData(String name) {
		InputStream inputStream = source.openResouce(name, ".nebula");
		return inputStream;
	}
}
