package nebula.vm;

public class LocalVar extends Var {
	public LocalVar(String name, Type type) {
		super(LOCAL, name, type);
	}

	public LocalVar(short category, String name, Type type, int ip, int offset) {
		super(LOCAL, name, type, ip, offset);
	}
}
