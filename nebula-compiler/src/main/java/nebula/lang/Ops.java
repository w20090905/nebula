package nebula.lang;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Ops {
	static public Expr opAnd(Expr e1, Expr e2) {
		return new And(e1, e2);
	}
	
	static public Expr opNot(Expr e1) {
		return new Not(e1);
	}

	static public Expr opOr(Expr e1, Expr e2) {
		return new Or(e1, e2);
	}

	static public Expr opNotEqualTo(Expr e1, Expr e2) {
		return new NotEqualTo(e1, e2);
	}

	static public Expr opEqualTo(Expr e1, Expr e2) {
		return new EqualTo(e1, e2);
	}

	static public Expr opGreaterThan(Expr e1, Expr e2) {
		return new GreaterThan(e1, e2);
	}

	static public Expr opGreaterThanOrEqualTo(Expr e1, Expr e2) {
		return new GreaterThanOrEqualTo(e1, e2);
	}

	static public Expr opLessThan(Expr e1, Expr e2) {
		return new LessThan(e1, e2);
	}

	static public Expr opLessThanOrEqualTo(Expr e1, Expr e2) {
		return new LessThanOrEqualTo(e1, e2);
	}

	static public Expr opAdd(Expr e1, Expr e2) {
		return new Add(e1, e2);
	}

	static public Expr opSub(Expr e1, Expr e2) {
		return new Sub(e1, e2);
	}

	static public Expr opMulti(Expr e1, Expr e2) {
		return new Multi(e1, e2);
	}

	static public Expr opDiv(Expr e1, Expr e2) {
		return new Div(e1, e2);
	}

	static public Expr opRemainder(Expr e1, Expr e2) {
		return new Remainder(e1, e2);
	}

	static public Expr opIncrement(Expr e1) {
		return new Increment(e1);
	}

	static public Expr opDecrement(Expr e1) {
		return new Decrement(e1);
	}

	static public Expr opPositive(Expr e1) {
		return new Positive(e1);
	}

	static public Expr opNegates(Expr e1) {
		return new Negates(e1);
	}

	static public Expr opCst(String e1) {
		return new Cst(e1);
	}

	static public Expr opVar(String e1) {
		return new Var(e1);
	}

	static public Expr opFieldOf(Expr e1, String name) {
		return new FieldOf(e1, name);
	}

	static abstract class Expression implements Opcodes, Expr {
	}

	static abstract class Unary extends Expression {
	}

	static abstract class BinaryExp extends Expression {
		Expr e1;
		Expr e2;

		BinaryExp(final Expr e1, final Expr e2) {
			this.e1 = e1;
			this.e2 = e2;
		}
	}

	/**
	 * A logical "and" expression.
	 */
	static class And extends BinaryExp {

		And(final Expr e1, final Expr e2) {
			super(e1, e2);
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1
			e1.compile(mv);
			// tests if e1 is false
			mv.visitInsn(DUP);
			Label end = new Label();
			mv.visitJumpInsn(IFEQ, end);
			// case where e1 is true : e1 && e2 is equal to e2
			mv.visitInsn(POP);
			e2.compile(mv);
			// if e1 is false, e1 && e2 is equal to e1:
			// we jump directly to this label, without evaluating e2
			mv.visitLabel(end);
		}

		@Override
		public Object exec() {
			return null;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " && " + e2.toString() + ")";
		}
	}

	/**
	 * A logical "not" expression.
	 */
	static class Not extends Unary {
		Expr e;

		Not(final Expr e) {
			this.e = e;
		}

		public void compile(final MethodVisitor mv) {
			// computes !e1 by evaluating 1 - e1
			mv.visitLdcInsn(new Integer(1));
			e.compile(mv);
			mv.visitInsn(ISUB);
		}

		@Override
		public Object exec() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String toString() {
			return "(!" + e.toString() + ")";
		}
	}

	/**
	 * A logical "or" expression.
	 */
	static class Or extends BinaryExp {

		Or(final Expr e1, final Expr e2) {
			super(e1, e2);
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1
			e1.compile(mv);
			// tests if e1 is true
			mv.visitInsn(DUP);
			Label end = new Label();
			mv.visitJumpInsn(IFNE, end);
			// case where e1 is false : e1 || e2 is equal to e2
			mv.visitInsn(POP);
			e2.compile(mv);
			// if e1 is true, e1 || e2 is equal to e1:
			// we jump directly to this label, without evaluating e2
			mv.visitLabel(end);
		}

		@Override
		public Object exec() {
			return null;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " || " + e2.toString() + ")";
		}
	}

	static class NotEqualTo extends ConditionalOP {
		NotEqualTo(Expr e1, Expr e2) {
			super(e1, e2);
		}

		public void compile(final MethodVisitor mv) {
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
		public Object exec() {
			return null;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " != " + e2.toString() + ")";
		}
	}

	static class EqualTo extends ConditionalOP {
		EqualTo(Expr e1, Expr e2) {
			super(e1, e2);
		}

		public void compile(final MethodVisitor mv) {
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
		public Object exec() {
			return null;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " == " + e2.toString() + ")";
		}
	}

	abstract static class ConditionalOP extends BinaryExp {
		ConditionalOP(Expr e1, Expr e2) {
			super(e1, e2);
		}

		@Override
		public Object exec() {
			return null;
		}
	}

	/**
	 * A "greater than" expression.
	 */
	static class GreaterThan extends BinaryExp {

		GreaterThan(final Expr e1, final Expr e2) {
			super(e1, e2);
		}

		public void compile(final MethodVisitor mv) {
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

		@Override
		public Object exec() {
			return (Integer) e1.exec() > (Integer) e2.exec();
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " > " + e2.toString() + ")";
		}
	}

	static class GreaterThanOrEqualTo extends ConditionalOP {
		GreaterThanOrEqualTo(Expr e1, Expr e2) {
			super(e1, e2);
		}

		public void compile(final MethodVisitor mv) {
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
		public Object exec() {
			return null;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " >= " + e2.toString() + ")";
		}
	}

	static class LessThan extends ConditionalOP {
		LessThan(Expr e1, Expr e2) {
			super(e1, e2);
		}

		public void compile(final MethodVisitor mv) {

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
		public Object exec() {
			return null;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " < " + e2.toString() + ")";
		}
	}

	static class LessThanOrEqualTo extends ConditionalOP {
		LessThanOrEqualTo(Expr e1, Expr e2) {
			super(e1, e2);
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds the instructions to compare the two
			// values

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
		public Object exec() {
			return null;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " <= " + e2.toString() + ")";
		}
	}

	/**
	 * An addition expression.
	 */
	static class Add extends BinaryExp {

		Add(final Expr e1, final Expr e2) {
			super(e1, e2);
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to add the two values
			e1.compile(mv);
			e2.compile(mv);
			mv.visitInsn(IADD);
		}

		@Override
		public Object exec() {
			return null;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " + " + e2.toString() + ")";
		}
	}

	static class Sub extends BinaryExp {
		Sub(Expr e1, Expr e2) {
			super(e1, e2);
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to add the two values
			e1.compile(mv);
			e2.compile(mv);
			mv.visitInsn(ISUB);
		}

		@Override
		public Object exec() {
			return null;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " - " + e2.toString() + ")";
		}
	}

	/**
	 * A multiplication expression.
	 */
	static class Multi extends BinaryExp {

		Multi(final Expr e1, final Expr e2) {
			super(e1, e2);
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			e1.compile(mv);
			e2.compile(mv);
			mv.visitInsn(IMUL);
		}

		@Override
		public Object exec() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " * " + e2.toString() + ")";
		}
	}

	static class Div extends BinaryExp {
		Div(Expr e1, Expr e2) {
			super(e1, e2);
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			e1.compile(mv);
			e2.compile(mv);
			mv.visitInsn(IDIV);
		}

		@Override
		public Object exec() {
			return null;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " / " + e2.toString() + ")";
		}
	}

	static class Remainder extends BinaryExp {
		Remainder(Expr e1, Expr e2) {
			super(e1, e2);
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			e1.compile(mv);
			e2.compile(mv);
			mv.visitInsn(IREM);
		}

		@Override
		public Object exec() {
			return null;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " % " + e2.toString() + ")";
		}
	}

	static class Increment extends Unary {
		Expr e1;

		Increment(final Expr e1) {
			this.e1 = e1;
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			e1.compile(mv);
			mv.visitInsn(ICONST_1);
			mv.visitInsn(IADD);
		}

		@Override
		public Object exec() {
			return null;
		}

		@Override
		public String toString() {
			return "++" + e1.toString();
		}
	}

	static class Decrement extends Unary {
		Expr e1;

		Decrement(Expr e1) {
			this.e1 = e1;
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			e1.compile(mv);
			mv.visitInsn(ICONST_1);
			mv.visitInsn(ISUB);
		}

		@Override
		public Object exec() {
			return null;
		}

		@Override
		public String toString() {
			return "--" + e1.toString();
		}
	}

	static class Positive extends Unary {
		Expr e1;

		Positive(Expr e1) {
			this.e1 = e1;
		}

		public void compile(final MethodVisitor mv) {
			e1.compile(mv);
		}

		@Override
		public Object exec() {
			return null;
		}

		@Override
		public String toString() {
			return "+" + e1.toString();
		}
	}

	static class Negates extends Unary {
		Expr e1;

		Negates(Expr e1) {
			this.e1 = e1;
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			e1.compile(mv);
			mv.visitInsn(INEG);
		}

		@Override
		public Object exec() {
			return null;
		}

		@Override
		public String toString() {
			return "+" + e1.toString();
		}
	}

	static class Var extends Expression {
		final String name;

		Var(String name) {
			this.name = name;
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			// mv.visitLdcInsn(new Integer(0));
			// mv.visitInsn(INE);//TODO
		}

		@Override
		public Object exec() {
			return null;
		}

		@Override
		public String toString() {
			return name.toString();
		}
	}

	static class Cst extends Expression {
		final int value;

		Cst(String value) {
			this.value = Integer.parseInt(value);
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			mv.visitLdcInsn(value);
		}

		@Override
		public Object exec() {
			return null;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}

	static class FieldOf implements Expr {
		final Expr e1;
		final String name;

		FieldOf(Expr e1, String name) {
			this.e1 = e1;
			this.name = name;
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			// mv.visitLdcInsn(value);
			// TODO
		}

		@Override
		public Object exec() {
			return null;
		}

		@Override
		public String toString() {
			return e1.toString() + "." + name.toString();
		}
	}

}
