package nebula.lang;

import java.math.BigDecimal;

import nebula.data.Entity;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.google.common.base.Function;

public class Compiler {
	Function<String, Type> typeResolve;

	Compiler(Function<String, Type> typeResolve) {
		this.typeResolve = typeResolve;
	}

	Type resolveType(String name) {
		return typeResolve.apply(name);
	}

	Type resolveType(Type type, String name) {
		for (Field f : type.getFields()) {
			if (f.name.equals(name)) {
				return f.getType();
			}
		}
		throw new RuntimeException("Cannot find field");
	}

	EntityExpressionComplier compiler = new EntityExpressionComplier();

	class EntityExpressionAgent<T> implements EntityExpression {
		final Expr<T> expr;
		final Type type;

		EntityExpressionAgent(final Expr<T> expr, final Type type) {
			this.expr = expr;
			this.type = type;
		}

		@Override
		public T eval(Entity entity) {
			return null;
		}

		EntityExpression compile() {
			return compiler.compile(expr, type);
		}
	}

	public <T> EntityExpression compile(Expr<T> expr, Type type) {
		return compiler.compile(expr, type);
	}

	public Expr<Boolean> opAnd(Expr<Boolean> e1, Expr<Boolean> e2) {
		return new And(e1, e2);
	}

	public Expr<Boolean> opNot(Expr<Boolean> e1) {
		return new Not(e1);
	}

	public Expr<Boolean> opOr(Expr<Boolean> e1, Expr<Boolean> e2) {
		return new Or(e1, e2);
	}

	public <V extends Comparable<V>> Expr<Boolean> opNotEqualTo(Expr<V> e1, Expr<V> e2) {
		return new NotEqualTo<V>(e1, e2);
	}

	public <V extends Comparable<V>> Expr<Boolean> opEqualTo(Expr<V> e1, Expr<V> e2) {
		return new EqualTo<V>(e1, e2);
	}

	public <V extends Comparable<V>> Expr<Boolean> opGreaterThan(Expr<V> e1, Expr<V> e2) {
		return new GreaterThan<V>(e1, e2);
	}

	public <V extends Comparable<V>> Expr<Boolean> opGreaterThanOrEqualTo(Expr<V> e1, Expr<V> e2) {
		return new GreaterThanOrEqualTo<V>(e1, e2);
	}

	public <V extends Comparable<V>> Expr<Boolean> opLessThan(Expr<V> e1, Expr<V> e2) {
		return new LessThan<V>(e1, e2);
	}

	public <V extends Comparable<V>> Expr<Boolean> opLessThanOrEqualTo(Expr<V> e1, Expr<V> e2) {
		return new LessThanOrEqualTo<V>(e1, e2);
	}

	public Expr<Object> opAdd(Expr<Object> e1, Expr<Object> e2) {
		return new Add(e1, e2);
	}

	public Expr<Object> opSub(Expr<Object> e1, Expr<Object> e2) {
		return new Sub(e1, e2);
	}

	public Expr<Object> opMulti(Expr<Object> e1, Expr<Object> e2) {
		return new Multi(e1, e2);
	}

	public Expr<Object> opDiv(Expr<Object> e1, Expr<Object> e2) {
		return new Div(e1, e2);
	}

	public Expr<Object> opRemainder(Expr<Object> e1, Expr<Object> e2) {
		return new Remainder(e1, e2);
	}

	public Expr<Object> opIncrement(Expr<Object> e1) {
		return new Increment(e1);
	}

	public Expr<Object> opDecrement(Expr<Object> e1) {
		return new Decrement(e1);
	}

	public Expr<Object> opPositive(Expr<Object> e1) {
		return new Positive(e1);
	}

	public Expr<Object> opNegates(Expr<Object> e1) {
		return new Negates(e1);
	}

	public Expr<Integer> opIntegerCst(String value) {
		return new IntegerCst(value);
	}

	public Expr<BigDecimal> opDecimalCst(String value) {
		return new DecimalCst(value);
	}

	public Expr<String> opStringCst(String value) {
		return new StringCst(value);
	}

	public Expr<DateTime> opTimestampCst(String value) {
		return new TimestampCst(value);
	}

	public Expr<DateTime> opDatetimeCst(String value) {
		return new DatetimeCst(value);
	}

	public Expr<DateTime> opDateCst(String value) {
		return new DateCst(value);
	}

	public Expr<DateTime> opTimeCst(String value) {
		return new TimeCst(value);
	}

	public Expr<Object> opThis() {
		return new VarRefer("this");
	}

	public Expr<Object> opVar(String text) {
		return new VarRefer(text);
	}

	public Expr<Object> opFieldOf(Expr<Object> e1, String name) {
		return new FieldOf(e1, name);
	}

	public Statement opPut(Expr<Object> field, Expr<Object> value) {
		return new PutField((FieldOf) field, value);
	}

