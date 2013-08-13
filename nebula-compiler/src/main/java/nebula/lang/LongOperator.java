package nebula.lang;

import java.util.EnumMap;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.google.common.collect.Maps;
import static nebula.lang.Operator.*;

public class LongOperator implements OperatorExpr, Opcodes {
	EnumMap<Operator, Integer> ops = Maps.newEnumMap(Operator.class);

	public LongOperator() {
		ops.put(ADD, LADD);
		ops.put(SUB, LSUB);
		ops.put(MUL, LMUL);
		ops.put(DIV, LDIV);
		ops.put(REM, LREM);

		ops.put(EQ, IFNE);// '==';
		ops.put(NE, IFEQ);// '!=';
		ops.put(GE, IFLT); // '>=';
		ops.put(GT, IFLE);// '>';
		ops.put(LE, IFGT); // '<=';
		ops.put(LT, IFGE);// '<';
	}

	public void arithmetic(ClassWriter cw, MethodVisitor mv, Context context, Operator op, Expr<Object> e1, Expr<Object> e2) {
		e1.compile(cw, mv, context);
		e2.compile(cw, mv, context);
		mv.visitInsn(ops.get(op));
	}


	@Override
	public void increment(ClassWriter cw, MethodVisitor mv, Context context, Expr<Object> e1) {
		e1.compile(cw, mv, context);
		mv.visitInsn(LCONST_1);
		mv.visitInsn(LADD);
	}

	@Override
	public void decrement(ClassWriter cw, MethodVisitor mv, Context context, Expr<Object> e1) {
		e1.compile(cw, mv, context);
		mv.visitInsn(LCONST_1);
		mv.visitInsn(LSUB);
	}

	@Override
	public void positive(ClassWriter cw, MethodVisitor mv, Context context, Expr<Object> e1) {
		e1.compile(cw, mv, context);
	}

	@Override
	public void negates(ClassWriter cw, MethodVisitor mv, Context context, Expr<Object> e1) {
		e1.compile(cw, mv, context);
		mv.visitInsn(LNEG);
	}

	public <V> void relational(ClassWriter cw, MethodVisitor mv, Context context, Operator op, Expr<V> e1, Expr<V> e2) {
		cmp(cw, mv, context, e1, e2, ops.get(op));
	}

	public <V> void cmp(ClassWriter cw, MethodVisitor mv, Context context, Expr<V> e1, Expr<V> e2, int op) {
		e1.compile(cw, mv, context);
		e2.compile(cw, mv, context);

		mv.visitInsn(LCMP);
		Label ifFalse = new Label();
		mv.visitJumpInsn(op, ifFalse);
		mv.visitInsn(ICONST_1);
		Label end = new Label();
		mv.visitJumpInsn(GOTO, end);
		mv.visitLabel(ifFalse);
		mv.visitInsn(ICONST_0);
		mv.visitLabel(end);
	}

}
