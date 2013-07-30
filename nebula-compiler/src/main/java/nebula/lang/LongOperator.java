package nebula.lang;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class LongOperator implements Operator, Opcodes {

	@Override
	public void add(MethodVisitor mv, Expr<Object> e1, Expr<Object> e2) {
		e1.compile(mv);
		e2.compile(mv);
		mv.visitInsn(IADD);
	}

	@Override
	public void sub(MethodVisitor mv, Expr<Object> e1, Expr<Object> e2) {
		e1.compile(mv);
		e2.compile(mv);
		mv.visitInsn(ISUB);
	}

	@Override
	public void multi(MethodVisitor mv, Expr<Object> e1, Expr<Object> e2) {
		e1.compile(mv);
		e2.compile(mv);
		mv.visitInsn(IMUL);
	}
	
	@Override
	public void div(MethodVisitor mv, Expr<Object> e1, Expr<Object> e2) {
		e1.compile(mv);
		e2.compile(mv);
		mv.visitInsn(IDIV);
	}

	@Override
	public void remainder(MethodVisitor mv, Expr<Object> e1, Expr<Object> e2) {
		e1.compile(mv);
		e2.compile(mv);
		mv.visitInsn(IREM);
	}

	@Override
	public void increment(MethodVisitor mv, Expr<Object> e1) {
		e1.compile(mv);
		mv.visitInsn(ICONST_1);
		mv.visitInsn(IADD);
	}

	@Override
	public void decrement(MethodVisitor mv, Expr<Object> e1) {
		e1.compile(mv);
		mv.visitInsn(ICONST_1);
		mv.visitInsn(ISUB);
	}

	@Override
	public void positive(MethodVisitor mv, Expr<Object> e1) {
		e1.compile(mv);
	}

	@Override
	public void negates(MethodVisitor mv, Expr<Object> e1) {
		e1.compile(mv);
		mv.visitInsn(INEG);
	}

	@Override
	public <V> void eq(MethodVisitor mv, Expr<V> e1, Expr<V> e2) {		
		e1.compile(mv);
		e2.compile(mv);
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
	public <V> void ne(MethodVisitor mv, Expr<V> e1, Expr<V> e2) {
		e1.compile(mv);
		e2.compile(mv);
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
	public <V> void le(MethodVisitor mv, Expr<V> e1, Expr<V> e2) {
		e1.compile(mv);
		e2.compile(mv);
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
	public <V> void lt(MethodVisitor mv, Expr<V> e1, Expr<V> e2) {
		e1.compile(mv);
		e2.compile(mv);
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
	public <V> void ge(MethodVisitor mv, Expr<V> e1, Expr<V> e2) {		
		e1.compile(mv);
		e2.compile(mv);
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
	public <V> void gt(MethodVisitor mv, Expr<V> e1, Expr<V> e2) {
		e1.compile(mv);
		e2.compile(mv);
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
