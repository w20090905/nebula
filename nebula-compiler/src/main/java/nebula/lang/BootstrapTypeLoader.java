package nebula.lang;

import java.net.URL;
import java.sql.Types;
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
		Type basicType = new Type(this, TypeStandalone.Basic.toString(), typeRoot,TypeStandalone.Abstract);

		Type master = new Type(this, TypeStandalone.Master.toString(), typeRoot,TypeStandalone.Abstract);		
		Type transaction = new Type(this, TypeStandalone.Transaction.toString(), typeRoot,TypeStandalone.Abstract);
		Type mixin = new Type(this, TypeStandalone.Mixin.toString(), typeRoot,TypeStandalone.Abstract);

		Type string = new Type(this, "String", basicType, RawTypes.String);
		string.attrs.put("jdbcType", Types.VARCHAR);
		string.attrs.put("length", 60);
		string.attrs.put("formatType", "text");

		Type number = new Type(this, "Number", basicType, RawTypes.Long);
		number.attrs.put("jdbcType", Types.BIGINT);

		number.attrs.put("formatType", "numeric");
		number.attrs.put("precision", 10);
		number.attrs.put("scale", 2);

		Type typeLong = new Type(this, "Long", number, RawTypes.Long);
		typeLong.attrs.put("jdbcType", Types.BIGINT);

		Type decimal = new Type(this, "Decimal", number, RawTypes.Decimal);
		decimal.attrs.put("jdbcType", Types.DECIMAL);
		decimal.attrs.put("precision", 10);
		decimal.attrs.put("scale", 2);

		Type date = new Type(this, "Date", basicType, RawTypes.Date);
		date.attrs.put("jdbcType", Types.DATE);

		date.attrs.put("formatType", "date");
		date.attrs.put("formatString", "YYYY-MM-DD");

		Type time = new Type(this, "Time", basicType, RawTypes.Time);
		time.attrs.put("jdbcType", Types.TIME);

		time.attrs.put("formatType", "time");
		time.attrs.put("formatString", "HH:mm:ss");

		Type datetime = new Type(this, "Datetime", basicType, RawTypes.Datetime);
		datetime.attrs.put("jdbcType", Types.DATE);

		datetime.attrs.put("formatType", "datetime");
		datetime.attrs.put("formatString", "YYYY-MM-DD HH:mm:ss");

		Type timestamp = new Type(this, "Timestamp", basicType, RawTypes.Timestamp);
		timestamp.attrs.put("jdbcType", Types.TIMESTAMP);

		timestamp.attrs.put("formatType", "timestamp");
		timestamp.attrs.put("formatString", "YYYY-MM-DD HH:mm:ss.S");

		Type name = new Type(this, "Name", string);
		name.attrs.put("maxLength", 60);

		Type attr = new Type(this, "Attr", string);
		attr.attrs.put("formatType", "text");

		List<Type> typeList = new ArrayList<Type>();

		typeList.add(typeRoot);

		typeList.add(basicType);
		typeList.add(master);
		typeList.add(transaction);
		typeList.add(mixin);

		typeList.add(string);
		typeList.add(number);
		typeList.add(typeLong);
		typeList.add(decimal);
		typeList.add(attr);

		typeList.add(date);
		typeList.add(time);
		typeList.add(datetime);
		typeList.add(timestamp);

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
