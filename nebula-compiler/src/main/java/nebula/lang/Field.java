package nebula.lang;

import util.InheritHashMap;

public class Field {
	final String name;
	Aliases nameAlias;
	int modifiers = 0;
	
	EntityExpression expr;
	EntityAction code;

	final Type resideType;
	boolean array = false;
	int rangeFrom = 0;
	int rangeTo = Integer.MAX_VALUE;

	Type type;
	Reference refer;

	InheritHashMap attrs;

	public Field(Type resideType, String name) {
		super();
		this.name = name;
		this.resideType = resideType;
		this.attrs = new InheritHashMap();
	}

	public String getDisplayName() {
		return this.nameAlias.defaultValue;
	}

	public boolean isKey() {
		return Modifier.isKey(modifiers);
	}

	public boolean isUnique() {
		return Modifier.isUnique(modifiers);
	}
	public boolean isCore() {
		return Modifier.isCore(modifiers);
	}

	public boolean isIgnorable() {
		return Modifier.isNullable(modifiers);
	}
	public boolean isRequired() {
		return !Modifier.isNullable(modifiers);
	}
	
	public boolean isNullable() {
		return Modifier.isNullable(modifiers);
	}

	public boolean isArray() {
		return array;
	}

	public void setArray(boolean array) {
		this.array = array;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
		this.attrs.setDefaults(type.getAttrs());
	}

	public Reference getRefer() {
		return refer;
	}

	public void setRefer(Reference refer) {
		this.refer = refer;
	}

	public String getName() {
		return name;
	}

	public Type getResideType() {
		return resideType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Field [name=").append(name).append(", displayName=").append(getDisplayName())
				.append(", array=").append(array).append(", from=")
				.append(refer).append("]");
		return builder.toString();
	}

	public InheritHashMap getAttrs() {
		return attrs;
	}

	public Aliases getNameAlias() {
		return nameAlias;
	}

	public boolean isDerived() {
		return Modifier.isDerived(modifiers);
	}
	public boolean isDefaultValue() {
		return Modifier.isDefaultValue(modifiers);
	}

	public EntityExpression getDerivedExpr() {
		return expr;
	}
	
	public boolean isPrimaryType(){
		return this.type.getStandalone() == TypeStandalone.Basic;
	}
	
	public boolean isInline(){
		return this.refer == Reference.Inline;
	}
	
	public boolean isByVal(){
		return this.refer ==Reference.ByVal;		
	}
	
	public boolean isByRef(){
		return this.refer ==Reference.ByRef;
	}
	
	public boolean isCascade(){
		return this.refer == Reference.Cascade;
	}
	

	public void setNameAlias(Aliases nameAlias) {
		this.nameAlias = nameAlias;
	}

	public EntityExpression getExpr() {
		return expr;
	}

	public EntityAction getCode() {
		return code;
	}
}
