package nebula.compiler;

import java.io.InputStream;

public class ClassPathTypeLoader extends TypeLoader {
	String path;

	public ClassPathTypeLoader(TypeLoader parent) {
		super(parent);
	}

	public ClassPathTypeLoader() {
		super(new SystemTypeLoader());
	}

	@Override
	protected InputStream loadClassData(String name) {
		ClassLoader clzLoader = this.getClass().getClassLoader();
		InputStream is = clzLoader.getResourceAsStream(name);
		if (is == null) {
			return null;
		}
		return is;
	}

}
