package nebula.lang;

import java.net.URL;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import nebula.data.Timable;
import util.InheritHashMap;

public class Type implements Timable {	
	public static final String ATTACH_TO = "AttachTo";
	public static final String ATTACH = "Attach";
	
	public static final String CTOR = "<ctor>";
	public static final String ONSAVE = "<onSave>";
	public static final String ONLOAD = "<onLoad>";

	final TypeLoader loader;

	final Type residedType;
	final Type superType;
	final RawTypes rawType;// For Basic Type

	final String name;
	Aliases nameAlias;

	final TypeStandalone standalone;

	final List<Field> fields;
	final List<Field> actions;

	final InheritHashMap attrs;

	URL url;
	boolean mutable = false;
	String code;

	long lastModified;

	final List<Type> subTypes;
	final List<Field> references;
	final List<Type> attachedBy;

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
		this.actions = new CopyOnWriteArrayList<Field>();
		this.attrs = new InheritHashMap();
		this.references = new CopyOnWriteArrayList<Field>();
		this.nameAlias = new Aliases(name);
		this.attachedBy = new CopyOnWriteArrayList<Type>();
		this.subTypes = new CopyOnWriteArrayList<Type>();
	}

	public String getDisplayName() {
		return nameAlias.getDefault();
	}

	/**
	 * Used by Basic type, Master,Tx
	 */
	Type(TypeLoader typeLoader, String name, Type superType) {
		this(typeLoader, null, name, superType, superType.rawType, superType.standalone);
	}

	/**
	 * Used by Master,Tx With standalone
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

	Type(TypeLoader typeLoader, Type residedType, String name, Type superType, RawTypes rawType, TypeStandalone standalone) {
		super();
		this.loader = typeLoader;
		this.name = name;

		this.superType = superType;

		this.standalone = standalone;
		this.rawType = rawType;

		this.residedType = residedType; // Mixin

		this.fields = new CopyOnWriteArrayList<Field>();
		this.actions = new CopyOnWriteArrayList<Field>();

		this.attrs = new InheritHashMap(this.superType.attrs);

		this.references = new CopyOnWriteArrayList<Field>();
		this.nameAlias = new Aliases(name);
		this.attachedBy = new CopyOnWriteArrayList<Type>();
		this.subTypes = new CopyOnWriteArrayList<Type>();
	}

	public boolean isArray() {
		return false;
	}

	public String getName() {
		return name;
	}

	public List<Field> getFields() {
		return fields;
	}

	public Aliases getNameAlias() {
		return nameAlias;
	}

	public void setNameAlias(Aliases alias) {
		this.nameAlias = alias;
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

	public List<Field> getReferences() {
		return references;
	}

	@Override
	public String toString() {
		return "Type [name=" + name + ", nameAlias=" + nameAlias + ", standalone=" + standalone + ", text=" + code + "]";
	}

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public List<Field> getActions() {
		return actions;
	}

	public Field getActionByName(String name) {
		for (Field f : actions) {
			if (name.equals(f.name)) {
				return f;
			}
		}
		if (this.superType != null) return this.superType.getActionByName(name);

		return null;
	}

	public List<Type> getAttachedBy() {
		return attachedBy;
	}

}
