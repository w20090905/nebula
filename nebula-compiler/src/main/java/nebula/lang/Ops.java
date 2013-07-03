package nebula.lang;

import java.math.BigDecimal;

import nebula.data.Entity;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Ops {
	static EntityExpressionComplier compiler = new EntityExpressionComplier();

	static public <T> EntityExpression<T> compile(Expr<T> expr, Type type) {
		return compiler.compile(expr, type);
	}

	static public Expr<Boolean> opAnd(Expr<Boolean> e1, Expr<Boolean> e2) {
		return new And(e1, e2);
	}

	static public Expr<Boolean> opNot(Expr<Boolean> e1) {
		return new Not(e1);
	}

	static public Expr<Boolean> opOr(Expr<Boolean> e1, Expr<Boolean> e2) {
		return new Or(e1, e2);
	}

	static public <V extends Comparable<V>> Expr<Boolean> opNotEqualTo(Expr<V> e1, Expr<V> e2) {
		return new NotEqualTo<V>(e1, e2);
	}

	static public <V extends Comparable<V>> Expr<Boolean> opEqualTo(Expr<V> e1, Expr<V> e2) {
		return new EqualTo<V>(e1, e2);
	}

	static public <V extends Comparable<V>> Expr<Boolean> opGreaterThan(Expr<V> e1, Expr<V> e2) {
		return new GreaterThan<V>(e1, e2);
	}

	static public <V extends Comparable<V>> Expr<Boolean> opGreaterThanOrEqualTo(Expr<V> e1, Expr<V> e2) {
		return new GreaterThanOrEqualTo<V>(e1, e2);
	}

	static public <V extends Comparable<V>> Expr<Boolean> opLessThan(Expr<V> e1, Expr<V> e2) {
		return new LessThan<V>(e1, e2);
	}

	static public <V extends Comparable<V>> Expr<Boolean> opLessThanOrEqualTo(Expr<V> e1, Expr<V> e2) {
		return new LessThanOrEqualTo<V>(e1, e2);
	}

	static public Expr<Integer> opAdd(Expr<Integer> e1, Expr<Integer> e2) {
		return new Add(e1, e2);
	}

	static public Expr<Integer> opSub(Expr<Integer> e1, Expr<Integer> e2) {
		return new Sub(e1, e2);
	}

	static public Expr<Integer> opMulti(Expr<Integer> e1, Expr<Integer> e2) {
		return new Multi(e1, e2);
	}

	static public Expr<Integer> opDiv(Expr<Integer> e1, Expr<Integer> e2) {
		return new Div(e1, e2);
	}

	static public Expr<Integer> opRemainder(Expr<Integer> e1, Expr<Integer> e2) {
		return new Remainder(e1, e2);
	}

	static public Expr<Integer> opIncrement(Expr<Integer> e1) {
		return new Increment(e1);
	}

	static public Expr<Integer> opDecrement(Expr<Integer> e1) {
		return new Decrement(e1);
	}

	static public Expr<Integer> opPositive(Expr<Integer> e1) {
		return new Positive(e1);
	}

	static public Expr<Integer> opNegates(Expr<Integer> e1) {
		return new Negates(e1);
	}

	static public Expr<Integer> opIntegerCst(String value) {
		return new IntegerCst(value);
	}

	static public Expr<BigDecimal> opDecimalCst(String value) {
		return new DecimalCst(value);
	}

	static public Expr<String> opStringCst(String value) {
		return new StringCst(value);
	}
	
	static public Expr<DateTime> opTimestampCst(String value) {
		return new TimestampCst(value);
	}

	static public Expr<DateTime> opDatetimeCst(String value) {
		return new DatetimeCst(value);
	}

	static public Expr<DateTime> opDateCst(String value) {
		return new DateCst(value);
	}

	static public Expr<DateTime> opTimeCst(String value) {
		return new TimeCst(value);
	}

	// TODO
	static public Expr<Integer> opVar(String e1) {
		return new IntegerVar(e1);
	}

	static public Expr<Integer> opFieldOf(Expr<Entity> e1, String name) {
		return new IntegerFieldOf(e1, name);
	}

	static abstract class Expression<T> implements Opcodes, Expr<T> {
	}

	static abstract class LogicExpr extends Expression<Boolean> {
	}

	/**
	 * A logical "and" expression.
	 */
	static class And extends LogicExpr {
		final Expr<Boolean> e1;
		final Expr<Boolean> e2;

		public And(Expr<Boolean> e1, Expr<Boolean> e2) {
			this.e1 = e1;
			this.e2 = e2;
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
		public Boolean exec() {
			return e1.exec() && e2.exec();
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " && " + e2.toString() + ")";
		}
	}

	/**
	 * A logical "not" expression.
	 */
	static class Not extends LogicExpr {
		final Expr<Boolean> e1;

		Not(Expr<Boolean> e1) {
			this.e1 = e1;
		}

		public void compile(final MethodVisitor mv) {
			// computes !e1 by evaluating 1 - e1
			mv.visitLdcInsn(new Integer(1));
			e1.compile(mv);
			mv.visitInsn(ISUB);
		}

		@Override
		public Boolean exec() {
			return !e1.exec();
		}

		@Override
		public String toString() {
			return "(!" + e1.toString() + ")";
		}
	}

	/**
	 * A logical "or" expression.
	 */
	static class Or extends LogicExpr {
		final Expr<Boolean> e1;
		final Expr<Boolean> e2;

		public Or(Expr<Boolean> e1, Expr<Boolean> e2) {
			this.e1 = e1;
			this.e2 = e2;
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
		public Boolean exec() {
			return e1.exec() || e2.exec();
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " || " + e2.toString() + ")";
		}
	}

	abstract static class CompareExpr<V extends Comparable<V>> extends Expression<Boolean> {
	}

	static class NotEqualTo<V extends Comparable<V>> extends CompareExpr<V> {
		final Expr<V> e1;
		final Expr<V> e2;

		NotEqualTo(Expr<V> e1, Expr<V> e2) {
			this.e1 = e1;
			this.e2 = e2;
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
		public Boolean exec() {
			return e1.exec().compareTo(e2.exec()) != 0;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " != " + e2.toString() + ")";
		}
	}

	static class EqualTo<V extends Comparable<V>> extends CompareExpr<V> {
		final Expr<V> e1;
		final Expr<V> e2;

		EqualTo(Expr<V> e1, Expr<V> e2) {
			this.e1 = e1;
			this.e2 = e2;
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
		public Boolean exec() {
			return e1.exec().compareTo(e2.exec()) == 0;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " == " + e2.toString() + ")";
		}
	}

	/**
	 * A "greater than" expression.
	 */
	static class GreaterThan<V extends Comparable<V>> extends CompareExpr<V> {
		final Expr<V> e1;
		final Expr<V> e2;

		GreaterThan(Expr<V> e1, Expr<V> e2) {
			this.e1 = e1;
			this.e2 = e2;
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
		public Boolean exec() {
			return e1.exec().compareTo(e2.exec()) > 0;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " > " + e2.toString() + ")";
		}
	}

	static class GreaterThanOrEqualTo<V extends Comparable<V>> extends CompareExpr<V> {
		final Expr<V> e1;
		final Expr<V> e2;

		GreaterThanOrEqualTo(Expr<V> e1, Expr<V> e2) {
			this.e1 = e1;
			this.e2 = e2;
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
		public Boolean exec() {
			return e1.exec().compareTo(e2.exec()) >= 0;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " >= " + e2.toString() + ")";
		}
	}

	static class LessThan<V extends Comparable<V>> extends CompareExpr<V> {
		final Expr<V> e1;
		final Expr<V> e2;

		LessThan(Expr<V> e1, Expr<V> e2) {
			this.e1 = e1;
			this.e2 = e2;
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
		public Boolean exec() {
			return e1.exec().compareTo(e2.exec()) < 0;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " < " + e2.toString() + ")";
		}
	}

	static class LessThanOrEqualTo<V extends Comparable<V>> extends CompareExpr<V> {
		final Expr<V> e1;
		final Expr<V> e2;

		LessThanOrEqualTo(Expr<V> e1, Expr<V> e2) {
			this.e1 = e1;
			this.e2 = e2;
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
		public Boolean exec() {
			return e1.exec().compareTo(e2.exec()) <= 0;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " <= " + e2.toString() + ")";
		}
	}

	static abstract class IntegerExpr extends Expression<Integer> {

	}

	/**
	 * An addition expression.
	 */
	static class Add extends IntegerExpr {
		final Expr<Integer> e1;
		final Expr<Integer> e2;

		Add(final Expr<Integer> e1, final Expr<Integer> e2) {
			this.e1 = e1;
			this.e2 = e2;
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to add the two values
			e1.compile(mv);
			e2.compile(mv);
			mv.visitInsn(IADD);
		}

		@Override
		public Integer exec() {
			return e1.exec() + e2.exec();
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " + " + e2.toString() + ")";
		}
	}

	static class Sub extends IntegerExpr {
		final Expr<Integer> e1;
		final Expr<Integer> e2;

		Sub(final Expr<Integer> e1, final Expr<Integer> e2) {
			this.e1 = e1;
			this.e2 = e2;
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to add the two values
			e1.compile(mv);
			e2.compile(mv);
			mv.visitInsn(ISUB);
		}

		@Override
		public Integer exec() {
			return e1.exec() - e2.exec();
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " - " + e2.toString() + ")";
		}
	}

	/**
	 * A multiplication expression.
	 */
	static class Multi extends IntegerExpr {
		final Expr<Integer> e1;
		final Expr<Integer> e2;

		Multi(final Expr<Integer> e1, final Expr<Integer> e2) {
			this.e1 = e1;
			this.e2 = e2;
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			e1.compile(mv);
			e2.compile(mv);
			mv.visitInsn(IMUL);
		}

		@Override
		public Integer exec() {
			return e1.exec() * e2.exec();
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " * " + e2.toString() + ")";
		}
	}

	static class Div extends IntegerExpr {
		final Expr<Integer> e1;
		final Expr<Integer> e2;

		Div(final Expr<Integer> e1, final Expr<Integer> e2) {
			this.e1 = e1;
			this.e2 = e2;
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			e1.compile(mv);
			e2.compile(mv);
			mv.visitInsn(IDIV);
		}

		@Override
		public Integer exec() {
			return e1.exec() / e2.exec();
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " / " + e2.toString() + ")";
		}
	}

	static class Remainder extends IntegerExpr {
		final Expr<Integer> e1;
		final Expr<Integer> e2;

		Remainder(final Expr<Integer> e1, final Expr<Integer> e2) {
			this.e1 = e1;
			this.e2 = e2;
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			e1.compile(mv);
			e2.compile(mv);
			mv.visitInsn(IREM);
		}

		@Override
		public Integer exec() {
			return e1.exec() % e2.exec();
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " % " + e2.toString() + ")";
		}
	}

	static class Increment extends IntegerExpr {
		Expr<Integer> e1;

		Increment(Expr<Integer> e1) {
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
		public Integer exec() {
			return e1.exec() + 1;
		}

		@Override
		public String toString() {
			return "++" + e1.toString();
		}
	}

	static class Decrement extends IntegerExpr {
		Expr<Integer> e1;

		Decrement(Expr<Integer> e1) {
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
		public Integer exec() {
			return e1.exec() - 1;
		}

		@Override
		public String toString() {
			return "--" + e1.toString();
		}
	}

	static class Positive extends IntegerExpr {
		Expr<Integer> e1;

		Positive(Expr<Integer> e1) {
			this.e1 = e1;
		}

		public void compile(final MethodVisitor mv) {
			e1.compile(mv);
		}

		@Override
		public Integer exec() {
			return e1.exec();
		}

		@Override
		public String toString() {
			return "+" + e1.toString();
		}
	}

	static class Negates extends IntegerExpr {
		Expr<Integer> e1;

		Negates(Expr<Integer> e1) {
			this.e1 = e1;
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			e1.compile(mv);
			mv.visitInsn(INEG);
		}

		@Override
		public Integer exec() {
			return -e1.exec();
		}

		@Override
		public String toString() {
			return "+" + e1.toString();
		}
	}

	static class IntegerVar extends IntegerExpr {
		final String name;

		IntegerVar(String name) {
			this.name = name;
		}

		public void compile(final MethodVisitor mv) {
			mv.visitVarInsn(ALOAD, 1);
			mv.visitLdcInsn(this.name);
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Entity", "get", "(Ljava/lang/String;)Ljava/lang/Object;");
			mv.visitTypeInsn(CHECKCAST, "java/lang/Integer");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue", "()I");
		}

		@Override
		public Integer exec() {
			return null;// TODO
		}

		@Override
		public String toString() {
			return name.toString();
		}
	}

	static class StringCst extends Expression<String> {
		final String value;

		StringCst(String value) {
			this.value = value;
		}

		public void compile(final MethodVisitor mv) {
			// TODO String
			mv.visitLdcInsn(value);
		}

		@Override
		public String exec() {
			return this.value;
		}

		@Override
		public String toString() {
			return value;
		}
	}

	static class DecimalCst extends Expression<BigDecimal> {
		final String text;

		DecimalCst(String text) {
			this.text = text;
		}

		public void compile(final MethodVisitor mv) {
			mv.visitTypeInsn(NEW, "java/math/BigDecimal");
			mv.visitInsn(DUP);
			mv.visitLdcInsn(text);
			mv.visitMethodInsn(INVOKESPECIAL, "java/math/BigDecimal", "<init>", "(Ljava/lang/String;)V");
		}

		@Override
		public BigDecimal exec() {
			return new BigDecimal(this.text);
		}

		@Override
		public String toString() {
			return text;
		}
	}

	static class IntegerCst extends IntegerExpr {
		final int value;

		IntegerCst(String text) {
			this.value = Integer.parseInt(text);
		}

		public void compile(final MethodVisitor mv) {
			mv.visitLdcInsn(value);
		}

		@Override
		public Integer exec() {
			return this.value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}
	

	static class TimestampCst extends Expression<DateTime> {
		static final DateTimeFormatter formater = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
		final DateTime value;

		TimestampCst(String text) {
			this.value =formater.parseDateTime(text);
		}

		public void compile(final MethodVisitor mv) {
			mv.visitLdcInsn(value);//TODO
		}

		@Override
		public DateTime exec() {
			return this.value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}


	static class DatetimeCst extends Expression<DateTime> {
		static final DateTimeFormatter formater = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		final DateTime value;

		DatetimeCst(String text) {
			this.value =formater.parseDateTime(text);
		}

		public void compile(final MethodVisitor mv) {
			mv.visitLdcInsn(value);//TODO
		}

		@Override
		public DateTime exec() {
			return this.value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}
	

	static class DateCst extends Expression<DateTime> {
		static final DateTimeFormatter formater = DateTimeFormat.forPattern("yyyy-MM-dd");
		final DateTime value;

		DateCst(String text) {
			this.value =formater.parseDateTime(text);
		}

		public void compile(final MethodVisitor mv) {
			mv.visitLdcInsn(value);//TODO
		}

		@Override
		public DateTime exec() {
			return this.value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}
	

	static class TimeCst extends Expression<DateTime> {
		static final DateTimeFormatter formater = DateTimeFormat.forPattern("HH:mm:ss");
		final DateTime value;

		TimeCst(String text) {
			this.value =formater.parseDateTime(text);
		}

		public void compile(final MethodVisitor mv) {
			mv.visitLdcInsn(value);//TODO
		}

		@Override
		public DateTime exec() {
			return this.value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}
	

	

	static class IntegerFieldOf extends IntegerExpr {
		final Expr<Entity> e1;
		final String name;

		IntegerFieldOf(Expr<Entity> e1, String name) {
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
		public Integer exec() {
			return (Integer) e1.exec().get(name);
		}

		@Override
		public String toString() {
			return e1.toString() + "." + name.toString();
		}
	}

}
