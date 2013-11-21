package nebula.lang;


public interface OperatorExpr {

	void arithmetic(final AsmCompiler compiler, final Operator op, final Expr<Object> e1, final Expr<Object> e2);

	<V> void relational(final AsmCompiler compiler, Operator op, Expr<V> e1, Expr<V> e2);

	void increment(final AsmCompiler compiler, final Expr<Object> e1);

	void decrement(final AsmCompiler compiler, final Expr<Object> e1);

	void positive(final AsmCompiler compiler, final Expr<Object> e1);

	void negates(final AsmCompiler compiler, final Expr<Object> e1);
}
