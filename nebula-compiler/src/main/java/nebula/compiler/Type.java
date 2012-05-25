package nebula.compiler;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Type {

	public static final String Master = "Master";
	public static final String Attribute = "Attribute";
	public static final String Underlying = "Underlying";
	public static final String Sequence = "Sequence";
	public static final String Scala = "Scala";
	public static final String Eembedded = "Eembedded";

	String name;
	Alias nameAlias;
	String displayName;

	boolean standalone = true;

	String master;
	
	boolean entity;

	Type declaringType = null;

	List<Field> fields;

	Properties attrs;
	// Type declaringType;

	final Type residedType;
	final Type superType;
	final String rawType;

	static Type BUILDERINTYPE = new Type("BuildInType");
	static Type ENTITY = new Type("Entity");

	private Type(String name) {
		this(name, null, null);
	}

	Type(String name, Type superType) {
		this(name, superType, superType.rawType);
	}

	Type(String name, Type superType, String rawType) {
		this(null, name, superType, rawType);
	}

	Type(Type residedType, String name, Type superType) {
		this(residedType, name, superType, superType.rawType);
	}

	Type(Type residedType, String name, Type superType, String rawType) {
		super();
		this.residedType = residedType;
		this.superType = superType;
		this.rawType = rawType;

		if (residedType != null) {
			standalone = false;
		}

		if (this.superType != null) {
			attrs = new Properties(this.superType.attrs);
			if(superType.entity){
				this.entity=true;
			}else{
				this.entity=false;
			}
		} else {
			attrs = new Properties();
			if("Entity".equals(name)){
				this.entity=true;				
			}else{
				this.entity=false;
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

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public List<Field> getFields() {
		return fields;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public boolean isStandalone() {
		return standalone;
	}

	public void setStandalone(boolean standalone) {
		this.standalone = standalone;
	}

	public Type getDeclaringType() {
		return declaringType;
	}

	public void setDeclaringType(Type declaringType) {
		this.declaringType = declaringType;
	}

	@Override
	public String toString() {
		return "Type [name=" + name + ", displayName=" + displayName + ", standalone=" + standalone + ", master="
				+ master + ", declaringType=" + declaringType + ", fields=" + fields + "]";
	}

}
