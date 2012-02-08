package nebula.vm;

public class FunctionSymbol {
	final ClassSymbol definedClass;
	final String name;
	final int hashcode;

	final int nargs; // how many arguments are there?
	final int nlocals; // how many locals are there?
	int[] code;

	boolean resolved = false;

	public FunctionSymbol(ClassSymbol parent, String name) {
		this(parent, name, 0, 0, null);
	}

	public FunctionSymbol(ClassSymbol parent, String name, int nargs, int nlocals, int[] code) {
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
		return o instanceof FunctionSymbol && definedClass.name.equals(((FunctionSymbol) o).definedClass.name)
				&& name.equals(((FunctionSymbol) o).name);
	}

	@Override
	public String toString() {
		return "FunctionSymbol{" + "name='" + name + '\'' + ", args=" + nargs + ", locals=" + nlocals + ", address="
				+ code.length + '}';
	}
}
