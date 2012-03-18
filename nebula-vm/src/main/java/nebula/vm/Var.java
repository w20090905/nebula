package nebula.vm;


public class Var extends Symbol {
	public short reg;
	public boolean applied = true;

	public Var(String name, Type type) {
		super(name, type);
	}

	@Override
	public String toString() {
		return "[ " + name  + ":" + type + " | " + reg + "  " + applied + "]";
	}

}
