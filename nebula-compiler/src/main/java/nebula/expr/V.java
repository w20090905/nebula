package nebula.expr;

@Deprecated
public interface V<T> extends Exp {
	T exec();

	LogicExp eq(V<T> v);	
	LogicExp ne(V<T> v);

	LogicExp gt(V<T> v);
	LogicExp ge(V<T> v);

	LogicExp lt(V<T> v);
	LogicExp le(V<T> v);

	LogicExp eqF(String name);
	LogicExp neF(String name);
	
	LogicExp gtF(String name);
	LogicExp geF(String name);

	LogicExp ltF(String name);
	LogicExp leF(String name);

	LogicExp eq(T v);
	LogicExp ne(T v);

	LogicExp gt(T v);
	LogicExp ge(T v);

	LogicExp lt(T v);
	LogicExp le(T v);
}