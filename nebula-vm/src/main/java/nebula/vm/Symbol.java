package nebula.vm;
public class Symbol { // A generic programming language symbol
    String name;      // All symbols at least have a name
    Type type;
//    Scope scope;      // All symbols know what scope contains them.

    public Symbol(String name) { this.name = name; }
    public Symbol(String name, Type type) { this(name); this.type = type; }
    public String getName() { return name; }

    @Override
	public boolean equals(Object o) {
		return o instanceof Symbol	&& name.equals(((Symbol) o).name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

    public static String stripBrackets(String s) {
        return s.substring(1,s.length()-1);
    }
}
