package nebula.compiler;

public class Field {

    public static final String Scala = "Scala";
    public static final String Inline = "Inline";
    public static final String Reference = "Reference";
    public static final String Cascade = "Cascade";

    public static final String KEY = "Key";
    public static final String IMPORTANT = "Important";
    public static final String REQUIRE = "Require";
    public static final String UNIMPORTANT = "Unimportant";

    final String name;
    String displayName;
    String importance = UNIMPORTANT;

    final Type resideType;
    boolean array = false;
    int from = 0;
    int to = -1;
    
    Type type;
    String type_name;

    public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	String refer;

    public Field(Type resideType,String name) {
        super();
        this.name = name;
        this.displayName = name;
        this.resideType = resideType;
        this.resideType.fields.add(this);
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

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
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
				.append(from).append(", to=").append(to).append(", type=").append(type).append(", refer=")
				.append(refer).append("]");
		return builder.toString();
	}
}