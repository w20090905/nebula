package nebula.lang;

import util.InheritHashMap;

public class Field {
	final String name;
	Aliases nameAlias;
	Importance importance = Importance.Unimportant;
	boolean derived = false;
	EntityExpression<?> derivedExpr;

	boolean hasDefaultValue = false;
	EntityExpression<?> defaultValueExpr;

	final Type resideType;
	boolean array = false;
	int rangeFrom = 0;
	int rangeTo = Integer.MAX_VALUE;

	Type type;
	String _typeName;
	Reference refer;

	InheritHashMap attrs;

	public String getType_name() {
		return _typeName;
	}

	public void setType_name(String type_name) {
		this._typeName = type_name;
	}

	public Field(Type resideType, String name) {
		super();
		this.name = name;
		this.resideType = resideType;
		this.attrs = new InheritHashMap();
	}

	public String getDisplayName() {
		return this.nameAlias.defaultValue;
	}

	public Importance getImportance() {
		return importance;
	}

	public boolean isKey() {
		return this.importance == Importance.Key;
	}

	public boolean isCore() {
		return this.importance == Importance.Core;
	}

	public boolean isIgnorable() {
		return this.importance == Importance.Unimportant;
	}

	public void setImportance(Importance importance) {
		this.importance = importance;
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
		this.attrs.setDefaults(type.attrs);
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
				.append(", importance=").append(importance).append(", array=").append(array).append(", from=")
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
		return derived;
	}

	public void setDerived(boolean derived) {
		this.derived = derived;
	}

	@SuppressWarnings("unchecked")
	public <T> EntityExpression<T> getValueExpr() {
		return (EntityExpression<T>) derivedExpr;
	}

	public <T> void setValueExpr(EntityExpression<T> valueExpr) {
		this.derivedExpr = valueExpr;
	}

	public void setNameAlias(Aliases nameAlias) {
		this.nameAlias = nameAlias;
	}
}
