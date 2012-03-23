package nebula.vm;

/***
 * Excerpted from "Language Implementation Patterns", published by The Pragmatic
 * Bookshelf. Copyrights apply to this code. It may not be used to create
 * training material, courses, books, articles, and the like. Contact us if you
 * are in doubt. We make no guarantees that this code is fit for any purpose.
 * Visit http://www.pragmaticprogrammer.com/titles/tpdsl for more book
 * information.
 ***/
public class FieldSymbol {
	final String name;
	final ClassSymbol definedClass;
	final Type type;
	int offset;
	int lenght = 1;
	
	final int hashcode;

	public FieldSymbol(ClassSymbol clazz, String name, Type type) {
		this.definedClass = clazz;
		this.name = name;
		this.type = type;
		hashcode=(definedClass + name).hashCode();
	}

	public FieldSymbol defineAt(int offset) {
		return defineAt(offset, 1);
	}

	public FieldSymbol defineAt(int offset, int lenght) {
		this.offset = offset;
		return this;
	}

	@Override
	public int hashCode() {
		return this.hashcode;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof FieldSymbol && name.equals(((FieldSymbol) o).name)
				&& definedClass.name.equals(((FieldSymbol) o).definedClass.name);
	}

	@Override
	public String toString() {
		return "FieldSymbol{" + "name='" + name + '\'' + '}';
	}
}
