package nebula.lang;

import java.io.File;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import nebula.SmartList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.FileUtil;

public class EditableTypeLoader extends TypeLoader {
	private Log log = LogFactory.getLog(this.getClass());

	final File root;

	public EditableTypeLoader(TypeLoader parent, File root) {
		super(parent);
		this.root = root;
		this.loadFolder(root);
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
					this.defineNebula(f.toURI().toURL());
				} else if (f.isDirectory()) {
					loadFolder(root, f);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected List<Type> defineNebula(Reader in) {
		List<Type> typeList = super.defineNebula(in);
		for (Type t : typeList) {
			t.mutable = true;
		}
		return typeList;
	}

	@Override
	protected List<Type> defineNebula(URL in) {
		List<Type> typeList = super.defineNebula(in);
		for (Type t : typeList) {
			t.mutable = true;
		}
		return typeList;
	}

	//
	// @Override
	// protected List<Type> defineNebula(InputStream in) {
	// List<Type> typeList = super.defineNebula(in);
	// for (Type t : typeList) {
	// t.mutable = true;
	// }
	// return typeList;
	// }

	@Override
	public Type update(Type oldType) {
		try {
			String code = oldType.code;

			List<Type> typeList = tryDefineNebula(new StringReader(code));
			
			File file;
			if (oldType.name == null) {
				String name = typeList.get(0).getName();
				file = new File(root, name + ".nebula");
			} else {
				Type oldInnerType = this.findType(typeList.get(0).name);
				if (!oldInnerType.mutable) {
					throw new RuntimeException("Cannot edit");
				}

				if (oldInnerType.getTypeLoader() != this) {
					return oldInnerType.getTypeLoader().update(oldType);
				}

				URL url = oldInnerType.url;
				file = new File(url.getFile());

				if (!"file".equals(url.getProtocol())) {
					throw new RuntimeException("Cannot edit");
				}
			}

			file = FileUtil.saveTo(code, file);

			typeList = super.defineNebula(file.toURI().toURL());
			for (Type t : typeList) {
				t.mutable = true;
			}

			return typeList.get(0);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected URL loadClassData(String name) {
		try {
			File file = new File(root, name.replace('.', '/') + ".nebula");
			return file.toURI().toURL();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}

interface Tracable {
	Object getSource();
}