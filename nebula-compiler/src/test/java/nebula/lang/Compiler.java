package nebula.lang;

import java.io.File;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Compiler extends TypeLoader {
	public Compiler(TypeLoader parent) {
		super(parent);
	}

	public Type load(String text) {
		Type type = super.defineNebula(new StringReader(text)).get(0);
		return type;
	}

	public Type loadFromFile(String name) {
		try {
			Type type = super.defineNebula(new File(name).toURI().toURL()).get(0);
			return type;
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected URL loadClassData(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
