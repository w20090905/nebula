package nebula.lang;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class LongOperator implements Operator, Opcodes {

	@Override
	public void add(MethodVisitor mv,Context context, Expr<Object> e1, Expr<Object> e2) {
		e1.compile(mv, context);
		e2.compile(mv, context);
		mv.visitInsn(LADD);
	}

	@Override
	public void sub(MethodVisitor mv,Context context, Expr<Object> e1, Expr<Object> e2) {
		e1.compile(mv, context);
		e2.compile(mv, context);
		mv.visitInsn(LSUB);
	}

	@Override
	public void multi(MethodVisitor mv,Context context, Expr<Object> e1, Expr<Object> e2) {
		e1.compile(mv, context);
		e2.compile(mv, context);
		mv.visitInsn(LMUL);
	}
	
	@Override
	public void div(MethodVisitor mv,Context context, Expr<Object> e1, Expr<Object> e2) {
		e1.compile(mv, context);
		e2.compile(mv, context);
		mv.visitInsn(LDIV);
	}

	@Override
	public void remainder(MethodVisitor mv,Context context, Expr<Object> e1, Expr<Object> e2) {
		e1.compile(mv, context);
		e2.compile(mv, context);
		mv.visitInsn(LREM);
	}

	@Override
	public void increment(MethodVisitor mv,Context context, Expr<Object> e1) {
		e1.compile(mv, context);
		mv.visitInsn(LCONST_1);
		mv.visitInsn(LADD);
	}

	@Override
	public void decrement(MethodVisitor mv,Context context, Expr<Object> e1) {
		e1.compile(mv, context);
		mv.visitInsn(LCONST_1);
		mv.visitInsn(LSUB);
	}

	@Override
	public void positive(MethodVisitor mv,Context context, Expr<Object> e1) {
		e1.compile(mv, context);
	}

	@Override
	public void negates(MethodVisitor mv,Context context, Expr<Object> e1) {
		e1.compile(mv, context);
		mv.visitInsn(LNEG);
	}

	@Override
	public <V> void eq(MethodVisitor mv,Context context, Expr<V> e1, Expr<V> e2) {		
		cmp(mv, context, e1, e2, IFEQ);
	}

	@Override
	public <V> void ne(MethodVisitor mv,Context context, Expr<V> e1, Expr<V> e2) {
		cmp(mv, context, e1, e2, IFNE);
	}
	
	@Override
	public <V> void le(MethodVisitor mv,Context context, Expr<V> e1, Expr<V> e2) {
		cmp(mv, context, e1, e2, IFLE);
	}

	@Override
	public <V> void lt(MethodVisitor mv,Context context, Expr<V> e1, Expr<V> e2) {
		cmp(mv, context, e1, e2, IFLT);
	}

	@Override
	public <V> void ge(MethodVisitor mv,Context context, Expr<V> e1, Expr<V> e2) {	
		cmp(mv, context, e1, e2, IFGE);
	}

	@Override
	public <V> void gt(MethodVisitor mv,Context context, Expr<V> e1, Expr<V> e2) {
		cmp(mv, context, e1, e2, IFGT);
	}

	public <V> void cmp(MethodVisitor mv,Context context, Expr<V> e1, Expr<V> e2,int op) {
		e1.compile(mv, context);
		e2.compile(mv, context);

		mv.visitInsn(LCMP);
		Label ifFalse = new Label();
		mv.visitJumpInsn(op, ifFalse);
		mv.visitInsn(ICONST_0);
		Label end = new Label();
		mv.visitJumpInsn(GOTO, end);
		mv.visitLabel(ifFalse);
		mv.visitInsn(ICONST_1);
		mv.visitLabel(end);
	}
}
