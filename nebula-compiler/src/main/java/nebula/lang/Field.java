package nebula.lang;

import util.InheritHashMap;

public class Field {
	public static final String SingleLine = "SingleLine";
	public static final String Heading = "Heading";
	public static final String OnSave = "OnSave";
	public static final String ComputeBackend = "ComputeBackend";
	public static final String RequiredOn = "RequiredOn";
	public static final String DisplayOn = "DisplayOn";
		
	final String name;
	Aliases nameAlias;
	int modifiers = 0;

	Code code;
	Code onChangeCode;
	EntityAction onChangeAsm;
	EntityExpression exprAsm;
	EntityAction actionAsm;

	final Type resideType;
	boolean array = false;
	int rangeFrom = 0;
	int rangeTo = Integer.MAX_VALUE;

	Type type;
	Reference refer;

	InheritHashMap attrs;

	boolean internal = false;

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
		return Modifier.isNullable(modifiers) || (!Modifier.isRequired(modifiers) && Modifier.isDefaultValue(modifiers));
	}

	public boolean isRequired() {
		return !isIgnorable();
	}

	public boolean isNullable() {
		return isIgnorable();
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
		builder.append("Field [name=").append(name).append(", displayName=").append(getDisplayName()).append(", array=").append(array).append(", from=")
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

	public boolean isPrimaryType() {
		return this.type.getStandalone() == TypeStandalone.Basic;
	}

	public boolean isInline() {
		return this.refer == Reference.Inline;
	}

	public boolean isByVal() {
		return this.refer == Reference.ByVal;
	}

	public boolean isByRef() {
		return this.refer == Reference.ByRef;
	}

	public boolean isCascade() {
		return this.refer == Reference.Cascade;
	}

	public void setNameAlias(Aliases nameAlias) {
		this.nameAlias = nameAlias;
	}

	public boolean isInternal() {
		return internal;
	}

	public Code getCode() {
		return code;
	}

	public EntityExpression getExprAsm() {
		return exprAsm;
	}

	public EntityAction getActionAsm() {
		return actionAsm;
	}

	public Code getOnChangeCode() {
		return onChangeCode;
	}
}
