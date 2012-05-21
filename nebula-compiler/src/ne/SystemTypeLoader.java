package ne;

import java.io.InputStream;

public class SystemTypeLoader extends TypeLoader {

	public SystemTypeLoader(){
		super(null);
		this.types.put("BuildInType", new Type("BuildInType"));
		this.types.put("String", new Type("String",types.get("BuildInType")));
		this.types.put("Number", new Type("String",types.get("BuildInType")));
		this.types.put("Int", new Type("String",types.get("Number")));
		this.types.put("Decimal", new Type("String",types.get("Number")));
		this.types.put("Entity", new Type("Entity"));
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
