package nebula.compiler;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public abstract class TypeLoader {
	TypeLoader parent;

	public TypeLoader(TypeLoader parent) {
		this.parent = parent;
	}

	Map<String, Type> types = new HashMap<String, Type>();

	protected final Type defineNebula(String name, InputStream is) {
		try {
			NebulaLexer assemblerLexer = new NebulaLexer(new ANTLRInputStream(is));
			CommonTokenStream tokens = new CommonTokenStream(assemblerLexer);
			NebulaParser parser = new NebulaParser(tokens, this);
			Type type = parser.typeDefinition();
			return type;
		} catch (RecognitionException e) {
			throw new NebulaRuntimeException(e);
		} catch (IOException e) {
			throw new NebulaRuntimeException(e);
		}
	}

	protected final Type defineNAsm(String name, InputStream is) {

		return null;
	}

	protected final Type defineNType(String name, InputStream is) {

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
		InputStream is = loadClassData(fullname);
		if (is != null) {
			type = defineNType(name, is);
			types.put(name, type);
			return type;
		}

		fullname = name.replace('.', '/') + ".nebula";
		is = loadClassData(fullname);
		if (is != null) {
			type = defineNebula(name, is);
			types.put(name, type);
			return type;
		}

		fullname = name.replace('.', '/') + ".nasm";
		is = loadClassData(fullname);
		if (is != null) {
			type = defineNAsm(name, is);
			types.put(name, type);
			return type;
		} else {
			return parent.findType(name);
		}
	}

	protected abstract InputStream loadClassData(String name);
}
