package nebula.vm;


public class SConst extends Var {
	public SConst(String value) {
		super(Var.SCONST, value, BuiltInTypeSymbol.INT);
	}
}
