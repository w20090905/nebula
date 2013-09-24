package nebula.lang;

import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.RecognitionException;

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
		Type typeRoot = new Type(this, Type.ROOT_TYPE);
		Type basicType = new Type(this, TypeStandalone.Basic.name(), typeRoot, TypeStandalone.Abstract);

		Type master = new Type(this, TypeStandalone.Master.name(), typeRoot, TypeStandalone.Abstract);
		master.attrs.put("Layout", "Basic");
		Type transaction = new Type(this, TypeStandalone.Transaction.name(), typeRoot, TypeStandalone.Abstract);
		transaction.attrs.put("Layout", "Basic");
		Type mixin = new Type(this, TypeStandalone.Mixin.name(), typeRoot, TypeStandalone.Abstract);

		Type number = new Type(this, "Number", basicType, RawTypes.Long);
		number.attrs.put("FormatType", "numeric");
		number.attrs.put("Precision", 10L);
		number.attrs.put("Scale", 2L);

		Type typeBoolean = new Type(this, RawTypes.Boolean.name(), number, RawTypes.Boolean);
		typeBoolean.attrs.put("FormatType", "checkbox");

		Type typeLong = new Type(this, RawTypes.Long.name(), number, RawTypes.Long);

		Type decimal = new Type(this, RawTypes.Decimal.name(), number, RawTypes.Decimal);
		decimal.attrs.put("Precision", 10L);
		decimal.attrs.put("Scale", 2L);

		Type string = new Type(this, RawTypes.String.name(), basicType, RawTypes.String);
		string.attrs.put("FormatType", "text");
		string.attrs.put("MaxLength", 60L);

		Type text = new Type(this, RawTypes.Text.name(), basicType, RawTypes.Text);
		text.attrs.put("FormatType", "textarea");
		text.attrs.put("MaxLength", 1024L);

		Type date = new Type(this, RawTypes.Date.name(), basicType, RawTypes.Date);
		date.attrs.put("FormatType", "date");
		date.attrs.put("FormatString", "YYYY-MM-DD");

		Type time = new Type(this, RawTypes.Time.name(), basicType, RawTypes.Time);
		time.attrs.put("FormatType", "time");
		time.attrs.put("FormatString", "HH:mm:ss");

		Type datetime = new Type(this, RawTypes.Datetime.name(), basicType, RawTypes.Datetime);
		datetime.attrs.put("FormatType", "datetime");
		datetime.attrs.put("FormatString", "YYYY-MM-DD HH:mm:ss");

		Type timestamp = new Type(this, RawTypes.Timestamp.name(), basicType, RawTypes.Timestamp);
		timestamp.attrs.put("FormatType", "timestamp");
		timestamp.attrs.put("FormatString", "YYYY-MM-DD HH:mm:ss.S");

		Type name = new Type(this, "Name", string);
		name.nameAlias= new Aliases("名称");
		name.attrs.put("MaxLength", 60L);
		name.attrs.put("ShouldBeLeader", "ShouldBeLeader");

		Type attr = new Type(this, "Attr", string);
		attr.attrs.put("SP", "Attr");
		attr.attrs.put("MaxLength", 60L);

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

		long lastModified = System.currentTimeMillis();
		for (Type type : typeList) {
			type.lastModified = lastModified;
		}

		this.types.addAll(typeList);

		//@formatter:off
		String strAttributes = "" +
				"type Attribute{\n" +
				"	!Name;\n" +
				"	Values[1..1000]{\n" +
				"		Name;\n" +
				"		?ReferName Name;\n" +
				"	};\n" +
				"};\n";
		//@formatter:on

		try {
			this.defineNebula(new StringReader(strAttributes));
		} catch (RecognitionException e) {
			log.error("RecognitionException", e);
			throw new RuntimeException(e);
		}

		Type typeAttribute = this.findType("Attribute");
		typeAttribute.attrs.put("Layout", "Compact");

		//@formatter:off
		String typeDefine = "" +
				"type Type{\n" +
				"	!Name;\n" +
				"	?ResidedType Type;\n" +
				"	%SuperType Type;\n" +
				"	Standalone Attr;\n" +
				"  Fields[]{\n" +
				"		!Name;\n" +
				"		Importance Attr;\n" +
				"		Refer Attr;\n" +
				"		Type Type;\n" +
				"		Array Boolean;\n" +
				"		?RangeFrom Number;\n" +
				"		?RangeTo Number;\n" +
				"	};\n" +
				"};\n";
		//@formatter:on

		try {
			this.defineNebula(new StringReader(typeDefine));
		} catch (RecognitionException e) {
			log.error("RecognitionException", e);
			throw new RuntimeException(e);
		}

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
