package nebula.lang;


public interface Expr<T> extends Code {
	Type getExprType(Context context);
	T eval();
}
