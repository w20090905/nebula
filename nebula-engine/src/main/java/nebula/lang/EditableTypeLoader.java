package nebula.lang;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.RecognitionException;

import util.FileUtil;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class EditableTypeLoader extends TypeLoader implements Runnable {
	final List<File> paths;
	final Map<URL, Type> loadedTypes;

	public EditableTypeLoader(TypeLoader parent, File... paths) {
		super(parent);
		this.paths = Lists.newCopyOnWriteArrayList();
		for (File file : paths) {
			this.paths.add(file);
		}
		loadedTypes = Maps.newHashMap();
	}
	
	public void loadAllImmediately(){
		for (File path : this.paths) {
			this.doLoadFolder(path, path);
		}
	}

	public void start() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
		for (File path : this.paths) {
			this.doLoadFolder(path, path);
		}
	}

	public void registerPath(File folder) {
		this.paths.add(folder);
		if (log.isDebugEnabled()) {
			log.debug("register folder - " + folder.getPath());
		}
	}

	private void doLoadFolder(File root, File d) {
		try {
			if (!d.exists() || !d.isDirectory()) return;

			for (File f : d.listFiles()) {
				if (f.isFile() && f.getName().endsWith(".nebula")) {
					if (log.isTraceEnabled()) {
						log.trace("load : " + f.toURI().toURL());
					}
					URL url = f.toURI().toURL();
					if (!loadedTypes.containsKey(url)) {
						this.defineNebula(f.toURI().toURL());
					}
				} else if (f.isDirectory()) {
					doLoadFolder(root, f);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	List<TypeImp> defineNebula(Reader in) throws RecognitionException {
		List<TypeImp> typeList = super.defineNebula(in);
		for (Type t : typeList) {
			TypeImp ti = (TypeImp)t;
			ti.mutable = true;
		}
		return typeList;
	}

	@Override
	List<TypeImp> defineNebula(URL in) throws IOException, RecognitionException {
		List<TypeImp> typeList = super.defineNebula(in);
		for (TypeImp t : typeList) {
			t.mutable = true;
		}
		loadedTypes.put(in, typeList.get(0));
		return typeList;

	}

	@Override
	public Type update(Type oldType, String newCode) {
		try {
			List<TypeImp> newTypes = tryDefineNebula(new StringReader(newCode));

			File file;
			if (oldType == null) {
				String name = newTypes.get(0).getName();
				file = new File(paths.get(0), name + ".nebula");
			} else {
				assert oldType.getName().equals(newTypes.get(0).getName());

				oldType = this.findType(oldType.getName());
				if (!oldType.isMutable()) {
					throw new RuntimeException("Cannot edit");
				}

				unlink(oldType);

				if (oldType.getTypeLoader() != this) {
					return oldType.getTypeLoader().update(oldType, newCode);
				}

				URL url = oldType.getUrl();
				file = new File(url.getFile());

				if (!"file".equals(url.getProtocol())) {
					throw new RuntimeException("Cannot edit");
				}
			}

			file = FileUtil.saveTo(newCode, file);

			newTypes = super.defineNebula(file.toURI().toURL());
			for (Type t : newTypes) {
				TypeImp ti = (TypeImp)t;
				ti.mutable = true;
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

	protected void unlink(Type topType) {
		for (Field f : topType.getFields()) {
			f.type.getReferences().remove(f);
			if (f.attrs.containsKey(Type.ATTACH)) {
				f.type.getAttachedBy().remove(topType);
			}
		}

		for (Type oldType : topType.getSubTypes()) {
			for (Field f : oldType.getFields()) {
				f.type.getReferences().remove(f);
				if (f.attrs.containsKey(Type.ATTACH)) {
					f.type.getAttachedBy().remove(oldType);
				}
			}
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
