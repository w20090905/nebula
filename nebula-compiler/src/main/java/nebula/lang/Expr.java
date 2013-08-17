package nebula.lang;


public interface Expr<T> extends Code {
	Type getExprType(CompilerContext context);
	T eval();
}
