package nebula.lang;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

enum TypeStandalone {
	Master, Sequence, Basic, Eembedded
}

public class Type {
	final TypeLoader loader;

	final Type residedType;
	final Type superType;
	final String rawType;// For Basic Type

	String name;
	Alias nameAlias;

	TypeStandalone standalone = TypeStandalone.Master;

	List<Field> fields;

	Properties attrs;

	URL url;
	boolean mutable = false;
	String code;

	List<Field> references;

	public static String TYPE = "Type";
	public static String ENTITY = "Entity";
	public static String BASIC = "Basic";

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
				this.standalone = TypeStandalone.Basic;
			}
		}
		this.name = name;
		this.fields = new ArrayList<Field>();
		references = new CopyOnWriteArrayList<>();
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

	public void link(TypeLoader loader) {
		for (Field f : fields) {
			f.type.references.add(f);
		}
	}

	public List<Field> getReferences() {
		return references;
	}

	@Override
	public String toString() {
		return "Type [name=" + name + ", nameAlias=" + nameAlias + ", standalone=" + standalone + ", text=" + code
				+ "]";
	}

}
