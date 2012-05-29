package nebula.compiler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringReader;

public class Compiler extends TypeLoader {
	public Compiler(TypeLoader parent) {
		super(parent);
	}

	public Type load(String text){
		Type type = super.defineNebula(new StringReader(text)).get(0);
		return type;		
	}
	
	public Type loadFromFile(String name){
		try {
			Type type = super.defineNebula(new FileInputStream(name)).get(0);
			return type;	
		} catch (FileNotFoundException e) {
			throw new NebulaRuntimeException(name,e);
		}		
	}

	@Override
	protected InputStream loadClassData(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
