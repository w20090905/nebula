package nebula.lang;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import java.util.Map;

import nebula.SmartList;
import nebula.frame.AutoIdentifiable;
import nebula.frame.SmartListImp;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.FileUtil;

public abstract class TypeLoader {
	private Log log = LogFactory.getLog(this.getClass());

	TypeLoader parent;

	SmartList<Type> types = new SmartListImp<Type>("Type", new AutoIdentifiable<Type>() {
		@Override
		public String getId(Type data) {
			return data.name;
		}

		@Override
		public void set(Map<String, Type> map, Type data) {
			map.put(data.name, data);
			if (data.nameAlias != null) {
				for (String v : data.nameAlias.alias.values()) {
					map.put(v, data);
				}
			}
		}

	});

	public TypeLoader(TypeLoader parent) {
		this.parent = parent;
	}


	protected List<Type> defineNebula(Reader is) {
		try {
			List<Type> typeList = parse(new ANTLRReaderStream(is));
			types.addAll(typeList);
			if (log.isTraceEnabled()) {
				log.trace(typeList.get(0).getName() + " load succeed");
			}
			return typeList;
		} catch (IOException e) {
			throw new NebulaRuntimeException(e);
		}
	}
	
	protected List<Type> tryDefineNebula(Reader is) {
		try {
			List<Type> typeList = parse(new ANTLRReaderStream(is));
			if (log.isTraceEnabled()) {
				log.trace(typeList.get(0).getName() + " load succeed");
			}
			return typeList;
		} catch (IOException e) {
			throw new NebulaRuntimeException(e);
		}
	}

	protected List<Type> defineNebula(InputStream is) {
		try {
			List<Type> typeList = parse(new ANTLRInputStream(is,"utf-8"));
			types.addAll(typeList);
			if (log.isTraceEnabled()) {
				log.trace(typeList.get(0).getName() + " load succeed");
			}
			return typeList;
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
				log.error("Parser ERROR!");
				throw new NebulaRuntimeException("Parser ERROR!");
			}
			return types;
		} catch (RecognitionException e) {
			throw new NebulaRuntimeException(e);
		}
	}

	public Type findType(String name) {
		try {
			if (log.isTraceEnabled()) {
				log.trace(" --- " + name);
			}
			Type type = parent.findType(name);
			if (type != null) {
				return type;
			}

			type = types.get(name);
			if (type != null) {
				return type;
			}
			
			URL url =  loadClassData(name);
			String code = FileUtil.readAllTextFrom(url);
			InputStream inputStream = loadClassData(name).openStream();
			if (inputStream != null) {
				List<Type> typeList = defineNebula(inputStream);
				for(Type t:typeList){
					t.code = code;
					t.url = url;
				}
				return typeList.get(0);
			} else {
				return null;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	protected abstract URL loadClassData(String name);

	public Type update(Type oldType) {
		throw new UnsupportedOperationException("change type");
	}

	public SmartList<Type> getList() {
		return this.types;
	}
}
