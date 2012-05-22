package nebula.compiler;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public abstract class TypeLoader {
	TypeLoader parent;

	public TypeLoader(TypeLoader parent) {
		this.parent = parent;
	}

	public Type load(Reader is) {
		Type type = defineNebula(is);
		types.put(type.name, type);
		return type;
	}

	public Type load(InputStream is) {
		Type type = defineNebula(is);
		types.put(type.name, type);
		return type;
	}
	
	Map<String, Type> types = new HashMap<String, Type>();

	protected final Type defineNebula(Reader is) {
		try {
			return parse(new ANTLRReaderStream(is));
		} catch (IOException e) {
			throw new NebulaRuntimeException(e);
		}
	}

	protected final Type defineNebula(InputStream is) {
		try {
			return parse(new ANTLRInputStream(is));
		} catch (IOException e) {
			throw new NebulaRuntimeException(e);
		}
	}

	protected Type parse(CharStream is) {
		try {
			NebulaLexer assemblerLexer = new NebulaLexer(is);
			CommonTokenStream tokens = new CommonTokenStream(assemblerLexer);
			NebulaParser parser = new NebulaParser(tokens, this);
			Type type = parser.typeDefinition();
			if(parser.getNumberOfSyntaxErrors() >0){
				throw new NebulaRuntimeException("Parser ERROR!");				
			}
			return type;
		} catch (RecognitionException e) {
			throw new NebulaRuntimeException(e);
		}
	}

	protected final Type defineNAsm(InputStream is) {

		return null;
	}

	protected final Type defineNType(InputStream is) {

		return null;
	}

	public Type findType(String name) {
		Type type = types.get(name);
		if (type != null) {
			return type;
		}

		type = parent.findType(name);
		if (type != null) {
			return type;
		}

		String fullname = name.replace('.', '/') + ".ntype";
		InputStream inputStream = loadClassData(fullname);
		if (inputStream != null) {
			type = defineNType(inputStream);
			types.put(type.name, type);
			return type;
		}

		fullname = name.replace('.', '/') + ".nebula";
		inputStream = loadClassData(fullname);
		if (inputStream != null) {
			type = defineNebula(inputStream);
			types.put(type.name, type);
			return type;
		}

		fullname = name.replace('.', '/') + ".nasm";
		inputStream = loadClassData(fullname);
		if (inputStream != null) {
			type = defineNAsm(inputStream);
			types.put(type.name, type);
			return type;
		} else {
			return parent.findType(name);
		}
	}

	protected abstract InputStream loadClassData(String name);
}
