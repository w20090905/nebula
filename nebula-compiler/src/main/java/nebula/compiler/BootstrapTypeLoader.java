package nebula.compiler;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

class BootstrapTypeLoader extends TypeLoader {

	private BootstrapTypeLoader() {
		super(null);

		init();
	}

	private static final TypeLoader loader = new BootstrapTypeLoader();

	public static TypeLoader getInstance() {
		return loader;
	}

	private void init() {
		Type buildInType = new Type(this, Type.BUILDIN);
		Type entity = new Type(this, Type.ENTITY);
		Type string = new Type(this, "String", buildInType, "String");
		Type number = new Type(this, "Number", buildInType);
		Type typeInt = new Type(this, "Long", number, "Long");
		Type decimal = new Type(this, "Decimal", number, "BigDecimal");

		List<Type> typeList = new ArrayList<>();

		typeList.add(buildInType);
		typeList.add(string);
		typeList.add(number);
		typeList.add(typeInt);
		typeList.add(decimal);
		typeList.add(entity);

		this.types.addAll(typeList);
	}

	@Override
	public Type findType(String name) {
		return this.types.get(name);
	}

	@Override
	protected InputStream loadClassData(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
