package nebula.lang;

public class Field {

    public static final String BASIC = "ByValue";
    public static final String INLINE = "Inline";
    public static final String REFERENCE = "Reference";
    public static final String CASCADE = "Cascade";

    public static final String KEY = "Key";
    public static final String CORE = "Core";
    public static final String IMPORTANT = "Core";    
    public static final String REQUIRE = "Require";
    public static final String UNIMPORTANT = "Unimportant";

    final String name;
    Alias nameAlias;
    String displayName;
    String importance = UNIMPORTANT;

    final Type resideType;
    boolean array = false;
    int rangeFrom = 0;
    int rangeTo = Integer.MAX_VALUE;
    
    Type type;
    String _typeName;

    public String getType_name() {
		return _typeName;
	}

	public void setType_name(String type_name) {
		this._typeName = type_name;
	}

	String refer;

    public Field(Type resideType,String name) {
        super();
        this.name = name;
        this.displayName = name;
        this.resideType = resideType;
    }

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public boolean isArray() {
		return array;
	}

	public void setArray(boolean array) {
		this.array = array;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public String getName() {
		return name;
	}

	public Type getResideType() {
		return resideType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Field [name=").append(name).append(", displayName=").append(displayName)
				.append(", importance=").append(importance).append(", array=").append(array).append(", from=")
				.append(refer).append("]");
		return builder.toString();
	}
}