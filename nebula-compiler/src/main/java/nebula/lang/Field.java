package nebula.lang;
public class Field {

//    public static final Reference BASIC = Reference.ByVal;
//    public static final Reference INLINE = Reference.Inline;
//    public static final Reference REFERENCE =Reference.ByRef;
//    public static final Reference CASCADE = Reference.Cascade;
//
//    public static final Importance KEY = Importance.Key;
//    public static final Importance CORE = Importance.Core; 
//    public static final Importance REQUIRE =Importance.Require;
//    public static final Importance UNIMPORTANT = Importance.Unimportant;

    final String name;
    Alias nameAlias;
    String displayName;
    Importance importance = Importance.Unimportant;

    final Type resideType;
    boolean array = false;
    int rangeFrom = 0;
    int rangeTo = Integer.MAX_VALUE;
    
    Type type;
    String _typeName;
    Reference refer;

    public String getType_name() {
		return _typeName;
	}

	public void setType_name(String type_name) {
		this._typeName = type_name;
	}


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

	public Importance getImportance() {
		return importance;
	}
	
	public boolean isKey(){
		return this.importance == Importance.Key;
	}

	public void setImportance(Importance importance) {
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

	public Reference getRefer() {
		return refer;
	}

	public void setRefer(Reference refer) {
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