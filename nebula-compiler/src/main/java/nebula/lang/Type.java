package nebula.lang;

import java.net.URL;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import nebula.data.Identifiable;
import util.InheritHashMap;

public class Type implements Identifiable {
	final TypeLoader loader;

	final Type residedType;
	final Type superType;
	final RawTypes rawType;// For Basic Type

	final String name;
	final Alias nameAlias;

	final TypeStandalone standalone;

	final List<Field> fields;

	final InheritHashMap attrs;

	URL url;
	boolean mutable = false;
	String code;
	
	long lastModified;

	final List<Field> references;

	public static String ROOT_TYPE = "T";

	/*
	 * 
	 * public static String BASIC = "Basic"; public static String MASTER =
	 * "Master"; public static String TRANSACTION = "Transaction"; public static
	 * String MIXIN = "Mixin";
	 */

	Type(TypeLoader typeLoader, String name) {
		this.superType = null;
		this.residedType = null;
		this.rawType = null;
		this.loader = typeLoader;
		this.standalone = TypeStandalone.Abstract;
		this.name = name;
		this.fields = new CopyOnWriteArrayList<Field>();
		this.attrs = new InheritHashMap();
		this.references = new CopyOnWriteArrayList<Field>();
		this.nameAlias = new Alias(name);
	}

	/**
	 * Used by Basic type, Master,Tx
	 */
	Type(TypeLoader typeLoader, String name, Type superType) {
		this(typeLoader, null, name, superType, superType.rawType, superType.standalone);
	}

	/**
	 * Used by Master,Tx  With standalone
	 */
	Type(TypeLoader typeLoader, String name, Type superType, TypeStandalone standalone) {
		this(typeLoader, null, name, superType, null, standalone);
	}

	/**
	 * Used by basic type init
	 */
	Type(TypeLoader typeLoader, String name, Type superType, RawTypes rawType) {
		this(typeLoader, null, name, superType, rawType, TypeStandalone.Basic);
	}

	/**
	 * Used by mixin
	 * 
	 */
	Type(TypeLoader typeLoader, Type residedType, String name, Type superType) {
		this(typeLoader, residedType, name, superType, null, TypeStandalone.Mixin);
	}

	Type(TypeLoader typeLoader, Type residedType, String name, Type superType, RawTypes rawType,
			TypeStandalone standalone) {
		super();
		this.loader = typeLoader;
		this.name = name;

		this.superType = superType;

		this.standalone = standalone;
		this.rawType = rawType;

		this.residedType = residedType; // Mixin


		this.fields = new CopyOnWriteArrayList<Field>();

		this.attrs = new InheritHashMap(this.superType.attrs);
		
		this.references = new CopyOnWriteArrayList<Field>();
		this.nameAlias = new Alias(name);
		
	}

	public String getName() {
		return name;
	}

	public List<Field> getFields() {
		return fields;
	}

	public Alias getNameAlias() {
		return nameAlias;
	}
	
	public void setNameAlias(Alias alias) {
		nameAlias.alias.putAll(alias.alias);
	}
	
	public InheritHashMap getAttrs() {
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

	public RawTypes getRawType() {
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

	@Override
	public String getID() {
		return name;
	}

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

}
