package nebula.lang;

import org.objectweb.asm.MethodVisitor;

public interface Operator {
	<V> void eq(final MethodVisitor mv, final Expr<V> e1, final Expr<V> e2);

	<V> void ne(final MethodVisitor mv, final Expr<V> e1, final Expr<V> e2);

	<V> void le(final MethodVisitor mv, final Expr<V> e1, final Expr<V> e2);

	<V> void lt(final MethodVisitor mv, final Expr<V> e1, final Expr<V> e2);

	<V> void ge(final MethodVisitor mv, final Expr<V> e1, final Expr<V> e2);

	<V> void gt(final MethodVisitor mv, final Expr<V> e1, final Expr<V> e2);

	
	void add(final MethodVisitor mv, final Expr<Object> e1, final Expr<Object> e2);

	void sub(final MethodVisitor mv, final Expr<Object> e1, final Expr<Object> e2);

	void multi(final MethodVisitor mv, final Expr<Object> e1, final Expr<Object> e2);

	void div(final MethodVisitor mv, final Expr<Object> e1, final Expr<Object> e2);

	void remainder(final MethodVisitor mv, final Expr<Object> e1, final Expr<Object> e2);

	void increment(final MethodVisitor mv, final Expr<Object> e1);

	void decrement(final MethodVisitor mv, final Expr<Object> e1);

	void positive(final MethodVisitor mv, final Expr<Object> e1);

	void negates(final MethodVisitor mv, final Expr<Object> e1);
}
