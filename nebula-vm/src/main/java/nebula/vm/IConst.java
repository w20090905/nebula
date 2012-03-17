package nebula.vm;


public class IConst extends Var {
	public IConst(String value) {
		super(Var.ICONST, value, BuiltInTypeSymbol.INT);
	}
}
