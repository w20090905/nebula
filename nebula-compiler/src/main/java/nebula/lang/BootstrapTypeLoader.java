package nebula.lang;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

class BootstrapTypeLoader extends TypeLoader {
	private Log log = LogFactory.getLog(this.getClass());

	private BootstrapTypeLoader() {
		super(null);

		init();
	}

	private static final TypeLoader loader = new BootstrapTypeLoader();

	public static TypeLoader getInstance() {
		return loader;
	}

	private void init() {
		Type typeRoot = new Type(this, Type.TYPE);
		Type badicType = new Type(this, Type.BASIC, typeRoot);
		Type entity = new Type(this, Type.ENTITY, typeRoot);
		Type string = new Type(this, "String", badicType, "String");
		string.attrs.put("formatType", "string");
		
		Type number = new Type(this, "Number", badicType);
		number.attrs.put("formatType", "numeric");
		
		Type typeLong = new Type(this, "Long", number, "long");
		typeLong.attrs.put("formatType", "long");
		
		Type decimal = new Type(this, "Decimal", number, "BigDecimal");
		decimal.attrs.put("formatType", "decimal");
		
		Type attr = new Type(this, "Attr", badicType);
		attr.attrs.put("formatType", "any");

		List<Type> typeList = new ArrayList<Type>();

		typeList.add(typeRoot);
		typeList.add(badicType);
		typeList.add(string);
		typeList.add(number);
		typeList.add(typeLong);
		typeList.add(decimal);
		typeList.add(entity);
		typeList.add(attr);
		
		this.types.addAll(typeList);
	}

	@Override
	public Type findType(String name) {
		return this.types.get(name);
	}

	@Override
	protected URL loadClassData(String name) {
		log.error("UnsupportedOperationException : URL loadClassData(" + name + ")");
		throw new UnsupportedOperationException("URL loadClassData(" + name + ")");
	}

}
