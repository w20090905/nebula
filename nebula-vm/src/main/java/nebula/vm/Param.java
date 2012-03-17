package nebula.vm;

public class Param extends Var {
	public Param(String name, Type type) {
		super(PARAM, name, type);
	}

	public Param(short category, String name, Type type, int ip, int offset) {
		super(PARAM, name, type, ip, offset);
	}
}
