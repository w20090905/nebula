package nebula.compiler;

import java.io.InputStream;

public class BootstrapTypeLoader extends TypeLoader {

	public BootstrapTypeLoader() {
		super(null);

		init();
	}

	private void init() {
		Type entity = Type.ENTITY;
		
		Type buildInType = Type.BUILDERINTYPE;
		Type string = new Type("String", buildInType,"String");
		Type number = new Type("String", buildInType);
		Type typeInt = new Type("String", number,"BigDecimal");
		Type decimal = new Type("String", number,"Integer");

		this.types.put("BuildInType", buildInType);
		this.types.put("String", string);
		this.types.put("Number", number);
		this.types.put("Int", typeInt);
		this.types.put("Decimal", decimal);
		this.types.put("Entity", entity);
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
