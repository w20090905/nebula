package nebula.vm;

import java.util.ArrayList;
import java.util.List;

/***
 * Excerpted from "Language Implementation Patterns", published by The Pragmatic
 * Bookshelf. Copyrights apply to this code. It may not be used to create
 * training material, courses, books, articles, and the like. Contact us if you
 * are in doubt. We make no guarantees that this code is fit for any purpose.
 * Visit http://www.pragmaticprogrammer.com/titles/tpdsl for more book
 * information.
 ***/
public class ClassSymbol {
	String name;
	List<FieldSymbol> fields = new ArrayList<>();

	public ClassSymbol(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	public int getLength() {
		return this.fields.size() + 1;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof ClassSymbol && name.equals(((ClassSymbol) o).name);
	}

	public ClassSymbol add(FieldSymbol field) {
		fields.add(field);
		field.defineAt(fields.size());
		return this;
	}

	public FieldSymbol getField(String name) {
		for (int i = 0; i < fields.size(); i++) {
			FieldSymbol field = fields.get(i);
			if (field.name.equals(name)) {
				return field;
			}

		}
		return null;
	}

	@Override
	public String toString() {
		return "FunctionSymbol{" + "name='" + name + '\'' + '}';
	}
}
