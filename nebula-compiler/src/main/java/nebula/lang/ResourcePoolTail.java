package nebula.lang;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

final class ClassResourcePath implements ResourcePath {
	private Class<?> thisClass;

	public ClassResourcePath(Class<?> c) {
		thisClass = c;
	}

	ClassResourcePath() {
		this(java.lang.Object.class);
	}

	public InputStream openResouce(String name, String ext) {
		name = "/" + name.replace('.', '/') + ext;
		return thisClass.getResourceAsStream(name);
	}

	public URL find(String name, String ext) {
		name = "/" + name.replace('.', '/') + ext;
		return thisClass.getResource(name);
	}

	public void close() {
	}

	public String toString() {
		return thisClass.getName();
	}
}

final class ResourcePathList {
	ResourcePathList next;
	ResourcePath path;

	ResourcePathList(ResourcePath p, ResourcePathList n) {
		next = n;
		path = p;
	}
}

final class DirResourcePath implements ResourcePath {
	String directory;

	DirResourcePath(String dirName) {
		directory = dirName;
	}

	public InputStream openResouce(String name, String ext) {
		try {
			char sep = File.separatorChar;
			String filename = directory + sep + name.replace('.', sep) + ext;
			return new FileInputStream(filename.toString());
		} catch (FileNotFoundException e) {
		} catch (SecurityException e) {
		}
		return null;
	}

	public URL find(String name, String ext) {
		char sep = File.separatorChar;
		String filename = directory + sep + name.replace('.', sep) + ext;
		File f = new File(filename);
		if (f.exists())
			try {
				return f.getCanonicalFile().toURI().toURL();
			} catch (MalformedURLException e) {
			} catch (IOException e) {
			}

		return null;
	}

	public void close() {
	}

	public String toString() {
		return directory;
	}
}

final class JarDirResourcePath implements ResourcePath {
	JarClassPath[] jars;

	JarDirResourcePath(String dirName) {
		File[] files = new File(dirName).listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				name = name.toLowerCase();
				return name.endsWith(".jar") || name.endsWith(".zip");
			}
		});

		if (files != null) {
			jars = new JarClassPath[files.length];
			for (int i = 0; i < files.length; i++)
				jars[i] = new JarClassPath(files[i].getPath());
		}
	}

	public InputStream openResouce(String name, String ext) {
		if (jars != null)
			for (int i = 0; i < jars.length; i++) {
				InputStream is = jars[i].openResouce(name, ext);
				if (is != null)
					return is;
			}

		return null; // not found
	}

	public URL find(String name, String ext) {
		if (jars != null)
			for (int i = 0; i < jars.length; i++) {
				URL url = jars[i].find(name, ext);
				if (url != null)
					return url;
			}

		return null; // not found
	}

	public void close() {
		if (jars != null)
			for (int i = 0; i < jars.length; i++)
				jars[i].close();
	}
}

final class JarClassPath implements ResourcePath {
	JarFile jarfile;
	String jarfileURL;

	JarClassPath(String pathname) {
		try {
			jarfile = new JarFile(pathname);
			jarfileURL = new File(pathname).getCanonicalFile().toURI().toURL().toString();
			return;
		} catch (IOException e) {
		}
		throw new RuntimeException(pathname);
	}

	public InputStream openResouce(String name, String ext) {
		try {
			String jarname = name.replace('.', '/') + ext;
			JarEntry je = jarfile.getJarEntry(jarname);
			if (je != null)
				return jarfile.getInputStream(je);
			else
				return null; // not found
		} catch (IOException e) {
		}
		throw new RuntimeException("broken jar file?: " + jarfile.getName());
	}

	public URL find(String name, String ext) {
		String jarname = name.replace('.', '/') + ext;
		JarEntry je = jarfile.getJarEntry(jarname);
		if (je != null)
			try {
				return new URL("jar:" + jarfileURL + "!/" + jarname);
			} catch (MalformedURLException e) {
			}

		return null; // not found
	}

	public void close() {
		try {
			jarfile.close();
			jarfile = null;
		} catch (IOException e) {
		}
	}

	public String toString() {
		return jarfile == null ? "<null>" : jarfile.toString();
	}
}

public class ResourcePoolTail implements ResourcePath {
	protected ResourcePathList pathList;
	private Hashtable<String, String> packages; // should be synchronized.

	public ResourcePoolTail() {
		pathList = null;
		packages = new Hashtable<String, String>();
	}

	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("[class path: ");
		ResourcePathList list = pathList;
		while (list != null) {
			buf.append(list.path.toString());
			buf.append(File.pathSeparatorChar);
			list = list.next;
		}

