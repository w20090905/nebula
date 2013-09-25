package nebula.lang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import java.util.Map;

import nebula.data.Classificator;
import nebula.data.DataListener;
import nebula.data.InheritSmartList;
import nebula.data.SmartList;
import nebula.data.impl.DataClassificator;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.FileUtil;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
public abstract class TypeLoader {	
	protected Log log = LogFactory.getLog(this.getClass());

	final TypeLoader parent;
	final SmartList<String, Type> types;
	final Map<String, Type> typesLoading;

	protected long lastModified;
	final Classificator<String, Type> classifyBy;

	
	Function<Type, String> typeIndexFunction =new Function<Type, String>() {
		@Override
		public String apply(Type from) {
			return from.name;
		}
	};
	
	@SuppressWarnings("unchecked")
	public TypeLoader(TypeLoader parent) {
		this.parent = parent;
		if (parent != null) {
			types = new InheritSmartList<String, Type>(parent.types, typeIndexFunction);
		} else {
			types = new SmartList<String, Type>(typeIndexFunction);
		}
		
		typesLoading = Maps.newHashMap();
		
		classifyBy = new DataClassificator<String, Type>(new Function<Type, String>() {
			@Override
			public String apply(Type from) {
				return from.getStandalone().toString();
			}
		});
		types.addListener((DataListener<Type>) classifyBy);
	}

	public void addListener(DataListener<Type> listener) {
		types.addListener(listener);
		if (this.parent != null) {
			parent.addListener(listener);
		}
	}

	//
	// public Filter<Type> liveFilter(Predicate<Type> filterFunction) {
	// return types.liveFilter(filterFunction);
	// }
	//
	// public List<Type> filter(Predicate<Type> filterFunction) {
	// return types.filter(filterFunction);
	// }
	//
	// public <K> Classificator<K, Type> classify(Function<Type, K>
	// indexerFunction) {
	// return types.classify(indexerFunction);
	// }
	//
	// public <K> Classificator<K, Type> liveClassify(Function<Type, K>
	// indexerFunction) {
	// return types.liveClassify(indexerFunction);
	// }

	//TODO refact
	public Classificator<String, Type> groupByStandalone() {
		return this.classifyBy;
	}

	List<Type> defineNebula(Reader in) throws RecognitionException {
		try {
			BufferedReader bin = new BufferedReader(in);
			String code = FileUtil.readAllTextFrom(bin);
			in = bin;
			List<Type> typeList = parse(new ANTLRReaderStream(in));
			this.lastModified = System.currentTimeMillis();
			for (Type t : typeList) {
				t.code = code;
				t.url = null;
				link(t);
				t.lastModified = this.lastModified;
			}

			types.addAll(typeList);

			if (log.isTraceEnabled()) {
				log.trace("\tload type define succeed - " + typeList.get(0).getName());
			}
			return typeList;
		} catch (IOException e) {
			throw new NebulaRuntimeException(e);
		}
	}

	List<Type> tryDefineNebula(Reader in) throws RecognitionException, IOException {
		BufferedReader bin = new BufferedReader(in);
		String code = FileUtil.readAllTextFrom(bin);
		in = bin;
		List<Type> typeList = parse(new ANTLRReaderStream(in));
		this.lastModified = System.currentTimeMillis();
		for (Type t : typeList) {
			t.code = code;
			t.url = null;
			t.lastModified = this.lastModified;
		}
		if (log.isTraceEnabled()) {
			log.trace("\tload type [" + typeList.get(0).getName() + "] succeed from reader ");
		}
		return typeList;
	}

	List<Type> defineNebula(URL in) throws IOException, RecognitionException {
		String code = FileUtil.readAllTextFrom(in);
		List<Type> typeList = parse(new ANTLRInputStream(in.openStream(), "utf-8"));
		long lastModified = in.openConnection().getLastModified();
		this.lastModified = lastModified > this.lastModified ? lastModified : this.lastModified;
		Type topType = typeList.get(0);
		for (Type type : typeList) {
			type.code = code;
			type.url = in;
			type.lastModified = lastModified;
			link(type);
			topType.subTypes.add(type);
		}
		types.addAll(typeList);
		if (log.isTraceEnabled()) {
			log.trace("\tload type [" + typeList.get(0).getName() + "]  succeed  from url - " + typeList.get(0).url);
		}
		return typeList;
	}


	protected void link(Type type) {
		for (Field f : type.fields) {
			f.type.references.add(f);
			if(f.attrs.containsKey(Type.ATTACH)){
				type.attrs.put(Type.ATTACH_TO, f.type.name);
				f.type.attachedBy.add(type);
			}
		}
	}

	private List<Type> parse(CharStream in) throws RecognitionException {
		NebulaLexer assemblerLexer = new NebulaLexer(in);
		CommonTokenStream tokens = new CommonTokenStream(assemblerLexer);
		NebulaParser parser = new NebulaParser(tokens, this);
		List<Type> typeList = parser.programDefinition();
		if (parser.getNumberOfSyntaxErrors() > 0) {
			log.error("Parser ERROR!");
			throw new NebulaRuntimeException("Parser ERROR!");
		}
		return typeList;
	}

	static long cntLevel = 0;

	public Type findType(String name) {
		if (log.isTraceEnabled()) {
			log.trace("\tsearch type [" + name + "]  from " + this.getClass().getName());
		}

		try {
			Type type = types.get(name);
			if (type != null) {
				if (log.isTraceEnabled()) {
					log.trace("\tfind type [" + name + "]  from " + this.getClass().getName());
				}
				return type;
			}

			type = parent.findType(name);
			if (type != null) {
				if (log.isTraceEnabled()) {
					log.trace("\tfind type [" + name + "]  from " + this.getClass().getName());
				}
				return type;
			}
			if (log.isDebugEnabled()) {
				log.debug("[ " + ++cntLevel + " ] " + name + " - try     loadClassData within "
						+ this.getClass().getName());
			}
			URL url = loadClassData(name);
			if (url != null) {
				List<Type> typeList = defineNebula(url);
				if (log.isDebugEnabled()) {
					log.debug("loaded type [" + name + "]  \tfrom " + this.getClass().getName());
				}
				if (log.isDebugEnabled()) {
					log.debug("[ " + cntLevel-- + " ] " + name + " - succeed loadClassData within "
							+ this.getClass().getName());
				}
				return typeList.get(0);
			} else {
				if (log.isDebugEnabled()) {
					log.debug("[ " + cntLevel-- + " ] " + name + " - cannot   loadClassData within "
							+ this.getClass().getName());
				}
				return null;
			}
		} catch (IOException e) {
			log.error("IOException ", e);
			throw new RuntimeException(e);
		} catch (RecognitionException e) {
			log.error("RecognitionException ", e);
			throw new RuntimeException(e);
		}
	}

	abstract URL loadClassData(String name);

	public Type update(Type oldType, String newCode) {
		throw new UnsupportedOperationException("change type");
	}

	public SmartList<String, Type> getList() {
		return this.types;
	}

	public long getLastModified() {
		return lastModified;
	}
}
