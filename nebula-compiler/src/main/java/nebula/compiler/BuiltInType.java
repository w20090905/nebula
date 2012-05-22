package nebula.compiler;
public class BuiltInType extends Type {
	BuiltInType(String name){
		this(name,null,null);
	}
	public BuiltInType(String name,BuiltInType superType){
		this(name,superType,null);
	}
	public BuiltInType(String name,BuiltInType superType,String rawType){
		super(name, superType, rawType);
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString() {
		return getName();
	}
	
	static BuiltInType BuildInType = new BuiltInType("BuiltInType");
	static BuiltInType string = new BuiltInType("String", BuildInType,"String");
	static BuiltInType number = new BuiltInType("String", BuildInType);
	static BuiltInType typeInt = new BuiltInType("String", number,"BigDecimal");
	static BuiltInType decimal = new BuiltInType("String", number,"Integer");
}