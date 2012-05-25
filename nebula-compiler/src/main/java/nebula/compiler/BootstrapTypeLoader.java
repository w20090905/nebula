package nebula.compiler;

import java.io.InputStream;

class BootstrapTypeLoader extends TypeLoader {

	private BootstrapTypeLoader() {
		super(null);

		init();
	}
	
	private static final TypeLoader loader = new BootstrapTypeLoader();
	public static TypeLoader getInstance(){
		return loader;
	}
	
	private void init() {
		Type buildInType = new Type(this,Type.BUILDIN);
		Type entity = new Type(this,Type.ENTITY);
		Type string = new Type(this,"String", buildInType,"String");
		Type number = new Type(this,"String", buildInType);
		Type typeInt = new Type(this,"String", number,"BigDecimal");
		Type decimal = new Type(this,"String", number,"Integer");

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
