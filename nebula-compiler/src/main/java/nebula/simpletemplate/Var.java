package nebula.simpletemplate;

import org.objectweb.asm.Type;

class Var {
	public Var(String name,Type type, int index) {
		this.name = name;
		this.index = index;
		this.type = type;
	}
	Type type;
	String name;
	int index;
}
