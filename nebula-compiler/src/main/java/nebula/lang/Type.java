package nebula.lang;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

enum TypeStandalone {
	Master, Sequence, BuilderIn, Eembedded
}

public class Type {


	final Type residedType;
	final Type superType;
	final String rawType;
	String name;
	Alias nameAlias;

	TypeStandalone standalone = TypeStandalone.Master;

	List<Field> fields;

	Properties attrs;

	final TypeLoader loader;
	URL url;
	boolean mutable=false; 
	String code;
	// Type declaringType;

	public static String ENTITY = "Entity";
	public static String BUILDIN = "BuildInType";

	Type(TypeLoader typeLoader, String name) {
		this(typeLoader, name, null, null);
	}

	Type(TypeLoader typeLoader, String name, Type superType) {
		this(typeLoader, name, superType, superType.rawType);
	}

	Type(TypeLoader typeLoader, String name, Type superType, String rawType) {
		this(typeLoader, null, name, superType, rawType);
	}

	Type(TypeLoader typeLoader, Type residedType, String name, Type superType) {
		this(typeLoader, residedType, name, superType, superType.rawType);
	}

	Type(TypeLoader typeLoader, Type residedType, String name, Type superType, String rawType) {
		super();
		this.loader = typeLoader;
		this.residedType = residedType;
		this.superType = superType;
		this.rawType = rawType;

		if (residedType != null) {
			standalone = TypeStandalone.Eembedded;
		}

		if (this.superType != null) {
			attrs = new Properties(this.superType.attrs);
			this.standalone = superType.standalone;
		} else {
			attrs = new Properties();
			if ("Entity".equals(name)) {
				this.standalone = TypeStandalone.Master;
			} else {
				this.standalone = TypeStandalone.BuilderIn;
			}
		}
		this.name = name;
		this.fields = new ArrayList<Field>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Field> getFields() {
		return fields;
	}

	public Alias getNameAlias() {
		return nameAlias;
	}

	public Properties getAttrs() {
		return attrs;
	}

	public TypeLoader getTypeLoader() {
		return loader;
	}

	public Type getResidedType() {
		return residedType;
	}

	public Type getSuperType() {
		return superType;
	}

	public String getRawType() {
		return rawType;
	}

	public TypeStandalone getStandalone() {
		return standalone;
	}

	public URL getUrl() {
		return url;
	}

	public boolean isMutable() {
		return mutable;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return "Type [name=" + name + ", nameAlias=" + nameAlias + ", standalone=" + standalone + ", text=" + code
				+ "]";
	}
}