		buf.append(']');
		return buf.toString();
	}

	public synchronized ResourcePath insertResourcePath(ResourcePath cp) {
		pathList = new ResourcePathList(cp, pathList);
		return cp;
	}

	public synchronized ResourcePath appendResourcePath(ResourcePath cp) {
		ResourcePathList tail = new ResourcePathList(cp, null);
		ResourcePathList list = pathList;
		if (list == null)
			pathList = tail;
		else {
			while (list.next != null)
				list = list.next;

			list.next = tail;
		}

		return cp;
	}

	public synchronized void removeResourcePath(ResourcePath cp) {
		ResourcePathList list = pathList;
		if (list != null)
			if (list.path == cp)
				pathList = list.next;
			else {
				while (list.next != null)
					if (list.next.path == cp)
						list.next = list.next.next;
					else
						list = list.next;
			}

		cp.close();
	}

	public ResourcePath appendSystemPath() {
		return appendResourcePath(new ClassResourcePath());
	}

	public ResourcePath insertResourcePath(String pathname) {
		return insertResourcePath(makePathObject(pathname));
	}

	public ResourcePath appendResourcePath(String pathname) {
		return appendResourcePath(makePathObject(pathname));
	}

	private static ResourcePath makePathObject(String pathname) {
		String lower = pathname.toLowerCase();
		if (lower.endsWith(".jar") || lower.endsWith(".zip"))
			return new JarClassPath(pathname);

		int len = pathname.length();
		if (len > 2 && pathname.charAt(len - 1) == '*'
				&& (pathname.charAt(len - 2) == '/' || pathname.charAt(len - 2) == File.separatorChar)) {
			String dir = pathname.substring(0, len - 2);
			return new JarDirResourcePath(dir);
		}

		return new DirResourcePath(pathname);
	}

	/**
	 * You can record "System" so that java.lang.System can be quickly found
	 * although "System" is not a package name.
	 */
	public void recordInvalidClassName(String name) {
		packages.put(name, name);
	}

	/**
	 * This method does not close the output stream.
	 */
	void writeClassfile(String name, String ext, OutputStream out) {
		InputStream fin = openResouce(name, ext);
		if (fin == null)
			throw new RuntimeException(name);

		try {
			copyStream(fin, out);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				fin.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * Opens the class file for the class specified by <code>name</code>.
	 * 
	 * @param name
	 *            a fully-qualified class name
	 * @return null if the file has not been found.
	 * @throws NotFoundException
	 *             if any error is reported by ClassPath.
	 */
	public InputStream openResouce(String name, String ext) {
		if (packages.get(name) != null)
			return null; // not found

		ResourcePathList list = pathList;
		InputStream ins = null;
		RuntimeException error = null;
		while (list != null) {
			try {
				ins = list.path.openResouce(name, ext);
			} catch (RuntimeException e) {
				if (error == null)
					error = e;
			}

			if (ins == null)
				list = list.next;
			else
				return ins;
		}

		if (error != null)
			throw error;
		else
			return null; // not found
	}

	/**
	 * Searches the class path to obtain the URL of the class file specified by
	 * name. It is also used to determine whether the class file exists.
	 * 
	 * @param name
	 *            a fully-qualified class name.
	 * @return null if the class file could not be found.
	 */
	public URL find(String name, String ext) {
		if (packages.get(name) != null)
			return null;

		ResourcePathList list = pathList;
		URL url = null;
		while (list != null) {
			url = list.path.find(name, ext);
			if (url == null)
				list = list.next;
			else
				return url;
		}

		return null;
	}

	/**
	 * Reads from an input stream until it reaches the end.
	 * 
	 * @return the contents of that input stream
	 */
	public static byte[] readStream(InputStream fin) throws IOException {
		byte[][] bufs = new byte[8][];
		int bufsize = 4096;

		for (int i = 0; i < 8; ++i) {
			bufs[i] = new byte[bufsize];
			int size = 0;
			int len = 0;
			do {
				len = fin.read(bufs[i], size, bufsize - size);
				if (len >= 0)
					size += len;
				else {
					byte[] result = new byte[bufsize - 4096 + size];
					int s = 0;
					for (int j = 0; j < i; ++j) {
						System.arraycopy(bufs[j], 0, result, s, s + 4096);
						s = s + s + 4096;
					}

					System.arraycopy(bufs[i], 0, result, s, size);
					return result;
				}
			} while (size < bufsize);
			bufsize *= 2;
		}

		throw new IOException("too much data");
	}

	/**
	 * Reads from an input stream and write to an output stream until it reaches
	 * the end. This method does not close the streams.
	 */
	public static void copyStream(InputStream fin, OutputStream fout) throws IOException {
		int bufsize = 4096;
		for (int i = 0; i < 8; ++i) {
			byte[] buf = new byte[bufsize];
			int size = 0;
			int len = 0;
			do {
				len = fin.read(buf, size, bufsize - size);
				if (len >= 0)
					size += len;
				else {
					fout.write(buf, 0, size);
					return;
				}
			} while (size < bufsize);
			fout.write(buf);
			bufsize *= 2;
		}

		throw new IOException("too much data");
	}

	@Override
	public void close() {
	}
}
