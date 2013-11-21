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

	public static Type ROOT;
	public static Type BASIC;
	
	public static Type BOOLEAN;
	
	public static Type STRING;
	public static Type TEXT;
	
	public static Type LONG;
	public static Type DECIMAL;
	
	public static Type DATE;
	public static Type TIME;
	public static Type DATETIME;
	public static Type TIMESTAMP;
	
	{
		TypeImp typeRoot = new TypeImp(this, TypeImp.ROOT_TYPE);
		ROOT = typeRoot;
		
		TypeImp basicType = new TypeImp(this, TypeStandalone.Basic.name(), ROOT, TypeStandalone.Abstract);
		BASIC = basicType;


//		TypeImp number = new TypeImp(this, "Number", basicType, RawTypes.Long);

		TypeImp typeBoolean = new TypeImp(this, RawTypes.Boolean.name(), BASIC, RawTypes.Boolean);
		typeBoolean.attrs.put("FormatType", "numeric");
		typeBoolean.attrs.put("Precision", 10L);
		typeBoolean.attrs.put("Scale", 2L);
		typeBoolean.attrs.put("FormatType", "checkbox");
		BOOLEAN = typeBoolean;
		

		TypeImp typeLong = new TypeImp(this, RawTypes.Long.name(), BASIC, RawTypes.Long);
		typeLong.attrs.put("FormatType", "numeric");
		typeLong.attrs.put("Precision", 10L);
		typeLong.attrs.put("Scale", 2L);
		LONG = typeLong;

		TypeImp decimal = new TypeImp(this, RawTypes.Decimal.name(), BASIC, RawTypes.Decimal);
		decimal.attrs.put("FormatType", "numeric");
		decimal.attrs.put("Precision", 10L);
		decimal.attrs.put("Scale", 2L);
		decimal.attrs.put("Precision", 10L);
		decimal.attrs.put("Scale", 2L);
		DECIMAL = decimal;

		TypeImp string = new TypeImp(this, RawTypes.String.name(), BASIC, RawTypes.String);
		string.attrs.put("FormatType", "text");
		string.attrs.put("MaxLength", 60L);
		STRING = string;

		TypeImp text = new TypeImp(this, RawTypes.Text.name(), BASIC, RawTypes.Text);
		text.attrs.put("FormatType", "textarea");
		text.attrs.put("MaxLength", 1024L);
		TEXT = text;

		TypeImp date = new TypeImp(this, RawTypes.Date.name(), BASIC, RawTypes.Date);
		date.attrs.put("FormatType", "date");
		date.attrs.put("FormatString", "YYYY-MM-DD");
		DATE = date;

		TypeImp time = new TypeImp(this, RawTypes.Time.name(), BASIC, RawTypes.Time);
		time.attrs.put("FormatType", "time");
		time.attrs.put("FormatString", "HH:mm:ss");
		TIME = time;

		TypeImp datetime = new TypeImp(this, RawTypes.Datetime.name(), BASIC, RawTypes.Datetime);
		datetime.attrs.put("FormatType", "datetime");
		datetime.attrs.put("FormatString", "YYYY-MM-DD HH:mm:ss");
		DATETIME = datetime;

		TypeImp timestamp = new TypeImp(this, RawTypes.Timestamp.name(), basicType, RawTypes.Timestamp);
		timestamp.attrs.put("FormatType", "timestamp");
		timestamp.attrs.put("FormatString", "YYYY-MM-DD HH:mm:ss.S");
		TIMESTAMP = timestamp;
	}

	private void init() {


		List<TypeImp> typeList = new ArrayList<TypeImp>();

		typeList.add((TypeImp)ROOT);
		typeList.add((TypeImp)BASIC);

		typeList.add((TypeImp)BOOLEAN);

		typeList.add((TypeImp)LONG);
		typeList.add((TypeImp)DECIMAL);

		typeList.add((TypeImp)STRING);
		typeList.add((TypeImp)TEXT);

		typeList.add((TypeImp)DATE);
		typeList.add((TypeImp)TIME);
		typeList.add((TypeImp)DATETIME);
		typeList.add((TypeImp)TIMESTAMP);

		
		TypeImp master = new TypeImp(this, TypeStandalone.Master.name(), ROOT, TypeStandalone.Abstract);
		master.attrs.put("Layout", "Basic");
		TypeImp transaction = new TypeImp(this, TypeStandalone.Transaction.name(), ROOT, TypeStandalone.Abstract);
		transaction.attrs.put("Layout", "Basic");
		TypeImp mixin = new TypeImp(this, TypeStandalone.Mixin.name(), ROOT, TypeStandalone.Abstract);
		
		TypeImp number = new TypeImp(this, "Number", LONG);
		number.nameAlias= new Aliases("数字");
		
		TypeImp name = new TypeImp(this, "Name", STRING);
		name.nameAlias= new Aliases("名称");
		name.attrs.put("MaxLength", 60L);
		name.attrs.put("ShouldBeLeader", "ShouldBeLeader");

		TypeImp attr = new TypeImp(this, "Attr", STRING);
		attr.attrs.put("SP", "Attr");
		attr.attrs.put("MaxLength", 60L);
		typeList.add(master);
		typeList.add(transaction);
		typeList.add(mixin);
		
		typeList.add(attr);

		typeList.add(name);
		typeList.add(number);

		long lastModified = System.currentTimeMillis();
		for (TypeImp type : typeList) {
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
		typeAttribute.getAttrs().put("Layout", "Compact");

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
				"		?RangeFrom Long;\n" +
				"		?RangeTo Long;\n" +
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
