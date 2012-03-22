package nebula.vm;
public class BuiltInTypeSymbol extends Symbol implements Type {
    int typeIndex;
    public BuiltInTypeSymbol(String name, int typeIndex) {
        super(name);
        this.typeIndex = typeIndex;
    }
    public int getTypeIndex() { return typeIndex; }

	public String toString() {
		return getName();
	}

	public static final Type VOID = new BuiltInTypeSymbol("V", 0);
	public static final Type INT = new BuiltInTypeSymbol("I", 1);
	public static final Type DECIMAL = new BuiltInTypeSymbol("D", 2);
	public static final Type FLEX = new BuiltInTypeSymbol("?", 2);
	public static final ClassSymbol FLEXCLASS = new ClassSymbol("?");
}