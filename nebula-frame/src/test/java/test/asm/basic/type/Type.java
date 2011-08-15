package test.asm.basic.type;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Type")
public class Type {

	public static final String Master = "Master";
	public static final String Attribute = "Attribute";
	public static final String Underlying = "Underlying";
	public static final String Sequence = "Sequence";
	public static final String Scala = "Scala";
	public static final String Eembedded = "Eembedded";

	@DisplayName("名称")
	String name;
	@DisplayName("显示名称")
	String displayName;

	@DisplayName("独立")
	boolean standalone = true;

	@DisplayName("实体类型")
	@A("VL:Master,Attribute,Underlying,Sequence,Scala,Eembedded")
	String entityType;

	@DisplayName("定义在")
	Type declaringType = null;

	@DisplayName("字段")
	List<Field> fields;

	public Type(String name) {
		this.name = name;
		this.fields = new ArrayList<Field>();
	}
}
