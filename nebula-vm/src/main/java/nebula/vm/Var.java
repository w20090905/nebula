package nebula.vm;


public class Var extends Symbol {
	public final short reg;
	public boolean applied = true;

	public Var(String name, Type type, int regIndex) {
		super(name, type);
		reg = (short)regIndex;
	}
	
	public String getName(){
		return this.name + "#" +  type.getName();
	}

	@Override
	public String toString() {
		return "[ " + name  + ":" + type + " | " + reg + "  " + applied + "]";
	}

}
