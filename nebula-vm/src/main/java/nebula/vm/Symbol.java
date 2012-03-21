package nebula.vm;
/***
 * Excerpted from "Language Implementation Patterns",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpdsl for more book information.
***/
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
