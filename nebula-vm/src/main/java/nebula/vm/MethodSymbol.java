package nebula.vm;

public class MethodSymbol {
	final ClassSymbol definedClass;
	final String name;
	final int hashcode;

	int nargs; // how many arguments are there?
	int nlocals; // how many locals are there?
	int[] code;

	boolean resolved = false;

	public MethodSymbol(ClassSymbol parent, String name) {
		this(parent, name, 0, 0, null);
	}

	public MethodSymbol(ClassSymbol parent, String name, int nargs, int nlocals, int[] code) {
		this.definedClass = parent;
		this.name = name;
		this.nargs = nargs;
		this.nlocals = nlocals;
		this.code = code;
		hashcode = (parent.name + name).hashCode();
	}

	@Override
	public int hashCode() {
		return this.hashcode;
	}

	public Object[] getConstPool() {
		return this.definedClass.poolLocalK;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof MethodSymbol && definedClass.name.equals(((MethodSymbol) o).definedClass.name)
				&& name.equals(((MethodSymbol) o).name);
	}

	@Override
	public String toString() {
		return "FunctionSymbol{" + "name='" + name + '\'' + ", args=" + nargs + ", locals=" + nlocals + ", address="
				+ code.length + '}';
	}
}