	public Expr<Object> opMethodCall(Expr<Entity> e1, String name) {
		return new MethodCall(e1, name);
	}

	abstract class Expression<T> implements Opcodes, Expr<T> {
		@Override
		public T exec() {
			throw new UnsupportedOperationException();
		}
	}

	abstract class LogicExpr extends Expression<Boolean> {
		@Override
		public Type getExprType() {
			return resolveType(RawTypes.Boolean.name());
		}
	}

	/**
	 * A logical "and" expression.
	 */
	class And extends LogicExpr {
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
	class Not extends LogicExpr {
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
	class Or extends LogicExpr {
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

	abstract class CompareExpr<V extends Comparable<V>> extends Expression<Boolean> {
		@Override
		public Type getExprType() {
			return resolveType(RawTypes.Boolean.name());
		}
	}

	class NotEqualTo<V extends Comparable<V>> extends CompareExpr<V> {
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

	class EqualTo<V extends Comparable<V>> extends CompareExpr<V> {
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
	class GreaterThan<V extends Comparable<V>> extends CompareExpr<V> {
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

	class GreaterThanOrEqualTo<V extends Comparable<V>> extends CompareExpr<V> {
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

	class LessThan<V extends Comparable<V>> extends CompareExpr<V> {
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

	class LessThanOrEqualTo<V extends Comparable<V>> extends CompareExpr<V> {
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

	abstract class ArithmeticExpr extends Expression<Object> {
		final Expr<Object> e1;
		final Expr<Object> e2;

		ArithmeticExpr(final Expr<Object> e1, final Expr<Object> e2) {
			this.e1 = e1;
			this.e2 = e2;
		}

		@Override
		public Type getExprType() {
			return e1.getExprType();
		}
	}

	/**
	 * An addition expression.
	 */
	class Add extends ArithmeticExpr {
		Add(final Expr<Object> e1, final Expr<Object> e2) {
			super(e1, e2);
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to add the two values
			e1.compile(mv);
			e2.compile(mv);
			mv.visitInsn(IADD);
		}

		@Override
		public Integer exec() {
			return (Integer) e1.exec() + (Integer) e2.exec();
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " + " + e2.toString() + ")";
		}
	}

	class Sub extends ArithmeticExpr {
		Sub(final Expr<Object> e1, final Expr<Object> e2) {
			super(e1, e2);
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to add the two values
			e1.compile(mv);
			e2.compile(mv);
			mv.visitInsn(ISUB);
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " - " + e2.toString() + ")";
		}
	}

	/**
	 * A multiplication expression.
	 */
	class Multi extends ArithmeticExpr {
		Multi(final Expr<Object> e1, final Expr<Object> e2) {
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
		public String toString() {
			return "(" + e1.toString() + " * " + e2.toString() + ")";
		}
	}

	class Div extends ArithmeticExpr {
		Div(final Expr<Object> e1, final Expr<Object> e2) {
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
		public String toString() {
			return "(" + e1.toString() + " / " + e2.toString() + ")";
		}
	}

	class Remainder extends ArithmeticExpr {
		Remainder(final Expr<Object> e1, final Expr<Object> e2) {
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
		public String toString() {
			return "(" + e1.toString() + " % " + e2.toString() + ")";
		}
	}

	abstract class UnaryArithmeticExpr extends Expression<Object> {
		final Expr<Object> e1;

		UnaryArithmeticExpr(final Expr<Object> e1) {
			this.e1 = e1;
		}

		@Override
		public Type getExprType() {
			return e1.getExprType();
		}
	}

	class Increment extends UnaryArithmeticExpr {
		Increment(Expr<Object> e1) {
			super(e1);
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			e1.compile(mv);
			mv.visitInsn(ICONST_1);
			mv.visitInsn(IADD);
		}

		@Override
		public String toString() {
			return "++" + e1.toString();
		}
	}

	class Decrement extends UnaryArithmeticExpr {
		Decrement(Expr<Object> e1) {
			super(e1);
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			e1.compile(mv);
			mv.visitInsn(ICONST_1);
			mv.visitInsn(ISUB);
		}

		@Override
		public String toString() {
			return "--" + e1.toString();
		}
	}

	class Positive extends UnaryArithmeticExpr {
		Positive(Expr<Object> e1) {
			super(e1);
		}

		public void compile(final MethodVisitor mv) {
			e1.compile(mv);
		}

		@Override
		public String toString() {
			return "+" + e1.toString();
		}
	}

	class Negates extends UnaryArithmeticExpr {
		Negates(Expr<Object> e1) {
			super(e1);
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			e1.compile(mv);
			mv.visitInsn(INEG);
		}

		@Override
		public String toString() {
			return "+" + e1.toString();
		}
	}

	class StringCst extends Expression<String> {
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

		@Override
		public Type getExprType() {
			return resolveType(RawTypes.String.name());
		}
	}

