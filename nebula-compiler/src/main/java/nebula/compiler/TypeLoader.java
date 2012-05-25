package nebula.compiler;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class TypeLoader {
	private Log log = LogFactory.getLog(this.getClass());

	TypeLoader parent;

	public TypeLoader(TypeLoader parent) {
		this.parent = parent;
	}

	public Type load(Reader is) {
		List<Type> typeList = defineNebula(is);
		for (Type type : typeList) {
			types.put(type.name, type);
		}
		return typeList.get(0);
	}

	public Type load(InputStream is) {
		List<Type> typeList = defineNebula(is);
		for (Type type : typeList) {
			types.put(type.name, type);
		}
		return typeList.get(0);
	}

	Map<String, Type> types = new HashMap<String, Type>();

	protected final List<Type> defineNebula(Reader is) {
		try {
			return parse(new ANTLRReaderStream(is));
		} catch (IOException e) {
			throw new NebulaRuntimeException(e);
		}
	}

	protected final List<Type> defineNebula(InputStream is) {
		try {
			return parse(new ANTLRInputStream(is));
		} catch (IOException e) {
			throw new NebulaRuntimeException(e);
		}
	}

	protected List<Type> parse(CharStream is) {
		try {
			NebulaLexer assemblerLexer = new NebulaLexer(is);
			CommonTokenStream tokens = new CommonTokenStream(assemblerLexer);
			NebulaParser parser = new NebulaParser(tokens, this);
			List<Type> types = parser.programDefinition();
			if (parser.getNumberOfSyntaxErrors() > 0) {
				throw new NebulaRuntimeException("Parser ERROR!");
			}
			return types;
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
		Type type = parent.findType(name);
		if (type != null) {
			return type;
		}

		type = types.get(name);
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
			List<Type> typeList = defineNebula(inputStream);
			for (Type t : typeList) {
				types.put(t.name, t);
			}
			return typeList.get(0);
		}

		fullname = name.replace('.', '/') + ".nasm";
		inputStream = loadClassData(fullname);
		if (inputStream != null) {
			type = defineNAsm(inputStream);
			types.put(type.name, type);
			return type;
		} else {
			throw null;
		}
	}

	protected InputStream loadClassData(String name) {
		ClassLoader clzLoader = this.getClass().getClassLoader();
		InputStream is = clzLoader.getResourceAsStream(name);
		if (log.isTraceEnabled()) {
			if (is != null)
				log.trace("search class data : " + name + " succeed!");
			else
				log.trace("search class data : " + name + " fail!");
		}
		return is;
	}
}
