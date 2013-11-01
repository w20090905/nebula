package nebula.simpletemplate;


class Argument {
	public Argument(Expr<Object> value) {
		this.value = value;
	}
	Expr<Object> value;
	String name;
	int index;
}
