package nebula.vm;


/***
 * Excerpted from "Language Implementation Patterns", published by The Pragmatic
 * Bookshelf. Copyrights apply to this code. It may not be used to create
 * training material, courses, books, articles, and the like. Contact us if you
 * are in doubt. We make no guarantees that this code is fit for any purpose.
 * Visit http://www.pragmaticprogrammer.com/titles/tpdsl for more book
 * information.
 ***/
public class ClassSymbol implements Type{
	String name;
	Object[] poolLocalK;
	MethodSymbol[] methods;
	FieldSymbol[] fields;

	public ClassSymbol(String name) {
		this.name = name;
	}

	public ClassSymbol(String name,ClassSymbol superType) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	public int getLength() {
		return this.fields.length + 1;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof ClassSymbol && name.equals(((ClassSymbol) o).name);
	}

	public MethodSymbol getEntryPoint() {
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].name.equals("main")) {
				return methods[i];
			}
		}
		throw new RuntimeException("Cann't find entry point");
	}

	public FieldSymbol getField(String name) {
		for (int i = 0; i < fields.length; i++) {
			FieldSymbol field = fields[i];
			if (field.name.equals(name)) {
				return field;
			}
		}
		throw new RuntimeException("Cann't resolve " + this.name + "." + name);
	}

	public MethodSymbol getFunction(String name) {
		for (int i = 0; i < methods.length; i++) {
			MethodSymbol func = methods[i];
			if (func.name.equals(name)) {
				return func;
			}
		}
		throw new RuntimeException("Cann't resolve " + this.name + "." + name);
	}
	@Override
	public String toString() {
		return "Class {" + "name='" + name + '\'' + '}';
	}

	@Override
	public String getName() {
		return this.name;
	}
}
