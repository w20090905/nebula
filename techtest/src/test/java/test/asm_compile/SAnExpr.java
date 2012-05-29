package test.asm_compile;


public class SAnExpr implements AnExpr<Field> {

	@Override
	public int eval(Field v) {
		return v.getName().length();
	}
	
	public static void main(String[] args) {
		AnExpr<Field> a = new SAnExpr();
		Field f = new Field(null, null);
		a.eval(f);
	}
}
