package nebula.lang;

public class ListType extends Type {

	ListType(Type type) {
		super(type.loader, type.name);
	}

	public boolean isArray() {
		return true;
	}
}
