package nebula.lang;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.antlr.runtime.RecognitionException;

import util.FileUtil;

import com.google.common.collect.Lists;

public class EditableTypeLoader extends TypeLoader {
	final List<File> paths;

	public EditableTypeLoader(TypeLoader parent, File root) {
		super(parent);
		this.paths = Lists.newCopyOnWriteArrayList();
		this.paths.add(root);
		this.doLoadFolder(root, root);
	}

	public void loadFolder(File folder) {
		this.paths.add(0, folder);
		if (log.isDebugEnabled()) {
			log.debug("load type define from folder - " + folder.getPath());
		}
		doLoadFolder(folder, folder);
	}

	private void doLoadFolder(File root, File d) {
		try {
			if (!d.exists() || !d.isDirectory()) return;

			for (File f : d.listFiles()) {
				if (f.isFile() && f.getName().endsWith(".nebula")) {
					if (log.isDebugEnabled()) {
						log.debug("load type define from  - " + f.toURI().toURL());
					}
					this.defineNebula(f.toURI().toURL());
				} else if (f.isDirectory()) {
					doLoadFolder(root, f);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	List<Type> defineNebula(Reader in) throws RecognitionException {
		List<Type> typeList = super.defineNebula(in);
		for (Type t : typeList) {
			t.mutable = true;
		}
		return typeList;
	}

	@Override
	List<Type> defineNebula(URL in) throws IOException, RecognitionException {
		List<Type> typeList = super.defineNebula(in);
		for (Type t : typeList) {
			t.mutable = true;
		}
		return typeList;
	}

	@Override
	public Type update(Type oldType, String newCode) {
		try {
			List<Type> newTypes = tryDefineNebula(new StringReader(newCode));

			File file;
			if (oldType == null) {
				String name = newTypes.get(0).getName();
				file = new File(paths.get(0), name + ".nebula");
			} else {
				assert oldType.getName().equals(newTypes.get(0).getName());

				oldType = this.findType(oldType.getName());
				if (!oldType.mutable) {
					throw new RuntimeException("Cannot edit");
				}

				if (oldType.getTypeLoader() != this) {
					return oldType.getTypeLoader().update(oldType, newCode);
				}

				URL url = oldType.url;
				file = new File(url.getFile());

				if (!"file".equals(url.getProtocol())) {
					throw new RuntimeException("Cannot edit");
				}
			}

			file = FileUtil.saveTo(newCode, file);

			newTypes = super.defineNebula(file.toURI().toURL());
			for (Type t : newTypes) {
				t.mutable = true;
			}

			return newTypes.get(0);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (RecognitionException e) {
			log.error(e.getClass().getName(), e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.error(e.getClass().getName(), e);
			throw new RuntimeException(e);
		}
	}

	@Override
	protected URL loadClassData(String name) {
		try {
			File file = null;
			for (File path : this.paths) {
				file = new File(path, name.replace('.', '/') + ".nebula");
				if (file.exists()) {
					break;
				}
			}
			if (file != null) {
				return file.toURI().toURL();
			} else {
				return null;
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}

interface Tracable {
	Object getSource();
}
