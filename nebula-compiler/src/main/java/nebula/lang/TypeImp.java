package nebula.lang;

import java.net.URL;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import util.InheritHashMap;

import com.google.common.collect.Lists;

public class TypeImp implements Type {

	final TypeLoader loader;

	final Type residedType;
	final Type superType;
	final List<Type> relations;

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
	Field keyField;

	public static String ROOT_TYPE = "T";

	TypeImp(TypeLoader typeLoader, String name) {
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
		this.relations = new CopyOnWriteArrayList<Type>();
	}

	public String getDisplayName() {
		return nameAlias.getDefault();
	}

	/**
	 * Used by Basic type, Master,Tx
	 */
	TypeImp(TypeLoader typeLoader, String name, Type superType) {
		this(typeLoader, null, name, superType, superType.getRawType(), superType.getStandalone());
	}

	/**
	 * Used by Master,Tx With standalone
	 */
	TypeImp(TypeLoader typeLoader, String name, Type superType, TypeStandalone standalone) {
		this(typeLoader, null, name, superType, null, standalone);
	}

	/**
	 * Used by basic type init
	 */
	TypeImp(TypeLoader typeLoader, String name, Type superType, RawTypes rawType) {
		this(typeLoader, null, name, superType, rawType, TypeStandalone.Basic);
	}

	/**
	 * Used by mixin
	 * 
	 */
	TypeImp(TypeLoader typeLoader, Type residedType, String name, Type superType) {
		this(typeLoader, residedType, name, superType, null, TypeStandalone.Mixin);
	}

	TypeImp(TypeLoader typeLoader, Type residedType, String name, Type superType, RawTypes rawType, TypeStandalone standalone) {
		super();
		this.loader = typeLoader;
		this.name = name;

		this.superType = superType;

		this.standalone = standalone;
		this.rawType = rawType;

		this.residedType = residedType; // Mixin

		this.fields = new CopyOnWriteArrayList<Field>();
		this.actions = new CopyOnWriteArrayList<Field>();

		this.attrs = new InheritHashMap(this.superType.getAttrs());

		this.references = new CopyOnWriteArrayList<Field>();
		this.nameAlias = new Aliases(name);
		this.attachedBy = new CopyOnWriteArrayList<Type>();
		this.subTypes = new CopyOnWriteArrayList<Type>();
		this.relations = new CopyOnWriteArrayList<Type>();
	}

	public boolean isArray() {
		return false;
	}

	public String getName() {
		return name;
	}

	public List<Field> getFields() {
		List<Field> allFields = null;
		if (this.superType == null) {
			allFields = Lists.newArrayList();
		} else {
			allFields = this.superType.getFields();
		}
		allFields.addAll(this.fields);
		return allFields;
	}

	public List<Field> getDeclaredFields() {
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

	public Field getKeyField() {
		return keyField;
	}

	public List<Type> getRelations() {
		return relations;
	}

	public List<Type> getSubTypes() {
		return subTypes;
	}

	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

}