	class DecimalCst extends Expression<BigDecimal> {
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

		@Override
		public Type getExprType() {
			return resolveType(RawTypes.Decimal.name());
		}
	}

	class IntegerCst extends Expression<Integer> {
		final Integer value;

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

		@Override
		public Type getExprType() {
			return resolveType(RawTypes.Long.name());
		}
	}

	class TimestampCst extends Expression<DateTime> {
		final DateTimeFormatter formater = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
		final DateTime value;

		TimestampCst(String text) {
			this.value = formater.parseDateTime(text);
		}

		public void compile(final MethodVisitor mv) {
			mv.visitLdcInsn(value);// TODO
		}

		@Override
		public DateTime exec() {
			return this.value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		@Override
		public Type getExprType() {
			return resolveType(RawTypes.Timestamp.name());
		}
	}

	class DatetimeCst extends Expression<DateTime> {
		final DateTimeFormatter formater = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		final DateTime value;

		DatetimeCst(String text) {
			this.value = formater.parseDateTime(text);
		}

		public void compile(final MethodVisitor mv) {
			mv.visitLdcInsn(value);// TODO
		}

		@Override
		public DateTime exec() {
			return this.value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		@Override
		public Type getExprType() {
			return resolveType(RawTypes.Datetime.name());
		}
	}

	class DateCst extends Expression<DateTime> {
		final DateTimeFormatter formater = DateTimeFormat.forPattern("yyyy-MM-dd");
		final DateTime value;

		DateCst(String text) {
			this.value = formater.parseDateTime(text);
		}

		public void compile(final MethodVisitor mv) {
			mv.visitLdcInsn(value);// TODO
		}

		@Override
		public DateTime exec() {
			return this.value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		@Override
		public Type getExprType() {
			return resolveType(RawTypes.Date.name());
		}
	}

	class TimeCst extends Expression<DateTime> {
		final DateTimeFormatter formater = DateTimeFormat.forPattern("HH:mm:ss");
		final DateTime value;

		TimeCst(String text) {
			this.value = formater.parseDateTime(text);
		}

		public void compile(final MethodVisitor mv) {
			mv.visitLdcInsn(value);// TODO
		}

		@Override
		public DateTime exec() {
			return this.value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		@Override
		public Type getExprType() {
			return resolveType(RawTypes.Time.name());
		}
	}

	class FieldOf extends Expression<Object> {
		final Expr<Object> e1;
		final String name;

		FieldOf(Expr<Object> e1, String name) {
			this.e1 = e1;
			this.name = name;
		}

		public void compile(final MethodVisitor mv) {
			switch (this.getExprType().rawType) {
			case Long:
				compileInteger(mv);
				break;
			case String:
				compileString(mv);
				break;
			default:
				break;
			}
		}
		
		public void compileInteger(final MethodVisitor mv) {
			mv.visitVarInsn(ALOAD, 1);
			mv.visitLdcInsn(this.name);
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Entity", "get", "(Ljava/lang/String;)Ljava/lang/Object;");
			mv.visitTypeInsn(CHECKCAST, "java/lang/Integer");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue", "()I");
		}

		public void compileString(final MethodVisitor mv) {
			mv.visitVarInsn(ALOAD, 1);
			mv.visitLdcInsn(this.name);
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Entity", "get", "(Ljava/lang/String;)Ljava/lang/Object;");
			mv.visitTypeInsn(CHECKCAST, "java/lang/String");
		}
		

		@Override
		public Object exec() {
			return ((Entity) e1.exec()).get(name);
		}

		@Override
		public String toString() {
			return e1.toString() + "." + name.toString();
		}

		@Override
		public Type getExprType() {
			return resolveType(e1.getExprType(), name);
		}
	}

	class MethodCall extends Expression<Object> {
		final Expr<Entity> e1;
		final String name;

		MethodCall(Expr<Entity> e1, String name) {
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

		@Override
		public Type getExprType() {
			return resolveType(e1.getExprType(), name);
		}
	}

	class VarRefer extends Expression<Object> {
		final String name;

		VarRefer(final String name) {
			this.name = name;
		}

		@Override
		public void compile(MethodVisitor mv) {
		}

		@Override
		public Type getExprType() {
			return resolveType(name);
		}

		@Override
		public String toString() {
			return name;
		}

	}

	class PutField implements Statement {
		final Expr<Object> parent;
		final Expr<Object> value;
		final String name;

		PutField(FieldOf field, Expr<Object> value) {
			this.parent = field.e1;
			this.name = field.name;
			this.value = value;
		}

		public void compile(final MethodVisitor mv) {
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			// mv.visitLdcInsn(value);
			// TODO
		}

		@Override
		public void exec() {
			// return (Integer) e1.exec().get(name);
		}

		@Override
		public String toString() {
			return parent.toString() + "." + name.toString() + " = " + value.toString() + ";\n";
		}
	}
}
