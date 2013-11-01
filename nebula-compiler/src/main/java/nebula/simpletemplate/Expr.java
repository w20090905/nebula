package nebula.simpletemplate;


public interface Expr<T> extends Code {
	T eval(Object root);
}
