package nebula.lang;

public class ListType extends TypeImp {
	Type underlyType;

	ListType(Type type) {
		super(((TypeImp)type).loader, type.getName());
		this.underlyType = type;
	}

	public boolean isArray() {
		return true;
	}

	public Type getUnderlyType() {
		return underlyType;
	}
}
