package nebula.lang;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class LongOperator implements Operator, Opcodes {

	@Override
	public void add(MethodVisitor mv,Context context, Expr<Object> e1, Expr<Object> e2) {
		e1.compile(mv, context);
		e2.compile(mv, context);
		mv.visitInsn(IADD);
	}

	@Override
	public void sub(MethodVisitor mv,Context context, Expr<Object> e1, Expr<Object> e2) {
		e1.compile(mv, context);
		e2.compile(mv, context);
		mv.visitInsn(ISUB);
	}

	@Override
	public void multi(MethodVisitor mv,Context context, Expr<Object> e1, Expr<Object> e2) {
		e1.compile(mv, context);
		e2.compile(mv, context);
		mv.visitInsn(IMUL);
	}
	
	@Override
	public void div(MethodVisitor mv,Context context, Expr<Object> e1, Expr<Object> e2) {
		e1.compile(mv, context);
		e2.compile(mv, context);
		mv.visitInsn(IDIV);
	}

	@Override
	public void remainder(MethodVisitor mv,Context context, Expr<Object> e1, Expr<Object> e2) {
		e1.compile(mv, context);
		e2.compile(mv, context);
		mv.visitInsn(IREM);
	}

	@Override
	public void increment(MethodVisitor mv,Context context, Expr<Object> e1) {
		e1.compile(mv, context);
		mv.visitInsn(ICONST_1);
		mv.visitInsn(IADD);
	}

	@Override
	public void decrement(MethodVisitor mv,Context context, Expr<Object> e1) {
		e1.compile(mv, context);
		mv.visitInsn(ICONST_1);
		mv.visitInsn(ISUB);
	}

	@Override
	public void positive(MethodVisitor mv,Context context, Expr<Object> e1) {
		e1.compile(mv, context);
	}

	@Override
	public void negates(MethodVisitor mv,Context context, Expr<Object> e1) {
		e1.compile(mv, context);
		mv.visitInsn(INEG);
	}

	@Override
	public <V> void eq(MethodVisitor mv,Context context, Expr<V> e1, Expr<V> e2) {		
		e1.compile(mv, context);
		e2.compile(mv, context);
		Label iftrue = new Label();
		Label end = new Label();
		mv.visitJumpInsn(IF_ICMPEQ, iftrue);
		// case where e1 <= e2 : pushes false and jump to "end"
		mv.visitInsn(ICONST_0);
		mv.visitJumpInsn(GOTO, end);
		// case where e1 > e2 : pushes true
		mv.visitLabel(iftrue);
		mv.visitInsn(ICONST_1);
		mv.visitLabel(end);		
	}

	@Override
	public <V> void ne(MethodVisitor mv,Context context, Expr<V> e1, Expr<V> e2) {
		e1.compile(mv, context);
		e2.compile(mv, context);
		Label iftrue = new Label();
		Label end = new Label();
		mv.visitJumpInsn(IF_ICMPEQ, iftrue);
		// case where e1 <= e2 : pushes false and jump to "end"
		mv.visitInsn(ICONST_1);
		mv.visitJumpInsn(GOTO, end);
		// case where e1 > e2 : pushes true
		mv.visitLabel(iftrue);
		mv.visitInsn(ICONST_0);
		mv.visitLabel(end);		
	}

	@Override
	public <V> void le(MethodVisitor mv,Context context, Expr<V> e1, Expr<V> e2) {
		e1.compile(mv, context);
		e2.compile(mv, context);
		Label iftrue = new Label();
		Label end = new Label();
		mv.visitJumpInsn(IF_ICMPLE, iftrue);
		// case where e1 <= e2 : pushes false and jump to "end"
		mv.visitInsn(ICONST_0);
		mv.visitJumpInsn(GOTO, end);
		// case where e1 > e2 : pushes true
		mv.visitLabel(iftrue);
		mv.visitInsn(ICONST_1);
		mv.visitLabel(end);		
	}

	@Override
	public <V> void lt(MethodVisitor mv,Context context, Expr<V> e1, Expr<V> e2) {
		e1.compile(mv, context);
		e2.compile(mv, context);
		Label iftrue = new Label();
		Label end = new Label();
		mv.visitJumpInsn(IF_ICMPLT, iftrue);
		// case where e1 <= e2 : pushes false and jump to "end"
		mv.visitInsn(ICONST_0);
		mv.visitJumpInsn(GOTO, end);
		// case where e1 > e2 : pushes true
		mv.visitLabel(iftrue);
		mv.visitInsn(ICONST_1);
		mv.visitLabel(end);
	}

	@Override
	public <V> void ge(MethodVisitor mv,Context context, Expr<V> e1, Expr<V> e2) {		
		e1.compile(mv, context);
		e2.compile(mv, context);
		Label iftrue = new Label();
		Label end = new Label();
		mv.visitJumpInsn(IF_ICMPGE, iftrue);
		// case where e1 <= e2 : pushes false and jump to "end"
		mv.visitInsn(ICONST_0);
		mv.visitJumpInsn(GOTO, end);
		// case where e1 > e2 : pushes true
		mv.visitLabel(iftrue);
		mv.visitInsn(ICONST_1);
		mv.visitLabel(end);
	}

	@Override
	public <V> void gt(MethodVisitor mv,Context context, Expr<V> e1, Expr<V> e2) {
		e1.compile(mv, context);
		e2.compile(mv, context);
		Label iftrue = new Label();
		Label end = new Label();
		mv.visitJumpInsn(IF_ICMPGT, iftrue);
		// case where e1 <= e2 : pushes false and jump to "end"
		mv.visitInsn(ICONST_0);
		mv.visitJumpInsn(GOTO, end);
		// case where e1 > e2 : pushes true
		mv.visitLabel(iftrue);
		mv.visitInsn(ICONST_1);
		mv.visitLabel(end);
	}

}
