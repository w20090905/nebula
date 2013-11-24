package nebula.lang;

class Var {
	public Var(String name, Type type) {
		this.name = name;
		this.type = type;
	}

	String name;
	Type type;
	int index;
}
