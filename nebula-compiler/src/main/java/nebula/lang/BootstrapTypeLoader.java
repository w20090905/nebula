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
		Type typeRoot = new Type(this, Type.ROOT_TYPE);
		Type basicType = new Type(this, TypeStandalone.Basic.name(), typeRoot,TypeStandalone.Abstract);

		Type master = new Type(this, TypeStandalone.Master.name(), typeRoot,TypeStandalone.Abstract);		
		Type transaction = new Type(this, TypeStandalone.Transaction.name(), typeRoot,TypeStandalone.Abstract);
		Type mixin = new Type(this, TypeStandalone.Mixin.name(), typeRoot,TypeStandalone.Abstract);

		Type number = new Type(this, "Number", basicType, RawTypes.Long);
		number.attrs.put("formatType", "numeric");
		number.attrs.put("precision", 10);
		number.attrs.put("scale", 2);

		Type typeBoolean = new Type(this, "Long", number, RawTypes.Boolean);
		typeBoolean.attrs.put("formatType", "checkbox");
		
		Type typeLong = new Type(this, "Long", number, RawTypes.Long);

		Type decimal = new Type(this, "Decimal", number, RawTypes.Decimal);
		decimal.attrs.put("precision", 10);
		decimal.attrs.put("scale", 2);
		
		Type string = new Type(this, "String", basicType, RawTypes.String);
		string.attrs.put("formatType", "text");
		string.attrs.put("maxLength", 60);

		Type text = new Type(this, "Text", basicType, RawTypes.Text);
		text.attrs.put("formatType", "textarea");
		text.attrs.put("maxLength", 1024);
		
		Type date = new Type(this, "Date", basicType, RawTypes.Date);
		date.attrs.put("formatType", "date");
		date.attrs.put("formatString", "YYYY-MM-DD");

		Type time = new Type(this, "Time", basicType, RawTypes.Time);
		time.attrs.put("formatType", "time");
		time.attrs.put("formatString", "HH:mm:ss");

		Type datetime = new Type(this, "Datetime", basicType, RawTypes.Datetime);
		datetime.attrs.put("formatType", "datetime");
		datetime.attrs.put("formatString", "YYYY-MM-DD HH:mm:ss");

		Type timestamp = new Type(this, "Timestamp", basicType, RawTypes.Timestamp);
		timestamp.attrs.put("formatType", "timestamp");
		timestamp.attrs.put("formatString", "YYYY-MM-DD HH:mm:ss.S");

		Type name = new Type(this, "Name", string);
		name.attrs.put("maxLength", 60);

		Type attr = new Type(this, "Attr", string);
		attr.attrs.put("maxLength", 10);

		List<Type> typeList = new ArrayList<Type>();

		typeList.add(typeRoot);

		typeList.add(basicType);
		typeList.add(master);
		typeList.add(transaction);
		typeList.add(mixin);

		typeList.add(typeBoolean);
		
		typeList.add(number);
		typeList.add(typeLong);
		typeList.add(decimal);
		
		typeList.add(string);
		typeList.add(text);
		
		typeList.add(date);
		typeList.add(time);
		typeList.add(datetime);
		typeList.add(timestamp);
		
		typeList.add(attr);

		typeList.add(name);

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
