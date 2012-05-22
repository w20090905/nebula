package nebula.compiler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringReader;

public class Compiler {
	public Type load(String text){
		ClassPathTypeLoader loader= new ClassPathTypeLoader();
		Type type = loader.load(new StringReader(text));
		return type;		
	}
	
	public Type loadFromFile(String name){
		try {
			ClassPathTypeLoader loader= new ClassPathTypeLoader();
			Type type = loader.load(new FileInputStream(name));
			return type;
		} catch (FileNotFoundException e) {
			throw new NebulaRuntimeException(name,e);
		}		
	}
}
