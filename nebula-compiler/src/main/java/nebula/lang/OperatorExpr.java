package nebula.lang;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public interface OperatorExpr {

	void arithmetic(final ClassWriter cw, final MethodVisitor mv, final Context context, final Operator op, final Expr<Object> e1, final Expr<Object> e2) ;
	<V> void relational(ClassWriter cw, MethodVisitor mv, Context context, Operator op, Expr<V> e1, Expr<V> e2);
	
	<V> void eq(ClassWriter cw, final MethodVisitor mv,Context context, final Expr<V> e1, final Expr<V> e2);

	<V> void ne(ClassWriter cw, final MethodVisitor mv,Context context, final Expr<V> e1, final Expr<V> e2);

	<V> void le(ClassWriter cw, final MethodVisitor mv,Context context, final Expr<V> e1, final Expr<V> e2);

	<V> void lt(ClassWriter cw, final MethodVisitor mv,Context context, final Expr<V> e1, final Expr<V> e2);

	<V> void ge(ClassWriter cw, final MethodVisitor mv,Context context, final Expr<V> e1, final Expr<V> e2);

	<V> void gt(ClassWriter cw, final MethodVisitor mv,Context context, final Expr<V> e1, final Expr<V> e2);

	
	void add(ClassWriter cw, final MethodVisitor mv,Context context, final Expr<Object> e1, final Expr<Object> e2);

	void sub(ClassWriter cw, final MethodVisitor mv,Context context, final Expr<Object> e1, final Expr<Object> e2);

	void multi(ClassWriter cw, final MethodVisitor mv,Context context, final Expr<Object> e1, final Expr<Object> e2);

	void div(ClassWriter cw, final MethodVisitor mv,Context context, final Expr<Object> e1, final Expr<Object> e2);

	void remainder(ClassWriter cw, final MethodVisitor mv,Context context, final Expr<Object> e1, final Expr<Object> e2);

	void increment(ClassWriter cw, final MethodVisitor mv,Context context, final Expr<Object> e1);

	void decrement(ClassWriter cw, final MethodVisitor mv,Context context, final Expr<Object> e1);

	void positive(ClassWriter cw, final MethodVisitor mv,Context context, final Expr<Object> e1);

	void negates(ClassWriter cw, final MethodVisitor mv,Context context, final Expr<Object> e1);
}
