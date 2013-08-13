package nebula.lang;

public class ListType extends Type {
	Type underlyType;

	ListType(Type type) {
		super(type.loader, type.name);
		this.underlyType = type;
	}

	public boolean isArray() {
		return true;
	}

	public Type getUnderlyType() {
		return underlyType;
	}
}
