package nebula.expr;

public interface V<T> extends Exp {
	T exec();

	LogicExp gt(V<T> v);

	LogicExp ls(V<T> v);

	LogicExp eq(V<T> v);

	LogicExp gtF(String name);

	LogicExp lsF(String name);

	LogicExp eqF(String name);

	LogicExp gt(T v);

	LogicExp ls(T v);

	LogicExp eq(T v);
}