package test.asm.basic.type;

@DisplayName("字段")
@A("child")
public class Field {

	public static final String Scala = "Scala";
	public static final String Inline = "Inline";
	public static final String Reference = "Reference";
	public static final String Cascade = "Cascade";

	public static final String PrimaryKey = "PrimaryKey";
	public static final String Core = "Core";
	public static final String Important = "Important";
	public static final String Normal = "Normal";

	@DisplayName("名称")
	String name;
	@DisplayName("显示名称")
	String displayName;

	@DisplayName("重要性")
	@A("VL:Key,Core,Important,Normal;VL-zh:Key->主键,Core->核心,Important->重点,Normal->一般,attach->附属")
	String importance = Normal;

	@A("hidden")
	String type_name;
	@DisplayName("类型")
	Type type;

	@DisplayName("数组")
	String array = "1";

	@A("Alias:VL")
	String[] valueList;

	@DisplayName("引用类型")
	@A("VL:Scala,Inline,Reference,Cascade")
	String refer;

	public Field(String name) {
		this.name = name;
		this.displayName = name;
	}

}
