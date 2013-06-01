package http.engine;

import nebula.data.Holder;
import nebula.lang.Type;
import nebula.lang.TypeLoader;

//TODO  refact
public class TypeHolder extends Holder<Type> {
	final TypeLoader loader;
	final String name;

	TypeHolder(TypeLoader loader, String name) {
		super(loader.findType(name));
		this.loader = loader;
		this.name = name;
	}

	@Override
	public Type get() {
		Type newType = loader.findType(name);
		return newType;
	}

}