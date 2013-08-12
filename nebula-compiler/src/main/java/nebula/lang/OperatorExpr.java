package nebula.lang;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public interface OperatorExpr {

	void arithmetic(final ClassWriter cw, final MethodVisitor mv, final Context context, final Operator op, final Expr<Object> e1, final Expr<Object> e2) ;
	<V> void relational(ClassWriter cw, MethodVisitor mv, Context context, Operator op, Expr<V> e1, Expr<V> e2);

	void increment(ClassWriter cw, final MethodVisitor mv,Context context, final Expr<Object> e1);

	void decrement(ClassWriter cw, final MethodVisitor mv,Context context, final Expr<Object> e1);

	void positive(ClassWriter cw, final MethodVisitor mv,Context context, final Expr<Object> e1);

	void negates(ClassWriter cw, final MethodVisitor mv,Context context, final Expr<Object> e1);
}
