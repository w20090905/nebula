package nebula.compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FolderTypeLoader extends TypeLoader {
	String path;

	public FolderTypeLoader(TypeLoader loader, String root) {
		super(loader);
		this.path = root;
	}

	@Override
	protected InputStream loadClassData(String name) {
		String filename = null;
		if (this.path != null) {
			filename = this.path + "/" + name;
		} else {
			filename = name;
		}
		File file = new File(filename);
		if (file.exists()) {
			try {
				InputStream is = new FileInputStream(file);
				return is;
			} catch (FileNotFoundException e) {
				throw new NebulaRuntimeException(e);
			}
		} else {
			return null;
		}
	}

}
