package nebula.lang;

class Var {
	public Var(String name, Type type, int index) {
		this.name = name;
		this.type = type;
		this.index = index;
	}

	String name;
	Type type;
	int index;
}
