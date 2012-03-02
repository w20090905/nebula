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

	public static final Type VOID = new BuiltInTypeSymbol("void", 0);
	public static final Type INT = new BuiltInTypeSymbol("int", 1);
	public static final Type DECIMAL = new BuiltInTypeSymbol("decimal", 2);
}