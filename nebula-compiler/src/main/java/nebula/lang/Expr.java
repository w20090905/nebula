package nebula.lang;


public interface Expr<T> extends Code {
	Type getType();
	T eval();
}
